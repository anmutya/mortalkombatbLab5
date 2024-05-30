package gameComponents;

/**
 * @author Мария
 */
public class Result {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public Result(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    private int points;
}