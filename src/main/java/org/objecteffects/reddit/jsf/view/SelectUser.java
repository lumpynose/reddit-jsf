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

    private String selectUser;
    private String addUser;

//    @PostConstruct
//    public void init() {
//        this.log.info("init");
//    }

    /**
     * @return List of User
     * @throws IOException
     * @throws InterruptedException
     */
    public List<RedditUser> getUsers()
            throws IOException, InterruptedException {
        this.log.info("getUsers");

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

        this.log.info("userNames: {}", userNames);

        return userNames;
    }

    /**
     * @param _user
     */
    public void setSelectUser(final String _user) {
        this.log.info("selectUser: {}", _user);

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
        this.log.info("addUser: {}", _user);

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

    /**
     * @return nothing
     */
    public String result() {
        String user = null;

        if (this.selectUser != null) {
            user = this.selectUser;
        }

        if (this.addUser != null) {
            user = this.addUser;
        }

        this.log.info("user: {}", user);

        this.selectUser = null;
        this.addUser = null;

        if (user == null) {
            return null;
        }

        return "/modifyuser.xhtml?faces-redirect=true";
    }
}
