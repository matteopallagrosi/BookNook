package it.ispw.booknook.logic.boundary.main_view;

import it.ispw.booknook.logic.entity.Book;
import javafx.scene.control.ListCell;

public class FavoritesViewCell extends ListCell<Book> {

    @Override
    public void updateItem(Book book, boolean empty)
    {
        super.updateItem(book,empty);

        if(book != null)
        {
            FavoritesCell data = new FavoritesCell();
            //setta contenuto della cella
            data.setInfo(book);
            //setta aspetto grafico della cella
            setGraphic(data.getCell());
        }
    }
}
