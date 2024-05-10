package src.features.match;

import src.base.Tuple;
import src.base.app.storage.StorageKeeper;
import src.features.board.piece.PieceModel;
import src.features.board.tile.TileModel;

public class MatchService {
    private PieceModel currentPiece;

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
    
    public boolean makeMove(Turn turn, String tileID) {
        var tuple = trySetupMove(tileID);
        if (!tuple.z) return false;
        var strategy = currentPiece.getMoveStrategy(tuple.y);
        if (strategy == null) return false;
        strategy.move(turn, tuple.x, tuple.y);
        return true;
    }
}
