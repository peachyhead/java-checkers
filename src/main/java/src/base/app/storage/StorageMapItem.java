package src.base.app.storage;

import src.base.interfaces.IIdentifiable;
import src.base.interfaces.IInitializable;

import java.lang.reflect.Type;

public class StorageMapItem{
    private final Type type;
    private final IGeneralStorage value;

    public StorageMapItem(Type type, IGeneralStorage value) {
        this.type = type;
        this.value = value;
    }
    
    public <TKey extends IIdentifiable & IInitializable> Storage<TKey> tryGetValue(Type key) {
        var success = tryUnlock(key);
        return success ? getValue() : null;
    }
    
    public <TKey extends IIdentifiable & IInitializable> Storage<TKey> getValue() {
        return (Storage<TKey>) value;
    }
    
    public boolean tryUnlock(Type key){
        return this.type == key;
    }
}
