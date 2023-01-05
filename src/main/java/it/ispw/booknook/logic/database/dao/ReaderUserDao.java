package it.ispw.booknook.logic.database.dao;

import it.ispw.booknook.logic.database.BookNookDB;
import it.ispw.booknook.logic.database.queries.LogQueries;
import it.ispw.booknook.logic.entity.User;
import it.ispw.booknook.logic.entity.UserType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderUserDao {

    private ReaderUserDao() {}

    //registra un nuovo utente Reader nel sistema
    public static void registerReaderUser(User user) {
        Connection conn = null;

        BookNookDB db = BookNookDB.getInstance();
        conn = db.getConn();
        try {
            LogQueries.saveReaderUser(conn, user);
        } catch(SQLException e) {
            Logger logger = Logger.getLogger("MyLog");
            logger.log(Level.INFO, "This is message 1", e);
        }
    }

    //recupera un utente dal db se presente
   public static User getReaderUser(String email) throws Exception{
        Connection conn = null;
        User user = null;

        BookNookDB db = BookNookDB.getInstance();
        conn = db.getConn();

        try {
            ResultSet rs = LogQueries.selectReaderUser(conn, email);

            if (!rs.first()){ // rs empty
                throw new Exception("No User Found matching with email and password");
            }

            //altrimenti l'utente è presente
            rs.first();

            String username = rs.getString("username");
            String userEmail = rs.getString("email");
            String password = rs.getString("password");
            String type = rs.getString("tipo");

            UserType userType = switch (type) {
                case "lettore" -> UserType.READER;
                case "bibliotecario" -> UserType.LIBRARIAN;
                default -> UserType.READER;
            };

            user = User.getUser();
            user.setLogDetails(username, userEmail, password, userType);
            rs.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static String getPassUser(String email) throws Exception{
        Connection conn = null;
        String readerPassword = null;


        BookNookDB db = BookNookDB.getInstance();
        conn = db.getConn();


        try {
            ResultSet rs = LogQueries.getpass(conn, email);

            if (!rs.first()){ // rs empty
                throw new Exception("No User Found matching with email and password");
            }

            //altrimenti l'utente è presente
            rs.first();

            readerPassword = rs.getString("password");


            rs.close();

        } catch(SQLException e) {
            Logger logger = Logger.getLogger("MyLog");
            logger.log(Level.INFO, "This is message 1", e);
        }
        return readerPassword;
    }



}
