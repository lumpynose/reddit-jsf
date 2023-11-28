package com.objecteffects.reddit.jsf.service;

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
import com.objecteffects.reddit.method.UpVotePosts;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedThreadFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 *
 */
@ApplicationScoped
public class ProcessModify implements Serializable {
    private static final long serialVersionUID = 8210622038575525242L;

    private final static Logger log =
            LoggerFactory.getLogger(ProcessModify.class.getSimpleName());

    @Inject
    UpVotePosts upVotePosts;

    @Resource
    private ManagedThreadFactory threadFactory;

    private ExecutorService tpe = null;

    /**
     */
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
    public Future<String> process(final String user, final String modify) {
        Future<String> result;

        try {
            log.info("running {}, {}", user, modify);

            final JobTask jobTask = new JobTask(user, modify);
            jobTask.setUpVotePosts(this.upVotePosts);
            result = this.tpe.submit(jobTask);
        }
        catch (final RejectedExecutionException ree) {
            return null; // new CompletableFuture<>();
        }

        return result;
    }

    static class JobTask implements Callable<String> {
        private final String user, modify;
        private UpVotePosts upVotePosts;

        public JobTask(final String _user, final String _modify) {
            this.user = _user;
            this.modify = _modify;
        }

        public void setUpVotePosts(final UpVotePosts upv) {
            log.debug("upv: {}", upv);

            this.upVotePosts = upv;
        }

        @Override
        public String call() throws Exception {
            try {
                log.debug("started {}, {}", this.user, this.modify);
//                Thread.sleep(this.JOB_EXECUTION_TIME);

                switch (this.modify) {
                case "upvote":
                    this.upVotePosts.upVotePosts(this.user, 1, null);
                    break;

                default:
                    break;
                }

                log.debug("finished {}, {}", this.user, this.modify);
            }
            catch (final InterruptedException ex) {
                log.error("InterruptedException: {}", ex);
            }

            return "finished";
        }
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
}
