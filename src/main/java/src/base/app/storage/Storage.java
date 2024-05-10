package src.base.app.storage;

import lombok.Getter;
import src.base.log.LogType;
import src.base.log.Logger;
import src.base.interfaces.IIdentifiable;
import src.base.interfaces.IInitializable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Storage<T extends IIdentifiable & IInitializable> implements IGeneralStorage {
    @Getter
    private final List<T> collection = new ArrayList<>();

    private final Action onItemAdd = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    };

    private final Action onItemRemove = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };
    
    public T find(String id){
        return collection.stream().filter(item -> Objects.equals(item.getID(), id))
                .findFirst().orElse(null);
    }
    
    public void add(T element) {
        collection.add(element);
        var type = element.getClass().getSimpleName();
        var output = MessageFormat.format("[Storage<{0}>] Inserted {1}", type, element.getID());
        element.initialize();
        Logger.log(output, LogType.Storage);
        onItemAdd.putValue("id", element);
    }
    
    public boolean remove(T item) {
        if (!collection.contains(item)) return false;
        var type = item.getClass().getSimpleName();
        var output = MessageFormat.format("[Storage<{0}>] Removed {1}", type, item.getID());
        
        Logger.log(output, LogType.Storage);
        collection.remove(item);
        onItemRemove.putValue("id", item);
        return true;
    }

    public void onItemAdd(PropertyChangeListener propertyChangeListener){
        onItemAdd.addPropertyChangeListener(propertyChangeListener);
    }

    public void onItemRemove(PropertyChangeListener propertyChangeListener){
        onItemRemove.addPropertyChangeListener(propertyChangeListener);
    }
}
