package src.features.match;

import src.base.interfaces.IGameInstaller;

public class MatchInstaller {
    public static void install(IGameInstaller gameInstaller){
        var boardFillStrategy = gameInstaller.getBoardFillStrategy();
        var boardModel = boardFillStrategy.fillTiles(gameInstaller.getSideLength());
        boardFillStrategy.fillPieces(boardModel);
        gameInstaller.getGameInfoPanel().initialize();
        var matchService = new MatchService();
        var matchRule = new MatchRule(matchService, gameInstaller);
        matchRule.initialize();
    }
}
