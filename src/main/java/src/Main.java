package src;

import src.UI.MainFrame;
import src.base.log.LogType;
import src.base.log.Logger;
import src.base.app.storage.StorageKeeper;
import src.base.interfaces.IGameInstaller;
import src.base.signal.SignalBus;
import src.features.board.BoardInstaller;
import src.features.checker.CheckerInstaller;
import src.features.match.MatchInstaller;
import src.features.narde.NardeInstaller;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    
    public static void main(String[] args) throws IOException {
        //var client = new Client();
        Logger.setAllowedLogs(LogType.Signal);
        
        var gameInstaller = resolveGameInstaller(args);
        var mainFrame = new MainFrame(gameInstaller);
        var storageKeeper = new StorageKeeper();
        
        BoardInstaller.install(mainFrame, storageKeeper);
        gameInstaller.install();
        mainFrame.initialize();
        MatchInstaller.install(gameInstaller);
        //client.setSignalSubscribe("tile_choose");
        //client.setSignalSubscribe("piece_choose");

        SignalBus.fire("s-player_add","1");
        SignalBus.fire("s-player_add","2");
    }
    
    private static IGameInstaller resolveGameInstaller(String[] args){
        return switch (Arrays.stream(args).findFirst().orElse("")){
            case "checkers" -> new CheckerInstaller();
            case "narde" -> new NardeInstaller();
            default -> throw new IllegalStateException("Can't resolve game: " + 
                    Arrays.stream(args).findFirst().get());
        };
    }
}