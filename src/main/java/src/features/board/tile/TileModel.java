package src.features.board.tile;

import lombok.Getter;
import src.base.Position;
import src.base.app.storage.IStorageItem;
import src.base.interfaces.IPositionProvider;
import src.features.board.piece.PieceModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class TileModel implements IStorageItem, IPositionProvider  {
    @Getter
    private PieceModel piece;
    @Getter
    private final Position position;
    @Getter
    private final ITileStrategy tileStrategy;
    
    private final Action onPieceChange = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    };

    public TileModel(ITileStrategy tileStrategy, Position position){
        piece = null;
        this.tileStrategy = tileStrategy;
        this.position = position;
    }

    public void setPiece(PieceModel piece){
        if (havePiece() || piece == null) return;
        this.piece = piece;
        piece.setPosition(position);
        onPieceChange.putValue("piece_change", piece);
    }

    public void subscribe(PropertyChangeListener propertyChangeListener){
        onPieceChange.addPropertyChangeListener(propertyChangeListener);
    }
    
    public void resetPiece(){
        piece = null;
        onPieceChange.putValue("piece_change", piece);
    }
    
    public boolean havePiece(){
        return piece != null;
    }
    
    @Override
    public String getID() {
        return position.toString();
    }

    @Override
    public void initialize() 
    { }
}
