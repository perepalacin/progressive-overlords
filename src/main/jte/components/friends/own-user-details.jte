@param progressive_overlords.entities.dao.PublicUserDao userDetails

<aside class="user-details-sidebar">
    @template.components.friends.user-logo(username = userDetails.getUsername())
    <p class="font-bold mb-1 text-center">${userDetails.getUsername()}</p>
    <ul class="flex-column gap-1">
            <li class="flex-row w-full align-center justify-between gap-1">
                <div class="flex-row gap-2 align-center">
                    <p>${userDetails.getUsername()}</p>
                </div>
                @template.components.friends.follow-button(userId = userDetails.getUserId())
            </li>
    </ul>
</aside>
