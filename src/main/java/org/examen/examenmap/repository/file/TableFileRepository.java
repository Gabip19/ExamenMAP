package org.examen.examenmap.repository.file;


import org.examen.examenmap.domain.Table;
import org.examen.examenmap.domain.factory.EntityAsStringFactory;
import org.examen.examenmap.domain.factory.EntityFactory;
import org.examen.examenmap.domain.factory.EntityTypes;

import java.util.List;

public class TableFileRepository extends AbstractFileRepository<Integer, Table> {

    public TableFileRepository(String fileName) {
        super(fileName);
    }

    @Override
    protected Table extractEntity(List<String> attr) {
        return EntityFactory.createEntity(EntityTypes.TABLE, attr);
    }

    @Override
    protected String createEntityAsString(Table entity) {
        return EntityAsStringFactory.getStringFromEntity(entity);
    }
}
