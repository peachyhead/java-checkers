package src.features.board;

import lombok.Getter;
import src.GameResolver;
import src.UI.MainFrame;
import src.base.app.storage.Storage;
import src.base.app.storage.StorageKeeper;
import src.features.board.piece.*;
import src.features.board.tile.*;

public class BoardInstaller {
    
    private PieceModelFactory pieceModelFactory;
    @Getter
    private TileModelFactory tileModelFactory;
    
    public void install(MainFrame frame, StorageKeeper storageKeeper){
        bindPieces(frame, storageKeeper);
        bindTiles(frame, storageKeeper);
        
        var boardFillStrategy = GameResolver.getBoardFillStrategy();
        boardFillStrategy.setFactory(pieceModelFactory, tileModelFactory);
    }
    
    private void bindPieces(MainFrame mainFrame, StorageKeeper storageKeeper){
        var pieceModelStorage = new Storage<PieceModel>();
        storageKeeper.bindStorage(PieceModel.class, pieceModelStorage);
        var pieceViewStorage = new Storage<PieceView>();
        storageKeeper.bindStorage(PieceView.class, pieceViewStorage);
        
        pieceModelFactory = new PieceModelFactory(storageKeeper);
        var pieceViewFactory = new PieceViewFactory(mainFrame, storageKeeper);

        pieceModelStorage.onItemAdd(evt -> {
            var pieceModel = (PieceModel) evt.getNewValue();
            if (pieceModel != null)
                pieceViewFactory.create(new PieceViewArgs((PieceModel) evt.getNewValue()));
        });

        pieceModelStorage.onItemRemove(evt -> {
            var pieceModel = (PieceModel) evt.getNewValue();
            if (pieceModel == null) return;
            var item = pieceViewStorage.find(pieceModel.getID());
            pieceViewStorage.remove(item);
            var parent = item.getParent();
            parent.remove(item);
        });
    }

    private void bindTiles(MainFrame mainFrame, StorageKeeper storageKeeper){
        var tileModelStorage = new Storage<TileModel>();
        storageKeeper.bindStorage(TileModel.class, tileModelStorage);
        var tileViewStorage = new Storage<TileView>();
        storageKeeper.bindStorage(TileView.class, tileViewStorage);
        
        tileModelFactory = new TileModelFactory(storageKeeper);
        var tileViewFactory = new TileViewFactory(mainFrame, storageKeeper);

        tileModelStorage.onItemAdd(evt -> {
            var tileModel = (TileModel) evt.getNewValue();
            if (tileModel != null)
                tileViewFactory.create(new TileViewArgs((tileModel)));
        });

        tileModelStorage.onItemRemove(evt -> {
            var tileModel = (TileModel) evt.getNewValue();
            if (tileModel == null) return;
            var item = tileViewStorage.find(tileModel.getID());    
            tileViewStorage.remove(item);
        });
    }
}
