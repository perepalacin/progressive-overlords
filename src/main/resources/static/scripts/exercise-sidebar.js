document.addEventListener('DOMContentLoaded', function() {
        const sidebar = document.getElementById('exercise-sidebar');
        const openButton = document.getElementById('open-mobile-exercise-sidebar-button');
        const closeButton = document.getElementById('close-mobile-exercise-sidebar-button');

        if (sidebar && openButton && closeButton) {
            const isHiddenInitially = sidebar.classList.contains('mobile-exercise-sidebar-hidden');
            openButton.setAttribute('aria-expanded', !isHiddenInitially);
            closeButton.setAttribute('aria-expanded', isHiddenInitially);

            openButton.addEventListener('click', function() {
                sidebar.classList.toggle('mobile-exercise-sidebar-hidden');

                const isNowHidden = sidebar.classList.contains('mobile-exercise-sidebar-hidden');
                openButton.setAttribute('aria-expanded', !isNowHidden);
                closeButton.setAttribute('aria-expanded', isNowHidden);
            });

            closeButton.addEventListener('click', function() {
                sidebar.classList.toggle('mobile-exercise-sidebar-hidden');

                const isNowHidden = sidebar.classList.contains('mobile-exercise-sidebar-hidden');
                openButton.setAttribute('aria-expanded', !isNowHidden);
                closeButton.setAttribute('aria-expanded', isNowHidden);
            });

            document.addEventListener('click', function(event) {
                const target = event.target;

                const clickedInsideSidebar = sidebar.contains(target);

                let clickedInsideToggleButton = false;
                if (target.closest('.mobile-exercise-sidebar-toggle-button')) {
                     clickedInsideToggleButton = true;
                }

                const isCurrentlyHidden = sidebar.classList.contains('mobile-exercise-sidebar-hidden');

                if (!clickedInsideSidebar && !clickedInsideToggleButton && !isCurrentlyHidden) {
                     sidebar.classList.add('mobile-exercise-sidebar-hidden');
                     openButton.setAttribute('aria-expanded', false);
                }
            });
        }
    });

