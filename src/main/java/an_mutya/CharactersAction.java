package an_mutya;

public interface CharactersAction {
    public abstract String getType();
    public abstract void realisation(Player player, Player enemy, ActionType actionType);
}
