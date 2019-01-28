package de.korten.tasks.create;

import de.korten.tasks.TaskEntry;
import de.korten.tasks.TaskService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;

import javax.inject.Inject;

public class CreateTaskPanel extends GenericPanel<TaskEntry> {

    @Inject
    private TaskService taskService;

    public CreateTaskPanel(String id) {
        super(id, new Model<>(new TaskEntry()));

        setOutputMarkupId(true);

        Form<Void> form = new Form<>("form");

        IModel<String> textModel = LambdaModel.of(getModel(), TaskEntry::getText, TaskEntry::setText);
        TextField<String> todoInput = new TextField<>("todoInput", textModel);
        todoInput.setOutputMarkupId(true);
        form.add(todoInput);

        AjaxButton submitButton = new AjaxButton("submitButton", form) {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);

                IModel<TaskEntry> taskModel = CreateTaskPanel.this.getModel();
                taskService.save(taskModel.getObject());
                taskModel.setObject(new TaskEntry());

                target.focusComponent(todoInput);

                send(this, Broadcast.BUBBLE, new TaskCreatedPayload(target));
            }
        };
        form.add(submitButton);

        add(form);
    }
}
