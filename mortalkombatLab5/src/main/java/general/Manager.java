package general;

import gameComponents.Game;
import characters.Human;
import characters.Player;

import javax.swing.table.DefaultTableModel;

public class Manager {
    private Game game;
    public Manager() {
    }

    public void setLocationForGame(int location) {
        game = new Game(location);
    }
    public Game getGame() {
        return game;
    }
    public void attack() {
        game.attack(game);
    }
    public void defence() {
        game.defence(game);
    }

    public Player getEnemy(){
        return game.getEnemies().get(game.getRound());
    }
    public Human getPlayer(){
        return game.getHuman();
    }
}
