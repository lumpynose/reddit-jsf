package com.objecteffects.reddit.jsf.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;

import org.slf4j.Logger;

import com.objecteffects.reddit.jsf.service.FuturesQueue;
import com.objecteffects.reddit.jsf.service.ProcessModify;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.lifecycle.ClientWindowScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 *
 */
@Named
@ClientWindowScoped
public class ModifyUser implements Serializable {
    private static final long serialVersionUID = -1L;

    @Inject
    private transient Logger log;

    @Inject
    private ProcessModify processModify;

    @Inject
    private FuturesQueue futuresQueue;

    @Inject
    @ManagedProperty(value = "#{param.user}")
    private String user;

//    @Inject
//    private Conversation conversation;

//    @Inject
//    @ManagedProperty("#{flash.user}")

//    @Inject
//    FacesContext facesContext;

//    private final String user = (String) FacesContext.getCurrentInstance()
//            .getExternalContext().getFlash().get("user");

    private List<String> item;
    private Future<String> future;

    @SuppressWarnings("boxing")
    private Integer count = 1;

    /**
     */
    @PostConstruct
    public void init() {
        this.log.debug("init user: {}", this.user);

//        this.user = (String) FacesContext.getCurrentInstance()
//                .getExternalContext().getFlash().get("user");
//
//        this.log.debug("init user2: {}", this.user);
    }

    /**
     * @return
     * @throws IOException
     */
    public String getUser() throws IOException {
        this.log.debug("getUser: {}", this.user);

        return this.user;
    }

    public void setUser(final String _user) {
        this.log.debug("setUser: {}", _user);

        this.user = _user;
    }

    /**
     * @return
     */
    public List<String> getItem() {
        this.log.debug("getItem, user: {}, data: {}",
                this.user, this.item);

        return this.item;
    }

    /**
     * @param _item
     */
    public void setItem(final List<String> _item) {
        this.item = _item;

        this.log.debug("setItem, user: {}, data: {}",
                this.user, this.item);
    }

    /**
     * @return the count
     */
    public Integer getCount() {
        this.log.debug("getCount, user: {}, count: {}",
                this.user, this.count);

        return this.count;
    }

    /**
     * @param _count the count to set
     */
    public void setCount(final Integer _count) {
        this.log.debug("setCount, user: {}, count: {}",
                this.user, this.count);

        this.count = _count;
    }

    /**
     * @return
     */
    public String submit() {
        this.log.debug("submit, user: {}, data: {}", this.user, this.item);

        if (this.item.size() > 0) {
            this.log.debug("processing: {}, {}",
                    this.user, this.item.get(0));

//            if (!this.conversation.isTransient()) {
//                this.conversation.end();
//            }

            this.future = this.processModify.process(this.user, this.count,
                    this.item.get(0));

            this.log.debug("future: {}", this.future);

            this.futuresQueue.add(this.future);

//            FacesContext.getCurrentInstance().getExternalContext()
//                    .getFlash().put("future", this.future);

            return "result.xhtml?faces-redirect=true";
        }

        return "";
    }
}
