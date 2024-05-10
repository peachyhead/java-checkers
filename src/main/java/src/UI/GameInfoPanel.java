package src.UI;

import src.base.app.storage.StorageKeeper;
import src.base.signal.SignalBus;
import src.base.signal.SignalListener;
import src.features.board.tile.TileView;
import src.features.match.Turn;
import src.features.player.PlayerModel;

import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;

public abstract class GameInfoPanel extends JPanel {
    protected PlayerModel playerA;
    protected PlayerModel playerB;
    
    protected final JTextField playerField;
    protected final JTextField turnCounter;
    private final JToggleButton debugButton;

    public GameInfoPanel() {
        var layout = new GridBagLayout();
        setLayout(layout);

        var c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(5, 5, 5, 5); // Add some spacing between components
        c.gridy = 0;
        c.weightx = 0.5;

        turnCounter = createTurnCounter(c);
        playerField = createTurnColorField(c);
        
        c.weightx = 0.5;
        c = addComponents(c);

        c.gridy += 1;
        c.weightx = 1;
        debugButton = new JToggleButton("Show debug");
        add(debugButton, c);
    }
    
    protected abstract GridBagConstraints addComponents(GridBagConstraints consts);
    
    public void initialize(){
        SignalBus.subscribe(new SignalListener<Turn>("new_turn", (turn -> {
            turnCounter.setText(MessageFormat.format("{0}", turn.id()));
            playerField.setText(MessageFormat.format("{0}", turn.playerModel().getPieceType()));
            subscribeOnNewTurn(turn);
        })));

        var viewStorage = StorageKeeper.getStorage(TileView.class);
        debugButton.addActionListener(e -> viewStorage.getCollection().forEach(view -> 
                view.showDebug(debugButton.isSelected())));
    }
    
    protected abstract void subscribeOnNewTurn(Turn turn);
    
    private JTextField createTurnColorField(GridBagConstraints c) {
        final JTextField playerField;
        var playerInfo = new JTextField();
        playerInfo.setText("Player:");
        playerInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        playerInfo.setEditable(false);
        c.gridx = 0; // Reset gridx to place the component in the first column
        c.gridy += 1; // Move to the next row
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

    public void setPlayers(PlayerModel playerA, PlayerModel playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }
}
