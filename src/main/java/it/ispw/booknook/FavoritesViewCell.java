package it.ispw.booknook;

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
