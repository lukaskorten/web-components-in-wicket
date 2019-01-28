package de.korten.tasks.list;

import de.korten.tasks.TaskEntry;
import de.korten.tasks.TaskListChangedPayload;
import org.apache.wicket.ajax.AjaxRequestTarget;

public class TaskDeletedPayload extends TaskListChangedPayload {

    private TaskEntry deletedTask;

    public TaskDeletedPayload(AjaxRequestTarget target, TaskEntry deletedTask) {
        super(target);
        this.deletedTask = deletedTask;
    }

    public TaskEntry getDeletedTask() {
        return deletedTask;
    }
}
