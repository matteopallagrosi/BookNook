package it.ispw.booknook.logic.control;

import it.ispw.booknook.logic.bean.BookBean;
import it.ispw.booknook.logic.database.dao.BookDao;

import java.util.ArrayList;
import java.util.List;

public class BorrowBookController {


    public List<String> borrowByName(BookBean requestedBook) {
        //creare la lista di libri risultanti dalla ricerca nel db
        //query al db con i titoli veri
        List<String> bookList = BookDao.getRequestedBooks(requestedBook.getTitle());
        return bookList;
    }
}
