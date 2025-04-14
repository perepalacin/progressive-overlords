@param java.util.UUID userId
<button class="button muted-button" hx-post="/api/v1/friends/${String.valueOf(userId)}" hx-target="this" hx-swap="outerHTML">Follow</button>