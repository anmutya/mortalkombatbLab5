package characters.fabrics;

import characters.ActionType;
import characters.Player;
import characters.ShaoKahn;

import javax.swing.*;

/**
 * @author Мария
 */
public class ShaoKahnFabric implements EnemyFabricInterface {

    @Override
    public Player create() {
        Player enemy =  new ShaoKahn(3, 100, 30, ActionType.HIT,
                new ImageIcon(getClass().getResource("/images/shao kahn.png")));
        enemy.setBoss(true);
        return enemy;
    }
}