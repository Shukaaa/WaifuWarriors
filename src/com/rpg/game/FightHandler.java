package com.rpg.game;

import com.rpg.entity.character.Character;
import com.rpg.entity.character.Character.charElement;
import com.rpg.entity.character.NPC;
import com.rpg.entity.character.PlayerChar;
import com.rpg.entity.character.chars.mage.*;
import com.rpg.entity.character.npc_chars.*;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class FightHandler {

    Scanner scanner = new Scanner(System.in);

    public void fight(NPC enemy) throws InterruptedException {
        boolean wrongInput = true;
        do {
            System.out.println("You're fighting vs. " + enemy.getName() + "\nElement: " + enemy.getElement() + "\n");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Choose your character with [choose]\n" +
                    "If you need help with element reactions type [element]");
            String input = scanner.nextLine().toLowerCase();

            switch (input) {
                case "choose" -> {
                    boolean wrongInput2 = true;
                    do {
                        System.out.println("""
                                You can choose these character:
                                [megumin]  |  Element: Fire
                                [rem]  |  Element: Wasser
                                [zerotwo]  |  Element: Thunder
                                [asuna]  |  Element: Wind
                                [raphtalia]  |  Element: Earth
                                [hatsunemiku]  |  Element: Ice
                                """);
                        input = scanner.nextLine().toLowerCase();
                        switch (input) {
                            case "megumin" -> {
                                Megumin megumin = new Megumin();
                                startFight(enemy, megumin);
                                wrongInput2 = false;
                            }
                            case "rem" -> {
                                Rem rem = new Rem();
                                startFight(enemy, rem);
                                wrongInput2 = false;
                            }
                            case "zerotwo" -> {
                                ZeroTwo zeroTwo = new ZeroTwo();
                                startFight(enemy, zeroTwo);
                                wrongInput2 = false;

                            }
                            case "asuna" -> {
                                Asuna asuna = new Asuna();
                                startFight(enemy, asuna);
                                wrongInput2 = false;

                            }
                            case "raphtalia" -> {
                                Raphtalia raphtalia = new Raphtalia();
                                startFight(enemy, raphtalia);
                                wrongInput2 = false;
                            }
                            case "hatsunemiku" -> {

                            }
                            default -> {
                                System.out.println("Wrong input");
                                TimeUnit.SECONDS.sleep(1);
                            }
                        }
                    } while (wrongInput2);
                    wrongInput = false;
                }
                case "element" -> elementHelper(enemy);
                default -> {
                    System.out.println("Wrong input");
                    TimeUnit.SECONDS.sleep(1);
                }
            }
        } while (wrongInput);
    }

    public void elementHelper(NPC enemy) throws InterruptedException {
        charElement goodElement = null;
        charElement badElement = null;

        switch (enemy.getElement()) {
            case Fire -> {
                goodElement = charElement.Ice;
                badElement = charElement.Water;
            }
            case Ice -> {
                goodElement = charElement.Wind;
                badElement = charElement.Fire;
            }
            case Wind -> {
                goodElement = charElement.Earth;
                badElement = charElement.Ice;
            }
            case Earth -> {
                goodElement = charElement.Thunder;
                badElement = charElement.Wind;
            }
            case Thunder -> {
                goodElement = charElement.Water;
                badElement = charElement.Earth;
            }
            case Water -> {
                goodElement = charElement.Fire;
                badElement = charElement.Thunder;
            }
        }
        System.out.println("The best element you can choose is " + badElement + " (x1.25)\n" +
                "and " + goodElement + " is bad (x0.75)\n");
        TimeUnit.SECONDS.sleep(3);
    }

    public boolean ranBoolean() {
        return Math.random() < 0.5;
    }

    public void startFight(NPC enemy, PlayerChar attacker) throws InterruptedException {
        System.out.println("The Character who starts gets randomized.");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("The Character who starts gets randomized..");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("The Character who starts gets randomized...");
        TimeUnit.SECONDS.sleep(1);

        Character startChar = enemy;
        if (ranBoolean()) startChar = attacker;

        System.out.println("The starting Character is: " + startChar.getName());

        System.out.println("Fight started!\n");
        TimeUnit.SECONDS.sleep(2);

        if (enemy.equals(startChar)) {
            while (enemy.getHP() > 0 && attacker.getHP() > 0) {
                if (fightLoopEnemy(enemy, attacker)) break;
                if (fightLoopChar(enemy, attacker)) break;
            }
        }
        if (attacker.equals(startChar)) {
            while (enemy.getHP() > 0 && attacker.getHP() > 0) {
                if (fightLoopChar(enemy, attacker)) break;
                if (fightLoopEnemy(enemy, attacker)) break;
            }
            if (enemy.getHP() <= 0) {
                System.out.println(attacker.getName() + " has won the battle!");
            } else if (attacker.getHP() <= 0){
                System.out.println(enemy.getName() + " has won the battle :( you lost");
            }
            attacker.setATK(attacker.getAtkBefore());
            attacker.setDEF(attacker.getLastDEF());
            attacker.setIntelligence(attacker.getIntelligence());
        }
    }

    private boolean fightLoopEnemy(NPC enemy, PlayerChar attacker) throws InterruptedException {
        if (enemy.getHP() <= 0) {
            System.out.println(enemy.getName() + " is dead.");
            return true;
        } else if (enemy instanceof IceFish) {
            ((IceFish) enemy).attack(attacker);
            return false;
        } else if (enemy instanceof BobTheBird) {
            ((BobTheBird) enemy).attack(attacker);
            return false;
        } else if (enemy instanceof DirtBlock) {
            ((DirtBlock) enemy).attack(attacker);
            return false;
        } else if (enemy instanceof PikachuMitEisenschwert) {
            ((PikachuMitEisenschwert) enemy).attack(attacker);
            return false;
        } else if (enemy instanceof WatergunWale) {
            ((WatergunWale) enemy).attack(attacker);
            return false;
        } else if (enemy instanceof ClashRoyaleMage) {
            ((ClashRoyaleMage) enemy).attack(attacker);
            return false;
        } else {
            System.out.println("Error FightHandler: fightLoopEnemy");
        }
        return true;
    }

    private boolean fightLoopChar(NPC enemy, PlayerChar attacker) throws InterruptedException {
        if (attacker.getHP() <= 0) {
            System.out.println(attacker.getName() + "is dead.");
            return true;
        } else if (attacker instanceof Megumin) {
            ((Megumin) attacker).attack(enemy);
            return false;
        } else if (attacker instanceof Rem) {
            ((Rem) attacker).attack(enemy);
            return false;
        } else if (attacker instanceof ZeroTwo) {
            ((ZeroTwo) attacker).attack(enemy);
            return false;
        } else if (attacker instanceof Asuna) {
            ((Asuna) attacker).attack(enemy);
            return false;
        } else if (attacker instanceof Raphtalia) {
            ((Raphtalia) attacker).attack(enemy);
            return false;
        } else if (attacker instanceof HatsuneMiku) {
            ((HatsuneMiku) attacker).attack(enemy);
            return false;
        } else {
            System.out.println("Error FightHandler: fightLoopChar");
        }
        return true;
    }
}