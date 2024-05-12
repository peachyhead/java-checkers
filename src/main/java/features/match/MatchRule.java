package features.match;

import base.GameResolver;
import base.interfaces.IInitializable;
import base.signal.SignalBus;
import base.signal.SignalListener;
import features.board.piece.PieceType;
import features.player.PlayerModel;

public class MatchRule implements IInitializable {
    private PlayerModel playerA;
    private PlayerModel playerB;
    
    private Turn currentTurn;
    private TurnResolver turnResolver;
    
    private final SignalListener<String> pieceChooseListener;
    private final SignalListener<String> tileChooseListener;
    private final SignalListener<String> playerAddListener;
    
    public MatchRule(MatchService matchService) {
        tileChooseListener = new SignalListener<>("s-tile_choose", (tile ->
        {
            if (currentTurn != null && matchService.makeMove(currentTurn, tile)) {
                currentTurn = turnResolver.resolve(currentTurn);
                matchService.onNewTurn();
            }
        }));
        
        pieceChooseListener = new SignalListener<>("s-piece_choose", (piece -> {
            if (currentTurn != null)
                matchService.selectPiece(currentTurn, piece);
        }));

        playerAddListener = new SignalListener<>("s-player_add", (piece -> {
            setPlayer();
            if (playerA != null && playerB != null) {
                setupMatch();
            }
        }));
    }
    
    private void setPlayer() {
        if (playerA == null)
            playerA = new PlayerModel(PieceType.Black);
        else if (playerB == null)
            playerB = new PlayerModel(PieceType.White);
    }
    
    private void setupMatch(){
        var boardFillStrategy = GameResolver.getBoardFillStrategy();
        var viewStrategy = GameResolver.getViewStrategy();
        var boardModel = boardFillStrategy.fillTiles(viewStrategy.getSideLength());
        boardFillStrategy.fillPieces(boardModel);
        turnResolver = new TurnResolver(playerA, playerB);
        currentTurn = turnResolver.start();
        //MainFrame.setPlayers(playerA, playerB);
    }
    
    @Override
    public void initialize() {
        SignalBus.subscribe(pieceChooseListener);
        SignalBus.subscribe(tileChooseListener);
        SignalBus.subscribe(playerAddListener);
    }
}
