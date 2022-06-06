package Core;

import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.sql.*;

public class DatabaseConnector {

    Connection connection;

    public DatabaseConnector() {
        String password = JOptionPane.showInputDialog(null, "Database password");
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://"+ "db.umea-ntig.se" +":"+ "3306" +"/"+ "te19" +"? " +
                            "allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    "te19", password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Database error!");
            System.exit(0);
        }
    }

    public void register(String username, String password) {
        if (username.length() >= 3 && password.length() >= 8) {
            String hash = BCrypt.hashpw(password, BCrypt.gensalt(10));
            try {
                Statement statement = connection.createStatement();
                String SQLQuery = "INSERT INTO jolabn_login (username, password) VALUES ('" + username + "', '" + hash +"')";
                statement.execute(SQLQuery);
                String SQLQuery2 = "SELECT * FROM jolabn_login WHERE username = " + "'" + username + "'";
                ResultSet rset = statement.executeQuery(SQLQuery2);
                int id = 0;
                while (rset.next()) {
                    id = rset.getInt("id");
                }
                String SQLQuery3 = "INSERT INTO jolabn_scores (user_id) VALUES ('" + id + "')";
                statement.execute(SQLQuery3);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public User login(String username, String password) {
        //password = new String(pf.getPassword());
        //username = uf.getText();
        try {
            Statement statement = connection.createStatement();
            String SQLQuery = "SELECT * FROM jolabn_login WHERE username = " + "'" + username + "'";

            ResultSet rset = statement.executeQuery(SQLQuery);

            while (rset.next()) {
                String user = rset.getInt("id") + " " +
                        rset.getString("username") + " " +
                        rset.getString("password") + " " +
                        rset.getString("created_at") + " " +
                        rset.getInt("admin");
                String[] splitStr = user.split(" ");
                String scores;
                String[] splitStr2 = new String[3];
                String SQLQuery2 = "SELECT jolabn_login.*, jolabn_scores.* FROM jolabn_login JOIN jolabn_scores ON jolabn_login.id = jolabn_scores.user_id WHERE jolabn_login.id = '"+splitStr[0]+"'";

                ResultSet rset2 = statement.executeQuery(SQLQuery2);
                while (rset2.next()) {
                    scores = rset2.getInt("id") + " " +
                            rset2.getInt("coinflip_highscore") + " " +
                            rset2.getInt("higher_or_lower_highscore") + " " +
                            rset2.getInt("user_id");
                    splitStr2 = scores.split(" ");
                }

                if (BCrypt.checkpw(password, splitStr[2])) {
                    boolean admin = false;
                    if (splitStr[4].equals("1")) {
                        admin = true;
                    }
                    return new User(Integer.parseInt(splitStr[0]), splitStr[1],Integer.parseInt(splitStr2[1]), Integer.parseInt(splitStr2[2]),admin);
                } else {
                    return null;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void updateScore(User user) {
        try {
            Statement statement = connection.createStatement();
            String SQLQuery = "UPDATE jolabn_scores SET coinflip_highscore = '" + user.getCfScore() + "', higher_or_lower_highscore = '" + user.getHlScore() + "' WHERE user_id = '" + user.getId()+"'";
            statement.execute(SQLQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
