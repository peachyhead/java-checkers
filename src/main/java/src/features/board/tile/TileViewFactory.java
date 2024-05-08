package src.features.board.tile;

import src.UI.MainFrame;
import src.base.Size;
import src.base.app.view.ViewOptions;
import src.base.app.storage.StorageKeeper;
import src.base.app.view.ViewFactory;

import java.awt.*;

import static src.features.checker.CheckerUtils.AvailableTileColor;
import static src.features.checker.CheckerUtils.UnavailableTileColor;

public class TileViewFactory extends ViewFactory<TileViewArgs, TileView> {
    
    public TileViewFactory(MainFrame frame, StorageKeeper storageKeeper) {
        super(frame, storageKeeper);
    }

    @Override
    protected ViewOptions getViewOptions() {
        return new ViewOptions("src/main/resources/checker/tile.png", 
                new Size(100, 100));
    }

    @Override
    protected TileView createView(TileViewArgs params, Size size) {
        var view = new TileView(params.model(), size);
        var color = params.model().getTileStrategy().isInitialTile()
                ? AvailableTileColor
                : UnavailableTileColor;
        view.setColor(color);
        view.setLayout(new GridBagLayout());
        return view;
    }
}
