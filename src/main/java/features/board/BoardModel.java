package features.board;

import features.board.tile.TileModel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class BoardModel{
    @Getter
    private final int width;
    @Getter
    private final int height;
    
    @Getter
    private final List<TileModel> tiles;

    public BoardModel(int width, int height){
        tiles = new ArrayList<>();
        this.width = width;
        this.height = height;
    }

    public void addTile(TileModel tileModel){
        tiles.add(tileModel);
    }
}
