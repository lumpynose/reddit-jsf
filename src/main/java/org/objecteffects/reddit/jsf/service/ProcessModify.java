package org.objecteffects.reddit.jsf.service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 */
@ApplicationScoped
public class ProcessModify {
//    @Inject
//    @SuppressWarnings("unused")
//    private transient Logger log;

    private final static Logger log =
            LoggerFactory.getLogger(ProcessModify.class.getSimpleName());

    @Resource
    ManagedExecutorService executorService;

    /**
     * @param user
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Future<String> process(final String user) {
        Future<String> result;
        final String resultValue = "(nothing)";

        try {
            log.info("running {}", user);
            result = this.executorService.submit(new JobTask(user));
        }
        catch (final RejectedExecutionException ree) {
            return null;
        }

//        try {
//            resultValue = result.get();
//
//            log.debug("result: {}", resultValue);
//        }
//        catch (InterruptedException | ExecutionException ex) {
//            log.error("exception: {}", ex);
//        }

        return result;
    }

    static class JobTask implements Callable<String> {
        private final int JOB_EXECUTION_TIME = 15000;
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
