package src.features.board.piece;

import src.base.signal.SignalBus;
import src.base.Size;
import src.base.app.view.View;
import src.features.match.MatchRule;
import src.features.match.MatchService;
import src.features.match.MatchSignals;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PieceView extends View {
    private static final int selectionStroke = 3;
    private static final Color selectionColor = Color.yellow;
    
    private final PieceModel pieceModel;
    private int strokeWidth;
    
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
            strokeWidth = value ? selectionStroke : 0;
        });
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (MatchService.canGrabPiece(pieceModel.getPieceType()))
                    SignalBus.fire(MatchSignals.pieceChoose, pieceModel.getID());
            }
        });
    }

    @Override
    protected void setupDebug() {
        
    }

    @Override
    protected void drawGraphics(Graphics2D g) {
        if (strokeWidth != 0) {
            g.setColor(selectionColor);
            g.fillOval(0, 0, getCustomSize().getX(), getCustomSize().getY());
        }
        
        g.setColor(color);
        g.fillOval(strokeWidth, strokeWidth, getCustomSize().getX() - strokeWidth * 2, 
                getCustomSize().getY() - strokeWidth * 2);
    }
}
