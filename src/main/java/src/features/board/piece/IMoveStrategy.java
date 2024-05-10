package src.features.board.piece;

import src.features.board.tile.TileModel;
import src.features.match.Turn;

public interface IMoveStrategy {
    public void set(PieceModel piece);
    public boolean canMove(TileModel to);
    public void move(Turn turn, TileModel from, TileModel to);
}
