package src.base.app.storage;

import lombok.SneakyThrows;
import src.base.interfaces.IIdentifiable;
import src.base.interfaces.IInitializable;

public abstract class StorageConnector<T extends IStorageItem> {
    private final StorageKeeper storageKeeper;

    protected StorageConnector(StorageKeeper storageKeeper) {
        this.storageKeeper = storageKeeper;
    }

    @SneakyThrows
    protected void tryPutItem(T item){
        var storage = (Storage<T>)storageKeeper.getStorage(item.getClass());
        if (storage == null) return;
        storage.add(item);
    }
}
