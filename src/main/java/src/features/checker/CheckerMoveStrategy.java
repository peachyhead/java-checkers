package src.features.checker;

import src.base.Position;
import src.features.board.piece.PieceType;
import src.features.board.tile.TileModel;
import src.features.match.Turn;

public class CheckerMoveStrategy extends BaseCheckerMoveStrategy {
    
    @Override
    protected Position getAvailablePosition(Position desiredPosition) {
        var yDirection = piece.getPosition().getY() +
                (piece.getPieceType() == PieceType.Black ? 1 : -1);
        var xDirection = piece.getPosition().getX() +
                (desiredPosition.getX() > piece.getPosition().getX() ? 1 : -1);
        return new Position(xDirection, yDirection);
    }

    @Override
    protected void finishMove(Turn turn, TileModel to) { }
}
