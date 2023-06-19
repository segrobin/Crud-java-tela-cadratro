package br.org.services;

import br.org.model.User;
import br.org.model.UserDAO;

import java.util.List;

public class UserService {

    public List<User> getUserList(){
        UserDAO userDAO = new UserDAO();
        return userDAO.getAllUsers();
    }
}
