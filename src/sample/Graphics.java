package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;

public class Graphics extends Pane {
    public VBox P20(){
        VBox vBox = new VBox();
        Map<Integer, Button> btnDict = createBtnsMap();
        Map<Integer, Integer> intDict = createIntsMap();
        AtomicReference<String> place = new AtomicReference<>("");
        Set<Integer> amount = new HashSet<>();
        int count = 0;
        Label wins = new Label("-");
        Label placement = new Label("-");
        Label label = new Label("Winning numbers: \n");
        wins.setVisible(false);
        placement.setVisible(false);
        label.setVisible(false);

        Button[] tempList = new Button[btnDict.values().size()];
        btnDict.values().toArray(tempList);
        ArrayList<Button> btnList = new ArrayList<>(Arrays.asList(tempList));

        GridPane grid = createGrid(btnList);
        vBox.getChildren().add(grid);
        VBox.setMargin(grid, new Insets(20,20,20,20));
        vBox.setAlignment(Pos.CENTER);

        for(Button i : btnList){
            int finalCount = count;
            i.setOnAction(e -> {
                i.setText(intDict.get(finalCount).toString());
                amount.add(finalCount);
                if(amount.size() == 10){
                    List<Integer> winningNums = winningNums();
                    place.set(checkNumbers(intDict.values(), winningNums));
                    wins.setText(winningNums.toString());
                    placement.setText("You placed " + place);
                    wins.setVisible(true);
                    placement.setVisible(true);
                    label.setVisible(true);
                }
            });
            count++;
        }
        wins.setStyle("-fx-font-size: 45pt");
        placement.setStyle("-fx-font-size: 45pt");
        label.setStyle("-fx-font-size: 45pt");
        vBox.getChildren().addAll(label, wins, placement);
        return vBox;
    }

    public Map<Integer, Button> createBtnsMap(){
        Map<Integer, Button> btnDict = new HashMap<>();
        for(int i = 0; i < 10; i++){
            Button button = new Button("?");
            button.setStyle(
                    "-fx-background-radius: 5em; " +
                            "-fx-min-width: 50px; " +
                            "-fx-min-height: 50px; " +
                            "-fx-max-width: 50px; " +
                            "-fx-max-height: 50px;" +
                            "-fx-background-color: -fx-body-color;" +
                            "-fx-background-insets: 0px; "
            );
            btnDict.put(i, button);
        }
        return btnDict;
    }

    public Map<Integer, Integer> createIntsMap(){
        Map<Integer, Integer> intDict = new HashMap<>();
        for(int i = 0; i <10; i++){
            int randomNum = ThreadLocalRandom.current().nextInt(1, 36);
            intDict.put(i, randomNum);
        }
        return intDict;
    }

    public GridPane createGrid(ArrayList<Button> buttons){
        GridPane grid = new GridPane();
        grid.setHgap(30);
        grid.setVgap(30);
        for(int i = 0; i < 10; i++){
            grid.add(buttons.get(i), i, 0);
        }
        grid.setAlignment(Pos.CENTER);
        return grid;
    }

    public String checkNumbers(Collection<Integer> values, List<Integer> winningNums){
        int count = 0;
        System.out.println(winningNums);
        System.out.println(values);

        for(Integer i : values){
            if(winningNums.contains(i)){
                count++;
                System.out.println(i);
            }
        }
        if(count == 7){
            System.out.println("First price");
            return "first";
        } else if(count == 6){
            int extraNum = ThreadLocalRandom.current().nextInt(1, 36);
            if (values.contains(extraNum)){
                return "second";
            } else{
                return "third";
            }
        } else if(count == 5){
            return "fourth";
        } else if(count == 4){
            return "fifth";
        } else{
            return ".. nowhere. You lost.";
        }

    }

    public List<Integer> winningNums(){
        List<Integer> winningNums = new ArrayList<>();
        for(int i = 0; i < 7; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 36);
            winningNums.add(randomNum);
        }
        return winningNums;
    }

    // Ville gerne have dette til at fungere, men er ikke sikker på hvordan man arbejder med Map i Map, da det ikke fungerer helt som de dictionaries jeg har siddet mest med
//    public Map<Integer, Map<Button, Integer>> createBtnsHash(){
//        Map<Integer, Map<Button, Integer>> btnDict = new HashMap<>();
//        for(int i = 0; i < 10; i++){
//            Map<Button, Integer> tempDict = new HashMap();
//            int randomNum = ThreadLocalRandom.current().nextInt(1, 36);
//            Button button = new Button("?");
//            button.setStyle(
//                    "-fx-background-radius: 5em; " +
//                            "-fx-min-width: 50px; " +
//                            "-fx-min-height: 50px; " +
//                            "-fx-max-width: 50px; " +
//                            "-fx-max-height: 50px;" +
//                            "-fx-background-color: -fx-body-color;" +
//                            "-fx-background-insets: 0px; "
//            );
//            tempDict.put(button, randomNum);
//            System.out.println(i);
//            btnDict.put(i, tempDict);
//        }
//        return btnDict;
//    }
}