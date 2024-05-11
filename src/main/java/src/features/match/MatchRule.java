package src.features.match;

import src.base.interfaces.IGameInstaller;
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
    private final IGameInstaller gameInstaller;
    
    private final SignalListener<String> pieceChooseListener;
    private final SignalListener<String> tileChooseListener;
    private final SignalListener<String> playerAddListener;
    
    public MatchRule(MatchService matchService, IGameInstaller gameInstaller) {
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
        this.gameInstaller = gameInstaller;

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
        turnResolver = new TurnResolver(playerA, playerB);
        currentTurn = turnResolver.start();
        gameInstaller.getGameInfoPanel().setPlayers(playerA, playerB);
    }
    
    @Override
    public void initialize() {
        SignalBus.subscribe(pieceChooseListener);
        SignalBus.subscribe(tileChooseListener);
        SignalBus.subscribe(playerAddListener);
    }
}
