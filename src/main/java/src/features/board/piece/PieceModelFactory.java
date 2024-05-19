package src.features.board.piece;

import src.base.log.LogType;
import src.base.log.Logger;
import src.base.app.factory.IArgFactory;
import src.base.app.storage.StorageConnector;
import src.base.app.storage.StorageKeeper;
import src.features.checker.CheckerAttackStrategy;
import src.features.checker.CheckerMoveStrategy;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class PieceModelFactory
        extends StorageConnector<PieceModel>
        implements IArgFactory<PieceModelArgs, PieceModel> {
    
    public PieceModelFactory(StorageKeeper storageKeeper) {
        super(storageKeeper);
    }

    @Override
    public PieceModel create(PieceModelArgs param) {
        var model = new PieceModel(param.id(), param.type(), param.position());
        var message = MessageFormat.format("Piece model {0} created!", model.getID());
        Logger.log(message, LogType.Factory);
        
        setupMoveStrategy().forEach(strategy -> {
            strategy.set(model);
            model.addMoveStrategy(strategy);
        });
        
        tryPutItem(model);
        return model;
    }
    
    private List<IMoveStrategy> setupMoveStrategy(){
        var result = new ArrayList<IMoveStrategy>();
        result.add(new CheckerMoveStrategy());
        result.add(new CheckerAttackStrategy());
        return result;
    }
}