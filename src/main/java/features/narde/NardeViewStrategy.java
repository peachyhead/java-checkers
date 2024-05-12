package features.narde;

import UI.BoardView;
import UI.InfoPanel;
import base.Size;
import base.interfaces.IViewStrategy;
import javafx.scene.Group;

public class NardeViewStrategy implements IViewStrategy {
    
    private final BoardView boardView = new NardeBoardView(getSideLength(), getYGap());
    private final InfoPanel infoPanel = new NardeInfoPanel();

    @Override
    public void setupViews(Group root) {
        root.getChildren().add(boardView);
        root.getChildren().add(infoPanel);
    }
    
    @Override
    public Size getSideLength() {
        return new Size(12, 2);
    }

    @Override
    public int getYGap() {
        return 600;
    }

    @Override
    public InfoPanel getGameInfoPanel() {
        return infoPanel;
    }

    @Override
    public BoardView getBoardView() {
        return boardView;
    }

    @Override
    public Size getWindowSize() {
        var width = getSideLength().getX() * boardView.getCellSize().getX() + infoPanel.getDesiredWidth();
        var height = getSideLength().getY() * boardView.getCellSize().getY();
        return new Size(width,  height);
    }
}
