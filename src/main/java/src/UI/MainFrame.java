package src.UI;

import lombok.Getter;
import src.base.app.view.View;
import src.base.interfaces.IGameInstaller;
import src.base.interfaces.IInitializable;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame 
        implements IInitializable {
    
    public static final int cellSize = 100;
    public static final int infoPanelWidth = 400;
    public static final Color boardColor = new Color(88, 57, 39);

    private final IGameInstaller gameInstaller;
    private BoardView contentPanel;
    @Getter private GameInfoPanel infoPanel;

    @Override
    public void initialize() {
        var sideLength = gameInstaller.getSideLength();
        var width = sideLength.getX() * cellSize + infoPanelWidth;
        var height = sideLength.getY() * cellSize + gameInstaller.getYGap();
        setTitle("Ooga booga");
        setSize(width, height);
        setJMenuBar(new MenuBar());

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        createPanels();
        var timer = new Timer(10, e -> repaint());
        timer.start();
    }

    public MainFrame(IGameInstaller gameInstaller){
        this.gameInstaller = gameInstaller;
        
        var parentPane = getContentPane();
        
        parentPane.removeAll();
        parentPane.setBackground(boardColor);
        parentPane.setVisible(true);
        setLayout(new GridBagLayout());
        
        
    }
    
    public void addView(View view) {
        contentPanel.addView(view);
    }

    private void createPanels() {
        var parentPane = getContentPane();
        var c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 5, 0, 5);
        c.weightx = 0.6;
        c.weighty = 1;
        contentPanel = gameInstaller.getBoardView();
        parentPane.add(contentPanel, c);

        c.gridx = 1;
        c.weightx = 0.2;
        c.insets = new Insets(0, 0, 0, 5);
        infoPanel = gameInstaller.getGameInfoPanel();
        parentPane.add(infoPanel, c);
    }
}
