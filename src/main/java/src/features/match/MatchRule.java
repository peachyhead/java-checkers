package src.features.match;

import src.GameResolver;
import src.base.signal.SignalBus;
import src.base.signal.SignalListener;
import src.base.interfaces.IInitializable;
import src.features.board.piece.PieceType;
import src.features.player.PlayerModel;

public class MatchRule implements IInitializable {
    private PlayerModel playerA;
    private PlayerModel playerB;
    
    private Turn currentTurn;
    private TurnResolver turnResolver;
    
    private final SignalListener<String> pieceMoveListener;
    private final SignalListener<String> pieceChooseListener;
    private final SignalListener<String> tileChooseListener;
    private final SignalListener<String> playerAddListener;
    
    public MatchRule(MatchService matchService) {
        pieceMoveListener = new SignalListener<>("piece_move", (ignored -> {
            if (currentTurn == null) return;
            if (!matchService.checkIfCanMove(currentTurn)) {
                currentTurn = turnResolver.resolve(currentTurn);
                matchService.onNewTurn();
            }
        }));
        
        tileChooseListener = new SignalListener<>("s-tile_choose", (tile -> {
            if (currentTurn == null) return;
            matchService.tryMove(currentTurn, tile);
        }));
        
        pieceChooseListener = new SignalListener<>("s-piece_choose", (piece -> {
            if (currentTurn != null)
                matchService.selectPiece(currentTurn, piece);
        }));

        playerAddListener = new SignalListener<>("s-player_add", (piece -> {
            if (playerA != null && playerB != null) return;
            
            setPlayer();
            if (playerA != null && playerB != null) {
                setupMatch();
            }
        }));
    }
    
    @Override
    public void initialize() {
        SignalBus.subscribe(pieceChooseListener);
        SignalBus.subscribe(tileChooseListener);
        SignalBus.subscribe(playerAddListener);
        SignalBus.subscribe(pieceMoveListener);
    }
    
    private void setPlayer() {
        if (playerA == null)
            playerA = new PlayerModel(PieceType.White);
        else if (playerB == null)
            playerB = new PlayerModel(PieceType.Black);
    }
    
    private void setupMatch(){
        var viewStrategy = GameResolver.getViewStrategy();
        var boardFillStrategy = GameResolver.getBoardFillStrategy();
        var boardModel = boardFillStrategy.fillTiles(viewStrategy.getSideLength());
        boardFillStrategy.fillPieces(boardModel);
        viewStrategy.getInfoPanel().initialize(playerA, playerB);
        
        turnResolver = new TurnResolver(playerA, playerB);
        currentTurn = turnResolver.start();
    }
}
