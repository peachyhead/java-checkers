package src.features.narde;

import src.UI.BaseBoardView;
import src.UI.BaseInfoPanel;
import src.base.Size;
import src.base.interfaces.IViewStrategy;

import java.awt.*;

public class NardeViewStrategy implements IViewStrategy {

    private static final int YGap = 600;
    
    private BaseBoardView boardView;
    private BaseInfoPanel infoPanel;
    
    @Override
    public Size getSideLength() {
        return new Size(12, 2);
    }

    @Override
    public Size getWindowSize() {
        var sideLength = getSideLength();
        var width = sideLength.getX() * boardView.cellSize + infoPanel.panelWidth;
        var height = sideLength.getY() * boardView.cellSize + YGap;
        return new Size(width, height);
    }

    @Override
    public BaseInfoPanel getInfoPanel() {
        if (infoPanel == null) {
            infoPanel = new NardeInfoPanel();
            infoPanel.setBackground(Color.GRAY);
        }
        return infoPanel;
    }

    @Override
    public BaseBoardView getBoardView() {
        if (boardView == null)
            boardView = new NardeBoardView(getSideLength(), YGap);
        return boardView;
    }
}
