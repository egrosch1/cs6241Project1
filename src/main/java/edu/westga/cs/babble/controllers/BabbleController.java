package edu.westga.cs.babble.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.EmptyTileBagException;

public class BabbleController {

    @FXML
    private ListView<Tile> tilesListView;
  

    private TileBag tileBag;
    private int currentIndex = 0;

    public BabbleController() {
        this.tileBag = new TileBag();
    }

    public void initialize() {
        tilesListView.setCellFactory(listView -> new TileListCell());

        try {
            for (int i = 0; i < 7; i++) {
                tilesListView.getItems().add(tileBag.drawTile());
            }
        } catch (EmptyTileBagException e) {
            System.err.println("The tile bag is empty.");
        }

    }

    

 

    private class TileListCell extends ListCell<Tile> {
        @Override
        protected void updateItem(Tile item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null || empty) {
                setText(null);
                setGraphic(null);
            } else {
                Label tileLabel = new Label(Character.toString(item.getLetter()));
                setGraphic(tileLabel);
            }
        }
    }
}
