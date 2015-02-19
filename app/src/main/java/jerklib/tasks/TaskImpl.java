package jerklib.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jerklib.Session;
import jerklib.events.IRCEvent.Type;
import jerklib.listeners.TaskCompletionListener;

/**
 * An impl of the Task interface. This impl also
 * provides methods for notifications to listeners.
 *
 * @author mohadib
 * @see Session#onEvent(jerklib.tasks.Task)
 * @see Session#onEvent(Task, jerklib.events.IRCEvent.Type...)
 * @see Type
 */
public abstract class TaskImpl implements Task {
    private final List<TaskCompletionListener> listeners = new ArrayList<TaskCompletionListener>();
    private boolean canceled;
    private String name;


    public TaskImpl(String name) {
        this.name = name;
    }


    /* (non-Javadoc)
     * @see jerklib.tasks.Task#getName()
     */
    public String getName() {
        return name;
    }

    /* (non-Javadoc)
     * @see jerklib.tasks.Task#cancel()
     */
    public void cancel() {
        canceled = true;
    }

    /* (non-Javadoc)
     * @see jerklib.tasks.Task#isCanceled()
     */
    public boolean isCanceled() {
        return canceled;
    }

    /**
     * Add a listener to be notified by this Task
     *
     * @param listener
     * @see jerklib.tasks.TaskImpl#taskComplete(Object)
     */
    public void addTaskListener(TaskCompletionListener listener) {
        listeners.add(listener);
    }

    /**
     * remove a listener
     *
     * @param listener
     * @return true if a listener was removed , else false
     */
    public boolean removeTaskListener(TaskCompletionListener listener) {
        return listeners.remove(listener);
    }

    /**
     * get a list of TaskCompletionListeners
     *
     * @return list of listeners
     */
    public List<TaskCompletionListener> getTaskListeners() {
        return Collections.unmodifiableList(listeners);
    }

    /**
     * Can be called to notifiy listeners
     *
     * @param result
     */
    protected void taskComplete(Object result) {
        for (TaskCompletionListener listener : listeners) {
            listener.taskComplete(result);
        }
    }
}
