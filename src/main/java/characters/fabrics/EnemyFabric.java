package characters.fabrics;

import characters.Player;

/**
 * @author Мария
 */
public class EnemyFabric {

    /**
     * Метод, генерирующий случайного врага, характеристики которого зависят от уровня игрока
     * @return объект Player, являющийся врагом
     */
    public static Player createEnemy() {
        EnemyFabricInterface fabric = null;
        int i = (int) (Math.random() * 3);
        switch (i) {
            case 0 -> fabric = new BarakaFabric();
            case 1 -> fabric = new SubZeroFabric();
            case 2 -> fabric = new LiuKangFabric();
        }
        return fabric.create();
    }

    /**
     * Метод, генерирующий случайного босса, характеристики которого зависят от уровня игрока
     * @return объект Player, являющийся врагом
     */
    public static Player createBoss() {
        EnemyFabricInterface fabric = null;
        int i = (int) (Math.random() * 2);
        switch (i) {
            case 0 -> fabric = new ShaoKahnFabric();
            case 1 -> fabric = new SonyaBladeFabric();
        }
        return fabric.create();
    }
}