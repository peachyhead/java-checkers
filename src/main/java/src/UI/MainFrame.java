package src.UI;

import lombok.Getter;
import src.base.Size;
import src.base.app.view.View;
import src.base.interfaces.IInitializable;

import javax.swing.*;
import java.awt.*;

public class MainFrame 
        extends JFrame 
        implements IInitializable {
    public static final int cellSize = 100;
    public static final int infoPanelWidth = 400;
    private JPanel contentPanel;
    @Getter
    private InfoPanel infoPanel;

    public MainFrame(Size sideLength){
        Container parentPane = getContentPane();
        parentPane.removeAll();
        parentPane.setBackground(new Color(88,57,39));
        parentPane.setVisible(true);
        setLayout(new GridBagLayout());
        
        setTitle("Ooga booga");
        setSize(sideLength.getX() * cellSize + infoPanelWidth, 
                sideLength.getY() * cellSize + 50);
        setJMenuBar(new MenuBar());

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        createPanels(sideLength);
    }
    
    public void addView(View view){
        var size = view.getCustomSize();
        contentPanel.add(view);
        view.setPreferredSize(new Dimension(size.getX(), size.getY()));
        contentPanel.revalidate();
    }

    @Override
    public void initialize() {
        var timer = new Timer(10, e -> repaint());
        timer.start();
    }

    private void createPanels(Size sideLength) {
        var parentPane = getContentPane();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(sideLength.getX(),sideLength.getY()));
        contentPanel.setBackground(Color.GRAY);
        parentPane.add(contentPanel, c);

        c.gridx = 1;
        c.weightx = 1;
        infoPanel = new InfoPanel();
        infoPanel.setBackground(Color.GRAY);
        infoPanel.initialize();
        parentPane.add(infoPanel, c);
    }
}
