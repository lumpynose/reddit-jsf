package org.objecteffects.reddit.jsf.view;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
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
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 */
@Named
@RequestScoped
public class Friends implements Serializable {
    private static final long serialVersionUID = -570500230181100578L;

    private final static Logger log =
            LoggerFactory.getLogger(Friends.class.getSimpleName());

    @Resource
    private ManagedExecutorService executorService;

    @Resource
    private ManagedThreadFactory threadFactory;

    @Inject
    GetFriends getFriends;

    private Future<List<Friend>> result;

    /**
     */
    @PostConstruct
    public void init() {
        log.debug("init");
    }

    static class JobFriends implements Callable<List<Friend>> {
        @Override
        public List<Friend> call() throws Exception {
            log.info("starting getFriends");

            return new GetFriends().getFriends(5, true);
        }
    }

    /**
     * @return
     */
    public String submit() {
        log.debug("submit");

        final ExecutorService tpe =
                new ThreadPoolExecutor(1, 1, 1, TimeUnit.HOURS,
                        new ArrayBlockingQueue<>(1), this.threadFactory);

        this.result = tpe.submit(new JobFriends());

        FacesContext.getCurrentInstance().getExternalContext().getFlash()
                .put("result", this.result);

        return "resultfriends.xhtml?faces-redirect=true";
    }
}
