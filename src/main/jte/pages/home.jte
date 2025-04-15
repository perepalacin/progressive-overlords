@param java.util.List<progressive_overlords.entities.dao.PublicUserDao> userSuggestionList
@param progressive_overlords.entities.dao.PublicUserDao ownUserDetails
@param java.util.List<progressive_overlords.entities.dao.WorkoutSummaryDao> feedActivity
@template.main(tabName = "Home",content = @`
    @template.components.nav.sidebar(activeTab = "feed")
    <div class="flex-row w-full justify-between gap-2">
        <div style="width: auto" class="px-2 py-1">
            <h1>Home</h1>
            @template.components.feeds.feed-activity(feedActivity = feedActivity)
        </div>
        <div class="flex-column gap-1 mobile-hidden px-1 py-1" style="width: 30%">
            @template.components.friends.own-user-details(userDetails = ownUserDetails)
            @template.components.friends.friend-recommendation-sidebar(userSuggestionList = userSuggestionList)
        </div>
    </div>
`)

