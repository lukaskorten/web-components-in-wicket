package de.korten.tasks.create;

import de.korten.tasks.TaskListChangedPayload;
import org.apache.wicket.ajax.AjaxRequestTarget;

public class TaskCreatedPayload extends TaskListChangedPayload {

    public TaskCreatedPayload(AjaxRequestTarget target) {
        super(target);
    }

}
