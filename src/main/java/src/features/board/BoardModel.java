package src.features.board;

import lombok.Getter;
import src.base.Position;
import src.features.board.tile.TileModel;

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
