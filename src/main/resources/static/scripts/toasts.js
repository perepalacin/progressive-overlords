window.addEventListener("DOMContentLoaded", () => {
    const message = localStorage.getItem("toastMessage");
    if (message) {
        showToast(message); // your custom toast function
        localStorage.removeItem("toastMessage");
    }
});

document.body.addEventListener("htmx:afterRequest", function(event) {
    const func = event.detail.xhr.getResponseHeader("HX-Trigger");

    if (func !== "ShowToast") {
        return;
    }

    const message = event.detail.xhr.getResponseHeader("X-Message");
    const redirectUrl = event.detail.xhr.getResponseHeader("HX-Redirect");

    if (redirectUrl && message) {
        localStorage.setItem("toastMessage", toastMessage);
    } else if (message) {
        showToast(message);
    }
});

export function showToast (message) {
    if (!message) {
        return;
    }

    let toastContainer = document.getElementById("toastContainer");

    if (!toastContainer) {
        return;
    }

    let toast = document.createElement("div");

    let code = message.split(":")[0];

    if (code === "success") {
        toast.className = `toast toast-success`;
    } else {
        toast.className = `toast toast-error`;
    }

    let messageSpan = document.createElement("span");
    messageSpan.textContent = message.split(":")[1];

    let closeButton = document.createElement("button");
    closeButton.innerHTML = `<img src="../icons/x.svg" style="width: 1rem" />`;
    closeButton.className = "ghost-button-icon toast-close";
    closeButton.onclick = function() {
        dismissToast(toast);
    };

    toast.appendChild(messageSpan);
    toast.appendChild(closeButton);
    toastContainer.appendChild(toast);

    setTimeout(() => {
        toast.classList.remove("toast-hidden");
        toast.classList.add("toast-visible");
    }, 10);
    
    setTimeout(() => {
        dismissToast(toast);
    }, 3000);
}

function dismissToast(toast) {
    toast.classList.remove("toast-visible");
    toast.classList.add("toast-hidden");

    setTimeout(() => {
        toast.remove();
    }, 500); 
}