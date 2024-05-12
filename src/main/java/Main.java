import base.GameResolver;
import base.app.storage.StorageKeeper;
import base.log.LogType;
import base.log.Logger;
import base.signal.SignalBus;
import features.board.BoardInstaller;
import features.match.MatchInstaller;
import UI.MainFrame;

public class Main {

    public static void main(String[] args) {
        Logger.setAllowedLogs(LogType.Signal);
        GameResolver.resolve(args);
        var storageKeeper = new StorageKeeper();
        
        MainFrame.initialize(args);
        BoardInstaller.install(storageKeeper);
        MatchInstaller.install();

        //var client = new Client();
        //client.setSignalSubscribe("tile_choose");
        //client.setSignalSubscribe("piece_choose");

        SignalBus.fire("s-player_add","1");
        SignalBus.fire("s-player_add","2");
    }
}
