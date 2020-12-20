package com.example.service;

import com.example.bean.Cnode;
import com.example.bean.Courses;
import com.example.dao.CnodeDao;
import com.example.dao.CoursesDao;
import com.example.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICnodeService implements CnodeService {

    @Autowired
    private CnodeDao cnodeDao;

    @Override
    public List<Cnode> findbyid(Integer coursesid)
    {
        return cnodeDao.findCnodeByid(coursesid);
    }
    @Override
    public List<Cnode> findbycoursesidAndnodeid(Integer coursesid,Integer nodeid)
    {
        return cnodeDao.findCnodeBycoursesidAndnodeid(coursesid,nodeid);
    }
}
