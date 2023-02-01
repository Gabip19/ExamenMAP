package org.examen.examenmap.repository.database;

import org.examen.examenmap.domain.MenuItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuItemDatabaseRepo extends AbstractDatabaseRepository<Integer, MenuItem> {

    public MenuItemDatabaseRepo(String tableName, String url, String username, String password) {
        super(tableName, url, username, password);
    }

    @Override
    public MenuItem save(MenuItem entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        String sql = "INSERT INTO menu_items(category, item, price, currency) VALUES (?, ?, ?, ?)";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, entity.getCategory());
            statement.setString(2, entity.getItem());
            statement.setFloat(3, entity.getPrice());
            statement.setString(4, entity.getCurrency());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public MenuItem update(MenuItem entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

        if (findOne(entity.getId()) != null) {
            String sql = "UPDATE menu_items SET price = ?, currency = ? WHERE id = ?";
            try (
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setFloat(1, entity.getPrice());
                statement.setString(2, entity.getCurrency());
                statement.setInt(3, entity.getId());
                statement.executeUpdate();

                return entity;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("The entity to be updated does not exist.\n");
    }

    @Override
    protected MenuItem createEntity(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String category = resultSet.getString("category");
        String item = resultSet.getString("item");
        float price = resultSet.getFloat("price");
        String currency = resultSet.getString("currency");

        return new MenuItem(id, category, item, price, currency);
    }
}
