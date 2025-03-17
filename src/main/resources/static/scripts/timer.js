function startElapsedTimer(startTimestamp) {
    const timerElement = document.getElementById("timer");

    const startTime = new Date(startTimestamp);

    if (isNaN(startTime.getTime())) {
        console.error("Invalid timestamp:", startTimestamp);
        timerElement.textContent = "Invalid Time";
        return;
    }

    function updateTimer() {
        const currentTime = Date.now();
        const elapsedTime = Math.floor((currentTime - startTime.getTime()) / 1000);

        const hours = String(Math.floor(elapsedTime / 3600)).padStart(2, '0');
        const minutes = String(Math.floor((elapsedTime % 3600) / 60)).padStart(2, '0');
        const seconds = String(elapsedTime % 60).padStart(2, '0');

        timerElement.textContent = `${hours}:${minutes}:${seconds}`;
    }

    updateTimer();
    setInterval(updateTimer, 1000);
}

document.addEventListener("DOMContentLoaded", function () {
    const timerElement = document.getElementById("timer");
    const startTimestamp = timerElement.textContent.trim();

    if (startTimestamp) {
        startElapsedTimer(startTimestamp);
    }
});
