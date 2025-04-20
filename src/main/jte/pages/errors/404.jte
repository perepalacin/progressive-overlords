@param String activeTab
@template.main(tabName = "404",content = @`
@template.components.nav.sidebar(activeTab = activeTab)
    <div class="flex-column gap-1 px-2">
        <h1>Ups...</h1>
        <p>The resource you are looking for doesn't exist!</p>
        <button class="button main-button" style="align-self: left">
            <a href="/">
            Go back home!
            </a>
        </button>
    </div>
`)
