package org.examen.examenmap.domain.factory;


import org.examen.examenmap.domain.Entity;
import org.examen.examenmap.domain.Table;


public class EntityAsStringFactory {
    public static <E extends Entity<Integer>> String getStringFromEntity(E entity) {
        if (entity instanceof Table) {
            return entity.getId().toString();
//        } else if (entity instanceof Friendship entityFriendship) {
//            return entityFriendship.getId() + ";"
//                    + entityFriendship.getSenderID() + ";"
//                    + entityFriendship.getReceiverID() + ";"
//                    + entityFriendship.getFriendshipDate();
        }
        throw new RuntimeException("Unknown entity type.\n");
    }
}
