package com.inter;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.blog.Blog;

public interface BlogDAO {
	public Blog Get(@Param("number")int number,@Param("password")String password);
	/*
	 * 这里的DAO层和以前JDBC时不一样，这里作为接口，只需要定义方法就可以,同时需要注意这里的@Param()这里是给参数加注解
	 */
	public int modifyPassword(@Param("number")int number,@Param("newPassword")String newPassword);//多个参数要起别名
	//public int modifyMoney(@Param("number")int number,@Param("money")double money);
	public int open(Blog blog);
	
	public ArrayList<Blog> List(@Param("number")int number,@Param("currentNumber")int currentNumber,@Param("move")int move);
	public ArrayList<Blog> list(@Param("username")String usernamer,@Param("currentNumber")int currentNumber,@Param("move")int move);
	public int add(@Param("number")int number,@Param("total") int total);
	public int totalNumber(int number);
	public int total(String username);
	public Blog GetBog(int number);
	public Blog GetBlog(@Param("number")int number,@Param("password")String password);
	public int  delete(int number);
}
