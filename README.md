# Item-System-Development-Task
Develop an item system in Java for a game where items have rarity and upgrade mechanics.

## Approach
- The task requirements were analyzed to design an inventory system capable of managing and upgrading items.
- Key components include:
  - Item class: Represents individual items with properties such as name, rarity, type, and upgrade count.
  - ItemInventory class: Manages the inventory, checks upgrade possibilities, and performs upgrades.
- The program uses a `Map` to store upgrade rules and checks for valid combinations dynamically.

## Design Choices
- ArrayList was used for the inventory to allow dynamic item management and easy iteration.
- Upgrade rules for EPIC items were implemented seperately as specific conditions, to simplify handling edge cases.
- Map was chosen to store upgrade rules for flexibility and easy addition of new rules.

## Instructions on how to run this game
1. Clone the repository: https://github.com/ellgalsty/Item-System-Development-Task.git
2. Navigate to the project directory:Item-System-Development-Task
3. Compile the program: javac -d bin src/com/ellgalsty/itemsystem/*.java
4. Run the program:java -cp bin com.ellgalsty.itemsystem.Main

## Features
- Add, remove, group (by rarity and name) and list items in the inventory,  get an item at a current index.
- Check if items can be combined based on rarity and type (and upgrade count in case of EPIC 0/1 items).
- Upgrade items dynamically based on predefined rules.
- Notify the user about invalid combinations with appropriate error messages.
- Random Item Generator with the following probablities applied: Common - 50%, Great - 25%, Rare - 15%, Epic - 8%, Legendary - 2%.
