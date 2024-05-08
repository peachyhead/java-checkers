package src.features.match;

import lombok.Getter;
import lombok.Setter;
import src.features.player.PlayerModel;

import java.util.Objects;

public final class Turn {
    private final int id;
    private final PlayerModel playerModel;
    @Getter @Setter
    private boolean succeed;

    public Turn(int id, PlayerModel playerModel) {
        this.id = id;
        this.playerModel = playerModel;
    }

    public int id() {
        return id;
    }

    public PlayerModel playerModel() {
        return playerModel;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Turn) obj;
        return this.id == that.id &&
                Objects.equals(this.playerModel, that.playerModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, playerModel);
    }

    @Override
    public String toString() {
        return "Turn[" +
                "id=" + id + ", " +
                "playerModel=" + playerModel + ']';
    }
}
