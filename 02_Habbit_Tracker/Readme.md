# Habit Tracker

A **Java-based Habit Tracker** that helps users track daily habits, maintain streaks, and customize the interface with personalized themes and stickers. Built using **Swing**, the application saves all data locally to ensure progress is never lost.

## Features
### 1. **Habit Management**  
   - Add, edit, complete, and delete habits.
   - Track **daily streaks** and **longest streaks** for each habit.
   - Display habit progress visually with dynamic **progress bars**.

### 2. **Data Persistence**  
   - Habit data is stored locally in a text file (`HabbitFile.txt`).
   - Data includes habit name, streak count, longest streak, and last completion date.
   - Automatically loads data from the file when the application starts.

### 3. **Customizable User Interface**  
   - Change **button, text, and frame colors** for a personalized experience.
   - Add **stickers** to enhance the look and feel of the application.

### 4. **Dual Interface**  
   - **Command-Line Interface (CLI)**: Interact with the tracker through simple commands.
   - **Graphical User Interface (GUI)**: Use an intuitive interface with buttons and visual feedback.

### 5. **Interactive Visual Feedback**  
   - **Progress Bars**: Display habit progress as percentage completion.
   - **Loading Screens**: Fun loading screens with progress indicators for an engaging experience.
   - **Error Handling**: Display alerts for invalid inputs or missing data.

---

## How It Works

### Data Flow
1. **Adding a Habit**:  
   - Add a new habit via GUI or CLI.
   - The habit is stored in memory and saved to `HabbitFile.txt`.
   
2. **Completing a Habit**:  
   - Mark a habit as complete to update the streak count.
   - Automatically saves updates to the file.

3. **Editing or Deleting Habits**:  
   - Rename or update habits through the GUI or CLI.
   - Deleted habits are removed from the data file.

### File Structure
- **`HabbitFile.txt`** stores data in the following format:
   ```plaintext
   HabitNumber ! HabitName ! CurrentStreak ! LongestStreak ! LastCompletionDate
   ```
   Example:
   ```plaintext
   0 ! Exercise ! 5 ! 10 ! 2024-11-18
   1 ! Reading ! 3 ! 8 ! 2024-11-19
   ```

---

## Technologies Used
- **Java (Core)**: Core logic, OOP principles, and data handling.
- **Swing**: Graphical User Interface (GUI) components.
- **File I/O**: Data persistence using text files.
- **Collections Framework**: Manage habits using `ArrayList`.

---

## Installation and Usage
1. **Clone the Repository** or download the files.
2. Compile the Java files:
   ```bash
   javac Main.java
   ```
3. Run the application:
   ```bash
   java Main
   ```

---

## Customization

### 1. **Themes and Colors**  
You can modify the colors of buttons, text, and frames directly in the `frame.java` file. Example:

```java
AddHabit.setBackground(Color.BLUE); // Button background color
AddHabit.setForeground(Color.WHITE); // Button text color
this.getContentPane().setBackground(Color.LIGHT_GRAY); // Frame background color
```

### 2. **Stickers and Icons**  
Add custom stickers or images in the GUI components by modifying the panel layout in `frame.java` or `system.java`. Example:

```java
JLabel sticker = new JLabel(new ImageIcon("path/to/sticker.png"));
panel3.add(sticker); // Add sticker to panel
```

---

## Future Enhancements (Optional Ideas)
- **Export Data**: Save habit data as CSV or PDF files.
- **Graphical Analytics**: Add charts to show progress trends over time.

---

## Contribution
Feel free to fork the repository and submit pull requests for enhancements or bug fixes.

---

## License
This project is licensed under the MIT License.

---
![Screenshot 2024-11-29 210119](https://github.com/user-attachments/assets/73247ada-c4f8-45c5-b96d-b49d2d10e9b0)
![Screenshot 2024-11-29 210145](https://github.com/user-attachments/assets/7b9f55d4-822b-4b20-a3b4-013d84cb205c)
![Screenshot 2024-11-29 210035](https://github.com/user-attachments/assets/99765a0c-8ee1-4653-81bb-5ebf92acaa8c)
![Screenshot 2024-11-29 210108](https://github.com/user-attachments/assets/5e79a8f7-80a3-4f22-8db7-96d48b183933)
