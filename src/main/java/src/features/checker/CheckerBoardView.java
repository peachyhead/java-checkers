package src.features.checker;

import src.UI.BaseBoardView;
import src.base.Size;
import src.base.app.view.View;

import java.awt.*;

import static src.UI.MainFrame.boardColor;

public class CheckerBoardView extends BaseBoardView {
    public CheckerBoardView(Size sideLength) {
        setLayout(new GridLayout(sideLength.getY(), sideLength.getX()));
        setBackground(boardColor);
    }
}
