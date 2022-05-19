package com.rpg.entity.character;

import com.rpg.entity.Entity;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Character extends Entity {

    private static final Random RANDOM = new Random();

    private String name;
    private double ATK;
    private double DEF;
    private int intelligence;
    private int suspend;
    private double lastATK;
    private double lastDEF;
    private int lastIntelligence;
    public enum charElement {
        Fire,
        Ice,
        Wind,
        Earth,
        Thunder,
        Water
    }
    private final charElement element;

    public Character(int posX, double HP, String name, double ATK, double DEF, int intelligence, int suspend, charElement element) {
        super(posX, HP);
        this.name = name;
        this.ATK = ATK;
        this.DEF = DEF;
        this.lastDEF = DEF;
        this.lastIntelligence = intelligence;
        this.intelligence = intelligence;
        this.suspend = suspend;
        this.element = element;
    }

    // AttackCalc Functions
    public double ATK(Character enemy) {
        double ATK = (getATK() - enemy.getDEF()) * elementReactions(enemy);
        if (enemy.intelligence > getIntelligence()) {
            double intValue = enemy.intelligence - getIntelligence();
            ATK = ATK - (intValue / 10);
        } else if(getIntelligence() > enemy.intelligence) {
            double intValue = getIntelligence() - enemy.intelligence;
            ATK = ATK + (intValue / 10);
        }
        if (ATK <= 0) {
            ATK = 1;
        }
        return ATK * 1.25;
    }

    public double Crit() {
        int min = 1;
        int max = 10;
        double critValue = 1;

        int value = RANDOM.nextInt(min, max + 1);
        switch (value) {
            case 8, 9 -> {
                critValue = 1.25;
                System.out.println("[Fight Log]: ### Small Crit hit! + 1.25x ###");
            }
            case 10 -> {
                critValue = 1.5;
                System.out.println("[Fight Log]: ### Big Crit hit! + 1.5x ###");
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
                    case Ice -> elementValue = 1.25;
                    case Water -> elementValue = 0.75;
                    default -> {
                    }
                }
            }
            case Ice -> {
                switch (enemy.getElement()) {
                    case Wind -> elementValue = 1.25;
                    case Fire -> elementValue = 0.75;
                    default -> {
                    }
                }
            }
            case Wind -> {
                switch (enemy.getElement()) {
                    case Earth -> elementValue = 1.25;
                    case Ice -> elementValue = 0.75;
                    default -> {
                    }
                }
            }
            case Earth -> {
                switch (enemy.getElement()) {
                    case Thunder -> elementValue = 1.25;
                    case Wind -> elementValue = 0.75;
                    default -> {
                    }
                }
            }
            case Thunder -> {
                switch (enemy.getElement()) {
                    case Water -> elementValue = 1.25;
                    case Earth -> elementValue = 0.75;
                    default -> {
                    }
                }
            }
            case Water -> {
                switch (enemy.getElement()) {
                    case Fire -> elementValue = 1.25;
                    case Thunder -> elementValue = 0.75;
                    default -> {
                    }
                }
            }
        }
        return elementValue;
    }

    public void suspend(Character enemy, int rounds) throws InterruptedException {
        enemy.setSuspend(getSuspend() + rounds);
        System.out.println(enemy.name + " got suspended for " + rounds + "x Round");
        TimeUnit.SECONDS.sleep(1);
    }

    public void attackBuff(double multiplier) throws InterruptedException {
        lastATK = getATK();
        setATK(getATK() * multiplier);
        System.out.println(getName() + " buffed attack\n" +
                "ATK before: " + lastATK + "  |  ATK after buff: " + Math.round(getATK()));
        TimeUnit.SECONDS.sleep(1);
    }

    public void defBuff(double num) throws InterruptedException {
        lastDEF = getDEF();
        setDEF(getDEF() + num);
        System.out.println(getName() + " buffed defense\n" +
                "DEF before: " + lastDEF + "  |  DEF after buff: " + Math.round(getDEF()));
        TimeUnit.SECONDS.sleep(1);
    }

    public void intBuff(double num) throws InterruptedException {
        lastIntelligence = getIntelligence();
        setIntelligence((int) (getIntelligence() + num));
        System.out.println(getName() + " buffed intelligence\n" +
                "DEF before: " + lastIntelligence + "  |  INT after buff: " + Math.round(getIntelligence()));
        TimeUnit.SECONDS.sleep(1);
    }

    public void healing(double heal) throws InterruptedException {
        double hpBefore = getHP();
        setHP(getHP() + heal);
        if (getHP() > getMaxHP()) {
            setHP(getMaxHP());
        }
        System.out.println(getName() + " got healed by " + (getHP() - hpBefore) + "\n" +
                "HP before: " + hpBefore + " HP after healing: " + getHP());
        TimeUnit.SECONDS.sleep(1);
    }

    public void multiATK(Character enemy, int min, int max, double multiplier) throws InterruptedException {
        int value = RANDOM.nextInt(min, max + 1);
        for (int i = 1; i <= value; i++) {
            basicAttack(enemy, multiplier);
            System.out.println("Hitted (" + i + ")");
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("Attack done! Connected attacks: " + value);
    }

    // Basic Attack
    public void basicAttack(Character enemy) throws InterruptedException {
        this.basicAttack(enemy, 1);
    }

    public void basicAttack(Character enemy, double atkModifier) throws InterruptedException {
        double ATK = ATK(enemy) * atkModifier;
        double Crit = Crit();

        ATK = Math.round(ATK * Crit);

        double healtBefore = enemy.getHP();
        enemy.setHP(enemy.getHP() - ATK);

        this.lastATK = ATK;

        attackLogBuilder(getName(), enemy.getName(), ATK, healtBefore, enemy.getHP());
    }

    public double getLastATK() {
        return lastATK;
    }

    // Dice Functions
    public int diceRoll() throws InterruptedException {
        int min = 1;
        int max = 10;

        int value = RANDOM.nextInt(min, max + 1);
        if (value > 10) {
            value = 1;
        }

        diceRollingDialog();

        return value;
    }

    public void diceRollingDialog() throws InterruptedException {
        System.out.println("Rolling started.");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Rolling started..");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Rolling started...");
        TimeUnit.SECONDS.sleep(1);
    }

    public boolean suspendCheck() {
        if (getSuspend() > 0) {
            System.out.println(getName() + " isn't able to attack because " + getName() + " got suspended for " + getSuspend() + "x round.\n");
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

    public void attackAnnouncement(String attackName) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("[Fight Log]: " + getName() + " attacking with " + attackName);
        TimeUnit.SECONDS.sleep(1);
    }

    public String getName() {
        return name;
    }

    public double getATK() {
        return Math.round(ATK);
    }

    public void setATK(double ATK) {
        this.ATK = Math.round(ATK);
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

    public double getLastDEF() {
        return lastDEF;
    }
}