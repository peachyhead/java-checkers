package src.features.board.tile;

import src.base.signal.SignalBus;
import src.base.Size;
import src.base.app.view.View;
import src.base.app.storage.StorageKeeper;
import src.features.board.piece.PieceModel;
import src.features.board.piece.PieceView;
import src.features.narde.TileLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.MessageFormat;
import java.util.Arrays;

public class TileView extends View {
    private final TileModel model;
    private JTextField debug;

    public TileView(TileModel model, Size size) {
        super(model, size);
        this.model = model;
        //setupDebug();
    }

    public void showDebug(boolean status) {
        Arrays.stream(getComponents())
                .toList().forEach(comp -> comp.setVisible(!status));
        var info = MessageFormat.format("{0}\n{1}", 
                model.getID(), getComponents().length - 1);
        debug.setText(info);
        debug.setVisible(status);
        revalidate();
    }

    @Override
    public void initialize(){
        model.subscribe(evt -> {
            var piece = (PieceModel) evt.getNewValue();
            if (piece == null) return;
            var storage = StorageKeeper.getStorage(PieceView.class);
            
            var child = storage.find(piece.getID());
            child.setParent(this);
            revalidate();
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
    protected void setupDebug() {
        var consts = new GridBagConstraints();
        consts.gridy = 0;
        consts.gridx = 0;
        
        debug = new JTextField();
        debug.setVisible(false);
        debug.setEditable(false);
        add(debug, consts);
    }

    @Override
    protected void drawGraphics(Graphics2D g) {
        g.setColor(color);
        g.fillRect(0, 0, getCustomSize().getX(), 
                getCustomSize().getY());

        // Create a rectangle to represent the clipping region
        var clipBounds = new Rectangle();

        // Iterate over all child components to determine the union of their bounds
        for (Component comp : getComponents()) {
            if (comp.isVisible()) {
                Rectangle bounds = comp.getBounds();
                clipBounds = clipBounds.union(bounds);
            }
        }

        // Set the clipping region to the union of the bounds of all child components
        ((Graphics2D) g).setClip(clipBounds);

        // Call the layout manager to position and size the child components
        getLayout().layoutContainer(this);

        // Paint the child components
        for (Component comp : getComponents()) {
            if (comp.isVisible()) {
                comp.paint(g.create(comp.getX(), comp.getY(), comp.getWidth(), comp.getHeight()));
            }
        }
    }
}
