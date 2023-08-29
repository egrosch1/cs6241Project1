package edu.westga.cs.babble.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.util.Callback;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.EmptyTileBagException;

public class BabbleController {

    @FXML
    private ListView<Tile> tilesListView;

    private TileBag tileBag;

    public BabbleController() {
        this.tileBag = new TileBag();
    }

    public void initialize() {
        tilesListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Tile> call(ListView<Tile> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Tile item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(Character.toString(item.getLetter()));
                        }
                    }
                };
            }
        });

        try {
            for (int i = 0; i < 7; i++) {
                tilesListView.getItems().add(tileBag.drawTile());
            }
        } catch (EmptyTileBagException e) {
            System.err.println("The tile bag is empty.");
        }
    }

}
