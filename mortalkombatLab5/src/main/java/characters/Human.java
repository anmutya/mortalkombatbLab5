package characters;

import javax.swing.*;

/**
 * @author Мария
 */

public class Human extends Player {

    private final int[] nextLevelExperience = {40, 90, 180, 260, 410, 1000, 100000};
    private int points;
    private int experience;
    private int nextExperience;

    public Human(int level, int health, int damage, ActionType attack, ImageIcon icon) {
        super(level, health, damage, attack, icon);
        this.points = 0;
        this.experience = 0;
        this.nextExperience = nextLevelExperience[0];
    }

    /**
     * Проверяет, достиг ли experience игрока значения nextExperience для перехода на следующий уровень,
     * и назначает новое значение параметру nextExperience в соответствии с полученным уровнем
     * @return true - если достиг нового уровня, false - если не достиг
     */
    public boolean checkNewLevel() {
        if (getExperience() >= getNextExperience()) {
            setLevel(getLevel() + 1);
            setNextExperience(nextLevelExperience[getLevel()]);
            return true;
        }
        return false;
    }

    /**
     * Метод, вызываемый при достижении игроком нового уровня, увеличивает выбранную игроком характеристикау
     * @param chosenCharacteristic - выбранная игроком характеристика
     */
    public void improveCharacteristic(int chosenCharacteristic) {
        if (chosenCharacteristic == 0) {
            setMaxHealth(getMaxHealth() + 20 + getLevel() * 5);
        } else if (chosenCharacteristic == 1) {
            setDamage(getDamage() + 3 + getLevel());
        }
        setHealth(getMaxHealth());
    }

    public int getPoints() {
        return this.points;
    }

    public int getExperience() {
        return this.experience;
    }

    public int getNextExperience() {
        return this.nextExperience;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setNextExperience(int nextExperience) {
        this.nextExperience = nextExperience;
    }

    @Override
    public String getName() {
        return "You";
    }
}