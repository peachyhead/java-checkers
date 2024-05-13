package src.features.match;

public class MatchInstaller {
    public static void install(){
        var matchService = new MatchService();
        var matchRule = new MatchRule(matchService);
        matchRule.initialize();
    }
}
