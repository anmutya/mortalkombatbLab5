package actions;

import characters.ActionType;
import characters.Player;
import gameComponents.Game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GameAction {

    public LinkedList<ActionType> enemyMoves = new LinkedList<>();
    public ArrayList<Integer> playerMovesInHisTurn = new ArrayList<>();
    public ArrayList<Integer> playerMovesInEnemyTurn = new ArrayList<>();

    public void defence(DefenceAction action, Game game) {
        action.performAction(game);
    }
    public void attack(AtackAction action, Game game) {
        action.performAction(game);
    }
    public void debuff(DebuffAction action, Game game) {
        action.performAction(game);
    }

    /**
     * Метод, определяющий поведение противника на следующий ход врага и свой ход
     * Поведение определяется только на 2 хода, после чего переопределяется
     * @param game объект текущей игры
     */
    public void setEnemyMoves(Game game) {
        enemyMoves.add(setEnemyMoveForHumanTurn(game));
        enemyMoves.add(setEnemyMoveForEnemyTurn(game));
    }
    /**
     * Метод, определяющий поведение противника на следующий ход игрока
     * Отталкивается от предыдущих действий игрока в ход игрока
     * Если игрок в свой ход чаще выбирал защиту - противник будет атаковать, чтобы не получать "стан"
     * Если игрок чаще выбирал атаку - противник будет защищаться для контратаки
     * При равных значениях противник выбирает действие случайно
     * Для босса случайным образом (15%) может определиться действие лечения
     *
     * @param game объект текущей игры
     * @return действие противника: 0 - защита, 1 - атака, 2 (для босса) - попытка лечения
     */
    public ActionType setEnemyMoveForHumanTurn(Game game) {
        ActionType enemyMove = setEnemyMoveForTreatmentAndDispute(game);
        if (enemyMove == null) {
            double movesSum = 0;
            for (int move : playerMovesInEnemyTurn) {
                movesSum += move;
            }
            if (movesSum / playerMovesInHisTurn.size() > 0.5) {
                enemyMove = ActionType.BLOCK;
            } else enemyMove = ActionType.HIT;
        }
        return enemyMove;
    }

    public ActionType setEnemyMoveForTreatmentAndDispute(Game game) {
        double movesSum = 0;
        ActionType enemyMove = null;
        Random rand = new Random();
        ActionType[] moves = new ActionType[]{ActionType.BLOCK, ActionType.HIT};

        if (game.getCurrentEnemy().isBoss() && Math.random() > 0.85) {
            enemyMove = ActionType.TREATMENT;
        }
        for (int move : playerMovesInEnemyTurn) {
            movesSum += move;
        }
        if (movesSum / playerMovesInEnemyTurn.size() == 0.5) {
            enemyMove = moves[rand.nextInt(moves.length)];
        }
        return enemyMove;

    }

    /**
     * Метод, определяющий поведение противника на следующий ход противника
     * Отталкивается от предыдущих действий игрока в ход противника
     * Если игрок в в ход противника чаще выбирал защиту - противник будет защищаться, чтобы иметь шанс "застанить" игрока
     * Если игрок чаще выбирал атаку - противник будет атаковать, чтобы нанести урон
     * При равных значениях противник выбирает действие случайно
     * Для босса случайным образом (15%) может определиться действие лечения
     *
     * @param game объект текущей игры
     * @return действие противника: 0 - защита, 1 - атака, 2 (для босса) - попытка лечения
     */
    public ActionType setEnemyMoveForEnemyTurn(Game game) {
        ActionType enemyMove = setEnemyMoveForTreatmentAndDispute(game);
        if (enemyMove == null) {
            double movesSum = 0;
            for (int move : playerMovesInEnemyTurn) {
                movesSum += move;
            }
            if (movesSum / playerMovesInEnemyTurn.size() > 0.5) {
                enemyMove = ActionType.HIT;
            } else enemyMove = ActionType.BLOCK;
        }
        return enemyMove;
    }
    /**
     * Метод, вызываемый при окончании раунда, т.е. после выбранной атаки или защиты игрока
     * Проверяет, не побежден ли один из участников
     * @param game объект текущей игры
     */
    public void endRoundCheck(Game game) {
        Player human = game.getHuman();
        Player enemy = game.getEnemies().get(game.getRound());
        if (human.getHealth() <= 0 & game.getItem(2).getCount() > 0) {
            human.setHealth((int) (human.getMaxHealth() * 0.05));
            game.setTurnResult(game.getTurnResult() + ". Вы воскресли");
            game.setItems(2, game.getItem(2).getCount() - 1);
        } else if (human.getHealth() <= 0) {
            game.setHumanDefeated(true);
        } else if (enemy.getHealth() <= 0) {
            enemyDefeat(game);
        } else game.newTurn();
    }
    /**
     * Метод, вызываемый при победе игрока над противником
     * Случайным образом выдает игроку предмет, опыт и очки в соответствии с уровнем противника
     * @param game объект текущей игры
     */
    public void enemyDefeat(Game game) {
        game.addItems();
        game.getHuman().setExperience(game.getHuman().getExperience() + game.getCurrentEnemy().getLevel() * 20);
        game.getHuman().setPoints(game.getHuman().getPoints() + game.getHuman().getHealth() + game.getCurrentEnemy().getLevel() * 30);
    }
    /**
     * Метод, вызывающийся при условии, что игрок в этот ход находится в "стане"
     * В зависимости от действий противника определяет исход хода
     * @param game  объект текущей игры
     * @param human объект игрока
     * @param enemy объект противника
     */
    protected void humanIsStunned(Game game, Player human, Player enemy) {
        switch (enemy.getAttack()) {
            case BLOCK -> game.setTurnResult("You are stunned, " + enemy.getName() + " didn't attack");
            case HIT -> {
                game.setTurnResult("You are stunned, " + enemy.getName() + " is attacked");
                human.setHealth(human.getHealth() - enemy.getDamage());
            }
            case TREATMENT -> {
                game.setTurnResult(enemy.getName() + " are healing!");
                enemy.setHealth(enemy.getHealth() + (enemy.getMaxHealth() - enemy.getHealth()) / 2);
            }
        }
        human.setStunned(false);
    }
    public void changeDebuffLevel(Player player) {
        player.setDebufferedCount(player.getDebufferedCount() - 1);
        if (player.getDebufferedCount() == 0) {
            player.setDebuffed(false);
        }
    }
}
