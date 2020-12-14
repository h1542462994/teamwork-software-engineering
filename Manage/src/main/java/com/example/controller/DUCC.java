package main.java.com.example.controller;

import main.java.com.example.*;
import main.java.com.example.bean.DepartmentUserCategory;
import main.java.com.example.bean.User;
import main.java.com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DUCC { // department user category controller
	@Autowired
	private DUCS ducs;// department user category service

	@RequestMapping("/actionfail")
	public String actionfail() {
		return "actionfail";
	}

	@RequestMapping("/actionsuccess")
	public String actionsuccess() {
		return "actionsuccess";
	}

	@RequestMapping("/showuserdepartment")
	public String showuserdepartment() {
		return "showuserdepartment";
	}
	
	@RequestMapping("/inviteorremoveuser")
	public String inviteorremoveuser() {
		return "inviteorremoveuser";
		
	}
	
	@RequestMapping("/addordeletedepartment")
	public String addordeletedepartment() {
		return "addordeletedepartment";
	}
	@RequestMapping("/createdepartmenthtml")
	public String createdepartmenthtml() {
		return "createdepartmenthtml";
	}

	@RequestMapping("/departmentbelongto")
	@ResponseBody
	public String selectdepartment(DepartmentUserCategory duc, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();// 用于找到相关部门
		User user = (User) session.getAttribute("user");
		List<DepartmentUserCategory> DUCList = ducs.selectbyid(duc.getId());
		if (DUCList.size() > 0) {
			model.addAttribute("DUCList", DUCList);
			return "showuserdepartment";
		} else
			return "actionfail";
	}

	@RequestMapping("/joindepartment")
	@ResponseBody
	public String joindepartment(DepartmentUserCategory duc, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();// 加入部门
		User user=(User) session.getAttribute("user");
		String normal="normal";
		Integer userID=(Integer) request.getAttribute("userID");
		String courseID=(String) request.getAttribute("courseID");
		boolean addsuccess = ducs.adddepartment(userID,courseID,normal);
		if (addsuccess)
			return "actionsuccess";
		else
			return "actionfail";
	}

	@RequestMapping("/quitdepartment")
	@ResponseBody
	public String quitdepartment(DepartmentUserCategory duc, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();// 加入部门
		User user = (User) session.getAttribute("user");
		Integer userID=(Integer) request.getAttribute("userID");
		String courseID=(String) request.getAttribute("courseID");
		String normal="normal";
		boolean addsuccess = ducs.quitdepartment(userID, courseID,normal);
		if (addsuccess)
			return "actionsuccess";
		else
			return "actionfail";
	}

	@RequestMapping("/createdepartment")
	@ResponseBody
	public String createdepartment(DepartmentUserCategory duc, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();// 加入部门
		User user = (User) session.getAttribute("user");
		String coursename= (String) request.getAttribute("coursename");
		String upperdepartment= (String) request.getAttribute("upperdepartment");
		String lowerdepartment= (String) request.getAttribute("lowerdepartment");
		String dchargeperonid= (String) request.getAttribute("dchargeperonid");
		String mainad="mainad";
		boolean addsuccess = ducs.createdepartment(dchargeperonid,coursename,upperdepartment,lowerdepartment,mainad);
		if (addsuccess)
			return "actionsuccess";
		else
			return "actionfail";
	}
	@RequestMapping("/inviteuser")
	@ResponseBody
	public String inviteuser(DepartmentUserCategory duc, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();// 加入部门
		User user = (User) session.getAttribute("user");
		Integer userId=(Integer) session.getAttribute("userId");
		String normal="normal";
		Integer userID=(Integer) request.getAttribute("userID");
		String courseID=(String) request.getAttribute("courseID");
		boolean addsuccess = ducs.adddepartment(userID,courseID,normal);
		if (addsuccess)
			return "actionsuccess";
		else
			return "actionfail";
		
	}
	
	@RequestMapping("/removeuser")
	@ResponseBody
	public String removeuser(DepartmentUserCategory duc, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();// 加入部门
		User user = (User) session.getAttribute("user");
		Integer userID=(Integer) request.getAttribute("userID");
		String courseID=(String) request.getAttribute("courseID");
		String normal="normal";
		boolean addsuccess = ducs.quitdepartment(userID,courseID,normal);
		if (addsuccess)
			return "actionsuccess";
		else
			return "actionfail";
		
	}
}
