package features.player;

import features.board.piece.PieceType;
import lombok.Getter;

public class PlayerModel {
    @Getter private final PieceType pieceType;
    @Getter private int attackCounter; 

    public PlayerModel(PieceType pieceType){
        this.pieceType = pieceType;
    }
    
    public void addAttack(){
        attackCounter += 1;
    }
}
