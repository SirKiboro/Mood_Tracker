import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class MoodTracker {

    public static void main(String[]args){

        ArrayList<Mood>moodList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        menuLoop:
        while(true){
            System.out.println("***MENU***\n" +
                    "Press 'a' to add mood\n" +
                    "'d' to delete mood(s)\n" +
                    "'e' to edit mood\n" +
                    "'s' to search for moods\n" +
                    "'M' to get all moods\n" +
                    "'w' to write the moods to a file\n" +
                    "Type 'Exit' to exit");

            String menuOption = scanner.nextLine();
                switch (menuOption){

                    case "a":
                        System.out.println("Enter mood to be added! ");
                        String name = scanner.nextLine();

                        System.out.println("Enter date (yyyy-MM-dd) or press Enter to use today: ");
                        String dateInput = scanner.nextLine().trim();
                        LocalDate date;

                        if (dateInput.isEmpty()) {
                            date = LocalDate.now();// if user pressed Enter (empty input)
                        } else {
                            date = LocalDate.parse(dateInput);  // if user typed a date, use that
                        }

                        System.out.println("Enter time (HH:mm) or press Enter to use current time: ");
                        String timeInput = scanner.nextLine().trim();
                        LocalTime time;

                        if (timeInput.isEmpty()){
                            time = LocalTime.now(); // if user pressed Enter (empty input)
                        }else {
                            time = LocalTime.parse(timeInput); // if user gave time, use that
                        }

                        Mood newMood = new Mood(name,date,time);

                        try {
                            for (Mood m : moodList){
                                if (m.equals(newMood)){
                                    throw new InvalidMoodException("Mood already exists for this date and time!");
                                }
                            }
                            moodList.add(newMood);
                            System.out.println("New mood added\n");

                        }catch (InvalidMoodException e){
                            System.out.println(e.getMessage());

                        }
                        break;

                    case "d":
                        if (moodList.isEmpty()){
                            System.out.println("List is empty.Nothing to delete! ");
                            break;

                        }
                        System.out.println("Current moods are: " + moodList);

                        System.out.println("Enter mood to be deleted: ");
                        String delName = scanner.nextLine().trim();

                        System.out.println("Enter date (yyyy-MM-dd): ");
                        LocalDate delDate = LocalDate.parse(scanner.nextLine().trim());

                        System.out.println("Enter time (HH-mm): ");
                        LocalTime delTime = LocalTime.parse(scanner.nextLine().trim());

                        boolean rmvMood = false;
                        for (int i=0; i< moodList.size(); i++){
                            Mood m = moodList.get(i);

                            if (m.getName().equalsIgnoreCase(delName)
                                    && m.getDate().equals(delDate)
                                    && m.getTime().equals(delTime)){
                                moodList.remove(i);
                                rmvMood = true;
                                break;
                            }
                        }
                        if (rmvMood) System.out.println("Mood deleted successfully!");
                        else System.out.println("Mood not found.");

                        break;

                    case "e":
                        if (moodList.isEmpty()){
                            System.out.println("List is empty.Nothing to edit! ");
                            break;

                        }
                        System.out.println("Current moods are: " + moodList);
                        System.out.println("Enter mood to edit ");
                        String editName = scanner.nextLine().trim();

                        System.out.println("Enter date (yyyy-MM-dd): ");
                        LocalDate editDate = LocalDate.parse(scanner.nextLine().trim());

                        System.out.println("Enter time (HH-mm): ");
                        LocalTime editTime = LocalTime.parse(scanner.nextLine().trim());

                        Mood foundIt = null;
                        for (Mood m : moodList){
                            if (m.getName().equalsIgnoreCase(editName)
                                    && m.getDate().equals(editDate)
                                    && m.getTime().equals(editTime)){
                                foundIt = m;
                                break;
                            }
                        }
                        if (foundIt != null){
                            System.out.println("Enter new notes: ");
                            foundIt.setNotes(scanner.nextLine().trim());
                            System.out.println("Notes updated successfully.");

                        }else {
                            System.out.println("Mood not found!");
                        }

                        break;

                    case "s":
                        System.out.println("Enter mood keyword to search: ");
                        String keyword = scanner.nextLine().toLowerCase();
                        boolean found = false;

                        for (Mood mood: moodList){
                            if (mood.getName().toLowerCase().contains(keyword) ||
                                    mood.getNotes() != null && mood.getNotes().toLowerCase().contains(keyword)){

                                System.out.println("Found: " + mood);
                                found = true;

                            }
                        }
                        if (!found) System.out.println("No mood matched your search.");
                        break;

                    case "M":
                        if (moodList.isEmpty()) {
                            System.out.println("No mood(s) recorded yet. ");

                        }else {
                            System.out.println("All moods: ");
                            for (int i=0; i< moodList.size(); i++){
                                System.out.println((i+1) + "." + moodList.get(i));

                            }
                        }
                        break;

                    case "w":
                        try (FileWriter writer = new FileWriter("moods.txt", false)){
                            for (Mood mood: moodList){
                                writer.write(mood + System.lineSeparator());

                            }
                            System.out.println("Moods written to 'moods.txt' successfully.");

                        }catch (IOException e){
                            System.out.println("Error writing to file!" + e.getMessage());

                        }
                        break;

                    case "Exit":
                        System.out.println("Exiting program...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid option. Try again.");
                        break;
                }
        }
    }
}
