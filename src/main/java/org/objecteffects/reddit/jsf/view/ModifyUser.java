package org.objecteffects.reddit.jsf.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;

import org.objecteffects.reddit.jsf.service.ProcessModify;
import org.slf4j.Logger;

import com.objecteffects.reddit.data.Friend;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 *
 */
@Named
@SessionScoped
public class ModifyUser implements Serializable {
    private static final long serialVersionUID = 4123414509789483173L;

    @Inject
    private transient Logger log;

    @Inject
    private ProcessModify processModify;

    @Inject
    @ManagedProperty("#{flash.user}")
    private String user;

    private List<String> item;
    private Future<List<Friend>> result;

    @PostConstruct
    public void init() {
        this.log.debug("init");
    }

    /**
     * @return
     * @throws IOException
     */
    public String getUser() throws IOException {
        this.log.debug("get user: {}", this.user);

        return this.user;
    }

    /**
     * @return
     */
    public Future<List<Friend>> getResult() {
        return this.result;
    }

    /**
     * @return
     */
    public List<String> getItem() {
        return this.item;
    }

    /**
     * @param _item
     */
    public void setItem(final List<String> _item) {
        this.item = _item;

        this.log.debug("data: {}", this.item);
    }

    /**
     * @return
     */
    public String submit() {
        this.log.debug("submit, data: {}", this.item);

        if (this.item.size() > 0) {
            this.log.debug("processing: {}", this.item.get(0));

//            this.result = this.processModify.process(this.item.get(0));
            this.result = this.processModify.processMTF(this.item.get(0));

            FacesContext.getCurrentInstance().getExternalContext().getFlash()
                    .put("result", this.result);

            return "result.xhtml?faces-redirect=true";
        }

        return "";
    }
}
