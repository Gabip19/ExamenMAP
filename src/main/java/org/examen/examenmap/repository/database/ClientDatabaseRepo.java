package org.examen.examenmap.repository.database;

import org.examen.examenmap.domain.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDatabaseRepo extends AbstractDatabaseRepository<Long, Client> {

    public ClientDatabaseRepo(String tableName, String url, String username, String password) {
        super(tableName, url, username, password);
    }

    @Override
    public Client save(Client entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        String sql = "INSERT INTO clients (id, client_name, fidelity_grade, age, hobbies) VALUES (?, ?, ?, ?, ?)";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setInt(3, entity.getFidelityGrade());
            statement.setInt(4, entity.getAge());
            statement.setString(5, String.valueOf(entity.getHobby()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Client update(Client entity) {
        return null;
    }

    @Override
    protected Client createEntity(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("client_name");
        int grade = resultSet.getInt("fidelity_grade");
        int age = resultSet.getInt("age");
        Client.ClientHobby hobby = Client.ClientHobby.valueOf(resultSet.getString("hobbies"));

        return new Client(id, name, grade, age, hobby);
    }
}
