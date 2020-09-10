package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;

public class Graphics extends Pane {
    public VBox createLottery(){
        String[] colors = new String[]{"#2176ff", "#ff5cfc", "#5eff6c", "#ffff36"};
        AtomicReference<String> place = new AtomicReference<>("");
        int count = 0;

        VBox vBox = new VBox();
        Map<Integer, Button> btnDict = createBtnsMap(colors);
        Map<Integer, Integer> intDict = createIntsMap();
        Set<Integer> amount = new HashSet<>();

        Label placement = new Label("-");
        placement.setStyle("-fx-font-size: 45pt");
        placement.setVisible(false);

        Button[] tempList = new Button[btnDict.values().size()];
        btnDict.values().toArray(tempList);
        ArrayList<Button> btnList = new ArrayList<>(Arrays.asList(tempList));

        GridPane grid = createGrid(btnList);

        vBox.getChildren().add(grid);
        VBox.setMargin(grid, new Insets(20,20,20,20));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(placement);

        for(Button i : btnList){
            int finalCount = count;
            i.setOnAction(e -> {
                i.setText(intDict.get(finalCount).toString());
                amount.add(finalCount);
                if(amount.size() == 10){
                    List<Integer> winningNums = winningNums();
                    addWins(winningNums, grid, colors);
                    place.set(checkNumbers(intDict.values(), winningNums));
                    placement.setText("You placed " + place);
                    placement.setVisible(true);
                }
            });
            count++;
        }
        return vBox;
    }

    private void addWins(List<Integer> wins, GridPane grid, String[] colors) {
        int count = 1;
        for(Integer i : wins){
            Button w =  new Button(i.toString());
            String color = colors[ThreadLocalRandom.current().nextInt(0, 3)];
            w.setStyle(
                    "-fx-background-radius: 5em; " +
                            "-fx-min-width: 70px; " +
                            "-fx-min-height: 70px; " +
                            "-fx-max-width: 70px; " +
                            "-fx-max-height: 70px;" +
                            "-fx-background-color: " + color + ";" +
                            "-fx-background-insets: 0px;" +
                            "-fx-text-fill: #ffffff;" +
                            "-fx-font-size: 20pt;" +
                            "-fx-font-weight: bold;"
            );
            count++;
            grid.add(w, count, 3);
        }
    }

    public Map<Integer, Button> createBtnsMap(String[] colors){
        Map<Integer, Button> btnDict = new HashMap<>();
        for(int i = 0; i < 10; i++){
            String color = colors[ThreadLocalRandom.current().nextInt(0, 3)];
            Button button = new Button("?");
            button.setStyle(
                    "-fx-background-radius: 5em; " +
                            "-fx-min-width: 55px; " +
                            "-fx-min-height: 55px; " +
                            "-fx-max-width: 55px; " +
                            "-fx-max-height: 55px;" +
                            "-fx-background-color: " + color + ";" +
                            "-fx-background-insets: 0px;" +
                            "-fx-text-fill: #262626;" +
                            "-fx-font-size: 15pt;" +
                            "-fx-font-weight: bold;"
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
        for(Integer i : values){
            if(winningNums.contains(i)){
                count++;
                System.out.println(i);
            }
        }
        switch (count){
            case 7:
                return "first";
            case 6:
                int extraNum = ThreadLocalRandom.current().nextInt(1, 36);
                if (values.contains(extraNum)){
                    return "second";
                } else{
                    return "third";
                }
            case 5:
                return "fourth";
            case 4:
                return "fifth";
            default:
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