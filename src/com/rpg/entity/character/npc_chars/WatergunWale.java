package com.rpg.entity.character.npc_chars;

import com.rpg.entity.character.Character;
import com.rpg.entity.character.NPC;

public class WatergunWale extends NPC {
    public WatergunWale() {
        super(0, 800, "Watergun Wale", 10, 15, 5, 0, charElement.Water);
    }

    public void attack(Character enemy) throws InterruptedException {
        if (suspendCheck()) return;

        attackRollBuilder(new String[][]{{"1-7", "Basic Attack", "Does normal damage"},
                {"8-9", "Watergun Turbo Hyper Splash", "Does 1.5x damage and stun you for 1 round"},
                {"10", "Wale Slapper", "Does x2 damage"}});

        int dice = diceRoll();
        System.out.println(getName() + "rolled a " + dice + "\n");

        switch (dice) {
            case 1, 2, 3, 4, 5, 6, 7 -> {
                attackAnnouncement("Basic Attack");
                basicAttack(enemy);
            }
            case 8, 9 -> {
                attackAnnouncement("Watergun Turbo Hyper Splash");
                basicAttack(enemy, 1.5);
            }
            case 10 -> {
                attackAnnouncement("Wale Slapper");
                basicAttack(enemy, 2);
            }
        }
    }
}