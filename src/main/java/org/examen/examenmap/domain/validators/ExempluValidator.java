//package org.examen.examenmap.domain.validators;
//
//
///**
// * Friendship entity validator
// */
//public class FriendshipValidator implements Validator<Friendship> {
//    @Override
//    public void validate(Friendship friendship) throws FriendshipException {
//        if (friendship.getSenderID() == null || friendship.getReceiverID() == null) {
//            throw new FriendshipException("IDs can not be null.\n");
//        }
//        if (friendship.getSenderID().equals(friendship.getReceiverID())) {
//            throw new FriendshipException("A user can not befriend himself.\n");
//        }
//    }
//}
