package it.ispw.booknook;

import javafx.scene.control.ListCell;

import java.util.Random;

public class LibraryViewCell extends ListCell<Library> {

    @Override
    public void updateItem(Library library, boolean empty)
    {
        super.updateItem(library,empty);
        if(library != null)
        {

            //recuperare l'informazione se almeno una copia del libro è presente in questa libreria
            LibraryCell data = new LibraryCell();
            //setta contenuto della cella
            data.setInfo(library);
            //setta aspetto grafico della cella
            setGraphic(data.getCell());
        }
    }
}
