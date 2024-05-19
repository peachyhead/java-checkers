package src.features.narde;

import src.base.Position;
import src.base.Size;
import src.features.board.BoardModel;
import src.features.board.BaseBoardFillStrategy;
import src.features.board.piece.PieceModelArgs;
import src.features.board.piece.PieceModelFactory;
import src.features.board.piece.PieceType;
import src.features.board.tile.ITileStrategy;
import src.features.board.tile.TileModelFactory;

public class NardeBoardFillStrategy extends BaseBoardFillStrategy {
    
    @Override
    public BoardModel fillTiles(Size sideLength) {
        var boardModel = new BoardModel(sideLength.getX(), sideLength.getY());
        for (int x = boardModel.getWidth(); x > 0; x--) {
            createTile(boardModel, new Position(x, 2));
        }

        for (int x = 1; x < boardModel.getWidth() + 1; x++) {
            createTile(boardModel, new Position(x, 1));
        }
        return boardModel;
    }

    @Override
    public void fillPieces(BoardModel boardModel) {
        var headTileBlack = boardModel.getTiles().stream()
                .filter(tileModel -> tileModel.getTileStrategy().isInitialTile())
                .filter(tile -> tile.getPosition().getX() == 1 && 
                        tile.getPosition().getY() == 1)
                .findFirst().get();
        
        for (int i = 1; i < 16; i++) {
//            var args = new PieceModelArgs(PieceType.Black, headTileBlack.getPosition());
//            var piece = pieceModelFactory.create(args);
//            headTileBlack.setPieces(piece);
        }

        var headTileWhite = boardModel.getTiles().stream()
                .filter(tileModel -> tileModel.getTileStrategy().isInitialTile())
                .filter(tile -> tile.getPosition().getX() == 1 &&
                        tile.getPosition().getY() == 2)
                .findFirst().get();

        for (int i = 1; i < 16; i++) {
//            var args = new PieceModelArgs(PieceType.White, headTileBlack.getPosition());
//            var piece = pieceModelFactory.create(args);
//            headTileWhite.setPieces(piece);
        }
    }

    @Override
    public ITileStrategy getTileStrategy() {
        return new NardeTileStrategy();
    }
}
