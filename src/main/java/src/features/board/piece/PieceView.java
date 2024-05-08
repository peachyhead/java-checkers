package src.features.board.piece;

import src.base.signal.SignalBus;
import src.base.Size;
import src.base.app.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PieceView extends View {
    private final PieceModel pieceModel;
    
    public PieceView(PieceModel pieceModel, Size size) {
        super(pieceModel, size);
        this.pieceModel = pieceModel;
    }

    @Override
    public String getID() {
        return pieceModel.getID();
    }

    @Override
    public void initialize() {
        pieceModel.onSelected(evt -> {
            var value = (boolean) evt.getNewValue();
            setBorder(value ? BorderFactory.createLineBorder(Color.yellow) 
                    : BorderFactory.createEmptyBorder());
        });
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                SignalBus.fire("piece_choose", pieceModel.getID());
            }
        });
    }

    @Override
    protected void drawGraphics(Graphics2D g) {
        g.fillOval(0, 0, getCustomSize().getX(), getCustomSize().getY());
    }
}
