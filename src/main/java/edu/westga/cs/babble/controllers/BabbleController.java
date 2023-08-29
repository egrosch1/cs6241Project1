package edu.westga.cs.babble.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileRack;
import edu.westga.cs.babble.model.EmptyTileBagException;
import edu.westga.cs.babble.model.PlayedWord;

/**
 * Controller class for the Word Game Babble
 * @author egrosch1
 * @version Fall 2023
 */
public class BabbleController {

	@FXML
	private ListView<Tile> tilesListView;
	@FXML
	private ListView<Tile> tilesChosenView;
	@FXML
	private Button playWordBtn;
	@FXML 
	private Button resetButton; 

	@FXML
	private TextField scoreTextField;

	private int cumulativeScore;
	private WordDictionary wordDictionary = new WordDictionary();
	private TileBag tileBag;
	private PlayedWord playedWord;
	private TileRack tileRack;

	/**
	 * Controller for the Babble game. Creates a tile bag and a cumulative score.
	 */
	public BabbleController() {
		this.tileBag = new TileBag();
		this.cumulativeScore = 0;

	}

	/**
	 * Method to create Accessibility features for Babble.
	 */
	private void setupTooltips() {
		Tooltip tilesListViewTooltip = new Tooltip("Available tiles to form words.");
		tilesListView.setTooltip(tilesListViewTooltip);

		Tooltip scoreTextFieldTooltip = new Tooltip("Cumulative score of played words.");
		scoreTextField.setTooltip(scoreTextFieldTooltip);

		Tooltip resetButtonTooltip = new Tooltip("Clears selected tiles and resets the game.");
		resetButton.setTooltip(resetButtonTooltip);

		Tooltip playWordButtonTooltip = new Tooltip("Submit the selected tiles as a word.");
		playWordBtn.setTooltip(playWordButtonTooltip);
	}

	/**
	 * Method to initialize the game
	 */
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
		this.tileRack = new TileRack();
		scoreTextField.setEditable(false);
		setupTooltips();
	}


	/**
	 * Method that allows the letters to reset when the mouse clicks the button "Reset"
	 * @param clickEvent when button is pressed
	 */
	@FXML
	public void handleResetButtonClicked(MouseEvent clickEvent) {
		if (!this.playedWord.tiles().isEmpty()) {
			this.tileRack.tiles().addAll(this.playedWord.tiles());
			this.playedWord.clear();
		} 
		this.tilesChosenView.getItems().clear();
		this.tilesListView.getItems().clear();
		this.initialize();

	}

	/**
	 * Method that allows the word to be played with button is clicked
	 * @param clickEvent when button is pressed
	 */
	@FXML
	public void handlePlayWordButtonClicked(MouseEvent clickEvent) {
		for (Tile chosenTile : tilesChosenView.getItems()) {
			playedWord.tiles().add(chosenTile);
		}

		String hand = playedWord.getHand();
		if (playedWord.tiles().isEmpty() ||(wordDictionary != null && !wordDictionary.isValidWord(hand))) {
			new Alert(AlertType.INFORMATION, "Not a valid word").showAndWait();
			return;
		}
		int wordScore = playedWord.getScore();
		cumulativeScore += wordScore;
		scoreTextField.setText(String.valueOf(cumulativeScore));

		tileRack.tiles().addAll(playedWord.tiles());
		playedWord.tiles().clear(); 

		tilesChosenView.getItems().clear();
		this.tilesListView.getItems().clear();
		this.initialize();
	}

	/**
	 * Method to show letters chosen
	 */
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

	/**
	 * Method to show tiles pulled from a bag.
	 */
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
						tilesListView.getItems().remove(item);
					}
				});
			}
		}
	}
}
