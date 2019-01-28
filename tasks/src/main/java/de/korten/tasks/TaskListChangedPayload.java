package de.korten.tasks;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class TaskListChangedPayload {

    private AjaxRequestTarget target;

    public TaskListChangedPayload(AjaxRequestTarget target) {

        this.target = target;
    }

    public AjaxRequestTarget getTarget() {
        return target;
    }
}
