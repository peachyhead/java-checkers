package base;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class Size {
    public static String sizeChangeProperty = "size_change";
    @Getter @Setter
    private int x;
    @Getter @Setter
    private int y;

    private final Action onSizeChange = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    };

    public Size(){
        x = 0;
        y = 0;
    }

    public Size(int x, int y){
        setSize(x, y);
    }

    public void setSize(int x, int y){
        this.x = x;
        this.y = y;
        onSizeChange.putValue(sizeChangeProperty, this);
    }

    public void subscribe(PropertyChangeListener listener){
        onSizeChange.addPropertyChangeListener(listener);
    }
}
