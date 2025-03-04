@param java.util.List<progressive_overlords.entities.dao.WorkoutDao> templates

<p>You have a total of ${templates.size()} templates</p>
<div>
    @for(progressive_overlords.entities.dao.WorkoutDao template : templates)
        <p>${template.getId()} - ${template.getName()}</p>
    @endfor
</div>