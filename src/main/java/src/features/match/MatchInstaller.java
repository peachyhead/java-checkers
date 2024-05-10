package src.features.match;

import src.UI.GameInfoPanel;
import src.base.interfaces.IGameInstaller;
import src.features.board.BaseBoardFillStrategy;
import src.features.board.piece.PieceType;
import src.features.player.PlayerModel;

public class MatchInstaller {
    public static void install(IGameInstaller gameInstaller, GameInfoPanel infoPanel, BaseBoardFillStrategy boardFillStrategy){
        var boardModel = boardFillStrategy.fillTiles(gameInstaller.getSideLength());
        boardFillStrategy.fillPieces(boardModel);
        var playerA = new PlayerModel(PieceType.Black);
        var playerB = new PlayerModel(PieceType.White);
        infoPanel.setPlayers(playerA, playerB);
        gameInstaller.getGameInfoPanel().initialize();
        var matchService = new MatchService();
        var matchRule = new MatchRule(matchService, playerA, playerB);
        matchRule.initialize();
    }
}
