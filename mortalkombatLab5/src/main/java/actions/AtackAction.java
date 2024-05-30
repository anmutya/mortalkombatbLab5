package actions;

import characters.ActionType;
import characters.Player;
import gameComponents.Game;

public class AtackAction extends GameAction {

    public void performAction(Game game) {
        if (enemyMoves.isEmpty()) {
            setEnemyMoves(game);
        }
        game.getCurrentEnemy().setAttack(enemyMoves.pollFirst());
        if (game.getPlayerTurn()) {
            playerAttackInHisTurn(game);
        } else {
            playerAttackInEnemyTurn(game);
        }
        endRoundCheck(game);
    }

    /**
     * Метод, вызывающийся при выборе кнопки "атаковать" (игорок ходит - противкник отвечает)
     * В зависимости от действия противника (enemy.getAttack) определяет исход атаки игрока
     *
     * @param game объект текущей игры
     */
    private void playerAttackInHisTurn(Game game) {
        playerMovesInHisTurn.add(ActionType.HIT.ordinal());
        Player human = game.getHuman();
        Player enemy = game.getEnemies().get(game.getRound());
        if (!checkOneOfPlayersIsStunnedByHit(game)) {
            switch (enemy.getAttack()) {
                case BLOCK -> {
                    if (enemy.isDebuffed()) {
                        human.setHealth(human.getHealth() - (enemy.getDamage() / 4));
                        game.setTurnResult(enemy.getName() + " counterattacked, but debuffed");
                        changeDebuffLevel(enemy);
                    } else {
                        human.setHealth(human.getHealth() - (enemy.getDamage() / 2));
                        game.setTurnResult(enemy.getName() + " counterattacked");
                    }
                }
                case HIT -> {
                    if (enemy.isDebuffed()) {
                        enemy.setHealth((int) (enemy.getHealth() - human.getDamage() * 1.25));
                        changeDebuffLevel(enemy);
                    } else {
                        enemy.setHealth(enemy.getHealth() - human.getDamage());
                        game.setTurnResult("You attacked");
                    }
                }
                case TREATMENT -> {
                    game.setTurnResult(enemy.getName() + " try to heal, but you attack him!");
                    enemy.setHealth(enemy.getHealth() - (human.getDamage() * 2));
                }
            }
        }
    }
    /**
     * Метод, вызывающийся при выборе кнопки "атаковать" в ответ на ход противника (противник ходит - игорк отвечает)
     * В зависимости от действия противника (enemy.getAttack) определяет исход атаки игрока
     *
     * @param game объект текущей игры
     */
    private void playerAttackInEnemyTurn(Game game) {
        playerMovesInEnemyTurn.add(ActionType.HIT.ordinal());
        Player human = game.getHuman();
        Player enemy = game.getEnemies().get(game.getRound());
        if (!checkOneOfPlayersIsStunnedByHit(game)) {
            switch (enemy.getAttack()) {
                case BLOCK -> game.setTurnResult(enemy.getName() + "didn't attack");
                case HIT -> {
                    if (enemy.isDebuffed()) {
                        human.setHealth(human.getHealth() - enemy.getDamage() / 2);
                        game.setTurnResult(enemy.getName() + " attacked, but debuffed");
                        changeDebuffLevel(enemy);
                    } else {
                        human.setHealth(human.getHealth() - enemy.getDamage());
                        game.setTurnResult(enemy.getName() + " attacked");
                    }

                }
                case TREATMENT -> {
                    game.setTurnResult(enemy.getName() + " try to heal, but you attack him!");
                    enemy.setHealth(enemy.getHealth() - (human.getDamage() * 2));
                }
            }
        }
    }
    /**
     * Метод, определяющий, находится ли сейчас один из игроков в "стане" при варианте "атаки" игрока
     * и возвращающий true - если кто-то находится в стане и false в противном случае
     *
     * @param game объект текущей игры
     */
    private boolean checkOneOfPlayersIsStunnedByHit(Game game) {
        Player human = game.getHuman();
        Player enemy = game.getEnemies().get(game.getRound());
        boolean isStunned = false;
        if (human.isStunned()) {
            humanIsStunned(game, human, enemy);
            isStunned = true;
        } else if (enemy.isStunned()) {
            game.setTurnResult(enemy.getName() + " are stunned, you attacked");
            enemy.setHealth(enemy.getHealth() - human.getDamage());
            enemy.setStunned(false);
            isStunned = true;
        }
        return isStunned;
    }
}
