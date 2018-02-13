package com.service;

import java.util.ArrayList;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.AccountFlow.Account;
import com.base.DataBase;
import com.blog.Blog;
import com.fenye.Fenye;
import com.inter.AccountDAO;
import com.inter.BlogDAO;



@Component	//这里是Spring的注入运行时Spring会自动扫描该注入的作用域,这里是配合Control层使用的
@Transactional	//这里的注入是用于spring和mybaties结合时的事务管理,它等同于mybaties中的sqlSession的connection,这里不需要手动的开启和关闭事务
public class Service {
	
	@Autowired
	private BlogDAO blogDao;
	
	@Autowired
	private AccountDAO accountDao;
	
	@Transactional(rollbackFor=Exception.class)
	public Blog open(String username,String password) {
		Blog blog=new Blog();
		blog.setNumber(DataBase.CreateNumber());
		blog.setPassword(DigestUtils.md5Hex(password));
		blog.setTotal(0);
		blog.setUsername(username);
		System.out.println(blog);
		if(blogDao.open(blog)==1) {//这里调用Card.xml类中的SQL语句
			return blog;
			}
		else {
			return null;
		}
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void write(int number,String password,String title,String context)  {
			Blog blog=blogDao.GetBog(number);//这里可以和之前的使用mybaties的获取事务进行比较就可以发现用spring+mybaties的优点,这里自动创建对象
			if(null==blog) {
				System.out.println("账号或者密码不存在");
				return;
			}
			if(!DigestUtils.md5Hex(password).equals(blog.getPassword())) {
				System.out.println("账号或者密码不存在");
				return;
			}
			System.out.println(blog.getTotal()+">>>>>>>");
			int total=blog.getTotal()+1;
			System.out.println("??????"+total);
			blogDao.add(number, total);
			System.out.println("'uuuuu"+blogDao.GetBog(number));
			Account account=new Account();
			account.setNumber(number);
			account.setContext(context);
			account.setTitle(title);
			account.setDescription("写入");
			accountDao.add(account);
			System.out.println("博客产生");
		}
		
/*	@Transactional(rollbackFor=Exception.class)
	public void draw(int number,String password,double money) {
			Blog card=cardDao.GetCad(number);
			if(null==card) {
				System.out.println("账号或者密码不存在");
				return;
			}
			if(!DigestUtils.md5Hex(password).equals(card.getPassword())) {
				System.out.println("账号或者密码不存在");
				return;
			}
			if(money<0) {
				System.out.println("金额小于零");
				return;
			}
			double x=card.getMoney();
			if(money>x) {
				System.out.println("金额不足");
				return;
			}
			x-=money;
			if(cardDao.modifyMoney(number, x)!=0) {
				System.out.println("取钱成功");	
			}
			Account account=new Account();
			account.setNumber(number);
			account.setMoney(money);
			account.setType(1);
			account.setDescription("取钱");
			accountDao.add(account);
			System.out.println("流水账产生");
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void transfer(int OutNumber,String password,double money,int InNumber) {
			Blog card1=cardDao.GetCad(OutNumber);
			if(null==card1) {
				System.out.println("账号或者密码不存在");
				return;
			}
			if(!DigestUtils.md5Hex(password).equals(card1.getPassword())) {
				System.out.println("账号或者密码不存在");
				return;
			}
			if(money<0) {
				System.out.println("金额小于零");
				return;
			}
			double x=card1.getMoney();
			if(money>x) {
				System.out.println("金额不足");
				return;
			}
			x-=money;
			if(cardDao.modifyMoney(OutNumber, x)!=0) {
				System.out.println("取钱成功");	
			}
			Account account1=new Account();
			account1.setNumber(OutNumber);
			account1.setMoney(money);
			account1.setType(1);
			account1.setDescription("取钱");
			accountDao.add(account1);
			System.out.println("流水账产生");
			Blog card2=cardDao.GetCad(InNumber);
			if(null==card2) {
				System.out.println("账号或者密码不存在");
				return;
			}
			double y=card2.getMoney();
			money+=y;
			if(cardDao.modifyMoney(InNumber, money)!=0) {
				System.out.println("转入成功");	
			}
			Account account2=new Account();
			account2.setNumber(InNumber);
			account2.setMoney(money);
			account2.setType(1);
			account2.setDescription("存钱");
			accountDao.add(account2);
			System.out.println("流水账产生");
		}//这里注意cardDao的使用
*/	
	@Transactional(rollbackFor=Exception.class)
	public Blog ChangePassword(int number,String oldPassword,String newPassword) {
			Blog a=blogDao.GetBog(number);
			if(null==a) {
				System.out.println("账号或者密码不存在");
				return null;
			}
			if(!DigestUtils.md5Hex(oldPassword).equals(a.getPassword())) {
				System.out.println("账号或者密码不存在");
				return null;
			}
			if(blogDao.modifyPassword(number, DigestUtils.md5Hex(newPassword))!=0) {
				a=blogDao.GetBog(number);
				return a;
			}
			else {
				return null;
			}
	}
	
	@Transactional(rollbackFor=Exception.class)
	public Fenye List(int number, String password, int currentPage) {
		Blog blog=blogDao.GetBog(number);
		if(null==blog) {
			System.out.println("账号或者密码不存在");
			return null;
		}
		if(!DigestUtils.md5Hex(password).equals(blog.getPassword())) {
			System.out.println("账号或者密码不存在");
			return null;
		}
		int totalNumber=accountDao.totalNumber(number);//调用totalNumber方法获取总纪录数目
		Fenye fenye=new Fenye(totalNumber, currentPage);//初始化，参数为总记录和当前页数
		 ArrayList<Account>list=accountDao.List(number, fenye.getcurrentNumber(), fenye.move);//调用List()方法获取该number的该页内容
		 fenye.setObject(list);//将获取的记录保存
		 if(null==fenye) {
			 return null;
		 }
		 else {
			 return fenye;
		 }
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int total(int number) {
		return accountDao.totalNumber(number);
	}
	
	public Blog GetBlog(int number,String password) {
			Blog a=blogDao.Get(number,password);
			System.out.println(a.getTotal()+"xxxxxx>>>>");
			if(null==a) {
				System.out.println("账号或者密码不存在");
				return null;
			}
			else {
				return a;
			}
		}
	
	@Transactional(rollbackFor=Exception.class)
	public Blog Get(int number,String password) {
			Blog a=blogDao.Get(number,DigestUtils.md5Hex(password));
			return a;
		}
	
	@Transactional(rollbackFor=Exception.class)//作用处理检查型异常
	public void delete(int number) {
			int i=blogDao.delete(number);
			System.out.println(i);
			if(i==0) {
				System.out.println("账号或者密码不存在");
			}
	}
	
	public void deletefFlow(int number,String title) {
		accountDao.delete(number, title);
	}
}
/*
 * 什么是检查型异常,什么是非检查型异常,这里的区分方法是:
 * 1 检查型异常继承于Exception 非检查型异常继承于RuntimException或者Error
 * 2 检查型异常必须捕获,但是非检查型异常可以不捕获
 * /
 */