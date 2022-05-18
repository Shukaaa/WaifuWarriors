package com.rpg.entity.character.npc_chars;

import com.rpg.entity.character.Character;
import com.rpg.entity.character.NPC;

public class DirtBlock extends NPC {
    public DirtBlock() {
        super(0, 350, "Dirt Block", 25, 10, 0, 0, charElement.Earth);
    }

    public void attack(Character enemy) throws InterruptedException {
        if (suspendCheck()) return;

        attackRollBuilder(new String[][]{{"1-7", "Basic Attack", "Does normal damage"},
                {"8-9", "Dirty Dirt Shot", "Does a lot of damage (x1.5)"},
                {"10", "Trapped in a dirt house !!!", "You get suspended for 2 Rounds, damage -> x0.75"}});

        int dice = diceRoll();
        System.out.println(getName() + "rolled a " + dice + "\n");

        switch (dice) {
            case 1, 2, 3, 4, 5, 6, 7 -> {
                attackAnnouncement("Basic Attack");
                basicAttack(enemy);
            }
            case 8, 9 -> {
                attackAnnouncement("Dirty Dirt Shot");
                basicAttack(enemy, 1.5);
            }
            case 10 -> {
                attackAnnouncement("Heavy Attack");
                basicAttack(enemy, 0.5);
                suspend(enemy, 2);
            }
        }
    }
}