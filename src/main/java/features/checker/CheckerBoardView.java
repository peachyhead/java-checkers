package features.checker;

import UI.BoardView;
import base.Size;

public class CheckerBoardView extends BoardView {
    public CheckerBoardView(Size sideLength) {
        super(sideLength);
    }

    @Override
    public Size getCellSize() {
        return new Size(100, 100);
    }
}
