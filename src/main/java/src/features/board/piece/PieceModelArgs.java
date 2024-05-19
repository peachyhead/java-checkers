package src.features.board.piece;

import src.base.Position;

public record PieceModelArgs(int id, PieceType type, Position position) {
}
