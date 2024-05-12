package features.board.tile;

import base.Size;
import base.app.storage.StorageKeeper;
import base.app.view.View;
import base.signal.SignalBus;
import features.board.piece.PieceModel;
import features.board.piece.PieceView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class TileView extends View {
    private final TileModel model;
    private final Rectangle rectangle;

    public TileView(TileModel model, Size size) {
        super(model, size);
        this.model = model;
        layout = new StackPane();
        rectangle = new Rectangle();
        var fill = model.getTileStrategy().isInitialTile()
                ? Color.BLACK : Color.WHITE;

        rectangle.setFill(fill);
    }

    @Override
    public void initialize(){
        model.subscribe(evt -> {
            var piece = (PieceModel) evt.getNewValue();
            if (piece == null) return;
            var storage = StorageKeeper.getStorage(PieceView.class);
            
            var child = storage.find(piece.getID());
            child.setParentView(this);
        });

        rectangle.setOnMouseClicked(mouseEvent -> {
            SignalBus.fire("s-tile_choose", model.getID());
        });
    }

    
    @Override
    public String getID() {
        return model.getID();
    }

    @Override
    public Shape getShape() {
        return rectangle;
    }

    @Override
    protected void setupDebug() {
        
    }
}
