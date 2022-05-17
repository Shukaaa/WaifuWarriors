package com.rpg.entity.character.chars.mage;

import com.rpg.entity.character.Character;
import com.rpg.entity.character.chars.Mage;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Rem extends Mage {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();

    public Rem() {
        super(0, 500, "Rem", 35, 10, 50, Gender.Female, 0, 0, charElement.Water);
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
        attackRollBuilder(new String[][]{{"1-7", "Basic Attack", "Just Attack Damage"},
                {"8", "Morningstar-Spin", "Randomly do 1-3 Spins (Normal damage)"},
                {"9-10", "Maid Dress-Up", "Defense + 2.5 & Int + 5 (Attack Damage goes on)"}});

        int dice = diceRoll();
        System.out.println("You rolled a " + dice + "\n");

        switch (dice) {
            case 1, 2, 3, 4, 5, 6, 7 -> {
                attackAnnouncement("Basic Attack");
                basicAttack(enemy);
                changeMana();
            }
            case 8 -> {
                attackAnnouncement("Morningstar-Spin");

                int value = RANDOM.nextInt(1, 3 + 1);
                for (int i = 1; i <= value; i++) {
                    basicAttack(enemy);
                    System.out.println("Hitted (" + i + ")");
                    changeMana();
                    TimeUnit.SECONDS.sleep(1);
                }
                System.out.println("Attack done! Connected attacks: " + value);
            }
            case 9, 10 -> {
                attackAnnouncement("Maid Dress-Up");
                setDEF(getDEF() + 2.5);
                setIntelligence(getIntelligence() + 5);
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