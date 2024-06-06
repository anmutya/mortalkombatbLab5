package characters.fabrics;

import characters.ActionType;
import characters.LiuKang;
import characters.Player;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @author Мария
 */
public class LiuKangFabric implements EnemyFabricInterface {

    @Override
    public Player create(int level) {
        Player enemy;
        Toolkit tk = Toolkit.getDefaultToolkit();
        enemy = new LiuKang(level, 70, 20, ActionType.HIT,
                new ImageIcon(getClass().getResource("/images/liukang.png")));
        return enemy;
    }
}