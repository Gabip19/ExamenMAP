package org.examen.examenmap.repository.database;

import org.examen.examenmap.domain.Location;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationDatabaseRepo extends AbstractDatabaseRepository<Double, Location> {

    public LocationDatabaseRepo(String tableName, String url, String username, String password) {
        super(tableName, url, username, password);
    }

    @Override
    public Location save(Location entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        String sql = "INSERT INTO locations (id, location_name) VALUES (?, ?)";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setDouble(1, entity.getId());
            statement.setString(2, entity.getLocationName());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Location update(Location entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        if (findOne(entity.getId()) != null) {
            String sql = "UPDATE locations SET location_name = ? WHERE id = ?";
            try (
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setString(1, entity.getLocationName());
                statement.setDouble(2, entity.getId());

                return entity;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("The entity to be updated does not exist.\n");
    }

    @Override
    protected Location createEntity(ResultSet resultSet) throws SQLException {
        Double id = resultSet.getDouble("id");
        String locationName = resultSet.getString("location_name");

        return new Location(id, locationName);
    }
}
