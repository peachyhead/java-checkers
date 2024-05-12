package features.board.tile;

import base.Size;
import base.app.storage.StorageKeeper;
import base.app.view.ViewFactory;
import base.app.view.ViewOptions;
import base.interfaces.IViewStrategy;

import static features.checker.CheckerUtils.AvailableTileColor;
import static features.checker.CheckerUtils.UnavailableTileColor;

public class TileViewFactory extends ViewFactory<TileViewArgs, TileView> {
    
    public TileViewFactory(IViewStrategy gameInstaller, StorageKeeper storageKeeper) {
        super(gameInstaller, storageKeeper);
    }

    @Override
    protected ViewOptions getViewOptions() {
        return new ViewOptions("", 
                new Size(100, 100));
    }

    @Override
    protected TileView createView(TileViewArgs params, Size size) {
        var view = new TileView(params.model(), size);
        var isAvailable = params.model().getTileStrategy().isInitialTile();
        var color = isAvailable ? AvailableTileColor
                : UnavailableTileColor;
        
        view.setColor(color);
        
        return view;
    }
}
