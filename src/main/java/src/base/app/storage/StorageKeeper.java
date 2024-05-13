package src.base.app.storage;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

public class StorageKeeper {
    private static final List<StorageMapItem> storageMap = new ArrayList<>();
    
    public <T extends IStorageItem> void bindStorage(Class<T> type, Storage<T> storage) {
        var item = new StorageMapItem(type, storage);
        storageMap.add(item);
    }

    public static <T extends IStorageItem> Storage<T> getStorage(Class<T> tClass) {
        try {
            return storageMap.stream()
                    .filter(item -> item.tryUnlock(tClass))
                    .findFirst()
                    .orElseThrow(() -> new InvalidKeyException("No storage found for " + tClass))
                    .getValue();
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
