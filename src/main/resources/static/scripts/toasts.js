document.body.addEventListener("htmx:afterRequest", function(event) {
        let message = event.detail.xhr.getResponseHeader("X-Message");
        showToast(message);
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
            toast.className = `relative bg-green-500 text-white px-4 py-2 rounded-lg shadow-lg flex items-center transition-transform transform translate-x-full opacity-0`;
        } else {
            toast.className = `relative bg-red-500 text-white px-4 py-2 rounded-lg shadow-lg flex items-center transition-transform transform translate-x-full opacity-0`;
        }

        let messageSpan = document.createElement("span");
        messageSpan.textContent = message.split(":")[1];

        let closeButton = document.createElement("button");
        closeButton.innerHTML = "&#10006;";
        closeButton.className = "ml-4 text-white font-bold hover:text-gray-300";
        closeButton.onclick = function() {
            dismissToast(toast);
        };

        toast.appendChild(messageSpan);
        toast.appendChild(closeButton);
        toastContainer.appendChild(toast);

        setTimeout(() => {
            toast.classList.remove("translate-x-full", "opacity-0");
            toast.classList.add("translate-x-0", "opacity-100");
        }, 10);

        setTimeout(() => {
            dismissToast(toast);
        }, 3000);
}

function dismissToast(toast) {
    toast.classList.remove("translate-x-0", "opacity-100");
    toast.classList.add("translate-x-full", "opacity-0");

    setTimeout(() => {
        toast.remove();
    }, 500);
}
