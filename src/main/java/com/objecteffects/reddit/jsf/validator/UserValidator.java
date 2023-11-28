package com.objecteffects.reddit.jsf.validator;

import java.io.Serializable;

import com.objecteffects.reddit.jsf.service.UserService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 *
 */
@FacesValidator(managed = true)
@Named
@RequestScoped
public class UserValidator implements Validator<String>, Serializable {
    private static final long serialVersionUID = -7192868908359756394L;

    @Inject
    private UserService userService;

    public UserValidator() {
        // empty
    }

    @Override
    public void validate(final FacesContext context,
            final UIComponent component,
            final String name) throws ValidatorException {
        if (name == null || name.isEmpty()) {
            return;
        }

        if (this.userService.exists(name)) {
            final FacesMessage msg =
                    new FacesMessage("Name already in use");

            throw new ValidatorException(msg);
        }
    }
}
