package Chaps;

import java.util.*;

public class Chap11 {

    public void chap11(){
        System.out.println("11.6:");
        System.out.println("Unique ints: " + uniqueInts(integerList()));
        System.out.println("11.8:");
        System.out.println("Length of string: " + maxLength(stringSet()));
        System.out.println("11.10:");
        System.out.println("Set after removal of even lengths: " + removeEvenLength(stringSet()));
        System.out.println("11.12:");
        System.out.println("Check occurrences: " + contains3(stringList(stringSet())));
        System.out.println("11.13:");
        System.out.println("Check duplicate values: " + isUnique(createMap()));
    }

    public List<Integer> integerList(){
        List<Integer> intList = new ArrayList<>();
        intList.add(5);
        intList.add(6);
        intList.add(7);
        intList.add(10);
        intList.add(7);
        intList.add(6);
        intList.add(13);
        intList.add(15);
        intList.add(5); //6
        return intList;
    }

    public Set<String> stringSet(){
        Set<String> stringSet = new HashSet<>();
        stringSet.add("String1");
        stringSet.add("String");
        stringSet.add("String3");
        stringSet.add("String the fourth");
        stringSet.add("String the fifth!! Crazy");
        stringSet.add("String :/");
        return stringSet;
    }

    public List<String> stringList(Set<String> set){
        List<String> list = new ArrayList<String>();
        list.addAll(set);
        list.add("String3");
        list.add("String3");
        list.add("String3");
        return list;
    }

    public int uniqueInts(List<Integer> list){ // 11.6
        Set<Integer> set = new HashSet<>();
        for (int i : list) {
            if (set.add(i)) {
                set.add(i);
            }
        }
        return set.size();
    }

    public int maxLength(Set<String> set){ // 11.8
        String temp = "";
        if (set.size() != 0) {
            for (String i : set) {
                if (temp.length() < i.length()) {
                    temp = i;
                }
            }
        }
        return temp.length();
    }

    public Set<String> removeEvenLength(Set<String> set){ //11.10
        if (set.size() != 0){
            set.removeIf(i -> i.length() % 2 == 0);
        }
        return set;
    }

    public boolean contains3(List<String> list){ // 11.12
        for (String i : list){
            if (Collections.frequency(list, i) > 2){
                System.out.println("String(s) that occurred > 2 times: " + i);
                return true;
            }
        }
        return false;
    }

    public HashMap<String, String> createMap(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Todoroki", "Shoto");
        map.put("Bakugo", "Katsuki");
        map.put("Bak_test", "Katsuki");
        map.put("Midoriya", "Izuku");
        map.put("Uzumaki", "Naruto");
        map.put("Uchiha", "Sasuke");
        return map;
    }

    public boolean isUnique(HashMap<String, String> map){ // 11.13
        for (String i : map.values()){
            if (Collections.frequency(map.values(), i) > 1){
                return false;
            }
        }
        return true;
    }
}
