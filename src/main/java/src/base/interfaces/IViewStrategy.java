package src.base.interfaces;

import src.UI.BaseBoardView;
import src.UI.BaseInfoPanel;
import src.base.Size;

public interface IViewStrategy {
    public Size getSideLength();
    public Size getWindowSize();
    public BaseInfoPanel getInfoPanel();
    public BaseBoardView getBoardView();
}
