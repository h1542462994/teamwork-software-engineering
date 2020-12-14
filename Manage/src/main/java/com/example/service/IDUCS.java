package main.java.com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import main.java.com.example.bean.DepartmentUserCategory;
import main.java.com.example.dao.DUCD;
import main.java.com.example.dao.UserDao;

public class IDUCS implements DUCS {
	@Autowired
	private DUCD ducDao;

	@Override
	public List<DepartmentUserCategory> selectbyid(Integer id) {
		return ducDao.findUserById(id);
	}

	@Override
	public boolean adddepartment(Integer integer, String did, String normal) {
		// 在相应的dao层面，string normal会变成level等级，来判断是什么身份
		boolean actionseccess = ducDao.addDepartmentCaseByIntegerAndDidAndLevel(integer, did, normal);
		if (actionseccess)
			return true;
		else
			return false;
	}

	@Override
	public boolean quitdepartment(Integer integer, String did, String normal) {
		// 在相应的dao层面，string normal会变成level等级，来判断是什么身份
		boolean actionseccess = ducDao.deleteDepartmentCaseByIntegerAndDidAndLevel(integer, did, normal);
		if (actionseccess)
			return true;
		else
			return false;
	}

	@Override
	public boolean createdepartment(String dchargeperonid, String coursename, String upperdepartment,
			String lowerdepartment, String mainad) {
		boolean actionseccess = ducDao.createDepartmentCaseByManidAndCoursenameAndUDmentAndLDmentAndLevel(dchargeperonid, coursename, 
				upperdepartment,lowerdepartment,mainad);
		if (actionseccess)
			return true;
		else
			return false;
	}
}
