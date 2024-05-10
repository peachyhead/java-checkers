package src.features.checker;

import src.UI.BoardView;
import src.UI.GameInfoPanel;
import src.base.Size;
import src.base.interfaces.IGameInstaller;
import src.features.board.BoardInstaller;
import src.features.board.BaseBoardFillStrategy;

import java.awt.*;

public class CheckerInstaller implements IGameInstaller {
    private static BaseBoardFillStrategy boardFillStrategy;
    
    private BoardView boardView;
    private GameInfoPanel infoPanel;
    
    @Override
    public void install(){
        infoPanel = new CheckerInfoPanel();
        infoPanel.setBackground(Color.GRAY);
        boardView = new CheckerBoardView(getSideLength());
        
        var pieceModelFactory = BoardInstaller.getPieceModelFactory();
        var tileModelFactory = BoardInstaller.getTileModelFactory();
        boardFillStrategy = new CheckerBoardFillStrategy(pieceModelFactory, tileModelFactory);
    }

    @Override
    public BaseBoardFillStrategy getBoardFillStrategy() {
        return boardFillStrategy;
    }

    @Override
    public Size getSideLength() {
        return new Size(8, 8);
    }

    @Override
    public int getYGap() {
        return 0;
    }

    @Override
    public GameInfoPanel getGameInfoPanel() {
        return infoPanel;
    }

    @Override
    public BoardView getBoardView() {
        return boardView;
    }
}
