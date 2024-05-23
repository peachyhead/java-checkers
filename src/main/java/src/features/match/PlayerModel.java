package src.features.match;

import lombok.Getter;
import src.features.board.piece.PieceType;

public class PlayerModel {
    @Getter private final PieceType pieceType;
    @Getter private int attackCounter; 
    @Getter private final boolean localPlayer;

    public PlayerModel(PieceType pieceType, boolean isLocalPlayer){
        this.pieceType = pieceType;
        localPlayer = isLocalPlayer;
    }
    
    public void addAttack(){
        attackCounter += 1;
    }
}
