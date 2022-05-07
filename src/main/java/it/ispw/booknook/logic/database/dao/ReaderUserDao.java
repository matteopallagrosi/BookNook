package it.ispw.booknook.logic.database.dao;

import it.ispw.booknook.logic.database.BookNookDB;
import it.ispw.booknook.logic.database.queries.LogQueries;
import it.ispw.booknook.logic.entity.ReaderUser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReaderUserDao {

    //registra un nuovo utente Reader nel sistema
    public static void registerReaderUser(ReaderUser user) {
        Statement stmt = null;
        Connection conn = null;

        BookNookDB db = BookNookDB.getInstance();
        conn = db.getConn();

        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            //esegue la query, salva l'utente sul db
            LogQueries.saveReaderUser(stmt, user);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(stmt != null) {
                    stmt.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //recupera un utente dal db se presente
    public static ReaderUser getReaderUser(String email, String password) throws Exception{
        Statement stmt = null;
        Connection conn = null;
        ReaderUser user = null;

        BookNookDB db = BookNookDB.getInstance();
        conn = db.getConn();

        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = LogQueries.selectReaderUser(stmt, email, password);

            if (!rs.first()){ // rs empty
                throw new Exception("No User Found matching with email and password");
            }

            //altrimenti l'utente è presente
            rs.first();

            String readerEmail = rs.getString("email");
            String readerPassword = rs.getString("password");

            user = new ReaderUser(readerEmail, readerPassword);
            rs.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(stmt != null) {
                    stmt.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }



}
