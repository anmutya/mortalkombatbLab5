package characters.fabrics;

import characters.ActionType;
import characters.Player;
import characters.SonyaBlade;

import javax.swing.*;

/**
 * @author Мария
 */
public class SonyaBladeFabric implements EnemyFabricInterface {

    @Override
    public Player create(int level) {
        Player enemy =  new SonyaBlade(level, 80, 16, ActionType.HIT,
                new ImageIcon(getClass().getResource("/images/sonya blade.png")));
        enemy.setBoss(true);
        return enemy;
    }
}