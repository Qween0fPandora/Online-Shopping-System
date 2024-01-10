package view;

import java.util.Scanner;

public interface State {
    void onEnter();
    void run(Scanner scanner);
    void onExit();
}
