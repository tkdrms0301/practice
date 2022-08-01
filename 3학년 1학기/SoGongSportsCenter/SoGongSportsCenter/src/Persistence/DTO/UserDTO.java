package Persistence.DTO;

public class UserDTO {
    protected int userId;
    protected String userPassword;
    protected String userName;
    protected String userType;

    public UserDTO(){

    }

    public UserDTO(int userId, String userPassword, String userName, String userType){
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userType = userType;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
