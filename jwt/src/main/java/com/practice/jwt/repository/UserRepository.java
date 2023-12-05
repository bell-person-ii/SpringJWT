package com.practice.jwt.repository;

import com.practice.jwt.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserRepository {

    private final EntityManager em;

    public void save(User user){
        em.persist(user);
    }

    public User findByUserName(String name){
        List<User> users = em.createQuery("select u from  User u where u.name =:name")
                .setParameter("name",name).getResultList();
        return users.get(0);
    }
}
