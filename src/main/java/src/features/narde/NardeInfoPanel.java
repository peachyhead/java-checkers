package src.features.narde;

import src.UI.BaseInfoPanel;
import src.features.match.Turn;

import java.awt.*;

public class NardeInfoPanel extends BaseInfoPanel {

    @Override
    protected GridBagConstraints addComponents(GridBagConstraints consts) {
        return consts;
    }

    @Override
    protected void subscribeOnNewTurn(Turn turn) {
        
    }
}
