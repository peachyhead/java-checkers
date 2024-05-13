package src.features.checker;

import src.UI.BaseInfoPanel;
import src.features.match.Turn;

import javax.swing.*;
import java.awt.*;

public class CheckerInfoPanel extends BaseInfoPanel {

    private JTextField playerAField;
    private JTextField playerBField;
    
    @Override
    protected GridBagConstraints addComponents(GridBagConstraints consts) {
        consts.gridx = 0;
        consts.gridy += 1;
        createField("Player A", consts);
        consts.gridx = 1;
        playerAField = createField("0", consts);

        consts.gridx = 0;
        consts.gridy += 1;
        createField("Player B", consts);
        consts.gridx = 1;
        playerBField = createField("0", consts);
        return consts;
    }

    public void subscribeOnNewTurn(Turn turn) {
        if (playerA != null)
            playerAField.setText("%s".formatted(playerA.getAttackCounter()));
        if (playerB != null)
            playerBField.setText("%s".formatted(playerB.getAttackCounter()));
    }
}
