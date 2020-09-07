package Chaps;

import java.util.ArrayList;
import java.util.List;

public class Chap10 {

    public void chap10(){
        System.out.println("10.1:");
        System.out.println("Average vowels pr. word: " + averageVowels(createChap10List()));
    }

    public ArrayList<String> createChap10List(){
        ArrayList<String> chap10List = new ArrayList<>();
        chap10List.add("banana");
        chap10List.add("apple");
        chap10List.add("pears");
        chap10List.add("more fruits?");
        return chap10List;
    }

    public List<Character> initVowels(){
        List<Character> vowels = new ArrayList<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('y');
        return vowels;
    }

    public double averageVowels(ArrayList<String> list){
        if(list.size() != 0){
            List<Character> vowels = initVowels();
            ArrayList<Integer> avg = new ArrayList<>();
            int total = 0;
            for(String i : list){
                int count = 0;
                for(int j = 0; j < i.length(); j++){
                    if(vowels.contains(i.charAt(j))){
                        count+= 1;
                    }
                }
                avg.add(count);
            }
            for(int i : avg){
                total+= i;
            }
            return (double)total / (avg.size());
        }
        return 0.0;
    }
}
