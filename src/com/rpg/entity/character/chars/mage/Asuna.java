package com.rpg.entity.character.chars.mage;

import com.rpg.entity.character.Character;
import com.rpg.entity.character.chars.Mage;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Asuna extends Mage {

    private static final Scanner SCANNER = new Scanner(System.in);

    public Asuna() {
        super(0, 500, "Asuna", 25, 10, 50, Gender.Female, 0, 0, charElement.Wind);
    }

    // AttackMenu
    public void attack(Character enemy) throws InterruptedException {
        if (suspendCheck()) return;

        boolean wrongInput = true;
        do {
            fightMenuBuilder(getGender(), getMana(), 500, "Mana");
            String input = SCANNER.nextLine().toLowerCase();

            switch (input) {
                case "roll" -> {
                    rollAttack(enemy);
                    wrongInput = false;
                }
                case "ult" -> {
                    if (getMana() >= 500) {
                        ultAttack(enemy);
                        wrongInput = false;
                    } else {
                        System.out.println("You don't have enough Mana!");
                        TimeUnit.SECONDS.sleep(1);
                    }
                }
                default -> {
                    System.out.println("Wrong input");
                    TimeUnit.SECONDS.sleep(1);
                }
            }
        } while (wrongInput);
        setMana(getMana() + 33);
    }

    // AttackMethod
    public void rollAttack(Character enemy) throws InterruptedException {
        attackRollBuilder(new String[][]{{"1-5", "Basic Attack", "Just Attack Damage"},
                {"6-7", "Sword Swing", "Randomly do 1-2 Attacks with (x1,1)"},
                {"8-10", "Elf-Potion", "+50 HP, +25 Mana & + x1.05 Attack + Attack (x0.75)"}});

        int dice = diceRoll();
        System.out.println("You rolled a " + dice + "\n");

        switch (dice) {
            case 1, 2, 3, 4, 5 -> {
                attackAnnouncement("Basic Attack");
                basicAttack(enemy);
                changeMana();
            }
            case 6, 7 -> {
                attackAnnouncement("Sword Swing");
                multiATK(enemy, 1, 2, 1.1);
                changeMana();
            }
            case 8, 9, 10 -> {
                attackAnnouncement("Elf-Potion");
                healing(50);
                manaBuff(25);
                attackBuff(1.05);
                basicAttack(enemy, 0.75);
                changeMana();
            }
        }
    }

    public void ultAttack(Character enemy) throws InterruptedException {
        setMana(getMana() - 500);
        attackAnnouncement("Ult");
        basicAttack(enemy, 2);
        changeMana();
    }
}