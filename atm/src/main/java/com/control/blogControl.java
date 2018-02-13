package com.control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.AccountFlow.Account;
import com.ajax.ajaxDAO;
import com.blog.Blog;
import com.fenye.Fenye;
import com.service.BlogService;
import com.service.Service;
import com.user.User;


@Controller	//这里的注入写法固定,SpringMVC会自动根据注入找到Control类
@RequestMapping("/blog")//这里就比较有意思,SpringMVC中这个注解是获取前端的url地址片,根据url地址找到相应的类和方法进行相应的操作
public class blogControl extends control{
	
	@Autowired	//这个注解程序会自动找到相关的类,进行自动的初始化,这样就不需要用set方法进行初始化,这里可以结合Service层的@Component
	private Service service;
	
	@Autowired
	private BlogService blogservice;
	
	@RequestMapping("/down")
	public void down(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		javax.servlet.http.HttpSession session = req.getSession(); 
		User user=(User) session.getAttribute("user");
		String a=req.getParameter("number");
		int number=Integer.parseInt(a);
		String password=req.getParameter("password");
		Blog cad=service.Get(number, password);
		String filename = number + ".csv";
		resp.setContentType("application/octet-stream");  
		resp.setHeader("Content-Disposition", "attachment;filename="+ filename);  
		
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream()))) {
			int currentPage = 1;
			String header = "卡号,标题,文章,备注,时间";
			bw.write(header);
			bw.newLine();
			bw.flush();
			while (true) {
				Fenye list = service.List(number, password, currentPage);
				System.out.println(currentPage);
				 ArrayList<Account>account=(ArrayList<Account>) list.getObject();
				if(null==account) {
					break;
				}
				if(account.isEmpty()) {//这里特别注意不然不能下载完成
					break;
				}
				for(Account i:account) {
					StringBuilder text = new StringBuilder();
					text.append(i.getNumber()).append(",")
					.append(i.getTitle()).append(",")
					.append(i.getContext()).append(",")
					.append(i.getDescription()).append(",")
					.append(i.getCreatetime()).append(",");
					bw.write(text.toString());
					bw.newLine();
					bw.flush();
					}
				System.out.println(111);
				currentPage++;
			}
		}
	}

	
	@RequestMapping("/toDelete")
	public String toDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a=req.getParameter("number");
		int number=Integer.parseInt(a);
		req.setAttribute("number", number);
		return "delete";
	}
	
	@RequestMapping("/delete")
	public String  delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		javax.servlet.http.HttpSession session = req.getSession();
		User user=(User)session.getAttribute("user");
		String a=req.getParameter("number");
		int number=Integer.parseInt(a);
		String password=req.getParameter("password");
		Blog blog=service.Get(number,password);
		if(blog==null) {
			return "failure";
		}
		else {
			service.delete(number);
			session.setAttribute("user",user);
			return toUsercenter(req, resp);
		}
	}
	
	@RequestMapping("/openAccount")
	public String openAccount(HttpServletRequest req, HttpServletResponse resp,HttpSession session) throws ServletException, IOException {
		User user=(User) session.getAttribute("user");
		String username=user.getUsername();
		String password=req.getParameter("password");
		Blog blog=service.open(username,password);
		req.setAttribute("blog", blog);
		req.setAttribute("password", password);//这里是通过键值对的形式存储相关的数值,对于Attribute属性有set方法也有get方法这里可以和Parameter参数区别
		return "success";
	}
	/*
	 * 注意这里的参数session,以前都是在方法中用request获取但是现在用Spring-MVC就简化了,可以在参数栏直接传入
	 */
	
	@RequestMapping("/toOpenAccount")
	public String toOpenAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		javax.servlet.http.HttpSession session = req.getSession();
		return "OpenAccount";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest req, HttpServletResponse resp,String number,String title,String password,String context) throws ServletException, IOException {
		String a=req.getParameter("number");
		int num=Integer.parseInt(number);
		Blog bog=service.Get(num,password);
		System.out.println(bog);
		if(bog==null) {
			return "failure";
		}
		else {
			service.write(num, password, title, context);
			Blog blog=service.Get(num, password);
			System.out.println("//////"+blog.getTotal());
			req.setAttribute("blog", blog);
			return "success2";
		}
	}
	
	@RequestMapping("/toWrite")
	public String  toWrite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a=req.getParameter("number");
		int number=Integer.parseInt(a);
		req.setAttribute("number", number);
		return "write";
	}
	
	/*@RequestMapping("/transfer")
	public String transfer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a=req.getParameter("number");
		int number=Integer.parseInt(a);
		String password=req.getParameter("password");
		String b=req.getParameter("money");
		double money=Double.parseDouble(b);
		Blog cad=service.Get(number,password);
		if(cad==null) {
			return "failure";
		}
		String c=req.getParameter("InNumber");
		int InNumber=Integer.parseInt(c);
		service.transfer(number, password, money, InNumber);
		Blog card1=service.GetCard(number);
		Blog card2=service.GetCard(InNumber);
		if((card1==null)||(card2==null)) {
			return "failure";
		}
		else {
			req.setAttribute("card1", card1);
			req.setAttribute("card2", card2);
			return "success2";
		}
	}
	
	@RequestMapping("/toTransfer")
	public String toTransfer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a=req.getParameter("number");
		int number=Integer.parseInt(a);
		req.setAttribute("number", number);
		return "transfer";
	}
	
	@RequestMapping("/check")
	public String  check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a=req.getParameter("number");
		int number=Integer.parseInt(a);
		String password=req.getParameter("password");
		String b=req.getParameter("money");
		double money=Double.parseDouble(b);
		Blog cad=service.Get(number,password);
		if(cad==null) {
			return "failure";
		}
		service.draw(number, password, money);
		Blog card=service.GetCard(number);
		if(card==null) {
			return "failure";
		}
		else {
			req.setAttribute("card", card);
			return "success";
		}
	}
	
	@RequestMapping("/toCheck")
	public String toCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a=req.getParameter("number");
		int number=Integer.parseInt(a);
		req.setAttribute("number", number);
		return "check";
	}*/
	
	@RequestMapping("/list")
	public String list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String currentPage = req.getParameter("currentPage");
			System.out.println(currentPage);
			String a=req.getParameter("number");
			int number=Integer.parseInt(a);
			System.out.println("number"+number);
			String password=req.getParameter("password");
			System.out.println("password"+password);
			Blog cad=service.Get(number,password);
			System.out.println(">>>>>>>>1"+cad);
			if(null==cad) {
				return "failure";
			}
			else {
				currentPage=currentPage ==null ? "1" : currentPage;//注意这里的分页后台实现
				Fenye list = service.List(number, password, Integer.parseInt(currentPage));//获取记录
				System.out.println("22222"+list);
				req.setAttribute("fenye", list);//保存记录
				req.setAttribute("number", number);
				req.setAttribute("password", password);//这里之所以保存number和password是为了前端页面显示的作用
				req.setAttribute("currentPage", currentPage);
				return "list";
			}
		}
	
	@RequestMapping("/toList")
	public String toList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a=req.getParameter("number");
		int number=Integer.parseInt(a);
		req.setAttribute("number", number);
		return "list";
	
	}
	
	@RequestMapping("/toChangePassword")
	public String toChangePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a=req.getParameter("number");
		int number=Integer.parseInt(a);
		req.setAttribute("number", number);
		return "password";
	}
	
	@RequestMapping("/password")
	public String  password(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a=req.getParameter("number");
		int number=Integer.parseInt(a);
		String oldPassword= req.getParameter("oldPassword");
		Blog bog=service.Get(number,oldPassword);
		System.out.println("ererer"+bog);
		if(bog==null) {
			return "failure";
		}
		else {
			String newPassword= req.getParameter("newPassword");
			Blog blog=service.ChangePassword(number, oldPassword, newPassword);
			req.setAttribute("blog", blog);
			req.setAttribute("password", newPassword);
			return "success";
		}
	}
	
	@RequestMapping("/toUsercenter")
	public String toUsercenter(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("--------");
		javax.servlet.http.HttpSession session = req.getSession();
		String username= (String) session.getAttribute("username");
		System.out.println(username);
		String currentPage = req.getParameter("currentPage");
		currentPage=currentPage ==null ? "1" : currentPage;//注意这里的分页后台实现
		Fenye list=blogservice.list(username, Integer.parseInt(currentPage));
		System.out.println("\\\\\\"+list);
		req.setAttribute("fenye", list);
		req.setAttribute("currentPage", Integer.parseInt(currentPage));
		req.setAttribute("username", username);
		return "usercenter";
	}
	
