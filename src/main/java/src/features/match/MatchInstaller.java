package src.features.match;

import src.UI.InfoPanel;
import src.base.Size;
import src.features.board.IBoardFillStrategy;
import src.features.board.piece.PieceType;
import src.features.player.PlayerModel;

public class MatchInstaller {
    public static void install(Size sideLength, InfoPanel infoPanel, IBoardFillStrategy boardFillStrategy){
        var boardModel = boardFillStrategy.fillTiles(sideLength);
        boardFillStrategy.fillPieces(boardModel);
        var playerA = new PlayerModel(PieceType.Black);
        var playerB = new PlayerModel(PieceType.White);
        infoPanel.setPlayers(playerA, playerB);
        var matchService = new MatchService();
        var matchRule = new MatchRule(matchService, playerA, playerB);
        matchRule.initialize();
    }
}
