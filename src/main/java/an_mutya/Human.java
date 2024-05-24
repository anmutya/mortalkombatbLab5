/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package an_mutya;

/**
 *
 * @author Мария
 */
public class Human extends Player{
    private int points;
    private int experience;
    private int win;
    private int nextexperience;
    private int locations_number;
    private int current_round = 0;

    public Human(int level, int health, int  damage, int attack, int locations){
        super(level, health, damage, attack);
        this.points = 0;
        this.experience = 0;
        this.nextexperience = 40;
        this.win = 0;
        this.locations_number = locations;
        this.current_round = 0;

    }

    public void incrementCurrentRound(){
        this.current_round ++;
    }
    public void setZeroRound(){
        this.current_round = 0;
    }
    public int getCurrent_round() {
        return current_round;
    }
    public int getLocations_number() {
        return locations_number;
    }
    public void setLocations_number(int locations_number) {
        this.locations_number = locations_number;
    }
    public int getPoints(){
        return this.points;
    }
    public int getExperience(){
        return this.experience;
    }
    public int getNextExperience(){
        return this.nextexperience;
    }
    public int getWin(){
        return this.win;
    }
    public void addPoints(int p){
        this.points += p;
    }
    public void addExperience(int e){
        this.experience += e;
    }
    public void setNextExperience(int e){
        this.nextexperience = e;
    }
    public void addWin(){
        this.win++;
    }

    @Override
    public String getName(){
        return "You";
    }
}
