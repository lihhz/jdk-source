package java.util.concurrent;

/**
 * A service that decouples the production of new asynchronous tasks
 * from the consumption of the results of completed tasks.  Producers
 * <tt>submit</tt> tasks for execution. Consumers <tt>take</tt>
 * completed tasks and process their results in the order they
 * complete.  A <tt>CompletionService</tt> can for example be used to
 * manage asynchronous IO, in which tasks that perform reads are
 * submitted in one part of a program or system, and then acted upon
 * in a different part of the program when the reads complete,
 * possibly in a different order than they were requested.
 *
 * <p>Typically, a <tt>CompletionService</tt> relies on a separate
 * {@link Executor} to actually execute the tasks, in which case the
 * <tt>CompletionService</tt> only manages an internal completion
 * queue. The {@link ExecutorCompletionService} class provides an
 * implementation of this approach.
 *
 * <p>Memory consistency effects: Actions in a thread prior to
 * submitting a task to a {@code CompletionService}
 * <a href="package-summary.html#MemoryVisibility"><i>happen-before</i></a>
 * actions taken by that task, which in turn <i>happen-before</i>
 * actions following a successful return from the corresponding {@code take()}.
 *
 */
public interface CompletionService<V> {
    /**
     * Submits a value-returning task for execution and returns a Future
     * representing the pending results of the task.  Upon completion,
     * this task may be taken or polled.
     *
     * @param task the task to submit
     * @return a Future representing pending completion of the task
     * @throws RejectedExecutionException if the task cannot be
     *         scheduled for execution
     * @throws NullPointerException if the task is null
     */
    // 提交要执行的值返回任务，并返回表示挂起的任务结果的 Future。
    Future<V> submit(Callable<V> task);

    /**
     * Submits a Runnable task for execution and returns a Future
     * representing that task.  Upon completion, this task may be
     * taken or polled.
     *
     * @param task the task to submit
     * @param result the result to return upon successful completion
     * @return a Future representing pending completion of the task,
     *         and whose <tt>get()</tt> method will return the given
     *         result value upon completion
     * @throws RejectedExecutionException if the task cannot be
     *         scheduled for execution
     * @throws NullPointerException if the task is null
     */
    // 提交要执行的 Runnable 任务，并返回一个表示任务完成的 Future，可以提取或轮询此任务。
    Future<V> submit(Runnable task, V result);

    /**
     * Retrieves and removes the Future representing the next
     * completed task, waiting if none are yet present.
     *
     * @return the Future representing the next completed task
     * @throws InterruptedException if interrupted while waiting
     */
    // 获取并移除表示下一个已完成任务的 Future，如果目前不存在这样的任务，则等待。
    Future<V> take() throws InterruptedException;


    /**
     * Retrieves and removes the Future representing the next
     * completed task or <tt>null</tt> if none are present.
     *
     * @return the Future representing the next completed task, or
     *         <tt>null</tt> if none are present
     */
    // 获取并移除表示下一个已完成任务的 Future，如果不存在这样的任务，则返回 null。
    Future<V> poll();

    /**
     * Retrieves and removes the Future representing the next
     * completed task, waiting if necessary up to the specified wait
     * time if none are yet present.
     *
     * @param timeout how long to wait before giving up, in units of
     *        <tt>unit</tt>
     * @param unit a <tt>TimeUnit</tt> determining how to interpret the
     *        <tt>timeout</tt> parameter
     * @return the Future representing the next completed task or
     *         <tt>null</tt> if the specified waiting time elapses
     *         before one is present
     * @throws InterruptedException if interrupted while waiting
     */
    // 获取并移除表示下一个已完成任务的 Future，如果目前不存在这样的任务，则将等待指定的时间（如果有必要）。
    Future<V> poll(long timeout, TimeUnit unit) throws InterruptedException;
}
