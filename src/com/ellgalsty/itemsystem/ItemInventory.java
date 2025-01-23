package com.ellgalsty.itemsystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ItemInventory {
    private final ArrayList<Item> itemsInventory;
    private static final Map<Rarity, Rarity> resultingRaritiesMap = new HashMap<>();

    static {
        resultingRaritiesMap.put(Rarity.COMMON, Rarity.GREAT);
        resultingRaritiesMap.put(Rarity.GREAT, Rarity.RARE);
        resultingRaritiesMap.put(Rarity.RARE, Rarity.EPIC);
        resultingRaritiesMap.put(Rarity.EPIC, Rarity.LEGENDARY);
    }

    public ItemInventory () {
        itemsInventory = new ArrayList<>();
    }

    public void addItem (Item item) {
        if (item != null) {
            itemsInventory.add(item);
        }
        groupItems();
    }

    public void removeItem (Item item) {
        itemsInventory.remove(item);
    }

    public Item getItemAt (int index) {
        return itemsInventory.get(index);
    }

    public ArrayList<Item> getItems () {
        return new ArrayList<>(itemsInventory);
    }

    public Item combine (Item item1, Item item2, Item item3) {
        if (!canCombine(item1, item2, item3)) {
            throw new IllegalArgumentException("can't combine these items:(");
        }
        Rarity rarity = item1.getRarity();
        if (rarity == Rarity.COMMON || rarity == Rarity.GREAT || rarity == Rarity.RARE || rarity == Rarity.EPIC) {
            item1.setRarity(resultingRaritiesMap.get(rarity));
            removeItem(item2);
            removeItem(item3);
            return item1;
        }
        return null;
    }

    public Item combine (Item item1, Item item2) {
        if (!canCombine(item1, item2)) {
            throw new IllegalArgumentException("can't combine these items:(");
        }
        Rarity rarity = item1.getRarity();
        if (item1.getUpgradeCount() == 1) {
            item1.upgrade();
            removeItem(item2);
            return item1;
        } else if (item2.getUpgradeCount() == 1) {
            item2.upgrade();
            removeItem(item1);
            return item2;
        } else {
            item1.upgrade();
            removeItem(item2);
            return item1;
        }
    }

    public Item upgrade (Item item1, Item item2, Item item3) {
        if (item1 == null || item2 == null || item3 == null) {
            System.out.println("can't combine null items:(");
        }
        Item upgradedItem = combine(item1, item2, item3);
        groupItems();
        return upgradedItem;
    }

    public Item upgrade (Item item1, Item item2) {
        if (item1 == null || item2 == null) {
            System.out.println("can't combine null items:(");
        }
        Item upgradedItem = combine(item1, item2);
        groupItems();
        return upgradedItem;
    }

    private boolean canCombine (Item item1, Item item2, Item item3) {
        if (item1 == null || item2 == null || item3 == null) {
            return false;
        }
        if (!haveSameRarity(item1, item2, item3)) {
            return false;
        }
        if (!haveSameItemType(item1, item2, item3)) {
            return false;
        }

        Rarity rarity = item1.getRarity();
        if (rarity == Rarity.COMMON || rarity == Rarity.GREAT || rarity == Rarity.RARE) {
            return true;
        } else if (rarity == Rarity.EPIC && item1.getUpgradeCount() == 2 && item2.getUpgradeCount() == 2 && item3.getUpgradeCount() == 2) {
            return true;
        }
        return false;
    }

    private boolean canCombine (Item item1, Item item2) {
        if (item1 == null || item2 == null) {
            return false;
        }
        if (!haveEpicRarity(item1, item2)) {
            return false;
        }
        if (item1.getUpgradeCount() == 1 || item2.getUpgradeCount() == 1) {
            return true;
        } else {
            return true;
        }
    }

    private boolean haveSameRarity (Item item1, Item item2, Item item3) {
        Rarity rarity = item1.getRarity();
        return item1.getRarity() == rarity && item2.getRarity() == rarity && item3.getRarity() == rarity;
    }

    private boolean haveEpicRarity (Item item1, Item item2) {
        Rarity rarity = item1.getRarity();
        return rarity == Rarity.EPIC && item2.getRarity() == rarity;
    }

    private boolean haveSameItemType (Item item1, Item item2, Item item3) {
        ItemType itemType = item1.getItemType();
        return item2.getItemType() == itemType && item3.getItemType() == itemType;
    }

    public void groupItems () {
        itemsInventory.sort(Comparator.comparing(Item::getRarity).thenComparing(Item::getName));
    }

//    public boolean noMoreCombosLeft(){
//
//    }
}
