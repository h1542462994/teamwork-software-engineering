package com.example.service;

import com.example.bean.Cnode;

import java.util.List;

public interface CnodeService {
    List<Cnode> findbyid(Integer coursesid);
    List<Cnode> findbycoursesidAndnodeid(Integer coursesid,Integer nodeid);
}
