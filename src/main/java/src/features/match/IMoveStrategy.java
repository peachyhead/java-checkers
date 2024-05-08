package src.features.match;

import src.features.board.piece.PieceModel;
import src.features.board.tile.TileModel;

public interface IMoveStrategy {
    public void set(PieceModel piece);
    public boolean canMove(TileModel to);
    public void move(Turn turn, TileModel from, TileModel to);
}
