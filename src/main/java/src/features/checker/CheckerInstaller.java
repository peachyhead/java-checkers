package src.features.checker;

import src.base.interfaces.IGameInstaller;
import src.features.board.BoardInstaller;
import src.features.board.IBoardFillStrategy;

public class CheckerInstaller implements IGameInstaller {
    private static IBoardFillStrategy boardFillStrategy;
    
    public void install(){
        var pieceModelFactory = BoardInstaller.getPieceModelFactory();
        var tileModelFactory = BoardInstaller.getTileModelFactory();
         boardFillStrategy = new CheckerBoardFillStrategy(pieceModelFactory, tileModelFactory);
    }

    @Override
    public IBoardFillStrategy getBoardFillStrategy() {
        return boardFillStrategy;
    }
}
