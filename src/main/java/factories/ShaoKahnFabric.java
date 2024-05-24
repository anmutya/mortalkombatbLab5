/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factories;

import an_mutya.Player;
import players.Baraka;
import players.ShaoKahn;

/**
 *
 * @author Мария
 */
public class ShaoKahnFabric implements EnemyFabricInterface{


    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new ShaoKahn(3, 100, 30, 1);
        return enemy;
    }
}
