package main.java.com.example.service;

import java.util.List;

import main.java.com.example.bean.DepartmentUserCategory;
import main.java.com.example.bean.User;

public interface DUCS {

	List<DepartmentUserCategory> selectbyid(Integer id);// 用id查找department user category的集合

	boolean adddepartment(Integer integer, String did, String normal);

	boolean quitdepartment(Integer integer, String did, String normal);

	boolean createdepartment(String dchargeperonid, String coursename, String upperdepartment, String lowerdepartment,
			String mainad);

}
