package br.org.repository;

import br.org.model.User;
import br.org.model.UserDAO;
import br.org.services.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class UserBean implements Serializable {
    private long id;
    private String name;
    private long editedId;
    private String email;
    private User selectedUser;
    private String editedName;
    private String editedEmail;


    private List<User> userList;
    public void register() {
        UserDAO userDAO = new UserDAO();
        User user = new User(name, email);
        userDAO.saveUser(user);
        try {
            redirectToPage("/pages/cadastrorealizado.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @PostConstruct
    public void init() {
        getUsersFromTable();
    }

    private void redirectToPage(String page) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + page);
    }

    public List<User> getUsersFromTable() {
        UserService userService = new UserService();
        userList = userService.getUserList();
        return userList;
    }

    public void deleteUser() {
        // Obtém o ID do usuário a ser deletado dos parâmetros da requisição
        String userIdParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("user");
        if (userIdParam != null) {
            long userId = Long.parseLong(userIdParam);

            // Chama o método deleteUserById do UserDAO para deletar o usuário
            UserDAO userDAO = new UserDAO();
            userDAO.deleteUserById(userId);

            userList = userDAO.getAllUsers();
        }
    }

    public void selectUserForEdit(User user) {
        selectedUser = user;
    }
    public void editUser() {
        UserDAO userDAO = new UserDAO();
        userDAO.updateUser(selectedUser);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuário atualizado com sucesso."));

        userList = userDAO.getAllUsers();
    }

    public void clearFields() {
        this.id = 0;
        this.name = "";
        this.email = "";
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
    public String getEditedName() {
        return editedName;
    }

    public long getEditedId() {
        return editedId;
    }

    public void setEditedId(long editedId) {
        this.editedId = editedId;
    }

    public void setEditedName(String editedName) {
        this.editedName = editedName;
    }

    public String getEditedEmail() {
        return editedEmail;
    }

    public void setEditedEmail(String editedEmail) {
        this.editedEmail = editedEmail;
    }




}
