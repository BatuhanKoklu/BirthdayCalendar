package batuhan.com.birthdaycalendar.Models;

public class BirthdayModel {

    private int birthdayId;
    private String birthdayName;
    private String birthdayNote;
    private String birthdayDate;
    private int birthdayFavorite;

    public BirthdayModel(int birthdayId, String birthdayName, String birthdayNote, String birthdayDate, int birthdayFavorite) {
        this.birthdayId = birthdayId;
        this.birthdayName = birthdayName;
        this.birthdayNote = birthdayNote;
        this.birthdayDate = birthdayDate;
        this.birthdayFavorite = birthdayFavorite;
    }

    public int getBirthdayId() {
        return birthdayId;
    }

    public void setBirthdayId(int birthdayId) {
        this.birthdayId = birthdayId;
    }

    public String getBirthdayName() {
        return birthdayName;
    }

    public void setBirthdayName(String birthdayName) {
        this.birthdayName = birthdayName;
    }

    public String getBirthdayNote() {
        return birthdayNote;
    }

    public void setBirthdayNote(String birthdayNote) {
        this.birthdayNote = birthdayNote;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public int getBirthdayFavorite() {
        return birthdayFavorite;
    }

    public void setBirthdayFavorite(int birthdayFavorite) {
        this.birthdayFavorite = birthdayFavorite;
    }
}
