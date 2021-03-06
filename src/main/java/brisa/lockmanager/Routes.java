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

    // Client Routes
    // -------------

    static final String ADMIN_CLIENT_LIST = ADMIN + "/client/list";
    static final String ADMIN_CLIENT_EDIT = ADMIN + "/client/edit";
    static final String ADMIN_CLIENT_DELETE = ADMIN + "/client/delete";

}
