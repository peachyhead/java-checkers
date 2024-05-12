package UI;

import javafx.scene.layout.TilePane;
import lombok.Getter;
import base.Size;
import base.app.view.View;

import javax.swing.*;

public abstract class BoardView extends TilePane {
    protected BoardView(Size sideLength){
        setPrefColumns(sideLength.getX());
        setPrefRows(sideLength.getY());

        setPrefTileWidth(getCellSize().getX());
        setPrefTileHeight(getCellSize().getY());
    }
    
    public void addView(View view) {
        getChildren().add(view.getShape());
    }
    
    public abstract Size getCellSize();
}
