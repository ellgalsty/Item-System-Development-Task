package com.ellgalsty.itemsystem;

public class Item {
    private final String name;
    private Rarity rarity;
    private final ItemType itemType;
    private int upgradeCount;

    public Item(String name, Rarity rarity, ItemType itemType) {
        this.name = name;
        this.rarity = rarity;
        this.itemType = itemType;
        upgradeCount = 0;
    }
    public Item(String name, Rarity rarity, ItemType itemType, int upgradeCount) {
        this.name = name;
        this.rarity = rarity;
        this.itemType = itemType;
        this.upgradeCount = upgradeCount;
    }

    public String getName() {
        return name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public int getUpgradeCount() {
        return upgradeCount;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }
    public void upgrade(){
        upgradeCount++;
    }

    public String toString() {
        if (rarity == Rarity.EPIC) {
            return name + " (" + rarity.toString() + " " + upgradeCount + ")";
        }
        return name + " (" + rarity.toString() + ")";
    }
}
