package com.objecteffects.reddit.jsf.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 */
@Entity
public class RedditUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1)
    @NotNull
    @Column(nullable = false, unique = true)
    private @NotNull String name;

    /**
     *
     */
    public RedditUser() {
        // empty
    }

    /**
     * @param _name
     */
    public RedditUser(final String _name) {
        this.name = _name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String _name) {
        this.name = _name;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    public void setId(final Long _id) {
        this.id = _id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        final RedditUser other = (RedditUser) obj;

        return this.name.equalsIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return "RedditUser [id=" + this.id + ", name=" + this.name + "]";
    }
}
