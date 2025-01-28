package com.ellgalsty.itemsystem;

import java.util.*;

public class Main {
    private static final ItemInventory inventory = new ItemInventory();
    private final static Map<Rarity, Integer> rarityColorMap = new HashMap<>();
    private static final String indentation = "            ";
    public static final int mainGameColor = 38;
    public static final int roomOneMainColor = 57;
    public static final int roomOneConversationColor = 111;
    public static final int roomTwoMainColor = 45;
    public static final int roomTwoConversationColor = 117;
    public static final int roomThreeMainColor = 200;
    public static final int roomFourMainColor = 196;
    public static final int roomFourConversationColor = 195;
    public static final int errorMsgColor = 197;
    public static final int winMsgColor = 46;
    public static final int mainTextColor = 225;

    static {
        rarityColorMap.put(Rarity.COMMON, 222);
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

        Scanner scanner = new Scanner(System.in);

        while (true) {
            roomsConversation();
            roomChoice(scanner);
        }
    }

    public static void roomsConversation () throws InterruptedException {
        padding();

        // ROOM 1 -----------------> GET MORE ITEMS
        formatWithoutNewLine(indentation + "ROOM 1 ", mainGameColor);
        for (int i = 0; i < 20; i++) {
            formatWithoutNewLine(" - ", mainGameColor);
            Thread.sleep(50);
        }
        formatWithNewLine(" > GET MORE ITEMS", roomOneMainColor);

        // ROOM 2 -----------------> UPGRADE YOUR ITEMS
        formatWithoutNewLine(indentation + "ROOM 2 ", mainGameColor);
        for (int i = 0; i < 20; i++) {
            formatWithoutNewLine(" - ", mainGameColor);
            Thread.sleep(50);
        }
        formatWithNewLine(" > UPGRADE YOUR ITEMS", roomTwoMainColor);

        // ROOM 3 -----------------> SEE YOUR INVENTORY
        formatWithoutNewLine(indentation + "ROOM 3 ", mainGameColor);
        for (int i = 0; i < 20; i++) {
            formatWithoutNewLine(" - ", mainGameColor);
            Thread.sleep(50);
        }
        formatWithNewLine(" > SEE YOUR INVENTORY", roomThreeMainColor);

        // ROOM 4 -----------------> LEAVE
        formatWithoutNewLine(indentation + "ROOM 4 ", mainGameColor);
        for (int i = 0; i < 20; i++) {
            formatWithoutNewLine(" - ", mainGameColor);
            Thread.sleep(50);
        }
        formatWithNewLine(" > LEAVE", roomFourMainColor);
        // choose the room by a number
        formatWithNewLine(indentation.repeat(3) + "CHOOSE A ROOM BABE 1/2/3/4 : ", mainGameColor);
    }

