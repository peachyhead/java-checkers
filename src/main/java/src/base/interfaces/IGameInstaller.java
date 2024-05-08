package src.base.interfaces;

import src.features.board.IBoardFillStrategy;

public interface IGameInstaller {
    public void install();
    public IBoardFillStrategy getBoardFillStrategy();
}
