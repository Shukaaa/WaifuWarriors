package com.rpg.entity.character;

import com.rpg.entity.Entity;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Character extends Entity {

    private static final Random RANDOM = new Random();

    private String name;
    private double ATK;
    private double DEF;
    private int intelligence;
    private int suspend;
    private double lastATK;
    public enum charElement {
        Fire,
        Ice,
        Wind,
        Earth,
        Thunder,
        Water
    }
    private charElement element;

    public Character(int posX, double HP, String name, double ATK, double DEF, int intelligence, int suspend, charElement element) {
        super(posX, HP);
        this.name = name;
        this.ATK = ATK;
        this.DEF = DEF;
        this.intelligence = intelligence;
        this.suspend = suspend;
        this.element = element;
    }

    // AttackCalc Functions
    public double ATK(Character enemy) {
        double ATK = getATK() - enemy.getDEF();
        if (enemy.intelligence > getIntelligence()) {
            double intValue = enemy.intelligence - getIntelligence();
            ATK = ATK - (intValue / 10);
        }
        if (ATK <= 0) {
            ATK = 1;
        }
        return ATK;
    }

    public double Crit() {
        int min = 1;
        int max = 10;
        double critValue = 1;

        int value = RANDOM.nextInt(min, max + 1);
        switch (value) {
            case 8, 9 -> {
                critValue = 1.25;
                System.out.println("[Fight Log]: ### Small Crit hitted! + 1.25x ###");
            }
            case 10 -> {
                critValue = 1.5;
                System.out.println("[Fight Log]: ### Big Crit hitted! + 1.5x ###");
            }
            default -> {
            }
        }
        return critValue;
    }

    public double elementReactions(Character enemy) {
        double elementValue = 1;
        switch (getElement()) {
            case Fire -> {
                switch (enemy.getElement()) {
                    case Ice -> elementValue = 1.5;
                    case Water -> elementValue = 0.5;
                    default -> {
                    }
                }
            }
            case Ice -> {
                switch (enemy.getElement()) {
                    case Wind -> elementValue = 1.5;
                    case Fire -> elementValue = 0.5;
                    default -> {
                    }
                }
            }
            case Wind -> {
                switch (enemy.getElement()) {
                    case Earth -> elementValue = 1.5;
                    case Ice -> elementValue = 0.5;
                    default -> {
                    }
                }
            }
            case Earth -> {
                switch (enemy.getElement()) {
                    case Thunder -> elementValue = 1.5;
                    case Wind -> elementValue = 0.5;
                    default -> {
                    }
                }
            }
            case Thunder -> {
                switch (enemy.getElement()) {
                    case Water -> elementValue = 1.5;
                    case Earth -> elementValue = 0.5;
                    default -> {
                    }
                }
            }
            case Water -> {
                switch (enemy.getElement()) {
                    case Fire -> elementValue = 1.5;
                    case Thunder -> elementValue = 0.5;
                    default -> {
                    }
                }
            }
        }
        return elementValue;
    }

    // Basic Attack
    public void basicAttack(Character enemy) throws InterruptedException {
        this.basicAttack(enemy, 1);
    }

    public void basicAttack(Character enemy, double atkModifier) throws InterruptedException {
        double ATK = ATK(enemy) * atkModifier;
        double Crit = Crit();

        ATK = ATK * Crit;

        double healtBefore = enemy.getHP();
        enemy.setHP(enemy.getHP() - ATK);

        this.lastATK = ATK;

        attackLogBuilder(getName(), enemy.getName(), ATK, healtBefore, enemy.getHP());
    }

    public double getLastATK() {
        return lastATK;
    }

    // Dice Functions
    public int diceRoll() {
        int min = 1;
        int max = 10;

        int value = RANDOM.nextInt(min, max + 1);
        if (value > 10) {
            value = 1;
        }
        return value;
    }

    public boolean suspendCheck() {
        if (getSuspend() > 0) {
            System.out.println(getName() + " isn't able to attack because " + getName() + " got suspended for " + getSuspend() + "x round.");
            setSuspend(getSuspend() - 1);
            return true;
        }
        return false;
    }

    // AttackLog
    public void attackLogBuilder(String attackerName, String defenderName, double attackerAttack,
                                 double defenderHealthBefore, double defenderHealthAfter) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("[Fight Log]: " + attackerName + " dealt " + attackerAttack + " to " + defenderName);
        TimeUnit.SECONDS.sleep(2);
        System.out.println("[Fight Log]: " + defenderName + " had " + defenderHealthBefore + "HP, now: " + defenderHealthAfter + "HP\n");
        TimeUnit.SECONDS.sleep(2);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getATK() {
        return ATK;
    }

    public void setATK(double ATK) {
        this.ATK = ATK;
    }

    public double getDEF() {
        return DEF;
    }

    public void setDEF(double DEF) {
        this.DEF = DEF;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getSuspend() {
        return suspend;
    }

    public void setSuspend(int suspend) {
        this.suspend = suspend;
    }

    public charElement getElement() {
        return element;
    }

    public void setElement(charElement element) {
        this.element = element;
    }
}