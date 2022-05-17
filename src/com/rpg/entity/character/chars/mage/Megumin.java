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
    }

    // AttackMethod
    public void rollAttack(Character enemy) throws InterruptedException {
        attackRollBuilder("1-6", "Basic Attack", "Just Attack Damage",
                "7-9", "Firewall", "0.5 * Attack + Enemy get suspend for 1 Round and can't attack",
                "10", "Fireball", "Normal Attack + 1.2x more Damage for every round because of the fire damage");

        int dice = diceRoll();
        System.out.println("You rolled a " + dice + "\n");

        switch (dice) {
            case 1, 2, 3, 4, 5, 6 -> {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("[Fight Log]: You're attacking with Basic Attack");
                TimeUnit.SECONDS.sleep(1);
                basicAttack(enemy);
                changeMana();
            }
            case 7, 8, 9 -> {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("[Fight Log]: You're attacking with Firewall");
                TimeUnit.SECONDS.sleep(1);
                basicAttack(enemy, 0.5);
                enemy.setSuspend(getSuspend() + 1);
                System.out.println("Enemy got suspended for 1 Round");
                changeMana();
            }
            case 10 -> {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("[Fight Log]: You're attacking with Fireball");
                TimeUnit.SECONDS.sleep(1);
                setATK(getATK() * 1.2);
                basicAttack(enemy);
                changeMana();
            }
        }
    }

    public void ultAttack(Character enemy) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("[Fight Log]: You're attacking with your Ult!");
        TimeUnit.SECONDS.sleep(1);
        basicAttack(enemy, 2);
        changeMana();
    }
}