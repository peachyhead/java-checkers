package features.board.tile;

import base.Position;

public record TileModelArgs(ITileStrategy strategy, Position position) {
}
