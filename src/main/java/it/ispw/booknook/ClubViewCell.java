package it.ispw.booknook;

import javafx.scene.control.ListCell;

public class ClubViewCell extends ListCell<Club> {

    @Override
    public void updateItem(Club club, boolean empty){
        super.updateItem(club, empty);
        if(club != null)
        {
            ClubCell data = new ClubCell();
            //setta contenuto della cella
            data.setInfo(club);
            //setta aspetto grafico della cella
            setGraphic(data.getCell());
        }
    }
}
