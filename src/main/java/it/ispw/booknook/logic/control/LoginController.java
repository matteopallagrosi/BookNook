package it.ispw.booknook.logic.control;

import it.ispw.booknook.logic.bean.LoginBean;
import it.ispw.booknook.logic.database.dao.ReaderUserDao;
import it.ispw.booknook.logic.entity.ReaderUser;

public class LoginController {

    public boolean checkUserLogged(LoginBean loginBean) {
        ReaderUser user = null;
        try {
            user = ReaderUserDao.getReaderUser(loginBean.getEmail(), loginBean.getPassword());
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
