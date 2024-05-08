package src.features.board.tile;

import src.base.Position;

public record TileModelArgs(ITileStrategy strategy, Position position) {
}
