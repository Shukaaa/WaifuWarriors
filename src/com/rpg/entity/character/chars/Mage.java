package com.rpg.entity.character.chars;

import com.rpg.entity.character.PlayerChar;
import com.rpg.inventory.EquipMenu;
import com.rpg.inventory.Rune;

import java.util.concurrent.TimeUnit;

public class Mage extends PlayerChar {

    private Rune rune;
    private int mana;

    public Mage(int posX, double HP, String name, double ATK, double DEF, int intelligence, Gender Gender, int mana, int suspend, charElement element) {
        super(posX, HP, name, ATK, DEF, intelligence, Gender, suspend, element);
        this.mana = mana;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void changeMana() {
        int lastATK = (int) getLastATK();
        setMana(getMana() + lastATK);
    }

    public void manaBuff(double num) throws InterruptedException {
        double lastMana = getMana();
        setMana((int) (getMana() + num));
        System.out.println(getName() + " buffed his mana\n" +
                "Mana before: " + lastMana + "  |  Mana after buff: " + Math.round(getMana()));
        TimeUnit.SECONDS.sleep(1);
    }

    public Rune getRune() {
        return rune;
    }

    public void setRune(Rune rune) {
        this.rune = rune;
    }
}