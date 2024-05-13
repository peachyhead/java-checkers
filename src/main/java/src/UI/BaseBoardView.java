package src.UI;

import src.base.app.view.View;

import javax.swing.*;
import java.awt.*;

public abstract class BaseBoardView extends JPanel {
    public final int cellSize = 100;
    
    public void addView(View view) {
        var size = view.getCustomSize();
        add(view);
        view.setPreferredSize(new Dimension(size.getX(), size.getY()));
        revalidate();
    }
}
