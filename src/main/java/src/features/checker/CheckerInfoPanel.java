package src.features.checker;

import src.UI.GameInfoPanel;
import src.features.match.Turn;

import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;

public class CheckerInfoPanel extends GameInfoPanel {

    private JTextField playerAScoreField;
    private JTextField playerBScoreField;


    @Override
    protected GridBagConstraints addComponents(GridBagConstraints consts) {
        playerAScoreField = createPlayerAScore(consts);
        playerBScoreField = createPlayerBScore(consts);
        return consts;
    }

    public void subscribeOnNewTurn(Turn turn) {
        playerAScoreField.setText(MessageFormat.format("{0}", playerA.getAttackCounter()));
        playerBScoreField.setText(MessageFormat.format("{0}", playerB.getAttackCounter()));
    }
    
    private JTextField createPlayerBScore(GridBagConstraints c) {
        final JTextField playerBScoreField;
        var playerBInfo = new JTextField();
        playerBInfo.setText("Player B score:");
        playerBInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        playerBInfo.setEditable(false);
        c.gridx = 0;
        c.gridy += 1;
        add(playerBInfo, c);

        playerBScoreField = new JTextField();
        playerBScoreField.setText("0");
        playerBScoreField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        playerBScoreField.setEditable(false);
        c.gridx = 1;
        add(playerBScoreField, c);
        return playerBScoreField;
    }

    private JTextField createPlayerAScore(GridBagConstraints c) {
        final JTextField playerAScoreField;
        var playerAInfo = new JTextField();
        playerAInfo.setText("Player A score:");
        playerAInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        playerAInfo.setEditable(false);
        c.gridx = 0;
        c.gridy = 2;
        add(playerAInfo, c);

        playerAScoreField = new JTextField();
        playerAScoreField.setText("0");
        playerAScoreField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        playerAScoreField.setEditable(false);
        c.gridx = 1;
        add(playerAScoreField, c);
        return playerAScoreField;
    }
}
