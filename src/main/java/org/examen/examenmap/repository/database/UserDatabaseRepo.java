//package org.examen.examenmap.repository.database;
//
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.UUID;
//
//public class UserDatabaseRepo extends AbstractDatabaseRepository<UUID, User> {
//
//    private final Validator<User> validator;
//
//    public UserDatabaseRepo(String tableName, String url, String username, String password, Validator<User> validator) {
//        super(tableName, url, username, password);
//        this.validator = validator;
//    }
//
//    @Override
//    public User save(User entity) {
//        if (entity == null)
//            throw new IllegalArgumentException("Entity must not be null.\n");
//
//        validator.validate(entity);
//
//        String sql = "INSERT INTO users (id, first_name, last_name, email, birthdate, profile_color) VALUES (?, ?, ?, ?, ?, ?)";
//        try (
//                PreparedStatement statement = connection.prepareStatement(sql)
//        ) {
//            statement.setObject(1, entity.getId());
//            statement.setString(2, entity.getFirstName());
//            statement.setString(3, entity.getLastName());
//            statement.setString(4, entity.getEmail());
//            statement.setDate(5, Date.valueOf(entity.getBirthdate()));
//            statement.setString(6, entity.getHexProfileColor());
//
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return entity;
//    }
//
//    @Override
//    public User update(User entity) {
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
//    }
//
//    @Override
//    protected User createEntity(ResultSet resultSet) throws SQLException {
//        UUID id = resultSet.getObject("id", UUID.class);
//        String firstName = resultSet.getString("first_name");
//        String lastName = resultSet.getString("last_name");
//        String email = resultSet.getString("email");
//        LocalDate birthdate = resultSet.getDate("birthdate").toLocalDate();
//        String hexProfileColor = resultSet.getString("profile_color");
//
//        return new User(id, firstName, lastName, email, birthdate, hexProfileColor);
//    }
//
//    public User findUserByEmail(String email) {
//        String sql = "SELECT * FROM users WHERE email = ?";
//        try (
//                PreparedStatement ps = connection.prepareStatement(sql)
//        ) {
//            ps.setString(1, email);
//            ResultSet resultSet = ps.executeQuery();
//            if (resultSet.next()) {
//                return createEntity(resultSet);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
