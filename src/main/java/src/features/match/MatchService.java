package src.features.match;

import src.base.Tuple;
import src.base.app.storage.StorageKeeper;
import src.base.signal.SignalBus;
import src.features.board.piece.PieceModel;
import src.features.board.piece.PieceType;
import src.features.board.tile.TileModel;
import src.features.checker.CheckerMoveStrategy;

public class MatchService {
    private static PieceModel currentPiece;

    public void selectPiece(Turn turn, String pieceID) {
        var storage = StorageKeeper.getStorage(PieceModel.class);
        var piece = storage.find(pieceID);
        if (piece == null || piece.getPieceType() != 
                turn.playerModel().getPieceType())
            return;
        
        if (currentPiece != null)
            currentPiece.select(false);
        currentPiece = piece;
        currentPiece.select(true);
    }
    
    private Tuple<TileModel, TileModel, Boolean> trySetupMove(String tileID){
        if (currentPiece == null)
            return new Tuple<>(null, null, false);
        var storage = StorageKeeper.getStorage(TileModel.class);
        var currentTile = storage.find(currentPiece.getPosition().toString());
        var targetTile = storage.find(tileID);
        var haveTarget = targetTile != null;
        return new Tuple<>(currentTile, targetTile, haveTarget);
    }
    
    public void onNewTurn() {
        currentPiece.select(false);
        currentPiece = null;
    }
    
    public void tryMove(Turn turn, String tileID) {
        var tuple = trySetupMove(tileID);
        if (!tuple.z) return;
        var strategy = currentPiece.getMoveStrategy(tuple.y);
        if (strategy == null) return;
        
        strategy.move(turn, tuple.x, tuple.y);
        SignalBus.fire("piece_move", "o");
    }
  
    public static boolean canGrabPiece(PieceType pieceType) {
        return pieceType == PlayerResolver.getLocalPlayer().getPieceType();
    }
    
    public static boolean canMoveCurrentPiece() {
        if (currentPiece == null) return false;
        return currentPiece.getPieceType() == PlayerResolver.getLocalPlayer().getPieceType();
    }
    
    public boolean checkIfCanMove(Turn turn) {
        if (turn.isShouldEnd()) return false;
        var storage = StorageKeeper.getStorage(TileModel.class);
        for (TileModel tile : storage.getCollection()) {
            var strategy = currentPiece.getMoveStrategy(tile);
            if (strategy == null) continue;
            if (turn.isSucceed() && strategy instanceof CheckerMoveStrategy)
                continue;
            
            return true;
        }
        return false;
    }
}
