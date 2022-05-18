package com.rpg.entity.character.npc_chars;

import com.rpg.entity.character.Character;
import com.rpg.entity.character.NPC;

public class PikachuMitEisenschwert extends NPC {
    public PikachuMitEisenschwert() {
        super(0, 300, "Pikachu with Iron Sword", 50, 0, 10, 0, charElement.Thunder);
    }

    public void attack(Character enemy) throws InterruptedException {
        if (suspendCheck()) return;

        attackRollBuilder(new String[][]{{"1-7", "Basic Attack", "Does normal damage"},
                {"8-9", "Lightning", "Does 0.75x damage and stun you for 1 round"},
                {"10", "Diamond Sword", "Does x1.25 damage for the rest of the round"}});

        int dice = diceRoll();
        System.out.println(getName() + "rolled a " + dice + "\n");

        switch (dice) {
            case 1, 2, 3, 4, 5, 6, 7 -> {
                attackAnnouncement("Basic Attack");
                basicAttack(enemy);
            }
            case 8, 9 -> {
                attackAnnouncement("Lightning");
                basicAttack(enemy, 0.75);
                suspend(enemy, 1);
            }
            case 10 -> {
                attackAnnouncement("Diamond Sword");
                attackBuff(1.25);
                basicAttack(enemy);
            }
        }
    }
}