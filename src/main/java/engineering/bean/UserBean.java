package engineering.bean;

public class UserBean {

    private String userEmail ;
    private Integer userType ;

    public UserBean(String userEmail, Integer userType) {
        setUserEmail(userEmail);
        setUserType(userType);
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
