package org.examen.examenmap.domain.factory;


import org.examen.examenmap.domain.Entity;
import org.examen.examenmap.domain.MenuItem;
import org.examen.examenmap.domain.Table;


public class EntityAsStringFactory {
    public static <E extends Entity<Integer>> String getStringFromEntity(E entity) {
        if (entity instanceof Table) {
            return entity.getId().toString();
        } else if (entity instanceof MenuItem entityMenuItem) {
            return entityMenuItem.getId() + ";" + entityMenuItem.getCategory()
                    + ";" + entityMenuItem.getItem() + ";" + entityMenuItem.getPrice()
                    + ";" + entityMenuItem.getCurrency();
        }
        throw new RuntimeException("Unknown entity type.\n");
    }
}
