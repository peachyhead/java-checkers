package src.base.signal;

import src.base.log.LogType;
import src.base.log.Logger;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class SignalBus {
    private static final List<SignalListener> listeners = new ArrayList<>();
    
    public static <TItem> void fire(String key, TItem item){
        var msg = MessageFormat.format("Fired signal {0}", key);
        Logger.log(msg, LogType.Signal);
        
        var valid = listeners.stream().filter(listener -> listener.isValid(key));
        valid.forEach(listener -> listener.apply(item));
    }
    
    public static void subscribe(SignalListener signalListener){
        listeners.add(signalListener);
    }
}
