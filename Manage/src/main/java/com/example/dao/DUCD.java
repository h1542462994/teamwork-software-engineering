package main.java.com.example.dao;

import main.java.com.example.bean.DepartmentUserCategory;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//DUCD == DepartmentUserCategoryDAO
public interface DUCD<DepartmentUserCategory> extends JpaRepository<DepartmentUserCategory, Integer> {
	@Modifying
	@Transactional
	@Query("select d.dname from DepartmentUserCategory duc ,Departmet d ,User u  where  u.id=:id and " + "duc.id =:u.id"
			+ "and duc.did =:d.did")
	public List<DepartmentUserCategory> findCoursesByname(@Param("id") String id);

	public List<main.java.com.example.bean.DepartmentUserCategory> findUserById(Integer id);

	public boolean addDepartmentCaseByIntegerAndDidAndLevel(Integer integer, String did, String normal);
	// 目前所知的是，在departusercategory表中加入数据

	public boolean deleteDepartmentCaseByIntegerAndDidAndLevel(Integer integer, String did, String normal);

	// 目前所知的是，在departusercategory表中删除数据
	public boolean createDepartmentCaseByManidAndCoursenameAndUDmentAndLDmentAndLevel(String dchargeperonid,
			String coursename, String upperdepartment, String lowerdepartment, String mainad);
	// 目前所知的是，在departusercategory表中加入数据，在department表中加入数据，cnode和cnodekey应该也是要加的，不对，资料存储时用这两个
	// 那么除非再加入dnode和dnodekey，就考虑一下在department表内部搞得那几个成员函数。

}
