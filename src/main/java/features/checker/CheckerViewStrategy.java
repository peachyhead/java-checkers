package features.checker;

import UI.BoardView;
import UI.InfoPanel;
import base.Size;
import base.interfaces.IViewStrategy;
import javafx.scene.Group;

public class CheckerViewStrategy implements IViewStrategy {
    
    private final BoardView boardView = new CheckerBoardView(getSideLength());
    private final InfoPanel infoPanel = new CheckerInfoPanel();
    
    @Override
    public void setupViews(Group root) {
        root.getChildren().add(boardView);
        root.getChildren().add(infoPanel);
    }

    @Override
    public Size getSideLength() {
        return new Size(8, 8);
    }

    @Override
    public int getYGap() {
        return 0;
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
