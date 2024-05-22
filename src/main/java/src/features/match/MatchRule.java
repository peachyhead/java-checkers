package src.features.match;

import src.GameResolver;
import src.base.signal.SignalBus;
import src.base.signal.SignalListener;
import src.base.interfaces.IInitializable;

public class MatchRule implements IInitializable {
    private Turn currentTurn;
    private TurnResolver turnResolver;
    
    private final SignalListener<String> pieceMoveListener;
    private final SignalListener<String> pieceChooseListener;
    private final SignalListener<String> tileChooseListener;
    private final SignalListener<String> matchStartListener;
    
    public MatchRule(MatchService matchService) {
        
        matchStartListener = new SignalListener<>(MatchSignals.matchStart, (ignored -> setupMatch()));
        
        pieceMoveListener = new SignalListener<>(MatchSignals.pieceMove, (ignored -> {
            if (currentTurn == null) return;
            if (!matchService.checkIfCanMove(currentTurn)) {
                currentTurn = turnResolver.resolve(currentTurn);
                matchService.onNewTurn();
            }
        }));
        
        tileChooseListener = new SignalListener<>(MatchSignals.fromServer(MatchSignals.tileChoose), (tile -> {
            if (currentTurn == null) return;
            matchService.tryMove(currentTurn, tile);
        }));
        
        pieceChooseListener = new SignalListener<>(MatchSignals.fromServer(MatchSignals.pieceChoose), (piece -> {
            if (currentTurn == null) return;
            matchService.selectPiece(currentTurn, piece);
        }));
    }
    
    @Override
    public void initialize() {
        SignalBus.subscribe(pieceChooseListener);
        SignalBus.subscribe(tileChooseListener);
        SignalBus.subscribe(pieceMoveListener);
        SignalBus.subscribe(matchStartListener);
    }
    
    private void setupMatch(){
        var viewStrategy = GameResolver.getViewStrategy();
        var boardFillStrategy = GameResolver.getBoardFillStrategy();
        var boardModel = boardFillStrategy.fillTiles(viewStrategy.getSideLength());
        var playerA = PlayerResolver.getPlayerA();
        var playerB = PlayerResolver.getPlayerB();
        
        boardFillStrategy.fillPieces(boardModel);
        viewStrategy.getInfoPanel().initialize(playerA, playerB);
        
        turnResolver = new TurnResolver(playerA, playerB);
        currentTurn = turnResolver.start();
    }
}
