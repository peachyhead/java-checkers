package src.features.narde;

import src.UI.BoardView;
import src.UI.GameInfoPanel;
import src.base.Size;
import src.base.interfaces.IGameInstaller;
import src.features.board.BaseBoardFillStrategy;
import src.features.board.BoardInstaller;

import java.awt.*;

public class NardeInstaller implements IGameInstaller {
    private static BaseBoardFillStrategy boardFillStrategy;
    
    private BoardView boardView;
    private GameInfoPanel infoPanel;
    
    @Override
    public void install() {
        infoPanel = new NardeInfoPanel();
        infoPanel.setBackground(Color.GRAY);
        boardView = new NardeBoardView(getSideLength(), getYGap());
        
        var pieceModelFactory = BoardInstaller.getPieceModelFactory();
        var tileModelFactory = BoardInstaller.getTileModelFactory();
        boardFillStrategy = new NardeBoardFillStrategy(pieceModelFactory, tileModelFactory);
    }

    @Override
    public BaseBoardFillStrategy getBoardFillStrategy() {
        return boardFillStrategy;
    }
    
    @Override
    public Size getSideLength() {
        return new Size(12, 2);
    }

    @Override
    public int getYGap() {
        return 600;
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
