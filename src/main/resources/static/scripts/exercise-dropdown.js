export function processExerciseDropdowns () {
    document.querySelectorAll(".exercises-drop-down").forEach(dropdown => {
        const input = dropdown.querySelector("input");
        const dropdownList = dropdown.querySelector(".exercises-drop-down-response");

        if (!dropdownList || !input) {
            return;
        }

         function showDropdown() {
             dropdownList.style.display = "block";
         }


        function hideDropdown(event) {
            if (!dropdown.contains(event.relatedTarget)) {
                dropdownList.style.display = "none";
            }
        }

        input.addEventListener("focus", showDropdown);
        dropdownList.addEventListener("focus", showDropdown);
        
        input.addEventListener("blur", hideDropdown);
        dropdownList.addEventListener("blur", hideDropdown);
    });
}

document.addEventListener("click", (event) => {
    const activeDropdowns = document.querySelectorAll(".exercises-drop-down-response");

    if (!activeDropdowns || activeDropdowns.length == 0) {
        return;
    }

    activeDropdowns.forEach((dropdown) => {
        if (!dropdown.contains(event.target) && !event.target.closest(".exercises-drop-down")) {
            dropdown.remove();
        }
    });
});