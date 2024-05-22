package src;

import src.UI.MainFrame;
import src.base.log.LogType;
import src.base.log.Logger;
import src.base.app.storage.StorageKeeper;
import src.base.signal.SignalBus;
import src.base.signal.SignalListener;
import src.features.board.BoardInstaller;
import src.features.match.MatchInstaller;
import src.features.match.MatchSignals;
import src.features.match.PlayerResolver;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    
    public static void main(String[] args) {
        GameResolver.resolve(args);
        Logger.setAllowedLogs(LogType.Server, LogType.Signal);
        MatchInstaller.install();
        
        var mainFrame = new MainFrame();
        var storageKeeper = new StorageKeeper();
        var boardInstaller = new BoardInstaller();
        boardInstaller.install(mainFrame, storageKeeper);
        mainFrame.initialize();

        setupClient(mainFrame);
    }

    private static void setupClient(MainFrame mainFrame) {
        var connectionPanel = new JPanel();
        
        connectionPanel.setLayout(new GridBagLayout());
        var contentPane = mainFrame.getContentPane();
        var c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
        var addressField = new JTextField();
        var portField = new JTextField();
        var connectionButton = new JButton("Connect");
        
        contentPane.add(connectionPanel);
        connectionPanel.setBackground(Color.GRAY);
        connectionPanel.add(addressField, c);
        c.gridy += 1;
        connectionPanel.add(portField, c);
        c.gridy += 1;
        connectionPanel.add(connectionButton, c);
        connectionPanel.revalidate();
        
        var client = getClient(connectionButton);

        connectionButton.addActionListener(e -> {
            InetAddress address;
            try {
                address = InetAddress.getByName(addressField.getText());
                client.launch(address, Integer.parseInt(portField.getText()));
            } catch (UnknownHostException ex) {
                throw new RuntimeException(ex);
            }
        });

        SignalBus.subscribe(new SignalListener<String>(MatchSignals.fromServer(MatchSignals.playerAdd), signal -> {
            mainFrame.createPanels();
            contentPane.remove(connectionPanel);
            PlayerResolver.resolve(signal);
        }));
    }

    private static Client getClient(JButton connectionButton) {
        var client = new Client();

        client.subscribeOnConnection(evt -> {
            var status = (int) evt.getNewValue();
            switch (status) {
                case 404: {
                    connectionButton.setText("❌ No such host");
                    connectionButton.setEnabled(true);
                    break;
                }
                case 200: {
                    connectionButton.setEnabled(false);
                    connectionButton.setText("⏳ Matchmaking...");
                    client.subscribeToSignal("tile_choose");
                    client.subscribeToSignal("piece_choose");
                    break;
                }
            }
        });
        return client;
    }
}