package com.rpg.entity.character.chars;

import com.rpg.entity.character.PlayerChar;

public class Mage extends PlayerChar {

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
}