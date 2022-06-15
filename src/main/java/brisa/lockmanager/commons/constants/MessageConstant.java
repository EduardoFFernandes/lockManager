package brisa.lockmanager.commons.constants;

public interface MessageConstant {

    public static final String SYSTEM_NAME = "system.name";

    public static final String SUCCESS_ALERT = "success";
    public static final String ERROR_ALERT = "error";
    public static final String WARNING_ALERT = "warning";
    public static final String INFO_ALERT = "info";

    public static final String MESSAGE_SUCCESS_OPERATION = "message.success.operation";
    public static final String MESSAGE_SUCCESS_CODE_SENT_SUCCESSFULLY = "message.success.code.sent.successfully";

    public static final String MESSAGE_INFO_CANT_DELETE_LAST_USER = "message.info.cant.delete.last.user";

    public static final String MESSAGE_ERROR_OPERATION = "message.error.operation";
    public static final String MESSAGE_ERROR_API_BAD_REQUEST = "message.error.api.bad.request";
    public static final String MESSAGE_ERROR_API_STRIPE_INTEGRATION = "message.error.api.stripe.integration";
    public static final String MESSAGE_ERROR_PIN_ACTIVE_ALREADY_EXISTS = "message.error.pin.active.already.exists";
    public static final String MESSAGE_ERROR_SINGLE_EMERGENCY_PIN = "message.error.single.emergency.pin";
    public static final String MESSAGE_ERROR_SINGLE_SPECIAL_PIN = "message.error.single.special.pin";
    public static final String MESSAGE_ERROR_ACCOUNT_SPECIAL_PIN_LIMIT = "message.error.account.special.pin.limit";
    public static final String MESSAGE_ERROR_INVALID_ACCOUNT = "message.error.invalid.account";
    public static final String MESSAGE_ERROR_INVALID_SERVICE_PLAN = "message.error.invalid.service.plan";
    public static final String MESSAGE_ERROR_NO_CREDIT_CARD = "message.error.no.credit.card";
    public static final String MESSAGE_ERROR_SAME_SERVICE_PLAN = "message.error.same.service.plan";
    public static final String MESSAGE_ERROR_INVALID_CREDIT_CARD_FIELDS = "message.error.invalid.credit.card.fields";
    public static final String MESSAGE_ERROR_SENDING_MAIL = "message.error.sending.mail";
    public static final String MESSAGE_ERROR_CUSTOMER_ACCOUNT_NOT_FOUND = "message.error.account.customer.not.found";
    public static final String MESSAGE_ERROR_PAYMENT_NOT_APPROVED = "message.error.payment.not.approved";
    public static final String MESSAGE_ERROR_PLAN_TOTALS_EXCEEDED_MOBILE_MESSAGE = "message.error.change.plan.totals.exceeded.mobile.message";
    public static final String MESSAGE_ERROR_INVITE_SIGN_UP_PLAN_TOTALS_EXCEEDED = "message.error.invite.sing.up.totals.exceeded";
    public static final String MESSAGE_ERROR_IMPORT_FILE = "message.error.import.file";
    public static final String MESSAGE_ERROR_UNAVAILABLE_LOCK_CODE_BACKUP = "message.error.unavailable.lock.code.backup";


