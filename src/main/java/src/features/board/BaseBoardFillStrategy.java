package src.features.board;

import src.base.Position;
import src.base.Size;
import src.features.board.piece.PieceModelFactory;
import src.features.board.tile.ITileStrategy;
import src.features.board.tile.TileModelArgs;
import src.features.board.tile.TileModelFactory;

public abstract class BaseBoardFillStrategy {
    protected TileModelFactory tileModelFactory;
    protected PieceModelFactory pieceModelFactory;

    public void setFactory(PieceModelFactory pieceModelFactory,
                                    TileModelFactory tileModelFactory) {
        this.pieceModelFactory = pieceModelFactory;
        this.tileModelFactory = tileModelFactory;
    }
    
    public abstract BoardModel fillTiles(Size sideLength);
    public abstract void fillPieces(BoardModel boardModel);
    
    protected void createTile(BoardModel boardModel, Position position){
        var strategy = getTileStrategy();
        var tile = tileModelFactory.create(new TileModelArgs(strategy, position));
        boardModel.addTile(tile);
    }
    
    protected abstract ITileStrategy getTileStrategy();
}
