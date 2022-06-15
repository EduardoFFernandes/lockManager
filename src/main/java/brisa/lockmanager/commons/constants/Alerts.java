package brisa.lockmanager.commons.constants;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Alerts extends MessageConstant {

    public static Alert success() {
        return new Alert(SUCCESS_ALERT, MESSAGE_SUCCESS_OPERATION);
    }

    public static Alert success(final String messageKey) {
        return new Alert(SUCCESS_ALERT, messageKey);
    }

    public static Alert error() {
        return new Alert(ERROR_ALERT, MESSAGE_ERROR_OPERATION);
    }

    public static Alert error(final boolean autohide, final String style) {
        return new Alert(ERROR_ALERT, MESSAGE_ERROR_OPERATION, autohide, style);
    }

    public static Alert error(final String messageKey) {
        return new Alert(ERROR_ALERT, messageKey);
    }

    public static Alert error(final String messageTag, final boolean autohide, final String style, final Object... args) {
        return new Alert(ERROR_ALERT, messageTag, autohide, style, args);
    }

    public static Alert info(final String messageKey) {
        return new Alert(INFO_ALERT, messageKey);
    }

    public static Alert info(final String messageKey, final boolean autohide, final String style, final Object... args) {
        return new Alert(INFO_ALERT, messageKey, autohide, style, args);
    }

    public static Alert warning() {
        return new Alert(WARNING_ALERT, MESSAGE_WARNING_OPERATION);
    }

    public static Alert warning(final String messageKey) {
        return new Alert(WARNING_ALERT, messageKey);
    }

    public static Alert warning(final boolean autohide, final String style) {
        return new Alert(WARNING_ALERT, MESSAGE_WARNING_OPERATION, autohide, style);
    }

    public static Alert warning(final String messageKey, final boolean autohide, final String style, final Object... args) {
        return new Alert(WARNING_ALERT, messageKey, autohide, style, args);
    }

    // ---------------------------------------------------------------------------------------------
    // Inner classes.
    // ---------------------------------------------------------------------------------------------
    public static class Alert {

        public static final String STRING = "alert";

        public static final String TOP_CENTER = "top center";
        public static final String TOP_LEFT = "top left";
        public static final String TOP_RIGHT = "top right";
        public static final String BOTTOM_MIDDLE = "bottom middle";
        public static final String BOTTOM_LEFT = "bottom left";
        public static final String BOTTOM_RIGHT = "bottom right";
        public static final String RIGHT_MIDDLE = "right middle";
        public static final String LEFT_MIDDLE = "left middle";

        private final String type;
        private final String message;
        private final Object[] args;
        @JsonIgnore
        private final String style;
        @JsonIgnore
        private final boolean autohide;

        public Alert(final String type, final String message) {
            super();
            this.type = type;
            this.message = message;
            this.autohide = true;
            this.style = TOP_CENTER;
            this.args = null;
        }

        public Alert(final String type, final String message, final Object[] args) {
            super();
            this.type = type;
            this.message = message;
            this.autohide = true;
            this.style = TOP_CENTER;
            this.args = args;
        }

        public Alert(final String type, final String message, final boolean autohide, final String style) {
            super();
            this.type = type;
            this.message = message;
            this.autohide = autohide;
            this.style = style;
            this.args = null;
        }

        public Alert(final String type, final String message, final boolean autohide, final String style, final Object[] args) {
            super();
            this.type = type;
            this.message = message;
            this.autohide = autohide;
            this.style = style;
            this.args = args;
        }

        // ---------------------------------------------------------------------------------------------
        // get/set.
        // ---------------------------------------------------------------------------------------------
        public String getType() {
            return this.type;
        }

        public String getMessage() {
            return this.message;
        }

        public boolean isAutohide() {
            return this.autohide;
        }

        public String getStyle() {
            return this.style;
        }

        public Object[] getArgs() {
            return this.args;
        }

    }
}
