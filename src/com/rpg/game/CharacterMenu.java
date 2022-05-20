package com.rpg.game;

import com.rpg.entity.character.chars.Mage;
import com.rpg.inventory.EquipMenu;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CharacterMenu {

    EquipMenu MENU = new EquipMenu();
    Scanner SCANNER = new Scanner(System.in);
    String input;

    public void openMenu(Mage character) throws InterruptedException {
        while (true) {
            System.out.println("~+~+~+~+~+~ Character Menu from: " + character.getName() + " ~+~+~+~+~+~\n" +
                    "[stats] | Show character stats\n" +
                    "[rune] | Open the Rune menu\n" +
                    "[exit] | Close the Character Menu\n" +
                    "~+~+~+~+~+~ Character Menu from: \" + character.getName() + \" ~+~+~+~+~+~");
            input = SCANNER.nextLine().toLowerCase();

            switch (input) {
                case "stats" -> {
                    System.out.println("######### " + character.getName() + " (" + character.getGender() + ") Character-Stats ######### \n" +
                            "Health: " + character.getHP() + "/" + character.getMaxHP() + "  |  " + "Defense: " + character.getDEF() + "\n" +
                            "Attack: " + character.getATK() + "  |  Mana: " + character.getMana() + "  |  " + "Intelligence: " + character.getIntelligence() + "\n" +
                            "######### " + character.getName() + " (" + character.getGender() + ") Character-Stats ######### \n");
                }
                case "rune" -> {
                    MENU.equipRuneMenu(character);
                }
                case "exit" -> {
                    return;
                }
                default -> {
                    System.out.println("Wrong input!");
                    TimeUnit.SECONDS.sleep(1);
                }
            }
        }
    }
}
