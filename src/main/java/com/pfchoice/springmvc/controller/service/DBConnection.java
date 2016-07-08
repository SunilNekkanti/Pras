package com.pfchoice.springmvc.controller.service;

import java.sql.Connection;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class DBConnection {

	@Autowired
	private SessionFactory sessionFactory;

	 @Resource
	 public void setSessionFactory(final SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	 }
	    
	 public Connection getConnection() {
	        final Session session = this.sessionFactory.getCurrentSession();
	        SessionImpl sessionImpl = (SessionImpl) session;
	        return   sessionImpl.connection();
	 }
}
