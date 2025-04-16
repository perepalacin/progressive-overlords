@param java.util.List<progressive_overlords.entities.dao.PublicUserDao> userSuggestionList
@param progressive_overlords.entities.dao.PublicUserDao ownUserDetails
@param java.util.List<progressive_overlords.entities.dao.WorkoutSummaryDao> feedActivity
@template.main(tabName = "Home",content = @`
    @template.components.nav.sidebar(activeTab = "feed")
    <div class="flex-row w-full justify-between">
        <div class="px-2 py-1 flex-column w-full align-center">
            <h1 style="align-self: start">Home</h1>
            <div class="px-2 py-1 flex-column w-full" style="max-width: 800px">
                @template.components.feeds.feed-activity(feedActivity = feedActivity, isOwnWorkout = false)
            </div>
        </div>
        <div class="flex-column gap-1 mobile-hidden px-1 py-1">
            @template.components.friends.own-user-details(userDetails = ownUserDetails)
            @template.components.friends.friend-recommendation-sidebar(userSuggestionList = userSuggestionList)
        </div>
    </div>
`)

