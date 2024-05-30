package actions;

import characters.ActionType;
import characters.Player;
import gameComponents.Game;

public class DefenceAction extends GameAction{

    public void performAction(Game game) {
        if (enemyMoves.isEmpty()) {
            setEnemyMoves(game);
        }
        game.getCurrentEnemy().setAttack(enemyMoves.pollFirst());
        if (game.getPlayerTurn()) {
            playerDefenceInHisTurn(game);
        } else {
            playerDefenceInEnemyTurn(game);
        }
        endRoundCheck(game);
    }
    private boolean checkOneOfPlayersIsStunnedByBlock(Game game) {
        Player human = game.getHuman();
        Player enemy = game.getEnemies().get(game.getRound());
        boolean isStunned = false;
        if (human.isStunned()) {
            humanIsStunned(game, human, enemy);
            isStunned = true;
        } else if (enemy.isStunned()) {
            game.setTurnResult(enemy.getName() + " are stunned, you didn't attack");
            enemy.setHealth(enemy.getHealth() - human.getDamage());
            enemy.setStunned(false);
            isStunned = true;
        }
        return isStunned;
    }

    /**
     * Метод, вызывающийся при выборе кнопки "защититься" в ход игрока (игорок ходит - противкник отвечает)
     * В зависимости от действия противника (enemy.getAttack) определяет исход защиты игрока
     *
     * @param game объект текущей игры
     */
    private void playerDefenceInHisTurn(Game game) {
        playerMovesInHisTurn.add(ActionType.BLOCK.ordinal());
        Player enemy = game.getEnemies().get(game.getRound());
        if (!checkOneOfPlayersIsStunnedByBlock(game)) {
            switch (enemy.getAttack()) {
                case BLOCK -> {
                    game.setTurnResult("Both defended himself");
                    if (Math.random() > 0.50) {
                        enemy.setStunned(true);
                    }
                }
                case HIT -> game.setTurnResult("Nothing happened");
                case TREATMENT -> {
                    game.setTurnResult(enemy.getName() + " are healing!");
                    enemy.setHealth(enemy.getHealth() + (enemy.getMaxHealth() - enemy.getHealth()) / 2);
                }
            }
        }
    }

    /**
     * Метод, вызывающийся при выборе кнопки "защититься" в ответ на ход протвника(противник ходит - игорк отвечает)
     * В зависимости от действия противника (enemy.getAttack) определяет исход защиты игрока
     *
     * @param game объект текущей игры
     */
    private void playerDefenceInEnemyTurn(Game game) {
        playerMovesInEnemyTurn.add(ActionType.BLOCK.ordinal());
        Player human = game.getHuman();
        Player enemy = game.getEnemies().get(game.getRound());
        if (!checkOneOfPlayersIsStunnedByBlock(game)) {
            switch (enemy.getAttack()) {
                case BLOCK -> {
                    game.setTurnResult("Both defended himself");
                    if (Math.random() > 0.50) {
                        human.setStunned(true);
                    }
                }
                case HIT -> {
                    enemy.setHealth(enemy.getHealth() - (human.getDamage() / 2));
                    game.setTurnResult("You counterattacked");
                }
                case TREATMENT -> {
                    game.setTurnResult(enemy.getName() + " are healing!");
                    enemy.setHealth(enemy.getHealth() + (enemy.getMaxHealth() - enemy.getHealth()) / 2);
                }
            }
        }
    }
}
