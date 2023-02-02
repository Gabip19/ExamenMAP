package org.examen.examenmap.repository.database;

import org.examen.examenmap.domain.Offer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class OfferDatabaseRepo extends AbstractDatabaseRepository<UUID, Offer> {

    public OfferDatabaseRepo(String tableName, String url, String username, String password) {
        super(tableName, url, username, password);
    }

    @Override
    public Offer save(Offer entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        String sql = "INSERT INTO offers (id, destination, hotel_name, time_period, price, person_num) VALUES (?, ?, ?, ?, ?, ?)";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setObject(1, entity.getId());
            statement.setString(2, entity.getDestination());
            statement.setString(3, entity.getHotelName());
            statement.setInt(4, entity.getTimePeriod());
            statement.setFloat(5, entity.getPrice());
            statement.setInt(6, entity.getPersonNum());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Offer update(Offer entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        if (findOne(entity.getId()) != null) {
            String sql = "UPDATE offers SET hotel_name = ?, time_period = ?, price = ?, person_num = ? WHERE id = ?";
            try (
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setString(1, entity.getHotelName());
                statement.setInt(2, entity.getTimePeriod());
                statement.setFloat(3, entity.getPrice());
                statement.setInt(4, entity.getPersonNum());
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
    protected Offer createEntity(ResultSet resultSet) throws SQLException {
        UUID id = resultSet.getObject("id", UUID.class);
        String destination = resultSet.getString("destination");
        String hotelName = resultSet.getString("hotel_name");
        int timePeriod = resultSet.getInt("time_period");
        float price = resultSet.getFloat("price");
        int personNum = resultSet.getInt("person_num");

        return new Offer(id, destination, hotelName, timePeriod, price, personNum);
    }

}
