package com.rpg.entity.character.chars.mage;

import com.rpg.entity.character.Character;
import com.rpg.entity.character.chars.Mage;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Megumin extends Mage {

    private static final Scanner SCANNER = new Scanner(System.in);

    public Megumin() {
        super(0, 400, "Megumin", 50, 5, 5, Gender.Female, 0, 0, charElement.Fire);
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
                {"7-9", "Firewall", "0.5 * Attack + Enemy get suspend for 1 Round and can't attack"},
                {"10", "Fireball", "Normal Attack + 1.15x more Damage for every round because of the fire damage"}});

        int dice = diceRoll();
        System.out.println("You rolled a " + dice + "\n");

        switch (dice) {
            case 1, 2, 3, 4, 5, 6 -> {
                attackAnnouncement("Basic Attack");
                basicAttack(enemy);
                changeMana();
            }
            case 7, 8, 9 -> {
                attackAnnouncement("Firewall");
                basicAttack(enemy, 0.5);
                suspend(enemy, 1);
                changeMana();
            }
            case 10 -> {
                attackAnnouncement("Fireball");
                attackBuff(1.15);
                basicAttack(enemy);
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