package base.signal;

import base.log.LogType;
import base.log.Logger;

import java.text.MessageFormat;
import java.util.function.Consumer;

public class SignalListener<TProvider> {
    private final Consumer<TProvider> func;
    private String id;
    private final String key;

    public SignalListener(String key, Consumer<TProvider> func) {
        this.key = key;
        this.func = func;
    }

    public SignalListener(String id, String key, Consumer<TProvider> func) {
        this.id = id;
        this.key = key;
        this.func = func;
    }

    public void apply(TProvider provider){
        var idField = id == null ? "Anon" : "id";
        var msg = MessageFormat.format("{0} Applied signal {1} with data {2}", 
                idField, key, provider.getClass().getSimpleName());
        Logger.log(msg, LogType.Signal);
        func.accept(provider);
    }
    
    public boolean isValid(String key) {
        return this.key.equals(key);
    }
}
