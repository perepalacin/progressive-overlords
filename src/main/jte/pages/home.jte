@param java.util.List<progressive_overlords.entities.dao.PublicUserDao> userSuggestionList
@param progressive_overlords.entities.dao.PublicUserDao ownUserDetails
@template.main(tabName = "Home",content = @`
    @template.components.nav.sidebar(activeTab = "feed")
    <div class="flex-row w-full gap-2">
        <div style="width: 80%">
            <p>Home page</p>
        </div>
        <div class="flex-column gap-1 mobile-hidden">
            @template.components.friends.own-user-details(userDetails = ownUserDetails)
            @template.components.friends.friend-recommendation-sidebar(userSuggestionList = userSuggestionList)
        </div>
    </div>
`)

