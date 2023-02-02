package org.examen.examenmap.repository.database;

import org.examen.examenmap.domain.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDatabaseRepo extends AbstractDatabaseRepository<String, Patient> {

    public PatientDatabaseRepo(String tableName, String url, String username, String password) {
        super(tableName, url, username, password);
    }

    @Override
    public Patient save(Patient entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        String sql = "INSERT INTO patients (cnp, age, premature, diagnosis, severity) VALUES (?, ?, ?, ?, ?)";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, entity.getId());
            statement.setInt(2, entity.getAge());
            statement.setBoolean(3, entity.isPremature());
            statement.setString(4, entity.getDiagnosis());
            statement.setInt(5, entity.getSeverity());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Patient update(Patient entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        if (findOne(entity.getId()) != null) {
            String sql = "UPDATE patients SET age = ?, premature = ?, diagnosis = ?, severity = ? WHERE cnp = ?";
            try (
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setInt(1, entity.getAge());
                statement.setBoolean(2, entity.isPremature());
                statement.setString(3, entity.getDiagnosis());
                statement.setInt(4, entity.getSeverity());
                statement.setString(5, entity.getCNP());
                statement.executeUpdate();

                return entity;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("The entity to be updated does not exist.\n");
    }

    @Override
    protected Patient createEntity(ResultSet resultSet) throws SQLException {
        String cnp = resultSet.getString("cnp");
        int age = resultSet.getInt("age");
        boolean premature = resultSet.getBoolean("premature");
        String diagnosis = resultSet.getString("diagnosis");
        int severity = resultSet.getInt("severity");

        return new Patient(cnp, age, premature, diagnosis, severity);
    }

    public ArrayList<Patient> getWaitingPatients() {
        ArrayList<Patient> entities = new ArrayList<>();

        String sql = "select * from patients P left join beds B on P.cnp = B.patient_cnp where B.id is NULL";
        try (
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            entities = (ArrayList<Patient>) getEntitiesFromResult(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

}
