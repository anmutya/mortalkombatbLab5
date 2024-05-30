package characters;
import actions.ActionType;

import javax.swing.*;

/**
 * @author Мария
 */
public class Player {

    private int level;
    private int health;
    private int maxHealth;
    private int damage;
    private ActionType attack;
    private ImageIcon icon;
    private boolean isStunned;
    private boolean isBoss = false;
    private boolean debuffed = false;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public ActionType getAttack() {
        return attack;
    }

    public void setAttack(ActionType attack) {
        this.attack = attack;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public void setBoss(boolean boss) {
        isBoss = boss;
    }

    public boolean isStunned() {
        return isStunned;
    }

    public void setStunned(boolean stunned) {
        isStunned = stunned;
    }

    public boolean isDebuffed() {
        return debuffed;
    }

    public void setDebuffed(boolean debuffed) {
        this.debuffed = debuffed;
    }

    private int debufferedCount;

    public int getDebufferedCount() {
        return debufferedCount;
    }

    public void setDebufferedCount(int debufferedCount) {
        this.debufferedCount = debufferedCount;
    }

    public Player(int level, int health, int damage, ActionType attack, ImageIcon icon) {
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.attack = attack;
        this.maxHealth = health;
        this.icon = icon;
    }

    public String getName() {
        return "";
    }
}