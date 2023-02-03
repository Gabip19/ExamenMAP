package org.examen.examenmap.repository.database;


import org.examen.examenmap.domain.Hotel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelDatabaseRepo extends AbstractDatabaseRepository<Double, Hotel> {

    public HotelDatabaseRepo(String tableName, String url, String username, String password) {
        super(tableName, url, username, password);
    }

    @Override
    public Hotel save(Hotel entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        String sql = "INSERT INTO hotels (id, location_id, hotel_name, no_rooms, price, hotel_type) VALUES (?, ?, ?, ?, ?, ?)";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setDouble(1, entity.getId());
            statement.setDouble(2, entity.getLocationId());
            statement.setString(3, entity.getHotelName());
            statement.setInt(4, entity.getNoRooms());
            statement.setDouble(5, entity.getPricePerNight());
            statement.setString(6, String.valueOf(entity.getType()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Hotel update(Hotel entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        if (findOne(entity.getId()) != null) {
            String sql = "UPDATE hotels SET price = ?, hotel_name = ? WHERE id = ?";
            try (
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setDouble(1, entity.getPricePerNight());
                statement.setString(2, entity.getHotelName());
                statement.setDouble(3, entity.getId());
                statement.executeUpdate();

                return entity;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("The entity to be updated does not exist.\n");
    }

    @Override
    protected Hotel createEntity(ResultSet resultSet) throws SQLException {
        Double id = resultSet.getDouble("id");
        Double locationId = resultSet.getDouble("location_id");
        String hotelName = resultSet.getString("hotel_name");
        int noRooms = resultSet.getInt("no_rooms");
        double price = resultSet.getDouble("price");
        Hotel.HotelType type = Hotel.HotelType.valueOf(resultSet.getString("hotel_type"));

        return new Hotel(id, locationId, hotelName, noRooms, price, type);
    }
}
