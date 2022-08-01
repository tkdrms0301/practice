package Service;

import Persistence.DAO.AdminDAO;
import Persistence.DAO.InstructorDAO;
import Persistence.DAO.MemberDAO;
import Persistence.DAO.UserDAO;
import Persistence.DTO.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserRead {
    private UserDAO userDAO;
    private AdminDAO adminDAO;
    private InstructorDAO instructorDAO;
    private MemberDAO memberDAO;

    public UserRead(){
        userDAO = UserDAO.getInstance();
        adminDAO = AdminDAO.getInstance();
        instructorDAO = InstructorDAO.getInstance();
        memberDAO = MemberDAO.getInstance();
    }

    public List<UserDTO> userDataResultByName(String name){
        return userDAO.selectUserByName(name);
    }

    public List<UserDTO> userDataResultByType(String userType){
        if(userType.equals("admin")){
            return adminDAO.selectUser();
        }else if(userType.equals("instructor")){
            return instructorDAO.selectUser();
        } else if (userType.equals("member")) {
            return memberDAO.selectUser();
        }else{
            return new ArrayList<>();
        }
    }

    public List<UserDTO> userDataResultAll(){
        return userDAO.selectUser();
    }
}
