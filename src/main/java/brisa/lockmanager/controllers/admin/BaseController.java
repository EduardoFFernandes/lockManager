package brisa.lockmanager.controllers.admin;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Controller;

import brisa.lockmanager.Routes;
import brisa.lockmanager.commons.components.Messages;
import brisa.lockmanager.commons.constants.Alerts;
import brisa.lockmanager.commons.constants.Alerts.Alert;
import brisa.lockmanager.commons.constants.ControllerConstant;

@Controller
public abstract class BaseController<T extends PagingAndSortingRepository<?, ?>> implements Routes, ControllerConstant {

    @Autowired
    protected T repository;
    @Autowired
    protected ResourceLoader resourceLoader;
    @Autowired
    protected Messages messages;

    protected String redirect(final String route) {
        return REDIRECT + route;
    }

    protected String forward(final String route) {
        return REDIRECT + route;
    }

    // ---------------------------------------------------------------------------------------------
    // Inner Classes
    // ---------------------------------------------------------------------------------------------
    public class CustomLocalizedDateEditor extends CustomDateEditor {

        private static final boolean ALLOW_EMPTY = true;

        // ---------------------------------------------------------------------------------------------
        // Constructors
        // ---------------------------------------------------------------------------------------------
        public CustomLocalizedDateEditor(final String dateFormat) {
            super(new SimpleDateFormat(dateFormat), ALLOW_EMPTY);
        }
    }

    public class RestOkResponse {

        public final boolean success = true;
        public final Alert alert;

        // ---------------------------------------------------------------------------------------------
        // Constructors
        // ---------------------------------------------------------------------------------------------
        public RestOkResponse() {
            super();
            this.alert = Alerts.success(BaseController.this.messages.get(MESSAGE_SUCCESS_OPERATION));
        }

        public RestOkResponse(final String messageKey) {
            super();
            this.alert = Alerts.success(BaseController.this.messages.get(messageKey));
        }
    }
}
