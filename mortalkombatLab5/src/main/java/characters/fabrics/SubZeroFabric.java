package characters.fabrics;

import characters.ActionType;
import characters.Player;
import characters.SubZero;

import javax.swing.*;

/**
 * @author Мария
 */
public class SubZeroFabric implements EnemyFabricInterface {

    @Override
    public Player create(int level) {
        Player enemy;
        enemy = new SubZero(level, 60, 16, ActionType.HIT,
                new ImageIcon(getClass().getResource("/images/subzero.png")));
        return enemy;
    }
}