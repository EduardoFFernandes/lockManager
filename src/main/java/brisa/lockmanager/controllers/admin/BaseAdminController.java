package brisa.lockmanager.controllers.admin;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Controller;

@Controller
public abstract class BaseAdminController<T extends PagingAndSortingRepository<?, ?>> extends BaseController<T> {

//    public User getConnectedUserInDB() throws UsernameNotFoundException {
//        return this.authService.getConnectedUserInDB();
//    }
//
//    public CustomUserDetails getConnectedUser() throws UsernameNotFoundException {
//        return this.authService.getConnectedUser();
//    }
//
//    public boolean isAuthenticated() {
//        return this.authService.isAuthenticated();
//    }
//
//    public void checkAccountPermission(final _BaseModelId object) {
//
//        if (object == null || object.getId() == null) {
//            return;
//        }
//
//        final Long idAccount = this.getConnectedUserInDB()
//                .getAccount()
//                .getId();
//
//        super.authService.checkAccountPermission(
//                object.getClass(),
//                object.getId(),
//                idAccount);
//    }
//
//    public void checkAccountRolePermission(final EnumFeature feature) {
//
//        if (!this.authService.checkAccountRolePermission(feature)) {
//            throw new ForbiddenException();
//        }
//    }
}
