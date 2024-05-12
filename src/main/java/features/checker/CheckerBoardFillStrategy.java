package features.checker;

import base.Position;
import base.Size;
import features.board.BaseBoardFillStrategy;
import features.board.BoardModel;
import features.board.piece.PieceModelArgs;
import features.board.piece.PieceType;
import features.board.tile.ITileStrategy;

public class CheckerBoardFillStrategy extends BaseBoardFillStrategy {

    @Override
    public BoardModel fillTiles(Size sideLength){
        var boardModel = new BoardModel(sideLength.getX(), sideLength.getY());
        for (int x = boardModel.getWidth(); x > 0; x--) {
            for (int y = 1; y < boardModel.getHeight() + 1; y++) {
                createTile(boardModel, new Position(y, x));
            }
        }
        return boardModel;
    }

    @Override
    public void fillPieces(BoardModel boardModel) {
        boardModel
                .getTiles().stream()
                .filter(tileModel -> tileModel.getTileStrategy().isInitialTile())
                .filter(tile -> tile.getPosition().getY() < boardModel.getHeight() / 2)
                .forEach(tile -> {
                    var args = new PieceModelArgs(PieceType.Black, tile.getPosition());
                    var piece = pieceModelFactory.create(args);
                    tile.setPieces(piece);
                });

        boardModel
                .getTiles().stream()
                .filter(tileModel -> tileModel.getTileStrategy().isInitialTile())
                .filter(tile -> tile.getPosition().getY() > boardModel.getHeight() / 2 + 1)
                .forEach(tile -> {
                    var args = new PieceModelArgs(PieceType.White, tile.getPosition());
                    var piece = pieceModelFactory.create(args);
                    tile.setPieces(piece);
                });
    }

    @Override
    public ITileStrategy getTileStrategy() {
        return new CheckerTileStrategy();
    }
}
