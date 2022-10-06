package it.ispw.booknook.logic.control;

import at.favre.lib.crypto.bcrypt.BCrypt;
import it.ispw.booknook.logic.bean.LoginBean;
import it.ispw.booknook.logic.database.dao.ReaderUserDao;
import it.ispw.booknook.logic.entity.ReaderUser;

public class SignUpController {

    public void registerReader(LoginBean loginBean) {
        ReaderUser reader =  new ReaderUser(loginBean.getUsername(), loginBean.getEmail(), BCrypt.withDefaults().hashToString(12, loginBean.getPassword().toCharArray()));
        ReaderUserDao.registerReaderUser(reader);
    }

}
