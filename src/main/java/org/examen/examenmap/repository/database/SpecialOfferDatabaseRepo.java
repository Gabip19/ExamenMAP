package org.examen.examenmap.repository.database;

import org.examen.examenmap.domain.SpecialOffer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SpecialOfferDatabaseRepo extends AbstractDatabaseRepository<Double, SpecialOffer> {

    public SpecialOfferDatabaseRepo(String tableName, String url, String username, String password) {
        super(tableName, url, username, password);
    }

    @Override
    public SpecialOffer save(SpecialOffer entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        String sql = "INSERT INTO offers (id, hotel_id, start_date, end_date, percents) VALUES (?, ?, ?, ?, ?)";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setDouble(1, entity.getId());
            statement.setDouble(2, entity.getHotelId());
            statement.setDate(3, Date.valueOf(entity.getStartDate()));
            statement.setDate(4, Date.valueOf(entity.getEndDate()));
            statement.setInt(5, entity.getPercent());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public SpecialOffer update(SpecialOffer entity) {
        return null;
//        if (entity == null)
//            throw new IllegalArgumentException("Entity must not be null.\n");
//
//        validator.validate(entity);
//
//        if (findOne(entity.getId()) != null) {
//            String sql = "UPDATE users SET first_name = ?, last_name = ?, birthdate = ? WHERE id = ?";
//            try (
//                    PreparedStatement statement = connection.prepareStatement(sql)
//            ) {
//                statement.setString(1, entity.getFirstName());
//                statement.setString(2, entity.getLastName());
//                statement.setDate(3, Date.valueOf(entity.getBirthdate()));
//                statement.setObject(4, entity.getId());
//                statement.executeUpdate();
//
//                return entity;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        throw new IllegalArgumentException("The entity to be updated does not exist.\n");
    }

    @Override
    protected SpecialOffer createEntity(ResultSet resultSet) throws SQLException {
        Double id = resultSet.getDouble("id");
        Double hotelId = resultSet.getDouble("hotel_id");
        LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
        LocalDate endDate = resultSet.getDate("end_date").toLocalDate();
        int percents = resultSet.getInt("percents");

        return new SpecialOffer(id, hotelId, startDate, endDate, percents);
    }
}
