package com.rpg.entity.character.chars.mage;

import com.rpg.entity.character.Character;
import com.rpg.entity.character.chars.Mage;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Raphtalia extends Mage {

    private static final Scanner SCANNER = new Scanner(System.in);

    public Raphtalia() {
        super(0, 200, "Raphtalia", 20, 0, 10, Gender.Female, 0, 0, charElement.Earth);
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
                {"6-7", "Sword Attack", "Heavy Attack with (x1.5)"},
                {"8-10", "Age-Upgrade", "+125 HP (+100 MAX HP), +100 Mana , +x1.25 Attack, +7.5 DEF & 7.5 INT + (No Attack this Round)"}});

        int dice = diceRoll();
        System.out.println("You rolled a " + dice + "\n");

        switch (dice) {
            case 1, 2, 3, 4, 5 -> {
                attackAnnouncement("Basic Attack");
                basicAttack(enemy);
                changeMana();
            }
            case 6, 7 -> {
                attackAnnouncement("Sword Attack");
                basicAttack(enemy, 1.5);
                changeMana();
            }
            case 8, 9, 10 -> {
                attackAnnouncement("Age-Upgrade");
                setMaxHP(getMaxHP() + 100);
                healing(125);
                manaBuff(100);
                attackBuff(1.25);
                defBuff(7.5);
                intBuff(7.5);
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