package src.features.checker;

import src.base.Position;
import src.base.Size;
import src.features.board.IBoardFillStrategy;
import src.features.board.piece.PieceModelArgs;
import src.features.board.piece.PieceModelFactory;
import src.features.board.tile.ITileStrategy;
import src.features.board.BoardModel;
import src.features.board.piece.PieceType;
import src.features.board.tile.TileModelArgs;
import src.features.board.tile.TileModelFactory;

public class CheckerBoardFillStrategy implements IBoardFillStrategy {
    private final TileModelFactory tileModelFactory;
    private final PieceModelFactory pieceModelFactory;

    public CheckerBoardFillStrategy(PieceModelFactory pieceModelFactory,
                                    TileModelFactory tileModelFactory) {
        this.pieceModelFactory = pieceModelFactory;
        this.tileModelFactory = tileModelFactory;
    }

    public BoardModel fillTiles(Size sideLength){
        var boardModel = new BoardModel(sideLength.getX(), sideLength.getY());
        for (int x = boardModel.getWidth(); x > 0; x--) {
            for (int y = 1; y < boardModel.getHeight() + 1; y++) {
                var position = new Position(y, x);
                var strategy = getTileStrategy();
                var tile = tileModelFactory.create(new TileModelArgs(strategy, position));
                boardModel.addTile(tile);
            }
        }
        return boardModel;
    }
    
    public void fillPieces(BoardModel boardModel){
        boardModel
                .getTiles().stream()
                .filter(tileModel -> tileModel.getTileStrategy().isInitialTile())
                .filter(tile -> tile.getPosition().getY() < boardModel.getHeight() / 2)
                .forEach(tile -> {
                    var args = new PieceModelArgs(PieceType.Black, tile.getPosition());
                    var piece = pieceModelFactory.create(args);
                    tile.setPiece(piece);
                });

        boardModel
                .getTiles().stream()
                .filter(tileModel -> tileModel.getTileStrategy().isInitialTile())
                .filter(tile -> tile.getPosition().getY() > boardModel.getHeight() / 2 + 1)
                .forEach(tile -> {
                    var args = new PieceModelArgs(PieceType.White, tile.getPosition());
                    var piece = pieceModelFactory.create(args);
                    tile.setPiece(piece);
                });
    }

    @Override
    public ITileStrategy getTileStrategy() {
        return new CheckerTileStrategy();
    }
}
