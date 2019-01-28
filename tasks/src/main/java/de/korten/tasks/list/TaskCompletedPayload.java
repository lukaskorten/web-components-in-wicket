package de.korten.tasks.list;

import de.korten.tasks.TaskEntry;
import de.korten.tasks.TaskListChangedPayload;
import org.apache.wicket.ajax.AjaxRequestTarget;

public class TaskCompletedPayload extends TaskListChangedPayload {

    private TaskEntry completedTask;

    public TaskCompletedPayload(AjaxRequestTarget target, TaskEntry completedTask) {
        super(target);
        this.completedTask = completedTask;
    }

    public TaskEntry getCompletedTask() {
        return completedTask;
    }
}
