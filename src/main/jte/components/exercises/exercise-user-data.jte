@param progressive_overlords.entities.dao.ExerciseDao exercise
@param progressive_overlords.entities.dao.ExerciseUserDataDao userData
@param String chartValues
<li class="exercise-dropdown-item" hx-trigger="click" hx-get="/api/v1/exercises/data/${exercise.getId()}" hx-target="#exercise-data-container" hx-swap="innerHTML">
    <img loading="lazy" src="${exercise.getThumbnail()}" />
    <h3>${exercise.getName()}</h3>
</li>
<p>${exercise.getDescription()}</p>
@if(userData == null)
    <h3 class="mt-2">We have no data to show you. You haven't trained this exercise yet...</h3>
@else
    <div class="exercise-kpis">
        <div>
            <p>Max. Weight</p>
            <h3 class="m-0">${String.format("%.2f", userData.getMaxWeight())} kg<span class="font-light"> (4 reps)</span></h3>
        </div>
        <div>
            <p>Avg. reps<span class="font-light"> (per set)</span></p>
            <h3 class="m-0">${String.format("%.2f", userData.getAvgReps())}</h3>
        </div>
        <div>
            <p>ERP<span class="font-light"> (1 rep)</span></p>
            <h3 class="m-0">${String.format("%.2f", userData.getErp())} kg</h3>
        </div>
    </div>
@endif
<div id="chart-container"></div>
<script type="module" src="../scripts/line-chart.js"></script>
<script type="module">
    @if(chartValues != null && !chartValues.isEmpty())
        import { drawLineChart } from '../scripts/line-chart.js';
        const chartValuesJsonString = '${chartValues}';
        const chartValues = JSON.parse(chartValuesJsonString);
        drawLineChart(chartValues);
    @endif
</script>
