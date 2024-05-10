package src.features.narde;

import src.features.board.tile.ITileStrategy;
import src.features.board.tile.TileModel;

public class NardeTileStrategy implements ITileStrategy {
    private TileModel tileModel;

    @Override
    public void setTile(TileModel tile) {
        this.tileModel = tile;
    }

    @Override
    public boolean isInitialTile() {
        if (tileModel == null || tileModel.getPosition() == null)
            return false;

        return tileModel.getPosition().getX() == 1;
    }
}
