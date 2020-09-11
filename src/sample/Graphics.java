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

        VBox vBox = new VBox();
        Map<Button, Integer> test = createBtnsHash(colors);
        Set<Button> amount = new HashSet<>();
        List<Button> btnList = new ArrayList<>(test.keySet());

        Label placement = new Label("-");
        placement.setStyle("-fx-font-size: 45pt");
        placement.setVisible(false);

        GridPane grid = createGrid(btnList);

        vBox.getChildren().add(grid);
        VBox.setMargin(grid, new Insets(20,20,20,20));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(placement);

        for (Map.Entry<Button, Integer> entry : test.entrySet()) {
            Button btn = entry.getKey();
            Integer v = entry.getValue();
            btn.setOnAction(e -> {
                btn.setText(String.valueOf(v));
                amount.add(btn);
                if(amount.size() == 10){
                    List<Integer> winningNums = winningNums();
                    addWins(winningNums, grid, colors);
                    place.set(checkNumbers(test.values(), winningNums));
                    placement.setText("You placed " + place);
                    placement.setVisible(true);
                }
            });
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
                            "-fx-min-width: 75px; " +
                            "-fx-min-height: 75px; " +
                            "-fx-max-width: 75px; " +
                            "-fx-max-height: 75px;" +
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

    public GridPane createGrid(List<Button> buttons){
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

    public Map<Button, Integer> createBtnsHash(String[] colors){
        Map<Button, Integer> btnDict = new HashMap<>();
        for(int i = 0; i < 10; i++){
            int randomNum = ThreadLocalRandom.current().nextInt(1, 36);
            String color = colors[ThreadLocalRandom.current().nextInt(0, 3)];
            Button button = new Button("?");
            button.setStyle(
                    "-fx-background-radius: 5em; " +
                            "-fx-min-width: 60px; " +
                            "-fx-min-height: 60px; " +
                            "-fx-max-width: 60px; " +
                            "-fx-max-height: 60px;" +
                            "-fx-background-color: " + color + ";" +
                            "-fx-background-insets: 0px;" +
                            "-fx-text-fill: #262626;" +
                            "-fx-font-size: 15pt;" +
                            "-fx-font-weight: bold;"
            );
            btnDict.put(button, randomNum);
        }
        return btnDict;
    }
}