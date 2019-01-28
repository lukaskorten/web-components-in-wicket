package de.korten.tasks.list;

import de.korten.tasks.TaskEntry;
import de.korten.tasks.TaskService;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;

import javax.inject.Inject;
import java.util.List;

public class TaskListModel extends LoadableDetachableModel<List<TaskEntry>> {

    @Inject
    private TaskService taskService;

    public TaskListModel() {
        Injector.get().inject(this);
    }

    @Override
    protected List<TaskEntry> load() {
        return taskService.findAll();
    }
}
