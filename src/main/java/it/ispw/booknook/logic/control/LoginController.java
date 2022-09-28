package it.ispw.booknook.logic.control;

import at.favre.lib.crypto.bcrypt.BCrypt;
import it.ispw.booknook.logic.bean.LoginBean;
import it.ispw.booknook.logic.database.dao.ReaderUserDao;

public class LoginController {

    public boolean checkUserLogged(LoginBean loginBean) {
        String userPass = null;
        try {
            userPass = ReaderUserDao.getPassUser(loginBean.getEmail());
            BCrypt.Result result = BCrypt.verifyer().verify(loginBean.getPassword().toCharArray(), userPass);
            return result.verified;
        } catch (Exception e) {
            return false;
        }
    }


}
