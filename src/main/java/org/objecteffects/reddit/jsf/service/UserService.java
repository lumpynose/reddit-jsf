package org.objecteffects.reddit.jsf.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;

import com.objecteffects.reddit.jsf.model.RedditUser;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

/**
 *
 */
@Stateless
public class UserService implements Serializable {
    private static final long serialVersionUID = 4297397703485393014L;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private transient Logger log;

    /**
     * @param user
     */
    public void mergeUser(final RedditUser user) {
        this.log.debug("adding/merging: {}", user);

        this.entityManager.merge(user);
    }

    /**
     * @return
     */
    public List<RedditUser> getUsers() {
        final CriteriaQuery<RedditUser> cq =
                this.entityManager.getCriteriaBuilder()
                        .createQuery(RedditUser.class);

        cq.select(cq.from(RedditUser.class));

        return this.entityManager.createQuery(cq).getResultList();
    }

    /**
     * @param id
     * @return
     */
    public RedditUser getUser(final Long id) {
        this.log.debug("getUser: id: {}", id);

        final CriteriaBuilder cb =
                this.entityManager.getCriteriaBuilder();

        final CriteriaQuery<RedditUser> cq =
                cb.createQuery(RedditUser.class);

        final Root<RedditUser> user =
                cq.from(RedditUser.class);

        cq.select(user);
        cq.distinct(true);
        cq.where(cb.equal(user.get("id"), id));

        return this.entityManager.createQuery(cq).getSingleResult();
    }

    /**
     * @param id
     */
    public void deleteUser(final Long id) {
        this.log.debug("deleteUser: id: {}", id);

        final CriteriaBuilder cb =
                this.entityManager.getCriteriaBuilder();

        // create delete
        final CriteriaDelete<RedditUser> cq =
                cb.createCriteriaDelete(RedditUser.class);

        // set the root class
        final Root<RedditUser> user = cq.from(RedditUser.class);

        // set where clause
        cq.where(cb.equal(user.get("id"), id));

        // perform update
        final int deletes =
                this.entityManager.createQuery(cq).executeUpdate();

        this.log.debug("deleted count: {}", Long.valueOf(deletes));
    }

    /**
     * @param user
     */
    public void deleteUser(final RedditUser user) {
        this.log.debug("deleteUser: user: {}", user);

        deleteUser(user.getId());
    }

    /**
     * @param name
     * @return
     */
    public boolean exists(final String name) {
        this.log.debug("exists: name: {}", name);

        final CriteriaBuilder cb =
                this.entityManager.getCriteriaBuilder();

        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        final Root<RedditUser> user =
                cq.from(RedditUser.class);

        cq.select(cb.count(user));
        cq.where(cb.equal(user.get("name"), name));

        final TypedQuery<Long> tq = this.entityManager.createQuery(cq);

        return tq.getSingleResult().longValue() > 0;
    }
}
