package Core;

public class User {

    private String username;
    private int id, hlScore, cfScore;
    private boolean admin;

    public User(int id, String username, int cfScore, int hlScore, boolean admin) {
        this.id = id;
        this.username = username;
        this.hlScore = hlScore;
        this.cfScore = cfScore;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getHlScore() {
        return hlScore;
    }

    public int getCfScore() {
        return cfScore;
    }
}
