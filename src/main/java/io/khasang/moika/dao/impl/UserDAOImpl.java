package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.RoleDAO;
import io.khasang.moika.dao.UserDAO;
import io.khasang.moika.entity.Role;
import io.khasang.moika.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Базовая реализация DAO пользователей
 *
 * @author Rostislav Dublin, Kovalev Aleksandr
 * @since 2017-03-01
 */
@Repository("userDao")
@Transactional
public class UserDAOImpl extends MoikaDaoCrudImpl<User> implements UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private RoleDAO roleDAO;

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public User findByLogin(String login) {
        return getCurrentSession().bySimpleNaturalId(User.class).load(login);
    }

    @Override
    public User findByEmail(String email) {
        try{
            return dataAccessUtil.getQueryOfEntityWithSoleEqualCondition(User.class, "email", email)
                    .getSingleResult();
        }catch(NoResultException e){
            logger.debug(e.getMessage());
            return null;
        }
    }

    @Override
    public User findByFieldValue(String field, Object value) {
        try{
            return dataAccessUtil.getQueryOfEntityWithSoleEqualCondition(User.class, field, value)
                    .getSingleResult();
        }catch(NoResultException e){
            logger.debug(e.getMessage());
            return null;
        }
    }

    @Override
    public void grantRole(@NotNull User user, @NotNull Role role) {
        user.getRoles().add(role);
        getCurrentSession().update(user);
    }

    @Override
    public void revokeRole(@NotNull User user, @NotNull Role role) {
        user.getRoles().remove(role);
        getCurrentSession().update(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(@NotNull User user) {
        Set<GrantedAuthority> grantedAuthoritySet = user.getRoles().stream()
                .map(r -> roleDAO.getAuthority(r))
                .collect(Collectors.toSet());

        return grantedAuthoritySet;
    }

    /**
     * CriteriaBuilder cb = this.em.getCriteriaBuilder();
     // create the query
     CriteriaQuery<Author> q = cb.createQuery(Author.class);

     // set the root class
     Root<Author> a = q.from(Author.class);

     // use metadata class to define the where clause
     q.where(cb.like(a.get(Author_.firstName), "J%"));

     // perform query
     this.em.createQuery(q).getResultList();
     */

}
