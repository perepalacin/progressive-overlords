@param progressive_overlords.entities.dao.WorkoutDao workout

@template.main(tabName = workout.getName(),content = @`
    @template.components.nav.sidebar(activeTab = "routines")
        <div class="flex-column align-center text-center mt-2 w-full">
            <h1 style="font-size:3rem;">
                Congratulations!
            </h1>
            <p>You finished a workout!</p>
            <h2 class="mt-2">You are on fire!</h2>
    <%--        TODO: FETCH THE HISTORY!--%>
    <%--        I think it would be worth storing a summary--%>
            <p>You worked out a total of</p>
            <br/>
            <h4 style="font-size: 2rem" class="flex-row align-center">
                <img src="../icons/flame.svg" style="width: 2rem; height: 2rem; margin-right: 0.5rem;">
                X times
            </h4>
            <br/>
            <p>in the last 30 days! Keep up the good work!</p>
            <h3 class="mt-2">You lifted a total of</h3>
            <h4 style="font-size:2rem;">${workout.getAggregatedWorkoutData().getVolume()} kg</h4>
            @if(workout.getAggregatedWorkoutData().getVolume() < 70)
                <p>That's like lifting another human being!</p>
            @elseif(workout.getAggregatedWorkoutData().getVolume() < 150)
                <p>You just lifted the weight of a tiger!</p>
            @elseif(workout.getAggregatedWorkoutData().getVolume() < 300)
                <p>That’s like lifting a baby elephant!</p>
            @elseif(workout.getAggregatedWorkoutData().getVolume() < 500)
                <p>You lifted the weight of a grand piano!</p>
            @elseif(workout.getAggregatedWorkoutData().getVolume() < 1000)
                <p>That's a whole polar bear worth of lifting!</p>
            @elseif(workout.getAggregatedWorkoutData().getVolume() < 2000)
                <p>You’ve lifted a small car! Incredible!</p>
            @elseif(workout.getAggregatedWorkoutData().getVolume() < 5000)
                <p>That’s equivalent to a T-Rex fossil. Beast mode!</p>
            @elseif(workout.getAggregatedWorkoutData().getVolume() < 10000)
                <p>You lifted a whole African elephant! Legend!</p>
            @else
                <p>You moved a freaking school bus. Are you even human?!</p>
            @endif
        </div>
<%--    <canvas id="confetti"></canvas>--%>
<%--    <script type="module" src="../scripts/balloons.js"></script>--%>
`)
