package org.examen.examenmap.repository.file;

import org.examen.examenmap.domain.MenuItem;
import org.examen.examenmap.domain.Table;
import org.examen.examenmap.domain.factory.EntityAsStringFactory;
import org.examen.examenmap.domain.factory.EntityFactory;
import org.examen.examenmap.domain.factory.EntityTypes;

import java.util.List;

public class MenuFileRepository extends AbstractFileRepository<Integer, MenuItem> {

    public MenuFileRepository(String fileName) {
        super(fileName);
    }

    @Override
    protected MenuItem extractEntity(List<String> attr) {
        return EntityFactory.createEntity(EntityTypes.MENU_ITEM, attr);
    }

    @Override
    protected String createEntityAsString(MenuItem entity) {
        return EntityAsStringFactory.getStringFromEntity(entity);
    }
}
