package src.features.checker;

import src.base.Position;
import src.features.board.piece.PieceModel;
import src.features.board.tile.TileModel;
import src.features.board.piece.IMoveStrategy;
import src.features.match.Turn;

public abstract class BaseCheckerMoveStrategy implements IMoveStrategy {
    protected PieceModel piece;   

    @Override
    public void set(PieceModel piece) {
        this.piece = piece;
    }

    @Override
    public boolean canMove(TileModel to) {
        if (piece == null) return false;
        if (to.havePiece()) return false;
        var availablePosition = getAvailablePosition(to.getPosition());
        var toID = to.getPosition().toString();
        var availID = availablePosition.toString();
        var result = toID.equals(availID);
        return result;
    }

    protected abstract Position getAvailablePosition(Position desiredPosition);
    
    @Override
    public void move(Turn turn, TileModel from, TileModel to) {
        finishMove(turn, to);
        from.resetPiece();
        to.setPieces(piece);
    }
    
    protected abstract void finishMove(Turn turn, TileModel to);
}
