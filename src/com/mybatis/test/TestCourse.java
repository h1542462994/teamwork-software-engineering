package com.mybatis.test;

import com.dao.CoursesDao;
import com.dao.CoursesToPersonDao;
import com.po.Courses;
import com.po.Courses_detail;
import com.po.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestCourse {
    public static void main(String args[]){
        try {
            InputStream config = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(config);
            SqlSession ss = ssf.openSession();

            CoursesDao courseDao = ss.getMapper(CoursesDao.class);
            CoursesToPersonDao ctpd=ss.getMapper(CoursesToPersonDao.class);
            Courses addco=new Courses();
            addco.setCid(30);
            addco.setCname("计算机网络原理");
            addco.setInfo("计算机网络是指将地理位置不同的具有独立功能的多台计算机及其外部设备，通过通信线路连接起来...");
            //创建课程
            int add=courseDao.addCourse(addco);
            System.out.println("创建了"+add+"个课程");
            //删除课程
            int dl=courseDao.deleteCourse(6);
            System.out.println("删除了"+dl+"个课程");
            //查询所有课程以及每个课程所对应的用户信息
            List<Courses> cs=courseDao.selectallCoursesAndPersons();
            for(Courses courses:cs){
                System.out.println(courses);
            }
            //向指定人群【根据工号】发布课程,保证工号和课程号都存在

            Courses_detail cd=new Courses_detail();
            cd.setCourses_id(2);
            cd.setPerson_id(2);
            int add2=ctpd.addCtp(cd);

            System.out.println("向指定对象发布了课程");




        }
        catch (IOException e){
            e.printStackTrace();
        }
    }



}
