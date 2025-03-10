document.addEventListener("DOMContentLoaded", function () {
    const menuButtons = document.querySelectorAll(".menu-button");
    const dropdownMenus = document.querySelectorAll(".dropdown-menu");

    menuButtons.forEach(button => {
        button.addEventListener("click", (event) => {
            event.stopPropagation();

            const buttonId = button.getAttribute("data-id");
            const menu = document.querySelector(`.dropdown-menu[data-id="${buttonId}"]`);

            dropdownMenus.forEach(m => {
                if (m !== menu) {
                    m.classList.add("hidden");
                }
            });

            menu.classList.toggle("hidden");
        });
    });

    document.addEventListener("click", (event) => {
        dropdownMenus.forEach(menu => {
            if (!menu.contains(event.target) && !event.target.classList.contains("menu-button")) {
                menu.classList.add("hidden");
            }
        });
    });
});