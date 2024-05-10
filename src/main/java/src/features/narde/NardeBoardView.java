package src.features.narde;

import src.UI.BoardView;
import src.base.Size;
import src.base.app.view.View;

import java.awt.*;

import static src.UI.MainFrame.boardColor;

public class NardeBoardView extends BoardView {
    public NardeBoardView(Size sideLength, int gapY) {
        setLayout(new GridLayout(sideLength.getY(), 
                sideLength.getX(), 0, gapY));
        setBackground(boardColor);
    }

    @Override
    public void addView(View view) {
        var size = view.getCustomSize();
        add(view);
        view.setPreferredSize(new Dimension(size.getX(), size.getY() + 100));
        revalidate();
    }
}
