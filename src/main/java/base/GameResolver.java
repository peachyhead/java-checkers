package base;

import features.board.BaseBoardFillStrategy;
import features.checker.CheckerBoardFillStrategy;
import features.checker.CheckerViewStrategy;
import features.narde.NardeBoardFillStrategy;
import features.narde.NardeViewStrategy;
import lombok.Getter;
import base.interfaces.IViewStrategy;

import java.util.Arrays;

public class GameResolver {
    @Getter 
    private static IViewStrategy viewStrategy;
    @Getter 
    private static BaseBoardFillStrategy boardFillStrategy;

    public static void resolve(String[] args) {
        viewStrategy = resolveViewStrategy(args);
        boardFillStrategy = resolveBoardFillStrategy(args);
    }
    
    public static IViewStrategy resolveViewStrategy(String[] args){
        return switch (Arrays.stream(args).findFirst().orElse("")){
            case "checkers" -> new CheckerViewStrategy();
            case "narde" -> new NardeViewStrategy();
            default -> throw new IllegalStateException("Can't resolve game: " +
                    Arrays.stream(args).findFirst().get());
        };
    }

    private static BaseBoardFillStrategy resolveBoardFillStrategy(String[] args){
        return switch (Arrays.stream(args).findFirst().orElse("")){
            case "checkers" -> new CheckerBoardFillStrategy();
            case "narde" -> new NardeBoardFillStrategy();
            default -> throw new IllegalStateException("Can't resolve game: " +
                    Arrays.stream(args).findFirst().get());
        };
    }
}
