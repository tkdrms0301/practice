package Persistence.DTO;

public class LockerDTO {
    private int id;
    private int lockerNumber;
    private int lockerLocation;
    private int amount;
    private int userId;

    public LockerDTO() {
    }

    public LockerDTO(int lockerNumber, int lockerLocation, int amount) {
        this.lockerNumber = lockerNumber;
        this.lockerLocation = lockerLocation;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "LockerDTO{" +
                "id=" + id +
                ", lockerNumber=" + lockerNumber +
                ", lockerLocation=" + lockerLocation +
                ", amount=" + amount +
                ", userId=" + userId +
                '}';
    }

    public LockerDTO(int lockerNumber, int lockerLocation, int amount, int userId) {
        this.lockerNumber = lockerNumber;
        this.lockerLocation = lockerLocation;
        this.amount = amount;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public int getLockerNumber() {
        return lockerNumber;
    }

    public int getLockerLocation() {
        return lockerLocation;
    }

    public int getAmount() {
        return amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLockerNumber(int lockerNumber) {
        this.lockerNumber = lockerNumber;
    }

    public void setLockerLocation(int lockerLocation) {
        this.lockerLocation = lockerLocation;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
