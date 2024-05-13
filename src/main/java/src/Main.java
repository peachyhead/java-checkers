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
        
        var mainFrame = new MainFrame();
        var storageKeeper = new StorageKeeper();
        var boardInstaller = new BoardInstaller();
        boardInstaller.install(mainFrame, storageKeeper);
        mainFrame.initialize();

        SignalBus.fire("s-player_add","1");
        SignalBus.fire("s-player_add","2");
    }
}