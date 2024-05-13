package src.features.match;

import src.base.signal.SignalBus;
import src.features.player.PlayerModel;

public class TurnResolver {
    private final PlayerModel playerA;
    private final PlayerModel playerB;
    
    public TurnResolver(PlayerModel playerA, PlayerModel playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }
    
    private Turn createTurn(int id, PlayerModel playerModel){
        var turn = new Turn(id, playerModel);
        SignalBus.fire("new_turn", turn);
        return turn;
    }
    
    public Turn start(){
        return createTurn(1, playerA);
    }
    
    public Turn resolve(Turn currentTurn) {
        var id = currentTurn.id() + 1;
        return createTurn(id, decidePlayer(currentTurn));
    }
    
    private PlayerModel decidePlayer(Turn turn) {
        return turn.playerModel() == playerA
                ? playerB
                : playerA;
    }
}
