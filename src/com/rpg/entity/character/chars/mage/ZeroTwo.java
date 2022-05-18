package com.rpg.entity.character.chars.mage;

import com.rpg.entity.character.Character;
import com.rpg.entity.character.chars.Mage;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ZeroTwo extends Mage {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();

    public ZeroTwo() {
        super(0, 600, "Zero Two", 25, 12.5, 5, Gender.Female, 0, 0, charElement.Thunder);
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
        attackRollBuilder(new String[][]{{"1-6", "Basic Attack", "Just Attack Damage"},
                {"7-8", "Darling Ohayo!", "Tactical Darling Ohayo Scream that stun enemies for 1 Round (Damage x0.3)"},
                {"9-10", "FranXX Upgrade", "+69 HP & +69 Mana (Attack Damage goes on but x0.45)"}});

        int dice = diceRoll();
        System.out.println("You rolled a " + dice + "\n");

        switch (dice) {
            case 1, 2, 3, 4, 5, 6 -> {
                attackAnnouncement("Basic Attack");
                basicAttack(enemy);
                changeMana();
            }
            case 7, 8 -> {
                attackAnnouncement("Darling Ohayo!");
                suspend(enemy, 1);
                basicAttack(enemy, 0.3);
                changeMana();
            }
            case 9, 10 -> {
                attackAnnouncement("FranXX Upgrade");
                healing(69);
                manaBuff(69);
                basicAttack(enemy, 0.45);
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