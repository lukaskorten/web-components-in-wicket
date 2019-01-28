package de.korten.tasks;

import de.korten.tasks.base.BasePage;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/tasks")
public class TasksPage extends BasePage {

    public TasksPage() {

        add(new TasksPanel("todos"));

    }
}
