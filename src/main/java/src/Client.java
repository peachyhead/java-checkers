package src;

import src.base.log.LogType;
import src.base.log.Logger;
import src.base.signal.SignalBus;
import src.base.signal.SignalListener;
import src.features.match.MatchSignals;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.text.MessageFormat;

public class Client {
    
    private Socket socket;
    private ServerHandler handler;
    
    private final Action connectionListener = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    };

    public void launch(InetAddress address, int port) {
        try {
            var msg = MessageFormat.format("Connecting to {0} : {1} ...", address.toString(), String.valueOf(port));
            Logger.log(msg, LogType.Server);
            socket = new Socket(address, port);
            
            handler = new ServerHandler(socket);
            new Thread(handler).start();
            connectionListener.putValue("status", 200);
            msg = MessageFormat.format("Connected to {0} : {1}!", address.toString(), String.valueOf(port));
            Logger.log(msg, LogType.Server);
        }
        catch (ConnectException connectException) {
            connectionListener.putValue("status", 404);
            var msg = MessageFormat.format("Cant resolve host - {0} : {1}.", address.toString(), String.valueOf(port));
            Logger.log(msg, LogType.Server);
        }
        catch (IOException e) {
            var msg = MessageFormat.format("Disconnected from {0} : {1}.", address.toString(), String.valueOf(port));
            Logger.log(msg, LogType.Server);
            try {
                if (socket == null) return;
                socket.close();
                connectionListener.putValue("status", 499);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    
    public void subscribeOnConnection(PropertyChangeListener listener) {
        connectionListener.addPropertyChangeListener(listener);
    }
    
    public void subscribeToSignal(String signal) {
        new Thread(() -> { 
            SignalBus.subscribe(new SignalListener<>(signal, receive ->
                handler.sendSignal(MessageFormat.format("{0};{1}", signal, receive))));
        }).start();
    }

    private class ServerHandler implements Runnable {

        private final DataOutputStream out;
        private final DataInputStream in;
        private final Socket socket;

        public ServerHandler(Socket socket) throws IOException {
            this.socket = socket;
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        }

        @Override
        public void run() {
            while (true) {
                try {
                    handleRead();
                } 
                catch (IOException e) {
                    try {
                        socket.close();
                        in.close();
                        out.close();
                        break;
                    } 
                    catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }

        private void handleRead() throws IOException {
            String response = in.readUTF();
            var msg = MessageFormat.format("Got response from server: {0}", response);
            Logger.log(msg, LogType.Server);

            tryPushSignal(response, "s-tile_choose");
            tryPushSignal(response, "s-piece_choose");
            tryPushSignal(response, "s-player_add");
        }

        private void tryPushSignal(String response, String signal) {
            if (!response.contains(signal)) return;
            var split = response.split(";");
            SignalBus.fire(split[0], split[1]);
        }

        public void sendSignal(String signal) {
            try {
                out.writeUTF(signal);
                out.flush();
                var msg = MessageFormat.format("Pushed data to server: {0}", signal);
                Logger.log(msg, LogType.Server);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
