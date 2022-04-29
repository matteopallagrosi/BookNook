package it.ispw.booknook.logic.control;

import it.ispw.booknook.logic.bean.LoginBean;
import it.ispw.booknook.logic.database.dao.ReaderUserDao;
import it.ispw.booknook.logic.entity.ReaderUser;

public class SignUpController {

    public void registerReader(LoginBean loginBean) {
        ReaderUser reader =  new ReaderUser(loginBean.getEmail(), loginBean.getPassword());
        ReaderUserDao.registerReaderUser(reader);
    }

}
