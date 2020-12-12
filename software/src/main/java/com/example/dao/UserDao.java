package com.example.dao;

import com.example.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    @Modifying
    @Transactional
    //@Query("select u from User u  where u.id=:id and u.name=:name and u.password=:password")
    //public List<User> findUserByUsernameAndPwd(@Param("id") Integer id,@Param("name") String name,@Param("password") String password);
    @Query("select u from User u  where  u.name=:name and u.password=:password")
    public List<User> findUserByUsernameAndPwd(@Param("name") String name,@Param("password") String password);
}