    public static final String MESSAGE_WARNING_DELETE_CONSTRAINT_VIOLATION = "message.warning.delete.constraint.violation";
    public static final String MESSAGE_WARNING_EMAIL_USER_ACTIVE_ALREADY_EXISTS = "message.warning.email.user.active.already.exists";
    public static final String MESSAGE_WARNING_EMAIL_OTHER_ACCOUNT_ALREADY_EXISTS = "message.warning.email.other.account.already.exists";
    public static final String MESSAGE_WARNING_PIN_ACTIVE_ALREADY_EXISTS = "message.warning.pin.active.already.exists";
    public static final String MESSAGE_WARNING_LIMIT_NUMBER_OF_MEMBERS = "message.warning.limit.number.members";
    public static final String MESSAGE_WARNING_LIMIT_NUMBER_OF_STAFF_USERS = "message.warning.limit.number.staff";
    public static final String MESSAGE_WARNING_LIMIT_NUMBER_OF_GUEST_USERS = "message.warning.limit.number.guest";
    public static final String MESSAGE_WARNING_LIMIT_NUMBER_OF_LOCKS = "message.warning.limit.number.locks";
    public static final String MESSAGE_WARNING_SUBSCRIPTION_ACCOUNT_EXPIRED = "message.warning.subscription.account.expired";
    public static final String MESSAGE_WARNING_USER_HAS_NO_SUBSCRIPTION_ACCOUNT = "message.warning.user.has.no.subscription.account";
    public static final String MESSAGE_WARNING_USER_HAS_NO_SUBSCRIPTION_ACCOUNT_ACCESS_BILLING = "message.warning.user.has.no.subscription.account.access.billing";
    public static final String MESSAGE_WARNING_NOTIFY_SUBSCRIPTION_ACCOUNT_EXPIRATION = "message.warning.notify.subscription.account.expiration";
    public static final String MESSAGE_WARNING_NOTIFY_SUBSCRIPTION_ACCOUNT_PAYMENT_PROBLEM = "message.warning.notify.subscription.account.payment.problem";
    public static final String MESSAGE_WARNING_NOTIFY_SUBSCRIPTION_CANCELLED_BY_PAYMENT_PROBLEM = "message.warning.notify.subscription.cancelled.by.payment.problem";
    public static final String MESSAGE_WARNING_ALTER_SYSTEM_PARAMETER = "message.warning.alter.system.param";
    public static final String MESSAGE_WARNING_OPERATION = "message.warning.operation";
    public static final String MESSAGE_WARNING_USER_CREATED_MAIL_NOT_SENT = "message.warning.user.created.mail.not.sent";
    public static final String MESSAGE_WARNING_CONTACT_EMAIL_ALREADY_EXISTS = "message.warning.contact.email.already.exists";
    public static final String MESSAGE_WARNING_DEFAULT_PIN_LENGHT = "message.warning.default.pin.lenght";
    public static final String MESSAGE_WARNING_EMERGENCY_CODES_MAIL_NOT_SENT = "message.warning.emergency.codes.mail.not.sent";
    public static final String MESSAGE_WARNING_LOCK_SERIAL_NUMBER_NOT_FOUND = "message.warning.lock.serial.number.not.found";


    // Validations messages
    // -------
    public static final String VALIDATION_VALUE_DUPLICATED = "{validation.value.duplicated}";
    public static final String VALIDATION_VALUE_DUPLICATED_ACTIVE_LOCK = "{validation.value.duplicated.active.lock}";
    public static final String VALIDATION_FORMAT_ERROR = "validation.format.error";
    public static final String VALIDATION_FORMAT_ERROR_FIELD = "validation.format.error.field";
    public static final String VALIDATION_DATE_BETWEEN = "validation.date.between";
    public static final String VALIDATION_DATE_END_GREATER_START = "validation.date.end.greater.start";
    public static final String VALIDATION_INVALID_CREDIT_CARD = "validation.invalid.credit.card";
    public static final String VALIDATION_INVALID_CREDIT_CARD_CVV = "validation.invalid.credit.card.cvv";

    // Mail messages
    // -------
    public static final String MAIL_PASS_RESET_VERIFICATION_CODE_ERROR = "mail.pass.reset.verification.code.error";
    public static final String MAIL_PASS_RESET_MAIL_SUBJECT = "mail.pass.reset.mail.subject";
    public static final String MAIL_PASS_CREATE_MAIL_SUBJECT = "mail.pass.create.mail.subject";
    public static final String MAIL_PASS_INVITE_MAIL_SUBJECT = "mail.pass.invite.member.subject";
    public static final String MAIL_ALERT_PAYMENT_PROBLEM_MAIL_SUBJECT = "mail.alert.payment.problem.mail.subject";


    public static final String MAIL_LOCK_EVENT_SUBJECT = "mail.lock.event.subject";

    // SMS messages
    // -------
    public static final String SMS_LOCK_EVENT_MESSAGE = "sms.lock.event.message";
    public static final String SMS_LOCK_EVENT_NOUSER_MESSAGE = "sms.lock.event.nouser.message";

    // Automated Mail messages
    // -------
    public static final String DEFAULT_STAFF_AUTOMATED_EMAIL_BODY = "default.staff.automated.email.body";
    public static final String DEFAULT_GUEST_AUTOMATED_EMAIL_BODY = "default.guest.automated.email.body";
    public static final String ACCESS_INSTRUCTIONS = "access.instructions";
    public static final String MAIL_CONTACT_NAME_TAG = "mail.contact.name.tag";

    // HTTP Error messages
    // -------
    public static final String MESSAGE_HTTP_403 = "error.403.desc";
    public static final String MESSAGE_HTTP_404 = "error.404.desc";
    public static final String MESSAGE_HTTP_500 = "error.500.desc";

}
