package src;

import lombok.Getter;
import src.base.interfaces.IViewStrategy;
import src.features.board.BaseBoardFillStrategy;
import src.features.checker.CheckerBoardFillStrategy;
import src.features.checker.CheckerViewStrategy;
import src.features.narde.NardeBoardFillStrategy;
import src.features.narde.NardeViewStrategy;

import java.util.Arrays;

public class GameResolver {
    
    @Getter private static IViewStrategy viewStrategy;
    @Getter private static BaseBoardFillStrategy boardFillStrategy;
    
    public static void resolve(String[] args) {
        viewStrategy = resolveViewStrategy(args);
        boardFillStrategy = resolveBoardFillStrategy(args);
    }
    
    private static IViewStrategy resolveViewStrategy(String[] args){
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
