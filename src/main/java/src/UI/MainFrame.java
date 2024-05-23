package src.UI;

import src.base.app.view.View;
import src.base.interfaces.IViewStrategy;
import src.base.interfaces.IInitializable;
import src.features.match.GameResolver;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame 
        implements IInitializable {
    
    public static final Color boardColor = new Color(88, 57, 39);

    private IViewStrategy viewStrategy;

    @Override
    public void initialize() {
        viewStrategy = GameResolver.getViewStrategy();
        var windowSize = viewStrategy.getWindowSize();
        setTitle("Ooga booga");
        setSize(windowSize.getX(), windowSize.getY());

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        var timer = new Timer(10, e -> repaint());
        timer.start();
    }

    public MainFrame(){
        var parentPane = getContentPane();
        parentPane.removeAll();
        parentPane.setBackground(boardColor);
        parentPane.setVisible(true);
        setLayout(new GridBagLayout());
    }
    
    public void addView(View view) {
        viewStrategy.getBoardView().addView(view);
    }

    public void createPanels() {
        var parentPane = getContentPane();
        var c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 5, 0, 5);
        c.weightx = 0.6;
        c.weighty = 1;
        parentPane.add(viewStrategy.getBoardView(), c);

        c.gridx = 1;
        c.weightx = 0.2;
        c.insets = new Insets(0, 0, 0, 5);
        parentPane.add(viewStrategy.getInfoPanel(), c);
    }
}
