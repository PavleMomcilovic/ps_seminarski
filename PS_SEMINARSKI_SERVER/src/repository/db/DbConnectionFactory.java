/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import konfiguracija.Konfiguracija;

/**
 *
 * @author pavle
 */
public class DbConnectionFactory {
    private static DbConnectionFactory instanca;
    private Connection konekcija;

    private DbConnectionFactory() {
    }

    public static DbConnectionFactory getInstance() {
        if (instanca == null) {
            instanca = new DbConnectionFactory();
        }
        return instanca;
    }

    public Connection getConnection() throws SQLException {
        if (konekcija == null || konekcija.isClosed()) {
            String url = Konfiguracija.getInstanca().getProperty("url");
            String user = Konfiguracija.getInstanca().getProperty("username");
            String password = Konfiguracija.getInstanca().getProperty("password");
            konekcija = DriverManager.getConnection(url, user, password);
            konekcija.setAutoCommit(false);
        }
        return konekcija;
    }
}
