package features.checker;

import base.Position;
import features.board.piece.PieceType;
import features.board.tile.TileModel;
import features.match.Turn;

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
