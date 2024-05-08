package src.features.board.tile;

import src.base.signal.SignalBus;
import src.base.Size;
import src.base.app.view.View;
import src.base.app.storage.StorageKeeper;
import src.features.board.piece.PieceModel;
import src.features.board.piece.PieceView;

import java.awt.*;
import java.awt.event.*;

public class TileView extends View {
    private final TileModel model;

    public TileView(TileModel model, Size size) {
        super(model, size);
        this.model = model;
        //var text = new JTextField();
        //text.setText(model.getID());
        //this.add(text);
    }

    @Override
    public void initialize(){
        model.subscribe(evt -> {
            var piece = (PieceModel) evt.getNewValue();
            if (piece == null) return;
            var storage = StorageKeeper.getStorage(PieceView.class);
            
            var child = storage.find(piece.getID());
            child.setParent(this);
        });
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                SignalBus.fire("tile_choose", model.getID());
            }
        });
    }

    @Override
    public String getID() {
        return model.getID();
    }

    @Override
    protected void drawGraphics(Graphics2D g) {
        g.fillRect(0, 0, getCustomSize().getX(), getCustomSize().getY());
    }
}
