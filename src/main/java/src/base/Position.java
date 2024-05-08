package src.base;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.text.MessageFormat;

public class Position {
    public static String positionChangeProperty = "pos_change";
    @Getter @Setter
    private int x;
    @Getter @Setter
    private int y;
    
    private final Action onPositionChange = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    };

    public Position(){
        x = 0;
        y = 0;
    }

    public Position(int x, int y){
        setPosition(x, y);
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        onPositionChange.putValue(positionChangeProperty, this);
    }

    @Override
    public String toString(){
        return MessageFormat.format("({0},{1})", x, y);
    }
    
    public boolean equals(Position position){
        if (position == null) return false;

        return position.getX() == x && position.getY() == y;
    }
    
    public void subscribe(PropertyChangeListener listener){
        onPositionChange.addPropertyChangeListener(listener);
    }
}
