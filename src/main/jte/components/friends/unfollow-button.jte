@param java.util.UUID userId
<button class="button muted-button" hx-patch="/api/v1/friends/${String.valueOf(userId)}" hx-target="this" hx-swap="outerHTML">Unfollow</button>