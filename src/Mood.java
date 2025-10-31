import java.time.LocalDate;
import java.time.LocalTime;

public class Mood {

    private String name;
    private LocalDate date = LocalDate.now();
    private LocalTime time = LocalTime.MIDNIGHT;
    private String notes;


    public Mood(String name){
        this.name = name;

    }
    public Mood(String name, LocalDate date){
        this.name = name;
        this.date = date;

    }
    public Mood(String name,LocalDate date, LocalTime time){
        this.name = name;
        this.date = date;
        this.time = time;

    }
    public Mood(String name, String notes){
        this.name = name;
        this.notes = notes;

    }
    public Mood(String name, LocalDate date, String notes){
        this.name = name;
        this.date = date;
        this.notes = notes;

    }
    public Mood(String name, LocalDate date, LocalTime time, String notes){
        this.name = name;
        this.date = date;
        this.time = time;
        this.notes = notes;

    }
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;

    }
    public LocalDate getDate() {
        return date;
    }
    public LocalTime getTime() {
        return time;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes){
        this.notes = notes;

    }

    public boolean equalsTo(Object obj){
        if (obj instanceof Mood other){
            return date.equals(other.date) && time.equals(other.time);

        }
        return false;
    }

    public String toString(){
        return "Mood: " + name + ", Date: " + date + ", Time: " + time + ", Notes: " + notes;
    }




}
