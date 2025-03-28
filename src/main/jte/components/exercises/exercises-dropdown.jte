<div class="exercises-drop-down">
    <input type="search"
           name="query"
           autocomplete="off"
           hx-trigger="input changed delay:500ms, keyup[key=='Enter'], click"
           hx-get="/api/v1/exercises?page=0"
           placeholder="Select an exercise"
           hx-target="closest .exercises-drop-down"
           hx-swap="beforeend"
    >
</div>