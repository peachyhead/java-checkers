package src.features.match;

import lombok.Getter;
import src.base.signal.SignalBus;
import src.features.board.piece.PieceType;
import src.features.player.PlayerModel;

public class PlayerResolver {
    @Getter private static PlayerModel localPlayer;
    @Getter private static PlayerModel playerA;
    @Getter private static PlayerModel playerB;
    
    public static void resolve(String input) {
        var id = Integer.parseInt(input);
        playerA = new PlayerModel(PieceType.White, id == 1);
        playerB = new PlayerModel(PieceType.Black, id == 2);
        localPlayer = playerA.isLocalPlayer() ? playerA : playerB;
        SignalBus.fire(MatchSignals.matchStart, "o");
    }
}
