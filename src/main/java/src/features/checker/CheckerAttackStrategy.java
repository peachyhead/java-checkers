package src.features.checker;

import src.base.Position;
import src.base.app.storage.StorageKeeper;
import src.features.board.piece.PieceModel;
import src.features.board.tile.TileModel;
import src.features.match.Turn;

public class CheckerAttackStrategy extends BaseCheckerMoveStrategy {
    
    @Override
    protected Position getAvailablePosition(Position desiredPosition) {
        var yDirection = piece.getPosition().getY() +
                (desiredPosition.getY() > piece.getPosition().getY() ? 2 : -2);
        var xDirection = piece.getPosition().getX() +
                (desiredPosition.getX() > piece.getPosition().getX() ? 2 : -2);
        return new Position(xDirection, yDirection);
    }
    
    @Override
    protected void finishMove(Turn turn, TileModel to) {
        var attackPosition = getAttackedPosition(piece.getPosition(), to.getPosition());
        if (!tryRemoveAttacked(attackPosition)) return;
        turn.setSucceed(true);
        turn.playerModel().addAttack();
    }
    
    private Position getAttackedPosition(Position from, Position to){
        var yDirection = from.getY() +
                (to.getY() > from.getY() ? 1 : -1);
        var xDirection = from.getX() +
                (to.getX() > from.getX() ? 1 : -1);
        return new Position(xDirection, yDirection);
    }

    private boolean tryRemoveAttacked(Position attackedPosition) {
        var tileStorage = StorageKeeper.getStorage(TileModel.class);
        var attackedTile = tileStorage.find(attackedPosition.toString());
        if (attackedTile == null) return false;
        var attackedPiece = attackedTile.getPiece();
        if (attackedPiece == null) return false;
        attackedTile.resetPiece();
        var pieceStorage = StorageKeeper.getStorage(PieceModel.class);
        return pieceStorage.remove(attackedPiece);
    }
}
