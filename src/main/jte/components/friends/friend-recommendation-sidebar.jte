@param java.util.List<progressive_overlords.entities.dao.PublicUserDao> userSuggestionList



@if (!userSuggestionList.isEmpty())
    <aside class="user-suggestion-sidebar">
        <p class="font-bold mb-1">Suggested users</p>
        <ul class="flex-column gap-1">
           @for (progressive_overlords.entities.dao.PublicUserDao user : userSuggestionList)
                <li class="flex-row w-full align-center justify-between gap-1">
                    <div class="flex-row gap-2 align-center">
                        @template.components.friends.user-logo(username = user.getUsername())
                        <p class="w-12">${user.getUsername()}</p>
                    </div>
                    @template.components.friends.follow-button(userId = user.getUserId())
                </li>
           @endfor
        </ul>
    </aside>
@endif
