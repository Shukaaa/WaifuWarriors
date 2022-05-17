package com.rpg.entity.character.npc_chars;

import com.rpg.entity.character.Character;
import com.rpg.entity.character.NPC;

import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class IceFish extends NPC {
    public IceFish() {
        super(0, 400, "Ice Fish", 20, 2, 2, 0, charElement.Ice);
    }

    public void attack(Character enemy) throws InterruptedException {
        if (suspendCheck()) return;

        Scanner scanner = new Scanner(System.in);

        boolean inputWrong = true;
        do {
            System.out.println("""
                Type in [continue] to let the NPC roll
                1-7 = Basic Attack  |  Makes normal Damage
                8-9 = Freeze  |  Freezes you for 1 Round + x0.5 Damage
                10 = Heavy Attack  |  Makes a lot of Damage (x1.5)
                """);

            String input = scanner.nextLine().toLowerCase();

            if (Objects.equals(input, "continue")) {
                inputWrong = false;
                TimeUnit.SECONDS.sleep(1);
            } else {
                System.out.println("Wrong input, please try again\n");
            }
        } while (inputWrong);

        int dice = diceRoll();
        System.out.println(getName() + "rolled a " + dice + "\n");

        switch (dice) {
            case 1, 2, 3, 4, 5, 6, 7 -> {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("[Fight Log]: " + getName() + " attacking with Basic Attack");
                TimeUnit.SECONDS.sleep(1);
                basicAttack(enemy);
            }
            case 8, 9 -> {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("[Fight Log]: " + getName() + " attacking with Freeze");
                TimeUnit.SECONDS.sleep(1);
                basicAttack(enemy, 0.5);
                enemy.setSuspend(getSuspend() + 1);
                System.out.println("You got suspended for 1 Round");
            }
            case 10 -> {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("[Fight Log]: " + getName() + " attacking with Heavy Attack");
                TimeUnit.SECONDS.sleep(1);
                basicAttack(enemy, 1.5);
            }
        }
    }
}
