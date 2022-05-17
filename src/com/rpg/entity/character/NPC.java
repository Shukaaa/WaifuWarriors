package com.rpg.entity.character;

import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NPC extends Character {

    public NPC(int posX, double HP, String name, double ATK, double DEF, int intelligence, int suspend, charElement element) {
        super(posX, HP, name, ATK, DEF, intelligence, suspend, element);
    }

    public void attackRollBuilder(String[][] attacks) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        boolean inputWrong = true;
        do {
            System.out.println("Type in [r] to let the NPC roll\n" +
                    attacks[0][0] + " = " + attacks[0][1] + "  |  " + attacks[0][2] + "\n" +
                    attacks[1][0] + " = " + attacks[1][1] + "  |  " + attacks[1][2] + "\n" +
                    attacks[2][0] + " = " + attacks[2][1] + "  |  " + attacks[2][2] + "\n");

            String input = scanner.nextLine().toLowerCase();

            if (Objects.equals(input, "r")) {
                inputWrong = false;
                TimeUnit.SECONDS.sleep(1);
            } else {
                System.out.println("Wrong input, please try again\n");
            }
        } while (inputWrong);
    }
}