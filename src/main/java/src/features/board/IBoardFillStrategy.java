package src.features.board;

import src.base.Size;
import src.features.board.tile.ITileStrategy;

public interface IBoardFillStrategy {
    public BoardModel fillTiles(Size sideLength);
    public void fillPieces(BoardModel boardModel);
    public ITileStrategy getTileStrategy();
}
