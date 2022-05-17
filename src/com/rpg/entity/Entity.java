package com.rpg.entity;

public class Entity {

    private int posX;
    private double maxHP;
    private double HP;

    public Entity(int posX, double HP) {
        this.posX = posX;
        this.maxHP = HP;
        this.HP = HP;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public double getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(double maxHP) {
        this.maxHP = maxHP;
    }

    public double getHP() {
        return HP;
    }

    public void setHP(double HP) {
        this.HP = Math.max(HP, 0);
    }
}
