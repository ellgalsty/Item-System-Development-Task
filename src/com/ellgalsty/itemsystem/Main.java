package com.ellgalsty.itemsystem;

import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        ItemInventory inventory = new ItemInventory();

        // some test items
        for (int i = 0; i < 15; i++) {
            inventory.addItem(randomItemGenerator());
        }

        while (true) {
            System.out.println(inventory);
            System.out.println();
            System.out.println("What items would you like to upgrade from above, my dearest user?\n");
            Scanner scanner = new Scanner(System.in);
            try {
                int index1 = getValidIndex(scanner, inventory);
                int index2 = getValidIndex(scanner, inventory);

                System.out.print("Enter an item index (if no 3rd item, just skip this babe:)): ");
                String userResponse3 = scanner.nextLine();

                if (index1 == index2 || (!userResponse3.isEmpty() && (Integer.parseInt(userResponse3) == index1
                        || Integer.parseInt(userResponse3) == index2))) {
                    throw new IllegalArgumentException("NOPE, can't upgrade if you input the same numbers:3");
                }

                if (userResponse3.isEmpty()) {
                    // upgrade with 2 items only (EPIC ones)
                    inventory.upgrade(
                            inventory.getItemAt(index1),
                            inventory.getItemAt(index2));
                } else {
                    int index3 = parseAndValidate(userResponse3, inventory);
                    // upgrade with 3 items
                    inventory.upgrade(
                            inventory.getItemAt(index1),
                            inventory.getItemAt(index2),
                            inventory.getItemAt(index3)
                    );
                }
            } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }

            System.out.println();
            System.out.println("Would you like to end the game? (y/n)\n");
            String response = scanner.nextLine();
            if (response.equals("y")) {
                break;
            }
        }
    }

    // to get a valid index from the user
    private static int getValidIndex (Scanner scanner, ItemInventory inventory) {
        while (true) {
            System.out.print("Enter an item index: ");
            String input = scanner.nextLine();
            try {
                return parseAndValidate(input, inventory);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // to parse and validate an input string
    private static int parseAndValidate (String input, ItemInventory inventory) throws IllegalArgumentException {
        try {
            int index = Integer.parseInt(input);
            if (index < 0 || index >= inventory.getItems().size()) {
                throw new IllegalArgumentException("Index out of bounds. Must be between 0 and " + (inventory.getItems().size() - 1));
            }
            return index;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format. Please enter a valid integer.");
        }
    }

    private static Item randomItemGenerator () {
        double randomNum = Math.random();
        Item newItem;
        if (randomNum < 0.5) {
            newItem = new Item("Sword", Rarity.COMMON, ItemType.WEAPON);
        } else if (randomNum > 0.5 && randomNum < 0.75) {
            newItem = new Item("Shield", Rarity.GREAT, ItemType.WEAR);
        } else if (randomNum > 0.75 && randomNum < 0.9) {
            newItem = new Item("Bow", Rarity.RARE, ItemType.WEAPON);
        } else if (randomNum > 0.9 && randomNum < 0.98) {
            newItem = new Item("Axe", Rarity.RARE, ItemType.WEAPON);
        } else {
            newItem = new Item("Demon's ring", Rarity.LEGENDARY, ItemType.RING);
        }
        return newItem;
    }
}
