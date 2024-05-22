package src;

import lombok.Getter;
import src.base.interfaces.IViewStrategy;
import src.features.board.BaseBoardFillStrategy;
import src.features.checker.CheckerBoardFillStrategy;
import src.features.checker.CheckerViewStrategy;

public class GameResolver {
    
    @Getter private static IViewStrategy viewStrategy;
    @Getter private static BaseBoardFillStrategy boardFillStrategy;
    
    public static void resolve(String[] args) {
        viewStrategy = resolveViewStrategy(args);
        boardFillStrategy = resolveBoardFillStrategy(args);
    }
    
    private static IViewStrategy resolveViewStrategy(String[] args){
        return  new CheckerViewStrategy();
    }

    private static BaseBoardFillStrategy resolveBoardFillStrategy(String[] args){
        return new CheckerBoardFillStrategy();
    }
}
