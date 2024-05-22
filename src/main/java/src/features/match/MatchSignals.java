package src.features.match;

import java.text.MessageFormat;

public class MatchSignals {
    
    public static final String playerAdd = "player_add";
    public static final String pieceMove = "piece_move";
    public static final String tileChoose = "tile_choose";
    public static final String pieceChoose = "piece_choose";
    public static final String matchStart = "match_start";
    
    public static String fromServer(String signal) {
        return MessageFormat.format("{0}-{1}", "s", signal);
    }
}
