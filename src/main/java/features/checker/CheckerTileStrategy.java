package features.checker;

import features.board.tile.ITileStrategy;
import features.board.tile.TileModel;

public class CheckerTileStrategy implements ITileStrategy {
    private TileModel tileModel;
    
    @Override
    public void setTile(TileModel tile) {
        this.tileModel = tile;
    }
    
    @Override
    public boolean isInitialTile() {
        if (tileModel == null || tileModel.getPosition() == null)
            return false;

        var evenX = tileModel.getPosition().getX() % 2 == 0;
        var evenY = tileModel.getPosition().getY() % 2 == 0;
        return evenX && evenY || !evenX && !evenY;
    }
}
