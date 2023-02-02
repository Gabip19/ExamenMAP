package org.examen.examenmap.repository.database;

import org.examen.examenmap.domain.Locality;
import org.examen.examenmap.domain.River;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class LocalityDatabaseRepo extends AbstractDatabaseRepository<UUID, Locality> {

    public LocalityDatabaseRepo(String tableName, String url, String username, String password) {
        super(tableName, url, username, password);
    }

    @Override
    public Locality save(Locality entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        String sql = "INSERT INTO localities (id, locality_name, min_risk_alt, max_risk_alt, river_id) VALUES (?, ?, ?, ?, ?)";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setObject(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setInt(3, entity.getMinRiskAlt());
            statement.setInt(4, entity.getMaxRiskAlt());
            statement.setObject(5, entity.getRiverId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Locality update(Locality entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        if (findOne(entity.getId()) != null) {
            String sql = "UPDATE localities SET locality_name = ?, min_risk_alt = ?, max_risk_alt = ?, river_id = ? WHERE id = ?";
            try (
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setString(1, entity.getName());
                statement.setInt(2, entity.getMinRiskAlt());
                statement.setInt(3, entity.getMaxRiskAlt());
                statement.setObject(4, entity.getRiverId());
                statement.setObject(5, entity.getId());
                statement.executeUpdate();

                return entity;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("The entity to be updated does not exist.\n");
    }

    @Override
    protected Locality createEntity(ResultSet resultSet) throws SQLException {
        UUID id = resultSet.getObject("id", UUID.class);
        String name = resultSet.getString("locality_name");
        int minRiskAlt = resultSet.getInt("min_risk_alt");
        int maxRiskAlt = resultSet.getInt("max_risk_alt");
        UUID riverId = resultSet.getObject("river_id", UUID.class);

        return new Locality(id, name, riverId, minRiskAlt, maxRiskAlt);
    }
}
