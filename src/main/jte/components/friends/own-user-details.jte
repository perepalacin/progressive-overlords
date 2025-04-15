@param progressive_overlords.entities.dao.PublicUserDao userDetails

<aside class="user-details-sidebar">
    @template.components.friends.user-logo(username = userDetails.getUsername())
    <p class="font-bold mb-1 text-center">${userDetails.getUsername()}</p>
    <ul class="flex-row gap-1">
        <li class="flex-column align-center gap-05">
            <p class="label">Workouts</p>
            <p>${userDetails.getWorkouts()}</p>
        </li>
        <li class="flex-column align-center gap-05">
            <p class="label">Following</p>
            <p>${userDetails.getFollowing()}</p>
        </li>
        <li class="flex-column align-center gap-05 ">
            <p class="label">Followers</p>
            <p>${userDetails.getFollowers()}</p>
        </li>
    </ul>
</aside>
