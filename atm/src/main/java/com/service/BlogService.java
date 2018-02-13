package com.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.AccountFlow.Account;
import com.blog.Blog;
import com.fenye.Fenye;
import com.inter.AccountDAO;
import com.inter.BlogDAO;



@Component	//这里加注解是为了方便Control中的变量调用
@Transactional
public class BlogService {
	
	@Autowired
	private BlogDAO blogDao;
	
	@Autowired
	private AccountDAO accountDao;
	
	public Fenye List(int number,int currentPage) {
		int totalNumber=blogDao.totalNumber(number);
		Fenye fenye=new Fenye(totalNumber, currentPage);
		 ArrayList<Blog>list=blogDao.List(number, fenye.getcurrentNumber(), fenye.move);
		 fenye.setObject(list);//将获取的记录保存
		 System.out.println(">>>>>>>>"+fenye);
		 return fenye;
	}
	
	public Fenye list(String username,int currentPage) {
		int totalNumber=blogDao.total(username);
		System.out.println(totalNumber);
		Fenye fenye=new Fenye(totalNumber, currentPage);
		 ArrayList<Blog>list=blogDao.list(username, fenye.getcurrentNumber(), fenye.move);
		 fenye.setObject(list);//将获取的记录保存
		 return fenye;
	}
	
	public Account get(String title) {
		return accountDao.get(title);
	}

}
