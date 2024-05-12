package base.app.view;

import base.Size;
import base.app.storage.IStorageItem;
import base.interfaces.IPositionProvider;
import base.log.LogType;
import base.log.Logger;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.text.MessageFormat;

public abstract class View implements IStorageItem {
    @Getter private final IPositionProvider positionProvider;
    @Setter @Getter 
    private Size customSize;
    @Setter protected Color color;
    @Getter protected Pane layout;
    @Getter private View parentView;

    public View(IPositionProvider provider, Size size){
        this.positionProvider = provider;
        customSize = size;
    }
    
    public void setParentView(View view){
        var layout = view.getLayout();
        if (layout == null) return;
        
        view.getLayout().getChildren().add(getShape());
        parentView = view;
        
        var msg = MessageFormat.format("View ID {0} set parent {1}", 
                getID(), view.getID());
        Logger.log(msg, LogType.View);
    }
    
    public abstract Shape getShape();
    
    protected abstract void setupDebug();
}
