package io.khasang.moika.service.impl;

import io.khasang.moika.dao.RoleDAO;
import io.khasang.moika.dao.UserDAO;
import io.khasang.moika.entity.Role;
import io.khasang.moika.entity.User;
import io.khasang.moika.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    public User findById(Long id) {
        return userDAO.get(id);
    }

    @Override
    public User findByLogin(String login) {
        return userDAO.findByLogin(login);
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public User createUser(User user) {
        if(!StringUtils.isEmpty(user.getPassword())){
            user.setPassword(getEncodedPassword(user.getPassword()));
        }
        return userDAO.create(user);
    }

    @Override
    public List<User> getAllUsers(){
        return userDAO.getAll();
    }

    @Override
    public User createClientUser(User user) {
        Role role = roleDAO.findByName("ROLE_CLIENT");
        user.setRoles(Collections.singleton(role));
        createUser(user);
        return user;
    }

    @Override
    public void deleteUser(User user) {
        userDAO.delete(user);
    }

    @Override
    public User updateUser(User user) {
        userDAO.update(user);
        return user;
    }

    @Override
    public boolean isLoginUsed(String login, User exceptUser) {
        User foundUser = userDAO.findByLogin(login);
        return foundUser != null && (exceptUser == null || foundUser.getId() != exceptUser.getId());
    }

    @Override
    public boolean isEmailUsed(String email, User exceptUser) {
        User foundUser = userDAO.findByEmail(email);
        return foundUser != null && (exceptUser == null || foundUser.getId() != exceptUser.getId());
    }

    @Override
    public String getEncodedPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public void grantRole(User user, Role role) {
        Role roleBD = roleDAO.findByName(role.getName());
        if(roleBD!=null&&!user.getRoles().contains(roleBD)){
            user.getRoles().add(roleBD);
            userDAO.update(user);
        }
    }

    @Override
    public void revokeRole(User user, Role role) {
        if(role!=null&&user.getRoles().contains(role)){
            user.getRoles().remove(role);
            userDAO.update(user);
        }
    }


}
