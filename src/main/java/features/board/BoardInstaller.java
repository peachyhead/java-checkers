package features.board;

import base.GameResolver;
import base.app.storage.Storage;
import base.app.storage.StorageKeeper;
import base.interfaces.IViewStrategy;
import features.board.piece.*;
import features.board.tile.*;
import lombok.Getter;

public class BoardInstaller {
    @Getter
    private static Storage<PieceModel> pieceModelStorage;
    @Getter
    private static Storage<PieceView> pieceViewStorage;
    @Getter
    private static PieceModelFactory pieceModelFactory;
    @Getter
    private static PieceViewFactory pieceViewFactory;
    
    @Getter
    private static Storage<TileModel> tileModelStorage;
    @Getter
    private static Storage<TileView> tileViewStorage;
    @Getter
    private static TileModelFactory tileModelFactory;
    @Getter
    private static TileViewFactory tileViewFactory;

    public static void install(StorageKeeper storageKeeper){
        var viewStrategy = GameResolver.getViewStrategy();
        
        bindPieces(viewStrategy, storageKeeper);
        bindTiles(viewStrategy, storageKeeper);
        
        GameResolver.getBoardFillStrategy()
                .setFactory(pieceModelFactory, tileModelFactory);
    }
    
    private static void bindPieces(IViewStrategy viewStrategy, StorageKeeper storageKeeper){
        pieceModelStorage = new Storage<>();
        storageKeeper.bindStorage(PieceModel.class, pieceModelStorage);
        pieceViewStorage = new Storage<>();
        storageKeeper.bindStorage(PieceView.class, pieceViewStorage);
        
        pieceModelFactory = new PieceModelFactory(storageKeeper);
        pieceViewFactory = new PieceViewFactory(viewStrategy, storageKeeper);

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
            var parent = item.getParentView();
            parent.getLayout().getChildren().remove(item);
        });
    }

    private static void bindTiles(IViewStrategy viewStrategy, StorageKeeper storageKeeper){
        tileModelStorage = new Storage<>();
        storageKeeper.bindStorage(TileModel.class, tileModelStorage);
        tileViewStorage = new Storage<>();
        storageKeeper.bindStorage(TileView.class, tileViewStorage);
        
        tileModelFactory = new TileModelFactory(storageKeeper);
        tileViewFactory = new TileViewFactory(viewStrategy, storageKeeper);

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
