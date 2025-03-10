document.addEventListener("DOMContentLoaded", function () {
    const accordionButtons = document.querySelectorAll(".accordion-button");

    accordionButtons.forEach(button => {
        button.addEventListener("click", (event) => {
            event.stopPropagation();

            const buttonId = button.getAttribute("data-id");
            const accordion = document.querySelector(`.accordion[data-id="${buttonId}"]`);
            const icon = button.querySelector(".accordion-icon");

            if (!accordion) return;

            if (icon) {
                icon.style.transform = "rotate(180deg)";
            }

            accordion.classList.toggle("hidden");

            if (icon) {
                icon.style.transform = accordion.classList.contains("hidden") ? "rotate(0deg)" : "rotate(180deg)";
            }
        });
    });
});