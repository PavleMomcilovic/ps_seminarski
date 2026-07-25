/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.db;

import java.sql.SQLException;
import repository.Repository;

/**
 *
 * @author pavle
 */
public interface DbRepository<T> extends Repository<T> {
    default void connect() throws SQLException {
        DbConnectionFactory.getInstance().getConnection();
    }

    default void disconnect() throws SQLException {
        if (!DbConnectionFactory.getInstance().getConnection().isClosed()) {
            DbConnectionFactory.getInstance().getConnection().close();
        }
    }

    default void commit() throws SQLException {
        DbConnectionFactory.getInstance().getConnection().commit();
    }

    default void rollback() throws SQLException {
        DbConnectionFactory.getInstance().getConnection().rollback();
    }
}
