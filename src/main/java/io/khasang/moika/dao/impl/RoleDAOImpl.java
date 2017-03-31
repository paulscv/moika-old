package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.RoleDAO;
import io.khasang.moika.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

/**
 * Базовая реализация DAO ролей пользователей
 *
 * @since 2017-03-01
 * @author Rostislav Dublin
 */
@Repository("roleDao")
@Transactional
public class RoleDAOImpl extends MoikaDaoCrudImpl<Role> implements RoleDAO {
    private static final Logger logger = LoggerFactory.getLogger(RoleDAOImpl.class);
    @Override
    public void init(){
        //String hql = "select id from Role where name = 'ROLE_CLIENT' or name = 'ROLE_ADMIN'";
        String hql = "select id from Role where name = 'ROLE_CLIENT' or name = 'ROLE_ADMIN'";

        if(getCurrentSession().createQuery(hql, Integer.class).list().size()==0){
            System.out.println("All good");
            Role[] roleArr = new Role[]{new Role("ROLE_CLIENT","Base client role"),
                    new Role("ROLE_ADMIN","Base admin role")};
            for (Role role : roleArr){
                create(role);
            }
        }
    }

    @Override
    public Role findByName(String name) {
        try{
            return dataAccessUtil.getQueryOfEntityWithSoleEqualCondition(Role.class, "name", name)
                    .getSingleResult();
        }catch(NoResultException e){
            logger.debug(e.getMessage());
            return null;
        }
    }

    @Override
    public GrantedAuthority getAuthority(Role role) {
        return  new SimpleGrantedAuthority(role.getName());
    }
}
