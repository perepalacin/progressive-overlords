document.body.addEventListener("htmx:beforeRequest", function (event) {
    if (event.detail.elt && event.detail.elt.id === "workoutForm") {
        event.preventDefault();
        const form = event.target;
        if (!form) return;

        const formData = new FormData(form);

        formData.delete("warmups[]");

        form.querySelectorAll("input[type='checkbox']").forEach(input => {
            if (!input.checked) {
                formData.append(input.name, "false");
            } else {
                formData.append(input.name, "true");
            }
        });

        form.querySelectorAll(".set-num").forEach(setNum => {
            formData.append("setNums", setNum.textContent.trim());
        });



        const urlEncodedData = new URLSearchParams(formData).toString();

        fetch('/api/v1/routines', {
            method: event.detail.requestConfig.verb.toUpperCase(),
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: urlEncodedData
        })
        .then(response => {
            if (response.status === 303) {
                const redirectUrl = response.headers.get("HX-Redirect");
                const toastMessage = response.headers.get("X-Message");

                if (toastMessage) {
                    showToast(toastMessage);
                }

                if (redirectUrl) {
                    window.location.href = redirectUrl;
                }

                return;
            }

            return response.text();
        })
        .then(html => {
            form.outerHTML = html;
        })
        .catch(error => {
            console.error("Request failed:", error);
            if (error.status === 500) {
                console.error("An error occurred. Please try again.", "error");
            }
        });
    }
});
