package base.interfaces;

import UI.BoardView;
import UI.InfoPanel;
import base.Size;
import javafx.scene.Group;

public interface IViewStrategy {
    public void setupViews(Group root);
    public Size getSideLength();
    public int getYGap();
    public InfoPanel getGameInfoPanel();
    public BoardView getBoardView();
    public Size getWindowSize();
}
