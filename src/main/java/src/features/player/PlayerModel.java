package src.features.player;

import lombok.Getter;
import src.features.board.piece.PieceType;

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
