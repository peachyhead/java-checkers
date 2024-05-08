package src.features.board.tile;

import src.base.log.LogType;
import src.base.log.Logger;
import src.base.app.storage.StorageKeeper;
import src.base.app.factory.IArgFactory;
import src.base.app.storage.StorageConnector;

import java.text.MessageFormat;

public class TileModelFactory
        extends StorageConnector<TileModel>
        implements IArgFactory<TileModelArgs, TileModel> {
    
    public TileModelFactory(StorageKeeper storageKeeper) {
        super(storageKeeper);
    }

    @Override
    public TileModel create(TileModelArgs param) {
        var model = new TileModel(param.strategy(), param.position());
        var message = MessageFormat.format("Tile model {0}{1} created!",
                model.getPosition().getX(), model.getPosition().getY());
        param.strategy().setTile(model);
        Logger.log(message, LogType.Factory);
        
        tryPutItem(model);
        return model;
    }
}
