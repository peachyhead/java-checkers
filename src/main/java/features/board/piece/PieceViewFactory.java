package features.board.piece;

import base.Size;
import base.app.storage.StorageKeeper;
import base.app.view.ViewFactory;
import base.app.view.ViewOptions;
import base.interfaces.IViewStrategy;

import java.awt.*;

public class PieceViewFactory extends ViewFactory<PieceViewArgs, PieceView> {

    public PieceViewFactory(IViewStrategy gameInstaller, StorageKeeper storageKeeper) {
        super(gameInstaller, storageKeeper);
    }

    @Override
    protected ViewOptions getViewOptions() {
        return new ViewOptions("src/main/resources/checker/piece.png", 
                new Size(80, 80));
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
