package org.examen.examenmap.repository.database;

import org.examen.examenmap.domain.Table;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableDatabaseRepo extends AbstractDatabaseRepository<Integer, Table> {

    public TableDatabaseRepo(String tableName, String url, String username, String password) {
        super(tableName, url, username, password);
    }

    @Override
    public Table save(Table entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        String sql = "INSERT INTO tables VALUES (?)";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Table update(Table entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        if (findOne(entity.getId()) != null) {
            String sql = "UPDATE tables SET id = ? WHERE id = ?";
            try (
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setInt(1, entity.getId());
                statement.setInt(2, entity.getId());

                return entity;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("The entity to be updated does not exist.\n");
    }

    @Override
    protected Table createEntity(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        return new Table(id);
    }
}
