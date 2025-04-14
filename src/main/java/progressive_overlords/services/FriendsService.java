package progressive_overlords.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.PublicUserDao;
import progressive_overlords.repositories.FriendsRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FriendsService {

    private final FriendsRepository friendsRepository;

    public List<PublicUserDao> getRecommendedFriends () {
        List<PublicUserDao> listOfUsers = friendsRepository.getRandomUsersSuggestion(5);
        if (listOfUsers == null ||listOfUsers.isEmpty()) {
            //Throw
        }
        return listOfUsers;
    }

    public boolean followUser (UUID userId) {
        return friendsRepository.followUser(userId);
    }

    public boolean unFollowUser (UUID userId) {
        return friendsRepository.unFollowUser(userId);
    }

    public List<PublicUserDao> getUserFriends () {
        //TODO: add this so that we can look for them when creating the compare!!
        List<PublicUserDao> listOfFriends = friendsRepository.getUserFriendList();
        if (listOfFriends == null ||listOfFriends.isEmpty()) {
            //Throw
        }
        return listOfFriends;
    }



}
