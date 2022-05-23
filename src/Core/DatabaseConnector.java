package Core;

import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.sql.*;

public class DatabaseConnector {

    Connection connection;
    private String username;
    private String password;

    public DatabaseConnector() {

        JPasswordField pf = new JPasswordField();
        JTextField uf = new JTextField();
        JOptionPane.showConfirmDialog(null, uf, "Enter Username", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        password = new String(pf.getPassword());
        username = uf.getText();

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://"+ env.DBURL +":"+ env.DBPORT +"/"+ env.DBNAME +"? " +
                            "allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    env.DBNAME, env.DBPASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Database error!");
            System.exit(0);
        }
    }

    public void register() {
        String hash = BCrypt.hashpw(password, BCrypt.gensalt(10));
        try {
            Statement statement = connection.createStatement();
            String SQLQuery = "INSERT INTO jolabn_login (username, password) VALUES ('" + username + "', '" + hash +"')";
            statement.execute(SQLQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public User login() {
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

}
