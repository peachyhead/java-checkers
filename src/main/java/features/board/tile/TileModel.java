package features.board.tile;

import base.Position;
import base.app.storage.IStorageItem;
import base.interfaces.IPositionProvider;
import features.board.piece.PieceModel;
import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class TileModel implements IStorageItem, IPositionProvider {
    private List<PieceModel> pieces = new ArrayList<>();
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
        this.tileStrategy = tileStrategy;
        this.position = position;
    }

    public void setPieces(PieceModel piece){
        if (pieces == null) return;
        pieces.add(piece);
        piece.setPosition(position);
        onPieceChange.putValue("piece_change", piece);
    }
    
    public PieceModel getPiece(){
        return pieces.stream().findFirst().get();
    }

    public void subscribe(PropertyChangeListener propertyChangeListener){
        onPieceChange.addPropertyChangeListener(propertyChangeListener);
    }
    
    public void resetPiece(){
        pieces.clear();
        onPieceChange.putValue("piece_change", null);
    }
    
    public boolean havePiece(){
        return !pieces.isEmpty();
    }
    
    @Override
    public String getID() {
        return position.toString();
    }

    @Override
    public void initialize() 
    { }
}
