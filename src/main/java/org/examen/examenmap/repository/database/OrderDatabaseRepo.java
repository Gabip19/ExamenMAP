package org.examen.examenmap.repository.database;

import org.examen.examenmap.domain.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderDatabaseRepo extends AbstractDatabaseRepository<Integer, Order> {

    public OrderDatabaseRepo(String tableName, String url, String username, String password) {
        super(tableName, url, username, password);
    }

    @Override
    public Order save(Order entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

//        validator.validate(entity);

        String sql = "INSERT INTO orders(table_id, place_date, status) VALUES (?, ?, ?)";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, entity.getTableId());
            statement.setTimestamp(2, Timestamp.valueOf(entity.getDate()));
            statement.setString(3, String.valueOf(entity.getOrderStatus()));

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "SELECT * FROM orders WHERE table_id = ? AND place_date = ? AND status = ?";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, entity.getTableId());
            statement.setTimestamp(2, Timestamp.valueOf(entity.getDate()));
            statement.setString(3, String.valueOf(entity.getOrderStatus()));

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int entityId = createEntity(resultSet).getId();

            entity.getMenuItemIds().forEach(menuItemId -> {
                String sql1 = "INSERT INTO order_items(order_id, menu_item_id) VALUES (?, ?)";
                try (
                        PreparedStatement statement1 = connection.prepareStatement(sql1)
                ) {
                    statement1.setInt(1, entityId);
                    statement1.setInt(2, menuItemId);

                    statement1.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Order update(Order entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null.\n");

//        validator.validate(entity);

        if (findOne(entity.getId()) != null) {
            String sql = "UPDATE orders SET table_id = ?, place_date = ?, status = ? WHERE id = ?";
            try (
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setInt(1, entity.getTableId());
                statement.setTimestamp(2, Timestamp.valueOf(entity.getDate()));
                statement.setString(3, String.valueOf(entity.getOrderStatus()));
                statement.setInt(4, entity.getId());
                statement.executeUpdate();

                return entity;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("The entity to be updated does not exist.\n");
    }

    private ArrayList<Integer> getOrderMenuItems(int id) {
        ArrayList<Integer> menuItemsIds = new ArrayList<>();
        String sql = "SELECT mi.id FROM order_items INNER JOIN menu_items mi on mi.id = order_items.menu_item_id WHERE order_id = ?";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int menuItemId = resultSet.getInt(1);
                menuItemsIds.add(menuItemId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItemsIds;
    }

    @Override
    protected Order createEntity(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int table_id = resultSet.getInt("table_id");
        LocalDateTime place_date = resultSet.getTimestamp("place_date").toLocalDateTime();
        Order.OrderStatus status = Order.OrderStatus.valueOf(resultSet.getString("status"));
        ArrayList<Integer> menuItemsIds = getOrderMenuItems(id);

        return new Order(id, table_id, menuItemsIds, place_date, status);
    }
}
