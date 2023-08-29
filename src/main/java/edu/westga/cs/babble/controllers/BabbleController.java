package edu.westga.cs.babble.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileRack;
import edu.westga.cs.babble.model.EmptyTileBagException;
import edu.westga.cs.babble.model.PlayedWord;

public class BabbleController {

	@FXML
	private ListView<Tile> tilesListView;
	@FXML
	private ListView<Tile> tilesChosenView;
	@FXML
	private Button playWordBtn;
	@FXML 
	private Button resetButton; 

	private WordDictionary wordDictionary;
	private TileBag tileBag;
	private int currentIndex = 0;
	private PlayedWord playedWord;
	private TileRack tileRack;

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

		tilesChosenView.setCellFactory(listView -> new ChosenTileListCell());

		this.playWordBtn = new Button();
		this.playedWord = new PlayedWord();
	}
	
	@FXML
	public void handleResetButtonClicked(MouseEvent clickEvent) {
		if (!this.playedWord.tiles().isEmpty()) {
			this.tileRack.tiles().addAll(this.playedWord.tiles());
			this.playedWord.clear();
		} 
		this.tilesChosenView.getItems().clear();

	}
	
	@FXML 
	public void handlePlayWordButtonClicked(MouseEvent clickEvent) {
		for (int i = 0; i < this.tilesChosenView.getItems().size(); i++) {
			this.playedWord.append(this.tilesChosenView.getItems().get(i));	
		}
		String hand = this.playedWord.getHand();

		if (this.playedWord.tiles().isEmpty() || !this.wordDictionary.isValidWord(hand)) {
			new Alert(AlertType.INFORMATION, "Not a valid word").showAndWait();
			return;
		}

		this.playedWord.clear();
	}
	private class ChosenTileListCell extends ListCell<Tile> {
		@Override
		protected void updateItem (Tile item, boolean empty) {
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
				
				setOnMouseClicked (event -> {
					if (isSelected()) {
						tilesChosenView.getItems().add(item);
					}
				});
			}
		}
	}
}
