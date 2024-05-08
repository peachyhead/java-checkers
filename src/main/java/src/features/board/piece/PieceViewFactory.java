package src.features.board.piece;

import src.UI.MainFrame;
import src.base.Size;
import src.base.app.storage.StorageKeeper;
import src.base.app.view.ViewOptions;
import src.base.app.view.ViewFactory;
import java.awt.*;

public class PieceViewFactory extends ViewFactory<PieceViewArgs, PieceView> {

    public PieceViewFactory(MainFrame container, StorageKeeper storageKeeper) {
        super(container, storageKeeper);
    }

    @Override
    protected ViewOptions getViewOptions() {
        return new ViewOptions("src/main/resources/checker/piece.png", new Size(70, 70));
    }

    @Override
    protected PieceView createView(PieceViewArgs params, Size size) {
        var view = new PieceView(params.model(), size);
        view.setColor(params.model().getPieceType() == PieceType.White 
                ? Color.WHITE 
                : Color.BLACK );
        return view;
    }
}
