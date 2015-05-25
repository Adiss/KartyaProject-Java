package hu.experiment_team;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main class of the program, this one will run as the program.
 * @author Jakab Ádám
 * */
public class Main extends Application {

    /**
     * This method will be called when the program start, launches the JavaFX application.
     * @param args Arguments of the program
     * */
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage){
        stage.setTitle("KartyaProject");
        stage.setWidth(400);
        stage.setHeight(700);

        Label playerNumberL = new Label("How many people will be drunk?");
        TextField playerNumberTF = new TextField();
        playerNumberTF.lengthProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.intValue() > oldValue.intValue()){
                char ch = playerNumberTF.getText().charAt(oldValue.intValue());
                if(!(ch >= '0' && ch <= '9'))
                    playerNumberTF.setText(playerNumberTF.getText().substring(0, playerNumberTF.getText().length()-1));
            }
        });
        Button submitButton = new Button("Submit");
        Button nextButton = new Button("Next..");
        List<String> playerNames = new ArrayList<>();

        VBox root = new VBox();
        root.setSpacing(5);
        root.setPadding(new Insets(10, 10, 10, 10));

        nextButton.setOnAction(e -> {
            root.getChildren().remove(playerNumberL);
            root.getChildren().remove(playerNumberTF);
            root.getChildren().remove(nextButton);
            int players = Integer.parseInt(playerNumberTF.getText());
            for (int i = 1; i <= players; i++) {
                root.getChildren().add(new Label(i + ". Player"));
                root.getChildren().add(new TextField());
            }
            root.getChildren().add(submitButton);
        });

        root.getChildren().addAll(playerNumberL, playerNumberTF, nextButton);

        Scene primaryScene = new Scene(root, 350, 150);
        stage.setScene(primaryScene);

        // player container table
        TableView<Player> table = new TableView<>();
        ObservableList<Player> players = FXCollections.observableArrayList();
        table.setEditable(true);
        TableColumn playersCol = new TableColumn("Players");
        playersCol.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        playersCol.setSortable(false);
        playersCol.setMinWidth(90);
        TableColumn beerCol = new TableColumn("Beer");
        beerCol.setCellValueFactory(new PropertyValueFactory<Player, Boolean>("myTurn"));
        beerCol.setSortable(false);
        beerCol.setMinWidth(90);
        TableColumn queenCol = new TableColumn("Queen");
        queenCol.setCellValueFactory(new PropertyValueFactory<Player, Boolean>("haveQueen"));
        queenCol.setSortable(false);
        queenCol.setMinWidth(90);
        TableColumn aceCol = new TableColumn("Ace");
        aceCol.setCellValueFactory(new PropertyValueFactory<Player, Boolean>("haveAce"));
        aceCol.setSortable(false);
        aceCol.setMinWidth(90);
        table.getColumns().addAll(playersCol, beerCol, queenCol, aceCol);
        table.setMaxWidth(playersCol.getWidth() + beerCol.getWidth() + queenCol.getWidth() + aceCol.getWidth() + 2);

        // rule container table
        TableView<Rule> rulesTable = new TableView<>();
        ObservableList<Rule> rules = FXCollections.observableArrayList();
        TableColumn listOfRules = new TableColumn("Rules");
        listOfRules.setCellValueFactory(new PropertyValueFactory<Rule, String>("rule"));
        listOfRules.setSortable(false);
        listOfRules.setMinWidth(372);
        listOfRules.setMaxWidth(372);
        rulesTable.getColumns().addAll(listOfRules);

        // show cards
        VBox deckTable = new VBox();
        deckTable.setSpacing(10);
        Button drawCard = new Button("Draw a card");
        int tableHeaderHeight = 26;
        int tableRowHeight = 24;
        int beerRowHeight = 30;

        // fields on king
        Label addRuleLabel = new Label("Királyt húztál, adj meg egy szabályt!");
        TextField addRuleTextField = new TextField();
        Button addRuleButton = new Button("Elfogad");
        addRuleButton.setOnMouseClicked(event -> {
            rules.removeAll(rules.stream().filter(r -> r.getRule().equals("Még nem húztál királyt!")).collect(Collectors.toList()));
            rules.add(new Rule(addRuleTextField.getText()));
            rulesTable.setPrefHeight((rulesTable.getItems().size() * tableRowHeight) + tableHeaderHeight);
            addRuleTextField.setText("");
            root.getChildren().removeAll(addRuleLabel, addRuleTextField, addRuleButton);
            drawCard.setDisable(false);
        });

        //Table reset fields
        Button resetDeck = new Button("Reset Deck");
        Button resetTable = new Button("Reset Table");
        resetDeck.setOnMouseClicked(e -> {
            Dealer.INSTANCE.resetDeck();
            drawCard.setDisable(false);
            deckTable.getChildren().removeAll(resetDeck, resetTable);
        });
        resetTable.setOnMouseClicked(e -> {
            Dealer.INSTANCE.resetTable(table, players, rules);
            drawCard.setDisable(false);
            deckTable.getChildren().removeAll(resetDeck, resetTable);
        });

        drawCard.setOnMouseClicked(e -> {
            deckTable.getChildren().removeAll(deckTable.getChildren().stream().filter(s -> (s instanceof ImageView)).collect(Collectors.toList()));
            Card card = Dealer.INSTANCE.getRandomCard();
            if (card != null) {
                card.getImage().setTranslateX(27);
                deckTable.getChildren().add(card.getImage());
                Dealer.INSTANCE.giveBeer(players);
                if(card.getRank() == 12) Dealer.INSTANCE.giveQueen(players, card);
                if(card.getRank() == 1) Dealer.INSTANCE.giveAce(players, card);
                if(card.getRank() == 13){
                    root.getChildren().addAll(addRuleLabel, addRuleTextField, addRuleButton);
                    drawCard.setDisable(true);
                }
                table.getColumns().stream().forEach(s -> {s.setVisible(false); s.setVisible(true); });
            } else{
                drawCard.setDisable(true);
                deckTable.getChildren().addAll(resetDeck, resetTable);
            }
        });
        deckTable.setPadding(new Insets(10, 0, 10, (stage.getWidth() - 110) / 2));
        deckTable.getChildren().add(drawCard);


        submitButton.setOnMouseClicked(e -> {
            // get player names from textfields
            List<TextField> tfs = new ArrayList<>();
            root.getChildren().stream().filter(s -> (s instanceof TextField)).forEach(s -> tfs.add((TextField) s));
            tfs.stream().forEach(s -> playerNames.add(s.getText()));

            //remove children elements
            root.getChildren().removeAll(root.getChildren().stream().collect(Collectors.toList()));

            // add new players to the table
            playerNames.stream().forEach(s -> players.add(new Player(s, null, null, null)));
            table.setItems(players);
            table.setPrefHeight((table.getItems().size() * beerRowHeight) + tableHeaderHeight);
            table.setFixedCellSize(beerRowHeight);
            rules.add(new Rule("Még nem húztál királyt!"));
            rulesTable.setItems(rules);
            rulesTable.setPrefHeight(
                    (rulesTable.getItems().size() * tableRowHeight) + tableHeaderHeight);
            root.getChildren().addAll(table, deckTable, rulesTable);
        });

        stage.setResizable(false);
        stage.show();
    }

}
