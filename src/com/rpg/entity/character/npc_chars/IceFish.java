package com.rpg.entity.character.npc_chars;

import com.rpg.entity.character.Character;
import com.rpg.entity.character.NPC;

public class IceFish extends NPC {
    public IceFish() {
        super(0, 440, "Ice Fish", 25, 10, 5, 0, charElement.Ice);
    }

    public void attack(Character enemy) throws InterruptedException {
        if (suspendCheck()) return;

            attackRollBuilder(new String[][]{{"1-7", "Basic Attack", "Does normal damage"},
                    {"8-9", "Freeze", "Freezes you for 1 Round + x0.5 Damage"},
                    {"10", "Heavy Attack", "Does a lot of damage"}});

        int dice = diceRoll();
        System.out.println(getName() + "rolled a " + dice + "\n");

        switch (dice) {
            case 1, 2, 3, 4, 5, 6, 7 -> {
                attackAnnouncement("Basic Attack");
                basicAttack(enemy);
            }
            case 8, 9 -> {
                attackAnnouncement("Freeze");
                basicAttack(enemy, 0.5);
                enemy.setSuspend(getSuspend() + 1);
                System.out.println("You got suspended for 1 Round");
            }
            case 10 -> {
                attackAnnouncement("Heavy Attack");
                basicAttack(enemy, 1.5);
            }
        }
    }
}