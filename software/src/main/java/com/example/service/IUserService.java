package com.example.service;

import com.example.bean.User;
import com.example.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IUserService implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean save(User user) {
        try {
            if ("".equals(user.getPassword()) || "".equals(user.getName()) || "".equals(user.getSex()) || "".equals(user.getPhone()) || "".equals(user.getAge()))
                return false;
            if (!user.getPassword().equals(user.getRepassword())) return false;
            if (user.getAge() < 10 || user.getAge() > 90) return false;
            for (int i = 0; i < user.getPhone().length(); i++) {
                if (!Character.isDigit(user.getPhone().charAt(i))) {
                    return false;
                }
            }
            userDao.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public List<User> login(String username, String pwd) {
        return userDao.findUserByUsernameAndPwd(username, pwd);
    }
    /*4public List<User> login(Integer id,String username, String pwd) {
        return userDao.findUserByUsernameAndPwd(id,username, pwd);
    }*/
    @Override
    public boolean delete(Integer id) {
        try {
            userDao.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
