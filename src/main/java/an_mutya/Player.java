package an_mutya;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.ImageIcon;

/**
 *
 * @author Мария
 */
public class Player {
    private int level;
    private int health;
    private int maxhealth;
    private int damage;
    private int attack;
    private boolean recoveryAttempt;
    private ImageIcon icon;

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public Player(int level, int health, int damage, int attack){
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.attack = attack;
        this.maxhealth = health;
    }
    public void incrementLevel(){
        this.level++;
    }
    public void addHealth(int h){
        this.health += h;
    }
    public void setHealth(int h){
        this.health = h;
    }
    public void addDamage(int d){
        this.damage += d;
    }
    public void setAttack(int a){
        this.attack = a;
    }
    public void addMaxHealth(int h){
        this.maxhealth += h;
    }
    public int getLevel(){
        return this.level;
    }
    public int getHealth(){
        return this.health;
    }
    public int getDamage(){
        return this.damage;
    }
    public int getAttack(){
        return this.attack;
    }
    public int getMaxHealth(){
        return this.maxhealth;
    }
    public String getName(){
        return "";
    }
    public boolean getRecoveryAttempt() {
        return recoveryAttempt;
    }
    public void setRecoveryAttempt(boolean recoveryAttempt) {
        this.recoveryAttempt = recoveryAttempt;
    }
}
