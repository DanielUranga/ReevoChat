package jerklib.tasks;

import jerklib.Session;
import jerklib.events.IRCEvent;
import jerklib.events.IRCEvent.Type;

/**
 * An augmented {@link jerklib.tasks.TaskImpl} that only executes once
 * as it cancels itself out of the task queue once completed,
 * <em>even if it fails (= throws an <code>Exception</code>)</em>.
 *
 * @author pbleser
 * @see OnceUntilSucceedsTaskImpl
 * @see Session#onEvent(jerklib.tasks.Task)
 * @see Session#onEvent(Task, jerklib.events.IRCEvent.Type...)
 * @see jerklib.tasks.TaskImpl
 * @see Type
 */
public abstract class OnceTaskImpl extends TaskImpl {
    public OnceTaskImpl(String name) {
        super(name);
    }

    /**
     * Process the {@link IRCEvent}, once.
     *
     * @param e the {@link IRCEvent} to process
     */
    public abstract void receiveEventOnce(IRCEvent e);

    /**
     * {@inheritDoc}
     */
    public final void receiveEvent(IRCEvent e) {
        try {
            receiveEventOnce(e);
        } finally {
            this.cancel();
        }
    }

}