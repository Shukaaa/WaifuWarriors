package com.rpg.entity.character.npc_chars;

import com.rpg.entity.character.Character;
import com.rpg.entity.character.NPC;

public class BobTheBird extends NPC {
    public BobTheBird() {
        super(0, 200, "Bob the Bird", 50, 0, 0, 0, charElement.Wind);
    }

    public void attack(Character enemy) throws InterruptedException {
        if (suspendCheck()) return;

        attackRollBuilder(new String[][]{{"1-7", "Basic Attack", "Does normal damage"},
                {"8-9", "Flying Birdman", "Does a lot of damage (x1.25)"},
                {"10", "Heavy Attack", "Does a lot of damage (x1.5)"}});

        int dice = diceRoll();
        System.out.println(getName() + "rolled a " + dice + "\n");

        switch (dice) {
            case 1, 2, 3, 4, 5, 6, 7 -> {
                attackAnnouncement("Basic Attack");
                basicAttack(enemy);
            }
            case 8, 9 -> {
                attackAnnouncement("Flying Birdman");
                basicAttack(enemy, 1.25);
            }
            case 10 -> {
                attackAnnouncement("Heavy Attack");
                basicAttack(enemy, 1.5);
            }
        }
    }
}