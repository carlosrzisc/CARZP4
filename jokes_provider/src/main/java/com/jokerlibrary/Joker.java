package com.jokerlibrary;

import java.util.Random;

public class Joker {
    public static String[] jokes = {
            "Q: What is a programmer's favourite hangout place?\nA: Foo Bar.",
            "Q: 0 is false and 1 is true, right?\nA: 1",
            "Algorithm: Word used by programmers when... they do not want to explain what they did.",
            "Unix is user friendly... It is just very particular about who its friends are",
            "Q: Why did the programmer quit his job?\nA: Because he did not get arrays (a raise)."};

    public static String getJoke() {
        return jokes[new Random().nextInt(jokes.length)];
    }
}
