package com.rpg.entity.character.npc_chars;

import com.rpg.entity.character.Character;
import com.rpg.entity.character.NPC;

public class ClashRoyaleMage extends NPC {
    public ClashRoyaleMage() {
        super(0, 450, "Clash Royale Mage", 50, 5, 15, 0, charElement.Fire);
    }

    public void attack(Character enemy) throws InterruptedException {
        if (suspendCheck()) return;

        attackRollBuilder(new String[][]{{"1-7", "Basic Attack", "Does normal damage"},
                {"8-9", "Fireball Upgrade", "Damage * 1.1"},
                {"10", "Doubled Fireball", "Does x2 damage"}});

        int dice = diceRoll();
        System.out.println(getName() + "rolled a " + dice + "\n");

        switch (dice) {
            case 1, 2, 3, 4, 5, 6, 7 -> {
                attackAnnouncement("Basic Attack");
                basicAttack(enemy);
            }
            case 8, 9 -> {
                attackAnnouncement("Fireball Upgrade");
                setATK(getATK() * 1.1);
                basicAttack(enemy);
            }
            case 10 -> {
                attackAnnouncement("Doubled Fireball");
                basicAttack(enemy, 2);
            }
        }
    }
}