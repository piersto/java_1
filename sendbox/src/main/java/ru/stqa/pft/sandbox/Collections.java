package ru.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main(String[] args) {

        String[] langs = {"Java", "Python", "PHP", "C#"};
        for (String l : langs) {
            System.out.println("I wonna program on " + l);
        }

        List<String> languages = Arrays.asList("Java", "Python", "PHP", "C#");
        for (int i = 0; i < languages.size(); i++) {
            System.out.println("I will know " + languages.get(i));

            //"Java", "Python", "PHP", "C#"
        }

        for (String l : languages) {
            System.out.println("I want to program on " + l);
        }

        List langageDeProgammation = Arrays.asList("Python", "C#", "C++");
        for (Object l : langageDeProgammation) {
            System.out.println("Je veux programmer sur " + l);
        }



    }
}