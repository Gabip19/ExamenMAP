package org.examen.examenmap.domain.factory;


import org.examen.examenmap.domain.Entity;
import org.examen.examenmap.domain.Table;

import java.util.List;

//TODO maybe refactor generic method into separated methods
public class EntityFactory {
    public @SuppressWarnings("unchecked") static <E extends Entity<Integer>> E createEntity(EntityTypes entityType, List<String> attr) {
        if (entityType == EntityTypes.TABLE) {
            Table table = new Table(Integer.parseInt(attr.get(0)));
            return (E) table;
        }
//        else if (entityType == EntityTypes.FRIENDSHIP) {
//            return (E) new Friendship(
//                    UUID.fromString(attr.get(0)),
//                    UUID.fromString(attr.get(1)),
//                    UUID.fromString(attr.get(2)),
//                    attr.get(3),
//                    FriendshipStatus.valueOf(attr.get(4))
//            );
//        }
        return null;
    }
}
