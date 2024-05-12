package features.narde;

import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class TileLayout implements LayoutManager {

    @Setter private int yDirection;
    
    @Override
    public void addLayoutComponent(String name, Component comp) {}
    @Override
    public void removeLayoutComponent(Component comp) {}

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return new Dimension(100, 100); // Adjust as needed
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(100, 100); // Adjust as needed
    }

    @Override
    public void layoutContainer(Container parent) {
        int totalComponents = parent.getComponentCount();
        int parentWidth = parent.getWidth();
        int parentHeight = parent.getHeight();
        if (totalComponents == 0) return;

        // Set the parent container to not paint its background
        if (parent instanceof JComponent) {
            ((JComponent) parent).setOpaque(false);
        }
        parent.setBackground(new Color(0, 0, 0, 0));

        // Calculate the initial y-coordinate for the first component to be centered vertically
        int y = (parentHeight - parent.getComponent(0).getPreferredSize().height) / 2;

        for (Component comp : parent.getComponents()) {
            var compressionFactor = Math.max(1f - 0.05f * totalComponents, 0.1f);
            int compWidth = comp.getPreferredSize().width;
            int compHeight = comp.getPreferredSize().height;

            // Calculate the x-coordinate to center the component horizontally
            int x = (parentWidth - compWidth) / 2;

            // Set the bounds for the component
            comp.setBounds(x, y, compWidth, compHeight);

            // Update the y-coordinate for the next component
            y += (int) (compHeight * compressionFactor * yDirection);

            // Set the child component to not paint its background
            if (comp instanceof JComponent) {
                ((JComponent) comp).setOpaque(false);
            }
        }
    }

}