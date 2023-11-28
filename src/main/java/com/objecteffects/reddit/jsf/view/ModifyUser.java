package org.objecteffects.reddit.jsf.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;

import org.slf4j.Logger;

import com.objecteffects.reddit.jsf.service.ProcessModify;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 *
 */
@Named
@ApplicationScoped
public class ModifyUser implements Serializable {
    private static final long serialVersionUID = 4123414509789483173L;

    @Inject
    private transient Logger log;

    @Inject
    private ProcessModify processModify;

//    @Inject
//    @ManagedProperty("#{flash.user}")
    private final String user = (String) FacesContext.getCurrentInstance()
            .getExternalContext().getFlash().get("user");

    private List<String> item;
    private Future<String> result;

    /**
     */
    @PostConstruct
    public void init() {
        this.log.debug("init");

//        this.user = (String) FacesContext.getCurrentInstance()
//                .getExternalContext().getFlash().get("user");
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
//    public Future<String> getResult() {
//        return this.result;
//    }

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
        this.log.debug("submit, user: {}, data: {}", this.user, this.item);

        if (this.item.size() > 0) {
            this.log.debug("processing: {}, {}",
                    this.user, this.item.get(0));

            this.result =
                    this.processModify.process(this.user, this.item.get(0));

            FacesContext.getCurrentInstance().getExternalContext()
                    .getFlash().put("result", this.result);

            return "result.xhtml?faces-redirect=true";
        }

        return "";
    }
}
