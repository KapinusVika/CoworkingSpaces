package laba;

import static laba.ReservationService.initializeCoworkingSpaces;
import static laba.ReservationService.showMainMenu;

public class Main {

    public static void main(String[] args) {
        initializeCoworkingSpaces();
        showMainMenu();
    }
}