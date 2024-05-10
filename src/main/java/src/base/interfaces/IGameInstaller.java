package src.base.interfaces;

import src.UI.BoardView;
import src.UI.GameInfoPanel;
import src.base.Size;
import src.features.board.BaseBoardFillStrategy;

public interface IGameInstaller {
    public void install();
    public BaseBoardFillStrategy getBoardFillStrategy();
    public Size getSideLength();
    public int getYGap();
    public GameInfoPanel getGameInfoPanel();
    public BoardView getBoardView();
}
