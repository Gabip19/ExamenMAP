package org.examen.examenmap.repository.database;

import org.examen.examenmap.domain.River;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class RiverDatabaseRepo extends AbstractDatabaseRepository<UUID, River> {

    public RiverDatabaseRepo(String tableName, String url, String username, String password) {
        super(tableName, url, username, password);
    }

    @Override
    public River save(River entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        String sql = "INSERT INTO rivers (id, river_name, avg_altitude) VALUES (?, ?, ?)";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setObject(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setInt(3, entity.getAvgAltitude());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public River update(River entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        if (findOne(entity.getId()) != null) {
            String sql = "UPDATE rivers SET river_name = ?, avg_altitude = ? WHERE id = ?";
            try (
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setString(1, entity.getName());
                statement.setInt(2, entity.getAvgAltitude());
                statement.setObject(3, entity.getId());
                statement.executeUpdate();

                return entity;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("The entity to be updated does not exist.\n");
    }

    @Override
    protected River createEntity(ResultSet resultSet) throws SQLException {
        UUID id = resultSet.getObject("id", UUID.class);
        String riverName = resultSet.getString("river_name");
        int avgAltitude = resultSet.getInt("avg_altitude");

        return new River(id, riverName, avgAltitude);
    }

}
