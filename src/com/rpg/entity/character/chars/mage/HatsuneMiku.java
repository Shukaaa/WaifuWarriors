package com.rpg.entity.character.chars.mage;

import com.rpg.entity.character.Character;
import com.rpg.entity.character.chars.Mage;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class HatsuneMiku extends Mage {

    private static final Scanner SCANNER = new Scanner(System.in);

    public HatsuneMiku() {
        super(0, 500, "Hatsune Miku", 30, 5, 5, Gender.Female, 0, 0, charElement.Earth);
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
                {"7-8", "Scream", "Attack with (1.25x) + enemy get suspended 1 Round"},
                {"9-10", "Idol Gift", "+420 Mana & Normal Attack"}});

        int dice = diceRoll();
        System.out.println("You rolled a " + dice + "\n");

        switch (dice) {
            case 1, 2, 3, 4, 5, 6 -> {
                attackAnnouncement("Basic Attack");
                basicAttack(enemy);
                changeMana();
            }
            case 7, 8 -> {
                attackAnnouncement("Scream");
                basicAttack(enemy, 1.25);
                suspend(enemy, 1);
                changeMana();
            }
            case 9, 10 -> {
                attackAnnouncement("Idol Gift");
                manaBuff(420);
                attackBuff(1.25);
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