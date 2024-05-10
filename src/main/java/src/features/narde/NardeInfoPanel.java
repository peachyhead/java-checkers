package src.features.narde;

import src.UI.GameInfoPanel;
import src.features.match.Turn;

import java.awt.*;

public class NardeInfoPanel extends GameInfoPanel {

    @Override
    protected GridBagConstraints addComponents(GridBagConstraints consts) {
        return consts;
    }

    @Override
    protected void subscribeOnNewTurn(Turn turn) {
        
    }
}
