package com.spring.mvc.service;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.mvc.pojo.User;

public class AuthService {

	
	private HibernateTemplate hibernateTemplate;

	private AuthService() {

	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {

		this.hibernateTemplate = hibernateTemplate;

	}

	@SuppressWarnings({ "unchecked" })
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
	public boolean findUser(String uname, String upwd) {

		System.out.println("Checking the user in the database");

		boolean isValidUser = false;

		String sqlQuery = "from User u where u.name=? and u.password=?";

		try {

			List<User> userObj = (List<User>) hibernateTemplate.find(sqlQuery, uname, upwd);

			if (userObj != null && userObj.size() > 0) {

				System.out.println("Id= " + userObj.get(0).getId() + ", Name= " + userObj.get(0).getName()
						+ ", Password= " + userObj.get(0).getPassword());

				isValidUser = true;

			}

		} catch (Exception e) {

			isValidUser = false;

			System.out.println("An error occurred while fetching the user details from the database" + e);

		}

		return isValidUser;
	}
}
