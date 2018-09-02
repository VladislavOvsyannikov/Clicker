package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import system.dao.UserDao;
import system.model.User;

import java.security.MessageDigest;
import java.util.List;

@Service
public class ClickerService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String toMD5(String md5) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public User getCurrentUser() {
        return userDao.getUser(getUserName());
    }

    public List getAllUsers() {
        return userDao.getAllUsers();
    }

    public void click() {
        if (!getUserName().equals("anonymousUser")){
            User user = getCurrentUser();
            user.setCounter(user.getCounter()+1);
            userDao.updateUser(user);
        }
    }

    public boolean addUser(User user) {
        User oldUser = userDao.getUser(user.getName());
        if (!user.getName().equals("") && !user.getPassword().equals("") && oldUser==null) {
            User newUser = new User();
            newUser.setName(user.getName());
            newUser.setPassword(toMD5(user.getPassword()));
            newUser.setRole("ROLE_USER");
            newUser.setCounter(0);
            userDao.saveUser(newUser);
            return true;
        }
        return false;
    }
}
