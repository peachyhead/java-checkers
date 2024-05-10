package src.features.board.tile;

import lombok.Getter;
import src.base.Position;
import src.base.app.storage.IStorageItem;
import src.base.interfaces.IPositionProvider;
import src.features.board.piece.PieceModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class TileModel implements IStorageItem, IPositionProvider  {
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
