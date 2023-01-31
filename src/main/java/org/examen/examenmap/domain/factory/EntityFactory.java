package org.examen.examenmap.domain.factory;


import org.examen.examenmap.domain.Entity;
import org.examen.examenmap.domain.MenuItem;
import org.examen.examenmap.domain.Table;

import java.util.List;

//TODO maybe refactor generic method into separated methods
public class EntityFactory {
    public @SuppressWarnings("unchecked") static <E extends Entity<Integer>> E createEntity(EntityTypes entityType, List<String> attr) {
        if (entityType == EntityTypes.TABLE) {
            Table table = new Table(Integer.parseInt(attr.get(0)));
            return (E) table;
        } else if (entityType == EntityTypes.MENU_ITEM) {
            MenuItem menuItem = new MenuItem(
                    Integer.parseInt(attr.get(0)),
                    attr.get(1),
                    attr.get(2),
                    Float.parseFloat(attr.get(3)),
                    attr.get(4)
            );
            return (E) menuItem;
        }
        return null;
    }
}
