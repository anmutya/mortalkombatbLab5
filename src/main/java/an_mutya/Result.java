package an_mutya;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Мария
 */
public class Result {
    
    private String name;
    private int points;
    
    public Result(String n, int p){
        this.name=n;
        this.points=p;
    }
    
    public void setName(String name){
        this.name=name;
    }
    public void setPoints(int points){
        this.points=points;
    }
    
    public String getName(){
        return this.name;
    }
    public int getPoints(){
        return this.points;
    }
    
}
