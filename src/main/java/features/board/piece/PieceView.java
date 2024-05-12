package features.board.piece;

import base.Size;
import base.app.view.View;
import base.signal.SignalBus;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class PieceView extends View {
    private static final double selectionStroke = 3f;
    private static final Color selectionColor = Color.YELLOW;
    
    private final Circle circle;
    private final PieceModel pieceModel;
    
    public PieceView(PieceModel pieceModel, Size size) {
        super(pieceModel, size);
        this.pieceModel = pieceModel;
        circle = new Circle();
        var fill = pieceModel.getPieceType() == PieceType.White
                ? Color.WHITE : Color.BLACK;
        
        circle.setFill(fill);
        circle.setStroke(selectionColor);
    }

    @Override
    public String getID() {
        return pieceModel.getID();
    }

    @Override
    public void initialize() {
        pieceModel.onSelected(evt -> {
            var value = (boolean) evt.getNewValue();
            var strokeWidth = value ? selectionStroke : 0f;
            circle.setStrokeWidth(strokeWidth);
        });

        circle.setOnMouseClicked(mouseEvent -> {
            SignalBus.fire("s-piece_choose", getID());
        });
    }

    @Override
    public Shape getShape() {
        return circle;
    }

    @Override
    protected void setupDebug() {
        
    }
}
