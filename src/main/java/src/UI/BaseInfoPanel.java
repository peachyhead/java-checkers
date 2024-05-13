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

public abstract class BaseInfoPanel extends JPanel {
    public final int panelWidth = 400;
    
    protected PlayerModel playerA;
    protected PlayerModel playerB;
    
    private final Font regularFont = new Font("Tahoma", Font.PLAIN, 14);
    
    protected final JTextField playerField;
    protected final JTextField turnField;
    private final JToggleButton debugButton;

    public BaseInfoPanel() {
        var layout = new GridBagLayout();
        setLayout(layout);

        var c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridy = 0;
        c.weightx = 0.5;

        createField("Turn:", c);
        c.gridx = 1;
        turnField = createField("0", c);
        
        c.gridx = 0;
        c.gridy += 1;
        createField("Player:", c);
        c.gridx = 1;
        playerField = createField("Uh", c);
        
        c.weightx = 0.5;
        c = addComponents(c);

        c.gridy += 1;
        c.weightx = 1;
        debugButton = new JToggleButton("Show debug");
        add(debugButton, c);
    }
    
    protected abstract GridBagConstraints addComponents(GridBagConstraints consts);
    
    public void initialize(PlayerModel playerA, PlayerModel playerB){
        setPlayers(playerA, playerB);
        
        SignalBus.subscribe(new SignalListener<Turn>("new_turn", (turn -> {
            turnField.setText(MessageFormat.format("{0}", turn.id()));
            playerField.setText(MessageFormat.format("{0}", turn.playerModel().getPieceType()));
            subscribeOnNewTurn(turn);
        })));

        var viewStorage = StorageKeeper.getStorage(TileView.class);
        debugButton.addActionListener(e -> viewStorage.getCollection().forEach(view -> 
                view.showDebug(debugButton.isSelected())));
    }
    
    protected abstract void subscribeOnNewTurn(Turn turn);
    
    protected JTextField createField(String text, GridBagConstraints c) {
        var field = new JTextField();
        field.setText(text);
        field.setFont(regularFont);
        field.setEditable(false);
        add(field, c);
        return field;
    }
    
    private void setPlayers(PlayerModel playerA, PlayerModel playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }
}
