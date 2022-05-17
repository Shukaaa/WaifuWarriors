package com.rpg.entity.character;

import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PlayerChar extends Character{

    private Gender Gender;

    public PlayerChar(int posX, double HP, String name, double ATK, double DEF, int intelligence, Gender Gender, int suspend, charElement element) {
        super(posX, HP, name, ATK, DEF, intelligence, suspend, element);
        this.Gender = Gender;
    }

    public enum Gender {
        Male,
        Female,
        Undefined
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
    public void attackRollBuilder(String attackRolls1, String attackName1, String attackDesc1,
                                  String attackRolls2, String attackName2, String attackDesc2,
                                  String attackRolls3, String attackName3, String attackDesc3) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        boolean inputWrong = true;
        do {
            System.out.println("Type in [start] to roll your Attack\n" +
                    attackRolls1 + " = " + attackName1 + "  |  " + attackDesc1 + "\n" +
                    attackRolls2 + " = " + attackName2 + "  |  " + attackDesc2 + "\n" +
                    attackRolls3 + " = " + attackName3 + "  |  " + attackDesc3 + "\n");

            String input = scanner.nextLine().toLowerCase();

            if (Objects.equals(input, "start")) {
                inputWrong = false;
                diceRollingDialog();
            } else {
                System.out.println("Wrong input, please try again\n");
            }
        } while (inputWrong);
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
}
