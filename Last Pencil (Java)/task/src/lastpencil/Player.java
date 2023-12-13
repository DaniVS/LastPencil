package lastpencil;

public class Player {
    private final String name;
    private final boolean isTheBot;

    public Player(String name, boolean isTheBot) {
        this.name = name;
        this.isTheBot = isTheBot;
    }

    public String getName(){
        return this.name;
    }
    
    public boolean isTheBot(){
        return this.isTheBot;
    }
}
