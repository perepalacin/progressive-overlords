document.body.addEventListener("htmx:afterRequest", function (event) {
    if (event.detail.xhr.status === 200) {
        const response = JSON.parse(event.detail.xhr.responseText);
        if (response.redirectUrl) {
            window.location.href = response.redirectUrl;
        }
    }
});