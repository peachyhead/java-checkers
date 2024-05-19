package src;

import src.base.signal.SignalBus;
import src.base.signal.SignalListener;

import java.io.*;
import java.net.Socket;
import java.text.MessageFormat;

public class Client {
    private final static int port = 2345;
    private final static String address = "127.0.0.1";
    private Socket socket;
    private ServerHandler handler;

    public void launch() {
        try {
            System.out.println("Connecting to " + address + ":" + port + "...");
            socket = new Socket(address, port);
            
            handler = new ServerHandler(socket);
            new Thread(handler).start();
        } catch (IOException e) {
            System.out.println("Disconnected from " + address + ":" + port + ".");
            try {
                socket.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
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
        
        public ServerHandler(Socket socket) throws IOException {
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
            System.out.println("Got response from server: " + response);

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
                System.out.println("Pushed signal to server: " + signal);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
