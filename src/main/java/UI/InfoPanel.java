package UI;

import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import base.signal.SignalBus;
import base.signal.SignalListener;
import features.match.Turn;
import features.player.PlayerModel;

import java.text.MessageFormat;

public abstract class InfoPanel extends Rectangle {
    protected PlayerModel playerA;
    protected PlayerModel playerB;
    
    protected final Text playerField;
    protected final Text turnCounter;
    
    protected final HBox layout;
    protected final Font regularFont;
    //private final Toggle debugButton;

    public InfoPanel() {
        layout = new HBox();
        regularFont = new javafx.scene.text.Font("Tahoma", 14);

        createText("Turn:");
        turnCounter = createText("0");
        createText("Player:");
        playerField = createText("Uh");
        
        addComponents();
    }
    
    protected abstract void addComponents();
    
    public void initialize(){
        SignalBus.subscribe(new SignalListener<Turn>("new_turn", (turn -> {
            turnCounter.setText(MessageFormat.format("{0}", turn.id()));
            playerField.setText(MessageFormat.format("{0}", turn.playerModel().getPieceType()));
            subscribeOnNewTurn(turn);
        })));

        //var viewStorage = StorageKeeper.getStorage(TileView.class);
        //debugButton.addActionListener(e -> viewStorage.getCollection().forEach(view -> 
        //        view.showDebug(debugButton.isSelected())));
    }
    
    protected abstract void subscribeOnNewTurn(Turn turn);
    
    protected Text createText(String text) {
        var turnInfo = new Text();
        turnInfo.setText(text);
        turnInfo.setFont(regularFont);
        layout.getChildren().add(turnInfo);
        return turnCounter;
    }

    public void setPlayers(PlayerModel playerA, PlayerModel playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }
    
    public abstract int getDesiredWidth();
}
