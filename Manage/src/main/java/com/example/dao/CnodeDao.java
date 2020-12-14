package main.java.com.example.dao;

import main.java.com.example.bean.Cnode;
import main.java.com.example.bean.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CnodeDao extends JpaRepository<Cnode,Integer> {
    @Modifying
    @Transactional
    @Query("select u from Cnode u  where  u.coursesid=:coursesid ")
    public List<Cnode> findCnodeByid(@Param("coursesid") Integer coursesid);

    @Modifying
    @Transactional
    @Query("select u from Cnode u  where  u.coursesid=:coursesid and u.nodeid=:nodeid ")
    public List<Cnode> findCnodeBycoursesidAndnodeid(@Param("coursesid") Integer coursesid,@Param("nodeid") Integer nodeid);
}
