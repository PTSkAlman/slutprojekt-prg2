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
        System.out.println(BCrypt.hashpw(password, BCrypt.gensalt(10)));

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
                        rset.getString("created_at");
                String[] splitStr = user.split(" ");
                System.out.println(splitStr[2]);
                if (BCrypt.checkpw(password, splitStr[2])) {
                    boolean admin = false;
                    if (splitStr[1].equals("arne")) {
                        admin = true;
                    }
                    return new User(splitStr[1],0,0,admin);
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
