package src.features.checker;

import src.UI.BaseBoardView;
import src.UI.BaseInfoPanel;
import src.base.Size;
import src.base.interfaces.IViewStrategy;

import java.awt.*;

public class CheckerViewStrategy implements IViewStrategy {
    
    private BaseBoardView boardView;
    private BaseInfoPanel infoPanel;

    @Override
    public Size getSideLength() {
        return new Size(8, 8);
    }

    @Override
    public Size getWindowSize() {
        var sideLength = getSideLength();
        var width = sideLength.getX() * getBoardView().cellSize + getInfoPanel().panelWidth;
        var height = sideLength.getY() * getBoardView().cellSize;
        return new Size(width, height);
    }

    @Override
    public BaseInfoPanel getInfoPanel() {
        if (infoPanel == null) {
            infoPanel = new CheckerInfoPanel();
            infoPanel.setBackground(Color.GRAY);
        }
        return infoPanel;
    }

    @Override
    public BaseBoardView getBoardView() {
        if (boardView == null)
            boardView = new CheckerBoardView(getSideLength());
        return boardView;
    }
}
