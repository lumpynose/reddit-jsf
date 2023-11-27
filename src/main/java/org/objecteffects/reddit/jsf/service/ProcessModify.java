package org.objecteffects.reddit.jsf.service;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.objecteffects.reddit.data.Friend;
import com.objecteffects.reddit.method.GetFriends;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.enterprise.concurrent.ManagedThreadFactory;
import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 */
@ApplicationScoped
public class ProcessModify implements Serializable {
    private static final long serialVersionUID = 8210622038575525242L;

    private final static Logger log =
            LoggerFactory.getLogger(ProcessModify.class.getSimpleName());

    @Resource
    private ManagedExecutorService executorService;

    @Resource
    private ManagedThreadFactory threadFactory;

//    @Inject
//    private static FriendsService friendsService;

    private ExecutorService tpe = null;

    @PostConstruct
    public void init() {
        this.tpe = new ThreadPoolExecutor(1, 1, 1, TimeUnit.HOURS,
                new ArrayBlockingQueue<>(1), this.threadFactory);
    }

    /**
     * @param user
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Future<String> process(final String user) {
        Future<String> result;

        try {
            log.info("running {}", user);
            result = this.tpe.submit(new JobTask(user));
        }
        catch (final RejectedExecutionException ree) {
            return null; // new CompletableFuture<>();
        }

        return result;
    }

    /**
     * @param user
     * @return
     */
    public Future<List<Friend>> processFriends(final String user) {
        Future<List<Friend>> result;

        try {
            log.info("running getFriends");
//            result = this.tpe.submit(new JobTask(user));
            result = this.tpe.submit(new JobFriends());
        }
        catch (final RejectedExecutionException ree) {
            log.error("RejectedExecutionException: {}", ree);

            return null; // new CompletableFuture<>();
        }

        return result;
    }

    static class JobFriends implements Callable<List<Friend>> {
        @Override
        public List<Friend> call() throws Exception {
            log.info("starting getFriends");

            return new GetFriends().getFriends(5, true);
        }
    }

    static class JobTask implements Callable<String> {
        private final int JOB_EXECUTION_TIME = 10000;
        private final String user;

        public JobTask(final String _user) {
            this.user = _user;
        }

        @Override
        public String call() throws Exception {
            try {
                log.debug("started {}", this.user);
                Thread.sleep(this.JOB_EXECUTION_TIME);
                log.debug("finished {}", this.user);
            }
            catch (final InterruptedException ex) {
                log.error("InterruptedException: {}", ex);
            }

            return "finished";
        }
    }
}
