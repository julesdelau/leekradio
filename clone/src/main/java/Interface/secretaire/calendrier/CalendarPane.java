package Interface.secretaire.calendrier;


public class Calendar {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    public Calendar(){
        this.year = 0;
        this.month = 0;
        this.day = 0;
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    public Calendar(int year, int month, int day, int hour, int minute, int second){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }



}
