package characters.fabrics;

import actions.ActionType;
import characters.LiuKang;
import characters.Player;

import javax.swing.*;
import java.awt.*;

/**
 * @author Мария
 */
public class LiuKangFabric implements EnemyFabricInterface {

    @Override
    public Player create() {
        Player enemy;
        Toolkit tk = Toolkit.getDefaultToolkit();
        enemy = new LiuKang(1, 70, 20, ActionType.HIT,
                new ImageIcon(getClass().getResource("/images/liukang.png")));
        return enemy;
    }
}