package controller;

import view.State;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StateManager {
    private static final Map<String, State> states =  new HashMap<>();;
    private static State currentState;
    private static final Scanner scanner = new Scanner(System.in);;

    public static void addState(String name, State state) {
        states.put(name, state);
    }

    public static void setState(String name) {
        currentState = states.get(name);
        if (currentState == null) {
            throw new IllegalArgumentException("State '" + name + "' does not exist.");
        }
    }

    public static void run() {
        while (currentState != null) {
            currentState.onEnter();
            currentState.run(scanner);
            currentState.onExit();
        }
    }

    public static void exit() {
        currentState = null;
    }
}