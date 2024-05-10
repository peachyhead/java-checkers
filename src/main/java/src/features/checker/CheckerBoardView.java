package src.features.checker;

import src.UI.BoardView;
import src.base.Size;
import src.base.app.view.View;

import java.awt.*;

import static src.UI.MainFrame.boardColor;

public class CheckerBoardView extends BoardView {
    public CheckerBoardView(Size sideLength) {
        setLayout(new GridLayout(sideLength.getY(), sideLength.getX()));
        setBackground(boardColor);
    }

    @Override
    public void addView(View view) {
        var size = view.getCustomSize();
        add(view);
        view.setPreferredSize(new Dimension(size.getX(), size.getY()));
        revalidate();
    }
}
