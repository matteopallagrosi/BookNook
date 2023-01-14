package it.ispw.booknook.logic.boundary.main_view;

import it.ispw.booknook.logic.bean.BookBean;
import it.ispw.booknook.logic.control.LoginController;
import it.ispw.booknook.logic.control.ReadingListController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;

public class ListViewCell extends ListCell<BookBean>
{
    @Override
    public void updateItem(BookBean book, boolean empty)
    {
        super.updateItem(book,empty);

        if(book != null && !empty)
        {
            BookCell data = new BookCell();
            book.attach(data);
            //setta contenuto della cella
            data.setInfo(book);
            //setta aspetto grafico della cella
            setGraphic(data.getCell());
        }
    }
}
