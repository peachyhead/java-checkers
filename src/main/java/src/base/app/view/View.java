package src.base.app.view;

import lombok.Getter;
import lombok.Setter;
import src.base.log.LogType;
import src.base.log.Logger;
import src.base.Size;
import src.base.app.storage.IStorageItem;
import src.base.interfaces.IPositionProvider;

import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;

public abstract class View extends JPanel implements IStorageItem {
    @Getter private final IPositionProvider positionProvider;
    @Setter @Getter 
    private Size customSize;
    @Setter protected Color color;

    public View(IPositionProvider provider){
        this.positionProvider = provider;
        customSize = new Size();
    }

    public View(IPositionProvider provider, Size size){
        this.positionProvider = provider;
        customSize = size;
    }

    public void setParent(JPanel container){
        var currentParent = container.getParent();
        if (currentParent != null)
            currentParent.remove(this);
        container.add(this);
        
        var parentID = container.getClass().getSimpleName();
        if (container instanceof View)
            parentID = ((View) container).getID();
        var msg = MessageFormat.format("View ID {0} set parent {1}", getID(), parentID);
        Logger.log(msg, LogType.View);
    }
    
    protected abstract void setupDebug();
    
    @Override
    public void paintComponent(Graphics g) {
        var g2d = (Graphics2D) g.create();
        drawGraphics(g2d);
        g2d.dispose();
//        var msg = MessageFormat.format("Paint view with ID {0}", getID());
//        Logger.log(msg, LogType.View);
    }
    
    protected abstract void drawGraphics(Graphics2D g);
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(customSize.getX(), customSize.getY());
    }
}
