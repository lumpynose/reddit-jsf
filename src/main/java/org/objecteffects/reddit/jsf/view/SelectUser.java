package org.objecteffects.reddit.jsf.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.objecteffects.reddit.jsf.model.RedditUser;
import org.objecteffects.reddit.jsf.service.UserService;
import org.slf4j.Logger;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 *
 */
@Named
@RequestScoped
public class SelectUser implements Serializable {
    private static final long serialVersionUID = -570500230181100578L;

    @Inject
    private transient Logger log;

    @Inject
    private UserService userService;

    private String selectUser = null;
    private String addUser = null;
    private String user = null;

//    @PostConstruct
//    public void init() {
//        this.log.debug("init");
//    }

    /**
     * @return List of User
     * @throws IOException
     * @throws InterruptedException
     */
    public List<RedditUser> getUsers()
            throws IOException, InterruptedException {
        this.log.debug("getUsers");

        final List<RedditUser> users =
                this.userService.getUsers();

        return users;
    }

    /**
     * @return List of User names
     */
    public List<String> getUserNames() {
        final List<RedditUser> users =
                this.userService.getUsers();

        final List<String> userNames = new ArrayList<>();

        for (final RedditUser user : users) {
            userNames.add(user.getName());
        }

        this.log.debug("userNames: {}", userNames);

        return userNames;
    }

    /**
     * @param _user
     */
    public void setSelectUser(final String _user) {
        this.log.debug("selectUser: {}", _user);

        if (_user == null || _user.isEmpty()) {
            return;
        }

        this.selectUser = _user;

        final String msg = this.selectUser + " selected.";

        final FacesMessage facesMsg =
                new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);

        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    /**
     * @return selectUser
     */
    public String getSelectUser() {
        return this.selectUser;
    }

    /**
     * @param _user
     */
    public void setaddUser(final String _user) {
        this.log.debug("addUser: {}", _user);

        if (this.selectUser != null && !this.selectUser.isEmpty()) {
            return;
        }

        this.addUser = _user;

        this.userService.mergeUser(new RedditUser(this.addUser));

        final String msg = this.addUser + " added.";

        final FacesMessage facesMsg =
                new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);

        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    /**
     * @return addUser
     */
    public String getaddUser() {
        return this.addUser;
    }

    public String getUser() {
        return this.user;
    }

    /**
     * @return nothing
     */
    public String result() {
        if (this.selectUser != null) {
            this.user = this.selectUser;
        }

        if (this.addUser != null) {
            this.user = this.addUser;
        }

        this.log.debug("user: {}", this.user);

        this.selectUser = null;
        this.addUser = null;

        if (this.user == null) {
            return "";
        }

        FacesContext.getCurrentInstance().getExternalContext().getFlash()
                .put("user", this.user);

        return "/modifyuser.xhtml?faces-redirect=true";
    }
}
