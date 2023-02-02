package org.examen.examenmap.repository.database;

import org.examen.examenmap.domain.Bed;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BedDatabaseRepo extends AbstractDatabaseRepository<UUID, Bed> {

    public BedDatabaseRepo(String tableName, String url, String username, String password) {
        super(tableName, url, username, password);
    }

    @Override
    public Bed save(Bed entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        String sql = "INSERT INTO beds(id, type, ventilation, patient_cnp) VALUES (?, ?, ?, ?)";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setObject(1, entity.getId());
            statement.setString(2, String.valueOf(entity.getType()));
            statement.setBoolean(3, entity.hasVentilation());
            statement.setString(4, entity.getPatientCNP());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Bed update(Bed entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        if (findOne(entity.getId()) != null) {
            String sql = "UPDATE beds SET type = ?, ventilation = ?, patient_cnp = ? WHERE id = ?";
            try (
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setString(1, String.valueOf(entity.getType()));
                statement.setBoolean(2, entity.hasVentilation());
                statement.setString(3, entity.getPatientCNP());
                statement.setString(4, entity.getPatientCNP());
                statement.executeUpdate();

                return entity;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("The entity to be updated does not exist.\n");
    }

    @Override
    protected Bed createEntity(ResultSet resultSet) throws SQLException {
        UUID id = resultSet.getObject("id", UUID.class);
        Bed.BedType type = Bed.BedType.valueOf(resultSet.getString("type"));
        boolean ventilation = resultSet.getBoolean("ventilation");
        String patientCNP = resultSet.getString("patient_cnp");

        return new Bed(id, ventilation, patientCNP, type);
    }
}
