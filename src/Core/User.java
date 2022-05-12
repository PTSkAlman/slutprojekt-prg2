package Core;

public class User {

    private String username;
    private int hlScore, cfScore;
    private boolean admin;

    public User(String username, int hlScore, int cfScore, boolean admin) {
        this.username = username;
        this.hlScore = hlScore;
        this.cfScore = cfScore;
        this.admin = admin;
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
