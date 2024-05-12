package features.checker;

import UI.InfoPanel;
import features.match.Turn;
import javafx.scene.text.Text;

public class CheckerInfoPanel extends InfoPanel {

    private Text playerAScoreField;
    private Text playerBScoreField;
    
    @Override
    protected void addComponents() {
        playerAScoreField = createPlayerScore("Player A");
        playerBScoreField = createPlayerScore("Player B");
    }

    public void subscribeOnNewTurn(Turn turn) {
        if (playerA != null)
            playerAScoreField.setText("%s".formatted(playerA.getAttackCounter()));
        if (playerB != null)
            playerBScoreField.setText("%s".formatted(playerB.getAttackCounter()));
    }

    @Override
    public int getDesiredWidth() {
        return 400;
    }

    private Text createPlayerScore(String player) {
        createText(player + " score:");
        return createText("0");
    }
}
