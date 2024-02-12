package org.dasultra.mysql;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.UUID;
import org.dasultra.file.FileManager;

public class mysql {

    public static String getHost() {
        FileManager mysql = new FileManager("plugins/CoreSystem/MySQL.yml");
        return mysql.getString("Host");
    }

    public static String getPort() {
        FileManager mysql = new FileManager("plugins/CoreSystem/MySQL.yml");
        return mysql.getString("Port");
    }

    public static String getDatabase() {
        FileManager mysql = new FileManager("plugins/CoreSystem/MySQL.yml");
        return mysql.getString("Database");
    }

    public static String getUsername() {
        FileManager mysql = new FileManager("plugins/CoreSystem/MySQL.yml");
        return mysql.getString("Username");
    }

    public static String getPassword() {
        FileManager mysql = new FileManager("plugins/CoreSystem/MySQL.yml");
        return mysql.getString("Password");
    }

    public static String renderValueForSave(double v) {
        DecimalFormat format = new DecimalFormat("####.##");
        return format.format(v);
    }

    private final static String host = getHost();
    private final static String port = getPort();
    private final static String database = getDatabase();
    private final static String username = getUsername();
    private final static String password = getPassword();

    private static Connection connection;

    public static boolean isConnected() {
        return connection != null;
    }

    public static void connect() throws SQLException {
        if (!isConnected()) {
            connection = DriverManager.getConnection("jdbc:mysql://"
                    + host + ":" + port + "/" + database + "?autoReconnect=true" +
                    "&characterEncoding=utf8&useUnicode=true" +
                    "&sessionVariables=storage_engine%3DInnoDB" +
                    "&interactiveClient=true&dontTrackOpenResources=true", username, password);
             createStatsTable();
        }
    }

    public static void disconnect() throws SQLException {
        if (isConnected()) {
            connection.close();
        }
    }

    public static void createStatsTable() {
        try {

            Statement statement = connection.createStatement();

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS stats (UUID VARCHAR(200) NOT NULL , KILLS INT NOT NULL, DEATHS INT NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean existStatsPlayer(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM stats WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public static void createStatsPlayer(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM stats WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet result = statement.executeQuery();
            result.next();
            if (!existStatsPlayer(uuid)) {
                PreparedStatement insert = connection
                        .prepareStatement("INSERT INTO stats (UUID,KILLS,DEATHS) VALUES (?,?,?)");
                insert.setString(1, uuid.toString());
                insert.setInt(2, 0);
                insert.setInt(3, 0);
                insert.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void updateKills(UUID uuid, double value) {

        double parse = Double.parseDouble(renderValueForSave(value).replace(",", "."));
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE stats SET KILLS=? WHERE UUID=?");
            statement.setDouble(1, parse);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateDeaths(UUID uuid, double value) {

        double parse = Double.parseDouble(renderValueForSave(value).replace(",", "."));
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE stats SET DEATHS=? WHERE UUID=?");
            statement.setDouble(1, parse);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static double getKills(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM stats WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getDouble("KILLS");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
    public static double getDeaths(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM stats WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getDouble("DEATHS");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}

