package org.examen.examenmap.repository.database;

import org.examen.examenmap.domain.Booking;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BookingDatabaseRepo extends AbstractDatabaseRepository<UUID, Booking> {

    public BookingDatabaseRepo(String tableName, String url, String username, String password) {
        super(tableName, url, username, password);
    }

    @Override
    public Booking save(Booking entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        String sql = "INSERT INTO bookings (id, offer_id, client_name, address, person_num) VALUES (?, ?, ?, ?, ?)";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setObject(1, entity.getId());
            statement.setObject(2, entity.getOfferId());
            statement.setString(3, entity.getClientName());
            statement.setString(4, entity.getAddress());
            statement.setInt(5, entity.getPersonNum());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Booking update(Booking entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        if (findOne(entity.getId()) != null) {
            String sql = "UPDATE bookings SET offer_id = ?, client_name = ?, address = ?, person_num = ? WHERE id = ?";
            try (
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setObject(1, entity.getOfferId());
                statement.setString(2, entity.getClientName());
                statement.setString(3, entity.getAddress());
                statement.setInt(4, entity.getPersonNum());
                statement.setObject(5, entity.getId());

                return entity;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("The entity to be updated does not exist.\n");
    }

    @Override
    protected Booking createEntity(ResultSet resultSet) throws SQLException {
        UUID id = resultSet.getObject("id", UUID.class);
        UUID offerId = resultSet.getObject("offer_id", UUID.class);
        String clientName = resultSet.getString("client_name");
        String address = resultSet.getString("address");
        int personNum = resultSet.getInt("person_num");

        return new Booking(id, offerId, clientName, address, personNum);
    }
}
