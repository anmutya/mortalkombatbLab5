package characters.fabrics;

import actions.ActionType;
import characters.Player;
import characters.SubZero;

import javax.swing.*;

/**
 * @author Мария
 */
public class SubZeroFabric implements EnemyFabricInterface {

    @Override
    public Player create() {
        Player enemy;
        enemy = new SubZero(1, 60, 16, ActionType.HIT,
                new ImageIcon(getClass().getResource("/images/subzero.png")));
        return enemy;
    }
}