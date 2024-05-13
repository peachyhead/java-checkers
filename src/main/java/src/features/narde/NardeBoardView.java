package src.features.narde;

import src.UI.BaseBoardView;
import src.base.Size;
import src.base.app.view.View;

import java.awt.*;

import static src.UI.MainFrame.boardColor;

public class NardeBoardView extends BaseBoardView {
    public NardeBoardView(Size sideLength, int gapY) {
        setLayout(new GridLayout(sideLength.getY(), 
                sideLength.getX(), 0, gapY));
        setBackground(boardColor);
    }
}
