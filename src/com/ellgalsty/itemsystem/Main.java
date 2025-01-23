package com.ellgalsty.itemsystem;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final ItemInventory inventory = new ItemInventory();
    private final static Map<Rarity, Integer> rarityColorMap = new HashMap<Rarity, Integer>();
    private static final String indentation = "            ";
    public static final int mainGameColor = 190;
    public static final int roomOneMainColor = 154;
    public static final int roomTwoMainColor = 45;
    public static final int roomTwoConversationColor = 117;
    public static final int roomThreeMainColor = 200;
    public static final int roomFourMainColor = 196;
    public static final int roomFourConversationColor = 195;
    public static final int errorMsgColor = 197;
    public static final int winMsgColor = 46;
    public static final int mainTextColor = 225;

    static {
        rarityColorMap.put(Rarity.COMMON, 191);
        rarityColorMap.put(Rarity.GREAT, 48);
        rarityColorMap.put(Rarity.RARE, 51);
        rarityColorMap.put(Rarity.EPIC, 93);
        rarityColorMap.put(Rarity.LEGENDARY, 198);
    }

    public static void main (String[] args) throws InterruptedException {
        // some initial items
        for (int i = 0; i < 9; i++) {
            inventory.addItem(randomItemGenerator());
        }

        while (true) {
            roomsConversation();
            roomChoice();
        }
    }

    public static void roomsConversation () throws InterruptedException {
        padding();
        // ROOM 1 -----------------> GET MORE ITEMS
        System.out.print(formatText(indentation + "ROOM 1 ", mainGameColor));
        for (int i = 0; i < 20; i++) {
            System.out.print(formatText(" - ", mainGameColor));
            Thread.sleep(50);
        }
        System.out.println(formatText(" > GET MORE ITEMS", roomOneMainColor));

        // ROOM 2 -----------------> UPGRADE YOUR ITEMS
        System.out.print(formatText(indentation + "ROOM 2 ", mainGameColor));
        for (int i = 0; i < 20; i++) {
            System.out.print(formatText(" - ", mainGameColor));
            Thread.sleep(50);
        }
        System.out.println(formatText(" > UPGRADE YOUR ITEMS", roomTwoMainColor));

        // ROOM 3 -----------------> SEE YOUR INVENTORY
        System.out.print(formatText(indentation + "ROOM 3 ", mainGameColor));
        for (int i = 0; i < 20; i++) {
            System.out.print(formatText(" - ", mainGameColor));
            Thread.sleep(50);
        }
        System.out.println(formatText(" > SEE YOUR INVENTORY", roomThreeMainColor));

        // ROOM 4 -----------------> LEAVE
        System.out.print(formatText(indentation + "ROOM 4 ", mainGameColor));
        for (int i = 0; i < 20; i++) {
            System.out.print(formatText(" - ", mainGameColor));
            Thread.sleep(50);
        }
        System.out.println(formatText(" > LEAVE", roomFourMainColor));
        // choose the room by a number
        System.out.println(formatText(indentation.repeat(3) + "CHOOSE A ROOM BABE 1/2/3/4 : ", mainGameColor));
    }

    public static void roomChoice () throws InputMismatchException, IndexOutOfBoundsException, InterruptedException {
        int choice;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                choice = choiceValidateAndReturn(scanner, 1, 4);
                break;
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                System.out.println(indentation + formatText(e.getMessage(), errorMsgColor));
            }
        }

        if (choice == 1) {
            roomOne();
        } else if (choice == 2) {
            roomTwo();
        } else if (choice == 3) {
            roomThree();
        } else {
            roomFour();
        }
    }

    private static int choiceValidateAndReturn (Scanner scanner, int boundStart, int boundEnd) throws InputMismatchException, IndexOutOfBoundsException {
        System.out.print(indentation);
        try {
            int choice = scanner.nextInt();
            if (choice < boundStart || choice > boundEnd) {
                throw new IndexOutOfBoundsException("Nah, babe, that is an invalid choice! Try again dumbass!");
            }
            return choice;
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Nah, babe, that is an invalid format! Try again dumbass!");
        }
    }

    public static void roomOne () throws InterruptedException {
        padding();
        Scanner scanner = new Scanner(System.in);

        System.out.println(formatText(indentation + "THIS IS ROOM 1, PRESS ENTER TO READ WHAT TO DO...", roomOneMainColor));
        storyTellingFormat(scanner, "If you are here...", roomOneMainColor);
        storyTellingFormat(scanner, "...You want to get more items, huh...", roomOneMainColor);
        storyTellingFormat(scanner, "...Then guess the hidden RARITY and get a CHEST!", roomOneMainColor);
        storyTellingFormat(scanner, "...Or maybe LOSE, who knows...", errorMsgColor);
        System.out.println();

        roomOneMainLogic();
    }

    private static void storyTellingFormat (Scanner scanner, String message, int color) {
        if (scanner.nextLine().isEmpty()) {
            System.out.println(formatText(indentation + message, color));
        }
    }

    private static void roomOneMainLogic () throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(formatText(indentation + "SOOO, CHOOSE PAL:3", roomOneMainColor));
        Thread.sleep(50);
        System.out.println(formatText(indentation.repeat(2) + "1. COMMON", rarityColorMap.get(Rarity.COMMON)));
        Thread.sleep(50);
        System.out.println(formatText(indentation.repeat(2) + "2. GREAT", rarityColorMap.get(Rarity.GREAT)));
        Thread.sleep(50);
        System.out.println(formatText(indentation.repeat(2) + "3. RARE", rarityColorMap.get(Rarity.RARE)));
        Thread.sleep(50);
        System.out.println(formatText(indentation.repeat(2) + "4. EPIC", rarityColorMap.get(Rarity.EPIC)));
        Thread.sleep(50);
        System.out.println(formatText(indentation.repeat(2) + "5. LEGENDARY", rarityColorMap.get(Rarity.LEGENDARY)));
        int actualRarityNum = generateRandomRarity();
        int choice = choiceValidateAndReturn(scanner, 1, 5);
        if (choice == actualRarityNum) {
            getChest();
        } else {
            loserMessage();
        }
        System.out.println(formatText(indentation + "Press ENTER to leave this room", roomOneMainColor));
        scanner.nextLine();
    }

    private static void loserMessage () throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(formatText(indentation.repeat(3) + "...YOU LOST MWUAHAHA...", errorMsgColor));
            System.out.println();
            System.out.println(formatText(indentation.repeat(3) + "GET OUT OF HERE, LOSER!", errorMsgColor));
        } while (!scanner.nextLine().isEmpty());
    }

    private static void getChest () throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        storyTellingFormat(scanner, "...WOW, I CAN'T BELIEVE IT!...", winMsgColor);
        System.out.println();
        storyTellingFormat(scanner, "...YOU WON A CHEST!...", winMsgColor);
        Thread.sleep(50);
        System.out.println();
        System.out.println();
        drawChest();
        System.out.println();
        System.out.println();
        storyTellingFormat(scanner, "Press ENTER to open it!", winMsgColor);
        System.out.println();
        System.out.println();
        getChestMainLogic(scanner);
    }

    private static void getChestMainLogic (Scanner scanner) throws InterruptedException {
        Item newItem1 = randomItemGenerator();
        Item newItem2 = randomItemGenerator();
        Item newItem3 = randomItemGenerator();

        storyTellingFormat(scanner, "The first item you won is... " + newItem1.toString(), winMsgColor);
        drawItem(newItem1.getRarity(), newItem1.getItemType());
        scanner.nextLine();
        System.out.println();
        storyTellingFormat(scanner, "The second item you won is..." + newItem2.toString(), winMsgColor);
        drawItem(newItem2.getRarity(), newItem2.getItemType());
        scanner.nextLine();
        System.out.println();
        storyTellingFormat(scanner, "The last item you won is..." + newItem3.toString(), winMsgColor);
        drawItem(newItem3.getRarity(), newItem3.getItemType());

        inventory.addItem(newItem1);
        inventory.addItem(newItem2);
        inventory.addItem(newItem3);
    }

    private static void drawItem (Rarity rarity, ItemType itemType) {
        if (itemType.equals(ItemType.WEAPON)) {
            drawWeapon(rarityColorMap.get(rarity));
        } else if (itemType.equals(ItemType.WEAR)) {
            drawWear(rarityColorMap.get(rarity));
        } else if (itemType.equals(ItemType.RING)) {
            drawRing(rarityColorMap.get(rarity));
        } else if (itemType.equals(ItemType.ACCESSORY)) {
            drawAccessory(rarityColorMap.get(rarity));
        }
    }

    private static void drawAccessory (Integer color) {
        System.out.println(formatText(indentation + "*****************", color));
        System.out.println(formatText(indentation + "*     *   *     *", color));
        System.out.println(formatText(indentation + "*    *** ***    *", color));
        System.out.println(formatText(indentation + "*   *********   *", color));
        System.out.println(formatText(indentation + "*    *******    *", color));
        System.out.println(formatText(indentation + "*     *****     *", color));
        System.out.println(formatText(indentation + "*       *       *", color));
        System.out.println(formatText(indentation + "*****************", color));
    }

    private static void drawRing (Integer color) {
        System.out.println(formatText(indentation + "*****************", color));
        System.out.println(formatText(indentation + "*     *******   *", color));
        System.out.println(formatText(indentation + "*       ***     *", color));
        System.out.println(formatText(indentation + "*     *     *   *", color));
        System.out.println(formatText(indentation + "*    *       *  * ", color));
        System.out.println(formatText(indentation + "*     *     *   *", color));
        System.out.println(formatText(indentation + "*       ***     *", color));
        System.out.println(formatText(indentation + "*****************", color));
    }

    private static void drawWeapon (Integer color) {
        System.out.println(formatText(indentation + "*****************", color));
        System.out.println(formatText(indentation + "*       *       *", color));
        System.out.println(formatText(indentation + "*      * *      *", color));
        System.out.println(formatText(indentation + "*      * *      *", color));
        System.out.println(formatText(indentation + "*      * *      *", color));
        System.out.println(formatText(indentation + "*     *****     *", color));
        System.out.println(formatText(indentation + "*       *       *", color));
        System.out.println(formatText(indentation + "*****************", color));
    }

    private static void drawWear (Integer color) {
        System.out.println(formatText(indentation + "*****************", color));
        System.out.println(formatText(indentation + "*   ***   ***   *", color));
        System.out.println(formatText(indentation + "*  *   ***   *  *", color));
        System.out.println(formatText(indentation + "*  *         *  *", color));
        System.out.println(formatText(indentation + "*  *         *  *", color));
        System.out.println(formatText(indentation + "*  ***********  *", color));
        System.out.println(formatText(indentation + "*               *", color));
        System.out.println(formatText(indentation + "*****************", color));
    }


    private static void drawChest () throws InterruptedException {
        System.out.println(formatText(indentation + "    ********************************", 214));
        Thread.sleep(40);
        System.out.println(formatText(indentation + "   *                              **", 214));
        Thread.sleep(40);
        System.out.println(formatText(indentation + "  *          * *                *  *", 48));
        Thread.sleep(40);
        System.out.println(formatText(indentation + " *         * * * *            *    *", 48));
        Thread.sleep(40);
        System.out.println(formatText(indentation + "* ****************************     *", 51));
        Thread.sleep(40);
        System.out.println(formatText(indentation + "*          * * * *           *     *", 51));
        Thread.sleep(40);
        System.out.println(formatText(indentation + "*            * *             *     *", 93));
        Thread.sleep(40);
        System.out.println(formatText(indentation + "*                            *     *", 93));
        Thread.sleep(40);
        System.out.println(formatText(indentation + "************************************", 178));
    }

    private static int generateRandomRarity () {
        double randomNum = Math.random();
        int rarityNum;
        if (randomNum < 0.5) {
            rarityNum = 1;
        } else if (randomNum > 0.5 && randomNum < 0.75) {
            rarityNum = 2;
        } else if (randomNum > 0.75 && randomNum < 0.9) {
            rarityNum = 3;
        } else if (randomNum > 0.9 && randomNum < 0.98) {
            rarityNum = 4;
        } else {
            rarityNum = 5;
        }
        return rarityNum;
    }

    public static void roomTwo () throws InterruptedException {
        padding();
        Scanner scanner = new Scanner(System.in);
        String response;
        System.out.println(formatText(indentation + "THIS IS ROOM 2, PRESS ENTER TO READ WHAT TO DO...", roomTwoMainColor));
        System.out.println();
        storyTellingFormat(scanner, "If you are here...", roomTwoConversationColor);
        storyTellingFormat(scanner, "...You want to UPGRADE your items...", roomTwoConversationColor);
        storyTellingFormat(scanner, "...Well, let's upgrade them for you!...", roomTwoConversationColor);
        System.out.println();
        roomTwoMainLogic();
    }

    private static void roomTwoMainLogic () throws InterruptedException {
        padding();
        Scanner scanner = new Scanner(System.in);
        System.out.println(formatText(indentation + "... SO, CHOOSE PAL, which items to upgrade:3 ...", roomTwoConversationColor));
        System.out.println();
        System.out.println();
        System.out.println(formatText(indentation + "<<<<<<<YOUR INVENTORY>>>>>>", roomTwoMainColor));
        showInventory();

        try {
            System.out.print(formatText(indentation + "1st item to upgrade: ", roomTwoConversationColor));
            int index1 = getValidIndex(scanner, inventory);
            System.out.print(formatText(indentation + "2nd item to upgrade: ", roomTwoConversationColor));
            int index2 = getValidIndex(scanner, inventory);

            System.out.print(formatText(indentation + "3rd item to upgrade (OPTIONAL): ", roomTwoConversationColor));
            String userResponse3 = scanner.nextLine();

            if (index1 == index2 || (!userResponse3.isEmpty() && (Integer.parseInt(userResponse3) == index1
                    || Integer.parseInt(userResponse3) == index2))) {
                throw new IllegalArgumentException("... NOPE, can't upgrade if you input the same numbers:3 ...");
            }

            if (userResponse3.isEmpty()) {
                // upgrade with 2 items only (EPIC ones)
                Item upgradedItem = inventory.upgrade(
                        inventory.getItemAt(index1),
                        inventory.getItemAt(index2));
                System.out.println(formatText(indentation + "... Congrats! You have just upgraded your first selected item into <<"
                        + upgradedItem.toString() + ">>   <3 ...", rarityColorMap.get(upgradedItem.getRarity())));
            } else {
                int index3 = parseAndValidate(userResponse3, inventory);
                // upgrade with 3 items
                Item upgradedItem = inventory.upgrade(
                        inventory.getItemAt(index1),
                        inventory.getItemAt(index2),
                        inventory.getItemAt(index3));
                System.out.println(formatText(indentation + "... Congrats! You have just upgraded your first selected item into <<"
                        + upgradedItem.toString() + ">>   <3 ...", rarityColorMap.get(upgradedItem.getRarity())));
            }
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.out.println(formatText(indentation + "... This is obviously invalid input, babe: " + e.getMessage(), errorMsgColor));
        }

        System.out.println();
        System.out.println(formatText(indentation + "... Press ENTER to leave this room ...", roomTwoConversationColor));
        scanner.nextLine();
    }

    private static void showInventory () {
        int k = 1;
        for (Item item : inventory.getItems()) {
            System.out.println(formatText(k + ". " + item.toString(), rarityColorMap.get(item.getRarity())));
            System.out.println();
            k++;
        }
    }

    public static void roomThree () {
        padding();
        Scanner scanner = new Scanner(System.in);
        System.out.println(formatText(indentation + "THIS IS ROOM 3, PRESS ENTER TO SEE YOUR INVENTORY...", roomThreeMainColor));
        scanner.nextLine();
        showInventory();
        System.out.println(formatText(indentation + "Press ENTER to leave this room", roomThreeMainColor));
        scanner.nextLine();
    }

    public static void roomFour () throws InterruptedException {
        System.out.println(formatText(indentation + "...Well, if you want to leave...", roomFourConversationColor));
        Thread.sleep(750);
        System.out.println(formatText(indentation + "...just leave... BUT...", roomFourConversationColor));
        Thread.sleep(750);
        System.out.print(formatText(indentation + "...know that there is someone else living their", roomFourConversationColor));
        Thread.sleep(750);
        System.out.print(formatText(" LEGENDARY ", rarityColorMap.get(Rarity.LEGENDARY)));
        System.out.println(formatText("life...", roomFourConversationColor));
        Thread.sleep(750);
        System.out.print(formatText(indentation + "...with", roomFourConversationColor));
        System.out.print(formatText(" LEGENDARY ", rarityColorMap.get(Rarity.LEGENDARY)));
        System.out.println(formatText(" items...", roomFourConversationColor));
        Thread.sleep(750);
        System.out.println(formatText(indentation + "...cause they STAYED...", roomFourMainColor));
        System.exit(0);
    }

    private static void padding () {
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
    }

    private static String formatText (String text, int color) {
        return "\033[38;5;" + String.valueOf(color) + "m" + text + "\033[0m";
    }

    // to get a valid index from the user
    private static int getValidIndex (Scanner scanner, ItemInventory inventory) {
        while (true) {
            System.out.print(formatText(indentation + "Enter an item index: ", mainTextColor));
            String input = scanner.nextLine();
            try {
                return parseAndValidate(input, inventory);
            } catch (IllegalArgumentException e) {
                System.out.println(formatText(indentation + e.getMessage(), errorMsgColor));
            }
        }
    }

    // to parse and validate an input string
    private static int parseAndValidate (String input, ItemInventory inventory) throws IllegalArgumentException {
        try {
            int index = Integer.parseInt(input);
            if (index < 0 || index >= inventory.getItems().size()) {
                throw new IllegalArgumentException("Your input is out of bounds babe! Must be between 0 and " + (inventory.getItems().size() - 1));
            }
            return index;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format? Really? Pathetic...");
        }
    }

    private static Item randomItemGenerator () {
        double randomNum = Math.random();
        Item newItem;
        if (randomNum < 0.5) {
            newItem = new Item("Sword", Rarity.COMMON, ItemType.WEAPON);
        } else if (randomNum > 0.5 && randomNum < 0.75) {
            newItem = new Item("Angel's Wings", Rarity.GREAT, ItemType.WEAR);
        } else if (randomNum > 0.75 && randomNum < 0.9) {
            newItem = new Item("Glory Necklace", Rarity.RARE, ItemType.ACCESSORY);
        } else if (randomNum > 0.9 && randomNum < 0.98) {
            newItem = new Item("Cuteness Overload Ring", Rarity.EPIC, ItemType.RING);
        } else {
            newItem = new Item("Demon's ring", Rarity.LEGENDARY, ItemType.RING);
        }
        return newItem;
    }

}
