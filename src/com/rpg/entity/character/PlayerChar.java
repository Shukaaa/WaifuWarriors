package com.rpg.entity.character;

import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PlayerChar extends Character {

    private Gender Gender;

    public enum Gender {
        Male,
        Female,
        Undefined
    }

    private double atkBefore;

    public PlayerChar(int posX, double HP, String name, double ATK, double DEF, int intelligence, Gender Gender, int suspend, charElement element) {
        super(posX, HP, name, ATK, DEF, intelligence, suspend, element);
        this.Gender = Gender;
        this.atkBefore = ATK;
    }

    public void fightMenuBuilder(PlayerChar.Gender gender, int manaStaminaValue, int ultCost, String manaOrStamina) {
        System.out.println("######### " + getName() + " (" + gender + ") Fight-Menu ######### \n" +
                "Health: " + getHP() + "/" + getMaxHP() + "  |  " + "Defense: " + getDEF() + "\n" +
                "Attack: " + getATK() + "  |  " + manaOrStamina + ": " + manaStaminaValue + "  |  " + "Intelligence: " + getIntelligence() + "\n" +
                "######### " + getName() + " (" + gender + ") Fight-Menu ######### \n" +
                "Type [roll] to make a roll attack  |  Requires: 0 Mana\n" +
                "Type [ult] to make a x2 Ult attack  |  Requires: " + ultCost + " Mana");
    }

    // DialogesBuilder
    public void attackRollBuilder(String[][] attacks) throws InterruptedException {
        System.out.println("Roll starting in 4 Seconds\n" +
                attacks[0][0] + " = " + attacks[0][1] + "  |  " + attacks[0][2] + "\n" +
                attacks[1][0] + " = " + attacks[1][1] + "  |  " + attacks[1][2] + "\n" +
                attacks[2][0] + " = " + attacks[2][1] + "  |  " + attacks[2][2] + "\n");
        TimeUnit.SECONDS.sleep(4);
    }

    public void diceRollingDialog() throws InterruptedException {
        System.out.println("Rolling started.");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Rolling started..");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Rolling started...");
        TimeUnit.SECONDS.sleep(1);
    }

    public Gender getGender() {
        return Gender;
    }

    public void setGender(Gender gender) {
        Gender = gender;
    }

    public double getAtkBefore() {
        return atkBefore;
    }

    public void setAtkBefore(double atkBefore) {
        this.atkBefore = atkBefore;
    }
}
