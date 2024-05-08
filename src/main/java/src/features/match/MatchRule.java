package src.features.match;

import src.base.signal.SignalBus;
import src.base.signal.SignalListener;
import src.base.interfaces.IInitializable;
import src.features.player.PlayerModel;

public class MatchRule implements IInitializable {
    private Turn currentTurn;
    private final TurnResolver turnResolver;
    
    private final SignalListener<String> pieceChooseListener;
    private final SignalListener<String> tileChooseListener;
    
    public MatchRule(MatchService matchService, PlayerModel playerA, PlayerModel playerB) {
        turnResolver = new TurnResolver(playerA, playerB);
        currentTurn = turnResolver.start();
        
        this.tileChooseListener = new SignalListener<>("tile_choose", (tile ->
        {
            if (matchService.makeMove(currentTurn, tile)) {
                currentTurn = turnResolver.resolve(currentTurn);
            }
        }));
        this.pieceChooseListener = new SignalListener<>("piece_choose", (piece -> {
            matchService.selectPiece(currentTurn, piece);
        }));
    }
    
    @Override
    public void initialize() {
        SignalBus.subscribe(pieceChooseListener);
        SignalBus.subscribe(tileChooseListener);
    }
}
