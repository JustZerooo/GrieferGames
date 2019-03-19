package de.niklas409.griefergames.features.blocklog;

import java.sql.ResultSet;
import com.mysql.jdbc.Statement;
import java.sql.SQLException;
import org.bukkit.Bukkit;
import java.sql.DriverManager;
import java.sql.Connection;

public class MySQL
{
    private String HOST;
    private String PORT;
    private String DATABASE;
    private String USER;
    private String PASSWORD;
    public static Connection con;
    
    public MySQL(final String host, final String port, final String database, final String user, final String password) {
        super();
        this.HOST = "";
        this.PORT = "";
        this.DATABASE = "";
        this.USER = "";
        this.PASSWORD = "";
        this.HOST = host;
        this.PORT = port;
        this.DATABASE = database;
        this.USER = user;
        this.PASSWORD = password;
        this.connect();
    }
    
    public void connect() {
        final String Prefix = "§8[§4§lGG Features§8] §r";
        try {
            MySQL.con = DriverManager.getConnection("jdbc:mysql://" + this.HOST + ":" + this.PORT + "/" + this.DATABASE, this.USER, this.PASSWORD);
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§aDie Verbindung mit MySQL wurde hergestellt!");
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie Verbindung mit MySQL ist fehlgeschlagen! §4Fehler: " + e.getMessage());
        }
    }
    
    public void close() {
        final String Prefix = "§8[§4§lGG Features§8] §r";
        try {
            if (MySQL.con != null) {
                MySQL.con.close();
                Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§aDie Verbindung mit MySQL wurde beendet!");
            }
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie Verbindung mit MySQL konnte nicht beendet werden! §4Fehler: " + e.getMessage());
        }
    }
    
    public void update(final String qre) {
        if (MySQL.con != null) {
            try {
                final Statement st = (Statement)MySQL.con.createStatement();
                st.executeUpdate(qre);
                st.close();
            }
            catch (SQLException e) {
                this.connect();
                System.err.print(e);
            }
        }
    }
    
    public ResultSet query(final String qre) {
        if (MySQL.con != null) {
            ResultSet rs = null;
            try {
                final Statement st = (Statement)MySQL.con.createStatement();
                rs = st.executeQuery(qre);
            }
            catch (SQLException e) {
                this.connect();
                System.err.print(e);
            }
            return rs;
        }
        return null;
    }
}
