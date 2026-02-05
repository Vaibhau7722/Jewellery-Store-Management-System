# Jewellery-Store-Management-System
This Java application aims to create a comprehensive "Jewellery Store Management System." The system is designed to manage a jewellery store, which consists of display cases containing display trays, and each tray can hold multiple items of jewellery. These items of jewellery are composed of one or more materials/components, each with varying quantities and qualities. Here's an overview of the system:
## System Overview

### Entities
- **Display Case**: Represents a display case in the store, with attributes such as a unique identifier, type (wall-mounted or freestanding), and lighting status (lit or unlit).
- **Display Tray**: Represents a tray inside a display case, identified by a unique identifier (e.g., A12), inlay material color (e.g., green), and dimensions (width x depth) in centimeters.
- **Jewellery Item**: Represents an item of jewellery, including properties like item description (free text), type (ring, watch, necklace, etc.), target gender (male, female, unisex, etc.), image URL, and retail price.
- **Material/Component**: Represents a material or component of an item of jewellery, including properties like name/type (e.g., gold, platinum, diamond, emerald, silver), description/information, quantity/amount/weight (numeric), and quality (numeric).

### Functionality
The system allows users to perform the following actions:

1. **Add Display Case**: Add a new display case to the system with a unique identifier, type, and lighting status (lit or unlit).

2. **Add Display Tray**: Add a new display tray to a display case with a unique identifier, inlay material color, and dimensions. Ensure uniqueness of the tray identifier across the system.

3. **Add Jewellery Item**: Add an item of jewellery to a display tray with item description, type, target gender, image URL, and retail price.

4. **Add Material/Component**: Add a material/component to an item of jewellery, specifying its name/type, description, quantity/amount/weight, and quality.

5. **View All Stock**: Display a list of all jewellery items, including their information, photos, etc., organized by display trays in display cases.

6. **Drill Down**: Interactively explore display cases, display trays, and jewellery items to view complete details, including material/component information, of a specific item of jewellery using a user-friendly GUI.

7. **Search by Text**: Search for items of jewellery by text, systematically searching all jewellery items and their material/component information. Display search results listing where matches are stored in the jewellery store.

8. **Smart Add**: Automatically identify suitable locations (display case and tray) to store jewellery items based on their description, type, value, etc.

9. **Remove Item of Jewellery**: Easily identify and remove specific items of jewellery from the system.

10. **Value Stock**: Calculate the value of individual display cases and display trays, as well as the total value of all items in the store.

11. **Reset**: Clear all system data to start fresh.

12. **Save and Load**: Implement data persistence by allowing users to save and load the entire system data using a suitable file format (e.g., CSV, XML, binary, etc.). No external database is required.

## Implementation Details

- This project is an individual exercise for a course.
- The system includes a JavaFX graphical user interface (GUI) for user interaction.
- Custom data structures and algorithms are used, with no reliance on existing Java collections or data structure classes.
