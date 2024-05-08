package src;

import src.UI.MainFrame;
import src.base.Size;
import src.base.log.LogType;
import src.base.log.Logger;
import src.base.app.storage.StorageKeeper;
import src.base.interfaces.IGameInstaller;
import src.features.board.BoardInstaller;
import src.features.checker.CheckerInstaller;
import src.features.match.MatchInstaller;

import java.util.Arrays;

public class Main {
    
    private static final Size sideLength = new Size(8, 8);
    
    public static void main(String[] args) {
        Logger.setAllowedLogs(LogType.Storage);
        
        var mainFrame = new MainFrame(sideLength);
        var storageKeeper = new StorageKeeper();
        var gameInstaller = resolveGameInstaller(args);
        mainFrame.initialize();
        
        BoardInstaller.install(mainFrame, storageKeeper);
        gameInstaller.install();
        MatchInstaller.install(sideLength, mainFrame.getInfoPanel(), 
                gameInstaller.getBoardFillStrategy());
    }
    
    private static IGameInstaller resolveGameInstaller(String[] args){
        return switch (Arrays.stream(args).findFirst().get()){
            case "checker" -> new CheckerInstaller();
            default -> throw new IllegalStateException("Unexpected value: " + 
                    Arrays.stream(args).findFirst().get());
        };
    }
}