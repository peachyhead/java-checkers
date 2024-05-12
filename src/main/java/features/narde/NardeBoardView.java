package features.narde;

import UI.BoardView;
import base.Size;

public class NardeBoardView extends BoardView {
    public NardeBoardView(Size sideLength, int gapY) {
        super(sideLength);
        setHgap(gapY);
    }

    @Override
    public Size getCellSize() {
        return new Size(100, 100);
    }
}