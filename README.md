# Mood Tracker App 😌

A simple Java console application that allows users to track their moods with details such as **name**, **date**, **time**, and **notes**.  
Users can **add**, **edit**, **delete**, **search**, and **save moods** to a file for future reference.

---

## Features
- Add a new mood with automatic or custom date and time.
- Prevents duplicate moods on the same date and time.
- Edit mood notes by selecting specific moods.
- Delete moods by date or specific details.
- Search moods by keyword or date.
- View all saved moods.
- Save all moods to a text file (`moodtracker.txt`) for future use.

---

## How It Works
- The program runs in a continuous menu loop.
- Users choose actions using menu commands (like `a` for add, `d` for delete, etc.).
- Moods are stored in an `ArrayList<Mood>`.
- When writing to a file, all moods are saved line by line for easy readability.

---

## File Output
When the user selects the **write to file** option:
1. A `FileWriter` opens (or creates) `moodtracker.txt`.
2. Each mood’s details (name, date, time, notes) are written to a new line.
3. The file is closed properly to ensure all data is saved.

Example file content:
