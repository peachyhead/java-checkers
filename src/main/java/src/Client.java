package src;

import src.base.signal.SignalBus;
import src.base.signal.SignalListener;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.text.MessageFormat;
import java.util.Arrays;

public class Client {
    static int port = 2345; // Порт, такой же, как у сервера
    static String address = "127.0.0.1"; // Адрес сервера
    private DataOutputStream out;
    private DataInputStream in;

    public void launch() {
        try {
            InetAddress addr = InetAddress.getByName(address);
            
            System.out.println("Поключаемся к " + address + ":" + port + "...");
            Socket socket = new Socket(addr, port);

            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            
            SignalBus.fire("s-add_player", "p");
            
            while (socket.isConnected()) {
                setSignalPush("tile_choose");
                setSignalPush("piece_choose");
                setSignalPush("add_player");
            }
        } catch (IOException x) {
            System.out.println("Ошибка ввода/вывода");
            x.printStackTrace();
        }
    }
    
    public void setSignalPush(String signal) throws IOException {
        var input = in.readUTF();
        var data = Arrays.stream(input.split(";")).toList();
        var key = data.stream().findFirst().get();
        var body = data.stream().skip(1).findFirst().get();
        if (key.contains(signal))
            SignalBus.fire(key, body);
    }
    
    public void setSignalSubscribe(String signal) {
        SignalBus.subscribe(new SignalListener<>(signal, data -> {
            var msg = MessageFormat.format("{0};{1}", signal, data);
            try {
                out.writeUTF(msg);
                out.flush(); // send the message
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
    }
}