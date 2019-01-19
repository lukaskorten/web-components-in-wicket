package de.korten.tasks.list;

import de.korten.tasks.base.TimeGap;
import de.korten.tasks.TaskEntry;
import de.korten.tasks.TaskService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;

import javax.inject.Inject;
import java.time.LocalDateTime;

import static org.apache.wicket.behavior.Behavior.onAttribute;
import static org.wicketstuff.lambda.components.ComponentFactory.ajaxLink;

public class TaskListItemPanel extends GenericPanel<TaskEntry> {

    private final AjaxLink<Void> completeTaskBtn;

    @Inject
    private TaskService taskService;

    public TaskListItemPanel(String id, IModel<TaskEntry> todoEntry) {
        super(id, todoEntry);

        IModel<String> textModel = LambdaModel.of(todoEntry, TaskEntry::getText);
        IModel<LocalDateTime> createdModel = LambdaModel.of(todoEntry, TaskEntry::getCreated);

        add(new Label("text", textModel));
        add(new TimeGap("created", createdModel));

        add(onAttribute("class", cssClass -> isCompleted() ? "is-completed list-item" : "list-item"));

        completeTaskBtn = ajaxLink("completeTask", this::onTaskComplete);
        add(completeTaskBtn);
        add(ajaxLink("deleteTask", this::onTaskDelete));

    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        boolean completed = isCompleted();
        completeTaskBtn.setVisible(!completed);
    }

    private boolean isCompleted() {
        return getModelObject().getCompleted() != null;
    }


    private <T> void onTaskComplete(AjaxLink<T> link, AjaxRequestTarget target) {
        TaskEntry completedTask = getModelObject();
        taskService.complete(completedTask);
        send(this, Broadcast.BUBBLE, new TaskCompletedPayload(target, completedTask));
    }

    private <T> void onTaskDelete(AjaxLink<T> link, AjaxRequestTarget target) {
        TaskEntry deletedTask = getModelObject();
        taskService.delete(deletedTask);
        send(this, Broadcast.BUBBLE, new TaskDeletedPayload(target, deletedTask));
    }
}