//	@RequestMapping("/toInformation")
//	public String toInformation	(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//		String a=req.getParameter("number");
//		int number=Integer.parseInt(a);
//		req.setAttribute("number", number);
//		return "information";
//	}
//	
	@RequestMapping("/information")
	public String information(HttpServletRequest req,String Title,String password)throws ServletException, IOException {
		Account account=blogservice.get(Title);
		System.out.println(account);
		req.setAttribute("account", account);
		req.setAttribute("password",password);
		return "information";
	}
	
	@RequestMapping("/deleteflow")
	public String deleteflow(HttpServletRequest req)throws ServletException, IOException {
		String title=(String) req.getParameter("Title");
		System.out.println("title"+title);
		String a=req.getParameter("number");
		System.out.println("aaa"+a);
		int number=Integer.parseInt(a);
		System.out.println("number"+number);
		service.deletefFlow(number, title);
		req.setAttribute("number", number);
		return "success3";
		
		
	}
	/*
	 * 注意这里的方法参数,其中的number和password必须和前端的name一样,
	 * 必须和前端的要取的数据名相同,但是因为都是从前端获取的所以必须都定义为String类型
	 * 这里可以结合information。jsp页面看就懂了,这也是Spring-MVC的用法特色,获取前端的数据
	 * 不需要用request获取,可以直接获取但是参数必须和前端的name相同
	 */
}
