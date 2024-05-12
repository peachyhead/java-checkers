package features.board.piece;

import base.Position;
import base.app.storage.IStorageItem;
import base.interfaces.IPositionProvider;
import features.board.tile.TileModel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class PieceModel implements IStorageItem, IPositionProvider {
    private final String id;
    @Getter @Setter
    private Position position;
    @Getter
    private final PieceType pieceType;
    private List<IMoveStrategy> moveStrategies = new ArrayList<>();
    
    private final Action onSelected = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    };
    
    public PieceModel(PieceType pieceType, Position position) {
        id = java.util.UUID.randomUUID().toString();
        this.position = position;
        this.pieceType = pieceType;
    }
    
    public void addMoveStrategy(IMoveStrategy moveStrategy) {
        moveStrategies.add(moveStrategy);
    }
    
    public IMoveStrategy getMoveStrategy(TileModel to){
        return moveStrategies.stream()
                .filter(strategy -> strategy.canMove(to))
                .findFirst().orElse(null);
    }
    
    public void select(boolean value){
        onSelected.putValue("selected", value);
    }
    
    public void onSelected(PropertyChangeListener listener){
        onSelected.addPropertyChangeListener(listener);
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public void initialize() 
    { }
}