    public static void roomChoice (Scanner scanner) throws InputMismatchException, IndexOutOfBoundsException, InterruptedException {
        int choice = 0;
        boolean correctAnswer = false;
        while (!correctAnswer) {
            try {
                choice = choiceValidateAndReturn(scanner, 1, 4);
                correctAnswer = true;
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                System.out.println(indentation + formatText(e.getMessage(), errorMsgColor));
                scanner.nextLine();
            }
        }

        if (choice == 1) {
            roomOne(scanner);
        } else if (choice == 2) {
            roomTwo(scanner);
        } else if (choice == 3) {
            roomThree(scanner);
        } else {
            roomFour(scanner);
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

    public static void roomOne (Scanner scanner) throws InterruptedException {
        padding();
        formatWithNewLine(indentation + "THIS IS ROOM 1, PRESS ENTER TO READ WHAT TO DO...", roomOneMainColor);
        scanner.nextLine();
        storyTellingFormat(scanner, "If you are here...", roomOneConversationColor);
        storyTellingFormat(scanner, "...You want to get more items, huh...", roomOneConversationColor);
        storyTellingFormat(scanner, "...Then guess the hidden RARITY and get a CHEST!", roomOneConversationColor);
        storyTellingFormat(scanner, "...Or maybe LOSE, who knows...", errorMsgColor);
        System.out.println();

        roomOneMainLogic(scanner);
    }

    private static void storyTellingFormat (Scanner scanner, String message, int color) {
        if (scanner.nextLine().isEmpty()) {
            formatWithNewLine(indentation + message, color);
        }
    }

    private static void roomOneMainLogic (Scanner scanner) throws InterruptedException {
        formatWithNewLine(indentation + "SOOO, CHOOSE PAL:3", roomOneConversationColor);
        Thread.sleep(50);
        formatWithNewLine(indentation.repeat(2) + "1. COMMON", rarityColorMap.get(Rarity.COMMON));
        Thread.sleep(50);
        formatWithNewLine(indentation.repeat(2) + "2. GREAT", rarityColorMap.get(Rarity.GREAT));
        Thread.sleep(50);
        formatWithNewLine(indentation.repeat(2) + "3. RARE", rarityColorMap.get(Rarity.RARE));
        Thread.sleep(50);
        formatWithNewLine(indentation.repeat(2) + "4. EPIC", rarityColorMap.get(Rarity.EPIC));
        Thread.sleep(50);
        formatWithNewLine(indentation.repeat(2) + "5. LEGENDARY", rarityColorMap.get(Rarity.LEGENDARY));
        int actualRarityNum = generateRandomRarity();
        int choice = choiceValidateAndReturn(scanner, 1, 5);
        if (choice == actualRarityNum) {
            getChest(scanner);
        } else {
            loserMessage(scanner);
        }
        formatWithNewLine(indentation + "Press ENTER to leave this room", roomOneConversationColor);
        scanner.nextLine();
    }

    private static void loserMessage (Scanner scanner) {
        do {
            formatWithNewLine(indentation + "YOU LOST MWUAHAHA...", errorMsgColor);
            System.out.println();
            formatWithNewLine(indentation + "...GET OUT OF HERE, LOSER!", errorMsgColor);
        } while (!scanner.nextLine().isEmpty());
    }

    private static void getChest (Scanner scanner) throws InterruptedException {
        storyTellingFormat(scanner, "...WOW, I CAN'T BELIEVE IT!...", winMsgColor);
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

    private static void getChestMainLogic (Scanner scanner) {
        Item newItem1 = randomItemGenerator();
        Item newItem2 = randomItemGenerator();
        Item newItem3 = randomItemGenerator();

        storyTellingFormat(scanner, "The first item you won is... " + newItem1, winMsgColor);
        drawItem(newItem1.getRarity(), newItem1.getItemType());
        System.out.println();
        storyTellingFormat(scanner, "The second item you won is..." + newItem2, winMsgColor);
        drawItem(newItem2.getRarity(), newItem2.getItemType());
        System.out.println();
        storyTellingFormat(scanner, "The last item you won is..." + newItem3, winMsgColor);
        drawItem(newItem3.getRarity(), newItem3.getItemType());
        scanner.nextLine();

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
        formatWithNewLine(indentation + "*****************", color);
        formatWithNewLine(indentation + "*     *   *     *", color);
        formatWithNewLine(indentation + "*    *** ***    *", color);
        formatWithNewLine(indentation + "*   *********   *", color);
        formatWithNewLine(indentation + "*    *******    *", color);
        formatWithNewLine(indentation + "*     *****     *", color);
        formatWithNewLine(indentation + "*       *       *", color);
        formatWithNewLine(indentation + "*****************", color);
    }

    private static void drawRing (Integer color) {
        formatWithNewLine(indentation + "*****************", color);
        formatWithNewLine(indentation + "*     *******   *", color);
        formatWithNewLine(indentation + "*       ***     *", color);
        formatWithNewLine(indentation + "*     *     *   *", color);
        formatWithNewLine(indentation + "*    *       *  * ", color);
        formatWithNewLine(indentation + "*     *     *   *", color);
        formatWithNewLine(indentation + "*       ***     *", color);
        formatWithNewLine(indentation + "*****************", color);
    }

    private static void drawWeapon (Integer color) {
        formatWithNewLine(indentation + "*****************", color);
        formatWithNewLine(indentation + "*       *       *", color);
        formatWithNewLine(indentation + "*      * *      *", color);
        formatWithNewLine(indentation + "*      * *      *", color);
        formatWithNewLine(indentation + "*      * *      *", color);
        formatWithNewLine(indentation + "*     *****     *", color);
        formatWithNewLine(indentation + "*       *       *", color);
        formatWithNewLine(indentation + "*****************", color);
    }

    private static void drawWear (Integer color) {
        formatWithNewLine(indentation + "*****************", color);
        formatWithNewLine(indentation + "*   ***   ***   *", color);
        formatWithNewLine(indentation + "*  *   ***   *  *", color);
        formatWithNewLine(indentation + "*  *         *  *", color);
        formatWithNewLine(indentation + "*  *         *  *", color);
        formatWithNewLine(indentation + "*  ***********  *", color);
        formatWithNewLine(indentation + "*               *", color);
        formatWithNewLine(indentation + "*****************", color);
    }


    private static void drawChest () throws InterruptedException {
        formatWithNewLine(indentation + "    ********************************", 214);
        Thread.sleep(40);
        formatWithNewLine(indentation + "   *                              **", 214);
        Thread.sleep(40);
        formatWithNewLine(indentation + "  *          * *                *  *", 48);
        Thread.sleep(40);
        formatWithNewLine(indentation + " *         * * * *            *    *", 48);
        Thread.sleep(40);
        formatWithNewLine(indentation + "* ****************************     *", 51);
        Thread.sleep(40);
        formatWithNewLine(indentation + "*          * * * *           *     *", 51);
        Thread.sleep(40);
        formatWithNewLine(indentation + "*            * *             *     *", 93);
        Thread.sleep(40);
        formatWithNewLine(indentation + "*                            *     *", 93);
        Thread.sleep(40);
        formatWithNewLine(indentation + "************************************", 178);
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

    public static void roomTwo (Scanner scanner) {
        padding();
        formatWithNewLine(indentation + "THIS IS ROOM 2, PRESS ENTER TO READ WHAT TO DO...", roomTwoMainColor);
        scanner.nextLine();
        storyTellingFormat(scanner, "If you are here...", roomTwoConversationColor);
        storyTellingFormat(scanner, "...You want to UPGRADE your items...", roomTwoConversationColor);
        storyTellingFormat(scanner, "...Well, let's upgrade them for you!...", roomTwoConversationColor);
        System.out.println();
        roomTwoMainLogic(scanner);
    }

    private static void roomTwoMainLogic (Scanner scanner) {
        padding();
        formatWithNewLine(indentation + "... SO, CHOOSE PAL, which items to upgrade:3 ...", roomTwoConversationColor);
        System.out.println();
        System.out.println();
        formatWithNewLine(indentation + "<<<<<<<YOUR INVENTORY>>>>>>", roomTwoMainColor);
        showInventory();

        try {
            formatWithoutNewLine(indentation + "1st item to upgrade: ", roomTwoConversationColor);
            int index1 = getValidIndex(scanner, inventory);
            formatWithoutNewLine(indentation + "2nd item to upgrade: ", roomTwoConversationColor);
            int index2 = getValidIndex(scanner, inventory);

            formatWithoutNewLine(indentation + "3rd item to upgrade (OPTIONAL): ", roomTwoConversationColor);
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
                formatWithNewLine(indentation + "... Congrats! You have just upgraded your first selected item into <<"
                        + upgradedItem.toString() + ">>   <3 ...", rarityColorMap.get(upgradedItem.getRarity()));
            } else {
                int index3 = parseAndValidate(userResponse3, inventory);
                // upgrade with 3 items
                Item upgradedItem = inventory.upgrade(
                        inventory.getItemAt(index1),
                        inventory.getItemAt(index2),
                        inventory.getItemAt(index3));
                formatWithNewLine(indentation + "... Congrats! You have just upgraded your first selected item into <<"
                        + upgradedItem.toString() + ">>   <3 ...", rarityColorMap.get(upgradedItem.getRarity()));
            }
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            formatWithNewLine(indentation + "... This is obviously invalid input, babe: " + e.getMessage(), errorMsgColor);
        }

        System.out.println();
        formatWithNewLine(indentation + "... Press ENTER to leave this room ...", roomTwoConversationColor);
        scanner.hasNextLine();
    }

    private static void showInventory () {
        int k = 1;
        for (Item item : inventory.getItems()) {
            formatWithNewLine(k + ". " + item.toString(), rarityColorMap.get(item.getRarity()));
            System.out.println();
            k++;
        }
    }

    public static void roomThree (Scanner scanner) {
        padding();
        formatWithNewLine(indentation + "THIS IS ROOM 3, PRESS ENTER TO SEE YOUR INVENTORY...", roomThreeMainColor);
        scanner.nextLine();
        showInventory();
        formatWithNewLine(indentation + "Press ENTER to leave this room", roomThreeMainColor);
        scanner.nextLine();
    }

    public static void roomFour (Scanner scanner) {
        formatWithNewLine(indentation + "...Well, if you want to leave...", roomFourConversationColor);
        scanner.nextLine();
        formatWithNewLine(indentation + "...just leave... BUT...", roomFourConversationColor);
        scanner.nextLine();
        formatWithoutNewLine(indentation + "...know that there is someone else living their", roomFourConversationColor);
        formatWithoutNewLine(" LEGENDARY ", roomFourMainColor);
        formatWithNewLine("life...", roomFourConversationColor);
        formatWithoutNewLine(indentation + "...with", roomFourConversationColor);
        formatWithoutNewLine(" LEGENDARY ", roomFourMainColor);
        formatWithNewLine(" items...", roomFourConversationColor);
        scanner.nextLine();
        formatWithNewLine(indentation + "...just because they STAYED...", roomFourMainColor);
        System.exit(0);
    }

    private static void padding () {
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
    }

    // to get a valid index from the user
    private static int getValidIndex (Scanner scanner, ItemInventory inventory) {
        while (true) {
            System.out.print(formatText(indentation + "Enter an item index: ", mainTextColor));
            String input = scanner.nextLine();
            try {
                return parseAndValidate(input, inventory);
            } catch (IllegalArgumentException e) {
                formatWithNewLine(indentation + e.getMessage(), errorMsgColor);
            }
        }
    }

    private static String formatText (String text, int color) {
        return "\033[38;5;" + color + "m" + text + "\033[0m";
    }

    private static void formatWithNewLine (String text, int color) {
        System.out.println(formatText(text, color));
    }

    private static void formatWithoutNewLine (String text, int color) {
        System.out.print(formatText(text, color));
    }
    // to parse and validate an input string
    private static int parseAndValidate (String input, ItemInventory inventory) throws IllegalArgumentException {
        try {
            int index = Integer.parseInt(input);
            if (index < 1 || index > inventory.getItems().size()) {
                throw new IllegalArgumentException("Your input is out of bounds babe! Must be between 1 and " + (inventory.getItems().size() - 1));
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

    private static void test () {
        ItemInventory inventory = new ItemInventory();

        inventory.addItem(new Item("Sword", Rarity.COMMON, ItemType.WEAPON));
        inventory.addItem(new Item("Sword", Rarity.COMMON, ItemType.WEAPON));
        inventory.addItem(new Item("Sword", Rarity.COMMON, ItemType.WEAPON));
        inventory.addItem(new Item("Bow", Rarity.GREAT, ItemType.WEAPON));
        inventory.addItem(new Item("Bow", Rarity.GREAT, ItemType.WEAPON));
        inventory.addItem(new Item("Dagger", Rarity.RARE, ItemType.WEAPON));

        System.out.println("Inventory after adding items:");
        printInventory(inventory);

        System.out.println("\nTesting invalid upgrade cases...");
        try {
            inventory.upgrade(new Item("Sword", Rarity.GREAT, ItemType.WEAPON), new Item("Sword", Rarity.GREAT, ItemType.WEAPON));
        } catch (IllegalArgumentException e) {
            System.out.println("✔️ Caught expected error: " + e.getMessage());
        }
        try {
            inventory.upgrade(new Item("Sword", Rarity.EPIC, ItemType.WEAPON, 2), new Item("Sword", Rarity.EPIC, ItemType.WEAPON, 2));
        } catch (IllegalArgumentException e) {
            System.out.println("✔️ Caught expected error: " + e.getMessage());
        }
        try {
            inventory.upgrade(inventory.getItemAt(1), inventory.getItemAt(2), inventory.getItemAt(5));
        }
        catch (IllegalArgumentException e) {
            System.out.println("✔️ Caught expected error: " + e.getMessage());
        }

        System.out.println("\nTesting valid upgrade cases...");
        try {
            inventory.upgrade(inventory.getItemAt(1), inventory.getItemAt(2), inventory.getItemAt(3));
            System.out.println("✔️ Successfully upgraded COMMON -> GREAT");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }

        System.out.println("\nTesting adding items...");
        System.out.println("Inventory before adding items:");
        printInventory(inventory);
        inventory.addItem(new Item("Sword", Rarity.RARE, ItemType.WEAPON));
        System.out.println("\nInventory after adding items:");
        printInventory(inventory);

        System.out.println("\nTesting removing items...");
        System.out.println("Inventory before removing the first item:");
        printInventory(inventory);
        inventory.removeItem(inventory.getItemAt(1));
        System.out.println("\nInventory after removing the first item:");
        printInventory(inventory);
    }

    private static void printInventory(ItemInventory inventory) {
        System.out.println("\nCurrent Inventory:");
        for (int i = 0; i < inventory.getItems().size(); i++) {
            System.out.println(i + ": " + inventory.getItems().get(i));
        }
    }
}