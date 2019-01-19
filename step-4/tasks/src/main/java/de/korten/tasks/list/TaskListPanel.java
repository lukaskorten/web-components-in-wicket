package de.korten.tasks.list;

import de.korten.tasks.TaskEntry;
import de.korten.tasks.TaskListChangedPayload;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.GenericPanel;

import java.util.List;

public class TaskListPanel extends GenericPanel<List<TaskEntry>> {

    public TaskListPanel(String id) {
        super(id, new TaskListModel());
        setOutputMarkupId(true);

        add(new ListView<TaskEntry>("tasks", getModel()) {
            @Override
            protected void populateItem(ListItem<TaskEntry> item) {

                item.add(new TaskListItemPanel("task", item.getModel()));

            }
        });
    }

    @Override
    public void onEvent(IEvent<?> event) {
        super.onEvent(event);

        if (event.getPayload() instanceof TaskListChangedPayload) {
            detachModel();
        }
    }
}
