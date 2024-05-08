package src.UI;

import src.base.signal.SignalBus;
import src.base.signal.SignalListener;
import src.features.match.MatchService;
import src.features.match.Turn;
import src.features.player.PlayerModel;

import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;

public class InfoPanel extends JPanel {
    
    private PlayerModel playerA;
    private PlayerModel playerB;

    private final JTextField playerField;
    private final JTextField turnCounter;
    private final JTextField playerAScoreField;
    private final JTextField playerBScoreField;

    public InfoPanel() {
        var layout = new GridBagLayout();
        setLayout(layout);

        var c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(5, 5, 5, 5); // Add some spacing between components

        turnCounter = createTurnCounter(c);
        playerField = createTurnColorField(c);
        playerAScoreField = createPlayerAScore(c);
        playerBScoreField = createPlayerBScore(c);
    }

    public void initialize() {
        SignalBus.subscribe(new SignalListener<Turn>("new_turn", (turn -> {
            turnCounter.setText(MessageFormat.format("{0}", turn.id()));
            playerField.setText(MessageFormat.format("{0}", turn.playerModel().getPieceType()));
            playerAScoreField.setText(MessageFormat.format("{0}", playerA.getAttackCounter()));
            playerBScoreField.setText(MessageFormat.format("{0}", playerB.getAttackCounter()));
        })));
    }
    
    public void setPlayers(PlayerModel playerA, PlayerModel playerB){
        this.playerA = playerA;
        this.playerB = playerB;
    }
    
    private JTextField createPlayerBScore(GridBagConstraints c) {
        final JTextField playerBScoreField;
        var playerBInfo = new JTextField();
        playerBInfo.setText("Player B score:");
        playerBInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        playerBInfo.setEditable(false);
        c.gridx = 0; // Reset gridx to place the component in the first column
        c.gridy = 3; // Move to the next row
        add(playerBInfo, c);

        playerBScoreField = new JTextField();
        playerBScoreField.setText("0");
        playerBScoreField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        playerBScoreField.setEditable(false);
        c.gridx = 1; // Place the component in the next column
        add(playerBScoreField, c);
        return playerBScoreField;
    }

    private JTextField createPlayerAScore(GridBagConstraints c) {
        final JTextField playerAScoreField;
        var playerAInfo = new JTextField();
        playerAInfo.setText("Player A score:");
        playerAInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        playerAInfo.setEditable(false);
        c.gridx = 0; // Reset gridx to place the component in the first column
        c.gridy = 2; // Move to the next row
        add(playerAInfo, c);

        playerAScoreField = new JTextField();
        playerAScoreField.setText("0");
        playerAScoreField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        playerAScoreField.setEditable(false);
        c.gridx = 1; // Place the component in the next column
        add(playerAScoreField, c);
        return playerAScoreField;
    }

    private JTextField createTurnColorField(GridBagConstraints c) {
        final JTextField playerField;
        var playerInfo = new JTextField();
        playerInfo.setText("Player:");
        playerInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        playerInfo.setEditable(false);
        c.gridx = 0; // Reset gridx to place the component in the first column
        c.gridy = 1; // Move to the next row
        add(playerInfo, c);

        playerField = new JTextField();
        playerField.setText("Uh");
        playerField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        playerField.setEditable(false);
        c.gridx = 1; // Place the component in the next column
        add(playerField, c);
        return playerField;
    }

    private JTextField createTurnCounter(GridBagConstraints c) {
        final JTextField turnCounter;
        var turnInfo = new JTextField();
        turnInfo.setText("Turn:");
        turnInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        turnInfo.setEditable(false);
        add(turnInfo, c);

        turnCounter = new JTextField();
        turnCounter.setText("0");
        turnCounter.setFont(new Font("Tahoma", Font.PLAIN, 14));
        turnCounter.setEditable(false);
        c.gridx = 1; // Place the component in the next column
        add(turnCounter, c);
        return turnCounter;
    }
}
