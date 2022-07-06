package brisa.lockmanager;

public interface Routes {

    static final String ERROR = "/error";
    static final String NOT_FOUND = ERROR + "/not-found";
    static final String SERVER_ERROR = ERROR + "/server-error";
    static final String ACCESS_DENIED = ERROR + "/access-denied";
    static final String ADMIN = "/admin";

    // Lock Routes
    // -------------

    static final String ADMIN_LOCK_LIST = ADMIN + "/lock/list";
    static final String ADMIN_LOCK_EDIT = ADMIN + "/lock/edit";
    static final String ADMIN_LOCK_DELETE = ADMIN + "/lock/delete";

    // Lock Model Routes
    // -------------

    static final String ADMIN_LOCK_MODEL_LIST = ADMIN + "/lock-model/list";
    static final String ADMIN_LOCK_MODEL_EDIT = ADMIN + "/lock-model/edit";
    static final String ADMIN_LOCK_MODEL_DELETE = ADMIN + "/lock-model/delete";

    // Warehouse Routes
    // -------------

    static final String ADMIN_WAREHOUSE_LIST = ADMIN + "/warehouse/list";
    static final String ADMIN_WAREHOUSE_EDIT = ADMIN + "/warehouse/edit";
    static final String ADMIN_WAREHOUSE_DELETE = ADMIN + "/warehouse/delete";

    // Warehouse Routes
    // -------------

    static final String ADMIN_VERSION_LIST = ADMIN + "/version/list";
    static final String ADMIN_VERSION_EDIT = ADMIN + "/version/edit";
    static final String ADMIN_VERSION_DELETE = ADMIN + "/version/delete";

    // Client Routes
    // -------------

    static final String ADMIN_CLIENT_LIST = ADMIN + "/client/list";
    static final String ADMIN_CLIENT_EDIT = ADMIN + "/client/edit";
    static final String ADMIN_CLIENT_DELETE = ADMIN + "/client/delete";

    // Purchase Routes
    // -------------

    static final String ADMIN_PURCHASE_LIST = ADMIN + "/purchase/list";
    static final String ADMIN_PURCHASE_EDIT = ADMIN + "/purchase/edit";
    static final String ADMIN_PURCHASE_ITEMS = ADMIN + "/purchase/items";
    static final String ADMIN_PURCHASE_DELETE = ADMIN + "/purchase/delete";

    // Item Routes
    // -------------

    static final String ADMIN_ITEM_EDIT = ADMIN + "/item/edit";
    static final String ADMIN_ITEM_DELETE = ADMIN + "/item/delete";

    // Invoice Routes
    // -------------

    static final String FILE = "file://";
    static final String PATH_TEMPLATE = "/static/assets/invoice/template";
    static final String ADMIN_INVOICE = ADMIN + "/invoice";

    // REST Routes
    // -----------
    static final String API = "/api";
    static final String API_EXISTS_LOCK_ASSOCIATIONS = API + "/lock/associations";
    static final String API_EXISTS_PURCHASE_ASSOCIATIONS = API + "/purchase/associations";
    static final String API_EXISTS_WAREHOUSE_ASSOCIATIONS = API + "/warehouse/associations";
    static final String API_EXISTS_LOCK_MODEL_ASSOCIATIONS = API + "/lock-model/associations";
    static final String API_EXISTS_CLIENT_ASSOCIATIONS = API + "/client/associations";
    static final String API_EXISTS_VERSION_ASSOCIATIONS = API + "/version/associations";
    static final String API_RELEASE_NOTES = API + "/version/release-notes";

}
