package com.rpg.inventory;

import com.rpg.entity.character.PlayerChar;
import com.rpg.entity.character.chars.Mage;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class EquipMenu {

    Scanner SCANNER = new Scanner(System.in);

    public LinkedList<Rune> unequippedRuneList = new LinkedList<Rune>();

    public void runeRegister(String name, Rune.runeType type, int value) {
        unequippedRuneList.add(new Rune(type, name, value));
    }

    public void runeRegister(String name, Rune.runeType type, double multiplier) {
        unequippedRuneList.add(new Rune(type, name, multiplier));
    }

    public void showAvailableRunes() {
        for (int i = 0; unequippedRuneList.size() > i; i++) {
            Rune rune = unequippedRuneList.get(i);
            if (rune.getMultiplier() != 0.0) {
                System.out.println("Name: " + rune.getName() + "  |  Description: This Rune boost your " + rune.getType() + ". With " + rune.getMultiplier() + "x\n");
            } else if (rune.getValue() != 0) {
                System.out.println("Name: " + rune.getName() + "  |  Description: This Rune boost your " + rune.getType() + " with +" + rune.getValue() + "\n");
            } else {
                System.out.println("Error: showAvailableRunes | else feedback");
            }
        }
    }

    public void equipRuneMenu(Mage character) throws InterruptedException {
        String input;
        while (true) {
            System.out.println("*#*#*#*#* " + character.getName() + " Rune Menu *#*#*#*#*\n" +
                    "[show] - See your equipped Rune\n" +
                    "[showall] - See all available/equippable Runes\n" +
                    "[equip] - Equip one Rune\n" +
                    "[remove] - Remove one Rune\n" +
                    "[exit] - Close the Rune menu\n" +
                    "*#*#*#*#* \" + character.getName() + \" Rune Menu *#*#*#*#*\\n");
            input = SCANNER.nextLine().toLowerCase();
            switch (input) {
                case "show" -> {
                    if (character.getRune() == null) {
                        System.out.println("You have no rune equipped!");
                    } else {
                        if (character.getRune().getMultiplier() != 0.0) {
                            System.out.println("Name: " + character.getRune().getName() +
                                    "  |  Description: This Rune boost your " + character.getRune().getType() +
                                    ". With " + character.getRune().getMultiplier() + "x\n");
                        } else if (character.getRune().getValue() != 0) {
                            System.out.println("Name: " + character.getRune().getName() +
                                    "  |  Description: This Rune boost your " + character.getRune().getType() +
                                    " with +" + character.getRune().getValue() + "\n");
                        } else {
                            System.out.println("Error: showAvailableRunes | else feedback");
                        }
                    }
                }
                case "equip" -> {
                    if (character.getRune() != null) {
                        System.out.println("You can only equip one Rune! Please remove it first\n");
                    } else {
                        showAvailableRunes();
                        input = SCANNER.nextLine().toLowerCase();
                        for (int i = 0; unequippedRuneList.size() > i; i++) {
                            Rune rune = unequippedRuneList.get(i);
                            if (input.equals(rune.getName().toLowerCase())) {
                                System.out.println("You equipped " + rune.getName() + "\n");
                                character.setRune(rune);
                                rune.runeBuff(character);
                            }
                        }
                        if (character.getRune() == null) {
                            System.out.println("Wrong name input, you get back to the Rune menu.");
                            TimeUnit.SECONDS.sleep(1);
                        }
                    }
                }
                case "remove" -> {
                    if (character.getRune() == null) {
                        System.out.println("You have no rune equipped!");
                    } else {
                        System.out.println("You removed " + character.getRune().getName() + "\n");
                        character.setRune(null);
                        character.getRune().runeBuffRemove(character);
                    }
                }
                case "exit" -> {
                    return;
                }
                default -> {
                    System.out.println("Wrong input please try again\n");
                    TimeUnit.SECONDS.sleep(1);
                }
            }
        }
    }

}
