package src.base.signal;

import src.base.log.LogType;
import src.base.log.Logger;

import java.text.MessageFormat;
import java.util.function.Consumer;

public class SignalListener<TProvider> {
    private final Consumer<TProvider> func;
    private final String key;

    public SignalListener(String key, Consumer<TProvider> func) {
        this.key = key;
        this.func = func;
    }

    public void apply(TProvider provider){
        var msg = MessageFormat.format("Applied signal {0} with data {1}", key, 
                provider.getClass().getSimpleName());
        Logger.log(msg, LogType.Signal);
        func.accept(provider);
    }
    
    public boolean isValid(String key) {
        return this.key.equals(key);
    }
}
