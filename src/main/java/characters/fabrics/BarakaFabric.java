package characters.fabrics;

import actions.ActionType;
import characters.Baraka;
import characters.Player;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 *
 * @author Мария
 */
public class BarakaFabric implements EnemyFabricInterface {

    @Override
    public Player create() {
        Player enemy;
        URL iconUrl = this.getClass().getResource("/images/baraka.png");
        Toolkit tk = Toolkit.getDefaultToolkit();
        enemy = new Baraka(1, 100, 12, ActionType.HIT,
                new ImageIcon(tk.getImage(iconUrl)));
        return enemy;
    }
}