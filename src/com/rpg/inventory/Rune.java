package com.rpg.inventory;

import com.rpg.entity.character.Character.charElement;
import com.rpg.entity.character.chars.Mage;

public class Rune {

    private int value;
    private double multiplier;
    private String name;
    public enum runeType {
        ATK,
        DEF,
        HP,
        INT,
        MANA,
        ELEMENT
    }
    private runeType type;

    public Rune(runeType type, String name, int value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public Rune(runeType type, String name, double multiplier) {
        this.type = type;
        this.name = name;
        this.multiplier = multiplier;
    }

    public void runeBuff(Mage character) {
        if (getValue() != 0) {
            switch (this.type) {
                case HP -> character.setHP(character.getHP() + value);
                case ATK -> character.setATK(character.getATK() + value);
                case DEF -> character.setDEF(character.getDEF() + value);
                case INT -> character.setIntelligence(character.getIntelligence() + value);
                case MANA -> character.setMana(character.getMana() + value);
                default -> {
                    System.out.println("Error: runeBuff | wrong case");
                    System.exit(1);
                }
            }
        } else if (getMultiplier() != 0) {
            switch (this.type) {
                case HP -> character.setHP(character.getHP() * multiplier);
                case ATK -> character.setATK(character.getATK() * multiplier);
                case DEF -> character.setDEF(character.getDEF() * multiplier);
                case INT -> character.setIntelligence((int) (character.getIntelligence() * multiplier));
                case MANA -> character.setMana((int) (character.getMana() * multiplier));
                default -> {
                    System.out.println("Error: runeBuff | wrong case");
                    System.exit(1);
                }
            }
        }
    }

    public void runeBuffRemove(Mage character) {
        if (getValue() != 0) {
            switch (this.type) {
                case HP -> character.setHP(character.getHP() - value);
                case ATK -> character.setATK(character.getATK() - value);
                case DEF -> character.setDEF(character.getDEF() - value);
                case INT -> character.setIntelligence(character.getIntelligence() - value);
                case MANA -> character.setMana(character.getMana() - value);
                default -> {
                    System.out.println("Error: runeBuff | wrong case");
                    System.exit(1);
                }
            }
        } else if (getMultiplier() != 0) {
            switch (this.type) {
                case HP -> character.setHP(character.getHP() / multiplier);
                case ATK -> character.setATK(character.getATK() / multiplier);
                case DEF -> character.setDEF(character.getDEF() / multiplier);
                case INT -> character.setIntelligence((int) (character.getIntelligence() / multiplier));
                case MANA -> character.setMana((int) (character.getMana() / multiplier));
                default -> {
                    System.out.println("Error: runeBuff | wrong case");
                    System.exit(1);
                }
            }
        }
    }

    public void runeElementChange(charElement element, Mage character) {
        if (this.type == runeType.ELEMENT) {
            character.setElement(element);
        } else {
            System.out.println("Error: runeElementChange | It's not an ELEMENT!");
            System.exit(1);
        }
    }

    public runeType getType() {
        return type;
    }

    public void setType(runeType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }
}