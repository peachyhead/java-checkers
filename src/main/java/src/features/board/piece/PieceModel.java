package src.features.board.piece;

import lombok.Getter;
import lombok.Setter;
import src.base.Position;
import src.base.app.storage.IStorageItem;
import src.base.interfaces.IPositionProvider;
import src.features.board.tile.TileModel;

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
    private final List<IMoveStrategy> moveStrategies = new ArrayList<>();
    
    private final Action onSelected = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    };
    
    public PieceModel(int id, PieceType pieceType, Position position) {
        this.id = String.valueOf(id);
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
