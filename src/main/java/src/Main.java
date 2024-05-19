package src;

import src.UI.MainFrame;
import src.base.log.LogType;
import src.base.log.Logger;
import src.base.app.storage.StorageKeeper;
import src.base.signal.SignalBus;
import src.features.board.BoardInstaller;
import src.features.match.MatchInstaller;

public class Main {
    
    public static void main(String[] args) {
        GameResolver.resolve(args);
        Logger.setAllowedLogs(LogType.Signal);
        MatchInstaller.install();
        
        var client = new Client();
        var mainFrame = new MainFrame();
        var storageKeeper = new StorageKeeper();
        var boardInstaller = new BoardInstaller();
        boardInstaller.install(mainFrame, storageKeeper);
        mainFrame.initialize();
        
        client.launch();
        client.subscribeToSignal("piece_choose");
        client.subscribeToSignal("tile_choose");
    }
}