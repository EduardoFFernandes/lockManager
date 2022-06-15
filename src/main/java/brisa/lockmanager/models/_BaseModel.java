package brisa.lockmanager.models;

import java.io.Serializable;
import java.util.Locale;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@MappedSuperclass
public class _BaseModel implements Serializable {

    private static final long serialVersionUID = -7997398371288457042L;

    // ---------------------------------------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------------------------------------
    public _BaseModel() {
        super();
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    // ---------------------------------------------------------------------------------------------
    // Enums
    // ---------------------------------------------------------------------------------------------
    public enum Role {

        ROLE_ADMIN("ADMIN"),
        ROLE_CUSTOMER("CUSTOMER");

        public static final String ROLE_ADMIN_STR = "ROLE_ADMIN";
        public static final String ROLE_CUSTOMER_STR = "ROLE_CUSTOMER";

        private String templateName;

        // ---------------------------------------------------------------------------------------------
        // Constructors
        // ---------------------------------------------------------------------------------------------
        private Role(final String templateName) {
            this.templateName = templateName;
        }

        // ---------------------------------------------------------------------------------------------
        // get
        // ---------------------------------------------------------------------------------------------
        public String templateName() {
            return this.templateName;
        }
    }

    public static enum Setting {

        SMTP_HOST,
        SMTP_PORT,
        SMTP_USER,
        SMTP_PASS,
        SMTP_SENDER,
        SMTP_SENDER_NAME,
        CAPTCHA_API,
        DISCADOR_URL,
        DISCADOR_COMPANY_ID,
        DISCADOR_API_KEY,
        DISCADOR_API_SECRET,
        DISCADOR_TIME_ZONE,
        SUPPORT_FAQ_URL,
        SUPPORT_MANUALS_URL,
        SUPPORT_ABOUT_US_URL,
        CONTACT_SALES_MAILS,
        CONTACT_SALES_PHONES,
        CONTACT_SUPPORT_MAILS,
        CONTACT_SUPPORT_PHONES,
        PAYMENT_PROVIDER_EMAIL,
        PAYMENT_PROVIDER_API_KEY,
        PAYMENT_PROVIDER_API_SECRET,
        PAYMENT_PROVIDER_MERCHANT_NAME,
        PAYMENT_PROVIDER_MERCHANT_TOKEN,
        PAYMENT_PROVIDER_MERCHANT_TRANSARMOR_TOKEN,
        PAYMENT_PROVIDER_API_BASE_URL,
        SYSTEM_URL,
        DAYS_TO_EXPIRE_SUBSCRIPTION,
        BACKUP_PIN_TIME,
        BACKUP_PIN_FORCE_SYNC,
        MOBILE_APP_ANDROID,
        MOBILE_APP_IOS;
    }

    public enum AccountType {

        COMPANY,
        INDIVIDUAL
    }

    public enum ForceSync {

        NO,
        FORCE_BEFORE,
        FORCE_AFTER
    }

    public enum EndUserType {

        GUEST,
        STAFF,
        OWNER,
        BACKUP,
    }

    public enum AccessType {
        INDIVIDUAL,
        LOCK_GROUP,
        LOCATION
    }

    public enum NotificationFrequency {
        FIRST_OCCURRENCE,
        ALWAYS
    }

    public enum NotificationType {
        EMAIL,
        SMS,
        WEBHOOK,
        BOTH
    }

    public enum WebhookType {
        JSON,
        FORM,
    }

    public enum AutomatedEmailStatus {
        DELIVERED,
        ERROR
    }

    public enum LockSettingStatus {
        CLOSED,
        OPEN,
        UNKNOWN,
        COMMUNICATION_ISSUE
    }

    public enum AccountSubscriptionStatus {
        ACTIVE,
        PAYMENT_PROBLEM,
        CANCELLED_MANUALLY,
        EXPIRED,
        CANCELLED_BY_PAYMENT_PROBLEM,
        CANCELLED_BY_PLAN_UPDATE,
    }

    public enum AccountSubscriptionType {
        MONTHLY,
        YEARLY
    }

    public enum AccountSubscriptionBilling {
        MIN_BILLING_DAY(1),
        MAX_BILLING_DAY(28);

        private Integer value;

        // ---------------------------------------------------------------------------------------------
        // Constructors
        // ---------------------------------------------------------------------------------------------
        private AccountSubscriptionBilling(final Integer value) {
            this.value = value;
        }

        // ---------------------------------------------------------------------------------------------
        // get
        // ---------------------------------------------------------------------------------------------
        public Integer value() {
            return this.value;
        }
    }

    public enum AccountSubscriptionError {
        NO_CREDIT_CARD_ERROR,
        INVALID_SERVICE_PLAN_ERROR,
        SAME_SERVICE_PLAN_ERROR,
        SUBSCRIPTION_ERROR,
    }

    public enum CancelSubscriptionError {
        CANCEL_OK,
        NO_CREDIT_CARD,
        INVALID_ACCOUNT,
        PAYMENT_PROBLEM,
        EMERGENCY_CODES_MAIL_NOT_SENT,
        CANCEL_ERROR,
    }

    public enum PmsReservationStatus {
        CONFIRMED,
        CANCELLED
    }

    public enum UserStatus {
        PENDING,
        ACTIVE,
        INACTIVE,
        EXPIRED,
        WAITING
    }

    public enum EnumEventType {
        UNLOCKED,
        LOCKED,
        ACCESS_DENIED,
        LOW_BATTERY,
        BATTERIES_REPLACED,
        POWER_ON,
        SYNCHRONIZATION_FAILED,
        CONNECTION_FAILURE,
        EMERGENCY_PIN,
        CONNECTION_LOST, // identified by server's Job
        UNDUE_ACCESS, // identified by server's integration route
        INIT, // identified by server's integration route
        SYNC, // identified by server's integration route
        COMMUNICATION_METHOD_ISSUE, // identified by server's integration route

    }

    public enum Carrier {
        ALLTEL,
        ATT,
        BELL,
        BOOST,
        CRICKET,
        METROPCS,
        ROGERS,
        SPRINT,
        TELUS,
        TMOBILE,
        TRACFONE,
        USCELLULAR,
        VERIZON,
        VIRGIN,
        XFINITY,
        OI,
        CLARO,
        TIM,
        VIVO
    }

    public enum EnumFeature {
        MANAGE_STAFF_ACCESS,
        MANAGE_GUEST_ACCESS,
        MANAGE_OWNER_ACCESS,
        MANAGE_LOCKS,
        MANAGE_LOCATIONS,
        MANAGE_SCHEDULE_ACCESS
    }

    public enum NoticeSchedule {
        BEFORE_7_D(7),
        BEFORE_6_D(6),
        BEFORE_5_D(5),
        BEFORE_4_D(4),
        BEFORE_3_D(3),
        BEFORE_2_D(2),
        BEFORE_1_D(1),
        BEFORE_04_H(4);

        private Integer interval;

        // ---------------------------------------------------------------------------------------------
        // Constructors
        // ---------------------------------------------------------------------------------------------
        private NoticeSchedule(final Integer interval) {
            this.interval = interval;
        }

        // ---------------------------------------------------------------------------------------------
        // get
        // ---------------------------------------------------------------------------------------------
        public Integer interval() {
            return this.interval;
        }
    }

    public enum VerificationServicePlanType {
        MEMBER,
        GUEST,
        STAFF,
        LOCK
    }

    public enum CommunicationMode {
        OFFLINE,
        WIFI,       // only wifi
        MOBILE,     // only mobile
        WIFI_MOBILE // both
    }

    public enum ResetStatus {
        PENDING,
        SYNCHED,
    }

    public enum AdminEventAction {
        CREATED,
        UPDATED,
        DELETED,
        DEACTIVATED,
        ACTIVATED,
        INVITED,
        INITIALIZED,
        RESET
    }

    public enum AdminEventType {
        GUEST,
        STAFF,
        OWNER,
        LOCK,
        COMMON_LOCK,
        MEMBER,
        LOCK_GROUP,
        LOCATION,
    }

    public enum CreditCardType {

        VISA("VISA"),
        MASTERCARD("MASTERCARD"),
        AMERICAN_EXPRESS("AMERICAN EXPRESS"),
        DINERS_CLUB("DINERS CLUB"),
        DISCOVERY("DISCOVERY"),
        JCB("JCB");

        private String value;

        // ---------------------------------------------------------------------------------------------
        // Constructors
        // ---------------------------------------------------------------------------------------------
        private CreditCardType(final String value) {
            this.value = value;
        }

        // ---------------------------------------------------------------------------------------------
        // get
        // ---------------------------------------------------------------------------------------------
        public String value() {
            return this.value;
        }
    }

    public enum PaymentTransactionType {

        AUTHORIZE("authorize"),
        PURCHASE("purchase"),
        RECURRING("recurring");

        private String value;

        // ---------------------------------------------------------------------------------------------
        // Constructors
        // ---------------------------------------------------------------------------------------------
        private PaymentTransactionType(final String value) {
            this.value = value;
        }

        // ---------------------------------------------------------------------------------------------
        // get
        // ---------------------------------------------------------------------------------------------
        public String value() {
            return this.value;
        }
    }

    public enum PaymentTransactionStatus {

        APPROVED("approved"),
        DECLINED("declined"),
        NOT_PROCESSED("not processed");

        private String value;

        // ---------------------------------------------------------------------------------------------
        // Constructors
        // ---------------------------------------------------------------------------------------------
        private PaymentTransactionStatus(final String value) {
            this.value = value;
        }

        // ---------------------------------------------------------------------------------------------
        // get
        // ---------------------------------------------------------------------------------------------
        public String value() {
            return this.value;
        }
    }

    public enum CurrencyCode {
        USD(Locale.US),
        EUR(Locale.FRANCE),
        BRL(Locale.forLanguageTag("pt-BR"));

        private Locale locale;

        // ---------------------------------------------------------------------------------------------
        // Constructors
        // ---------------------------------------------------------------------------------------------
        private CurrencyCode(final Locale locale) {
            this.locale = locale;
        }

        // ---------------------------------------------------------------------------------------------
        // get
        // ---------------------------------------------------------------------------------------------
        public Locale locale() {
            return this.locale;
        }
    }
}
