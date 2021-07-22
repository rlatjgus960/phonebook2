package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")  //요청 할 수 있는 주소
public class PhoneController extends HttpServlet {
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("컨트롤러");
		
		//파라미터 action 값을 읽어온다
		String action = request.getParameter("action");
		System.out.println(action);
		
		if("list".equals(action)) {
			//리스트업무
			
			//리스트
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList = phoneDao.getPersonList();
			
			System.out.println("controller=====================");
			System.out.println(personList);
			
			//데이터 넣기 --request 어트리뷰트에 데이터를 넣어준다
			request.setAttribute("pList", personList);
			//request.setAttribute("age", 45);
			//request.setAttribute("name", "김서현");
			
			//html 작업 jsp한테 시킨다 --> 포워드 해줌
			/*
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp"); //담당자 적어주기, 얘한테 넘길테니 기능쩜
			rd.forward(request, response); //포워드 여기까지 한세트
			아래 웹유틸로 대체*/
			WebUtil.forward(request, response, "/WEB-INF/list.jsp");
			
		}else if("wform".equals(action)) {
			System.out.println("[글쓰기폼]");
			
			//writeForm.jsp 포워드 --> 데이터X
			/*
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
			rd.forward(request, response);
			*/
			WebUtil.forward(request, response, "/WEB-INF/writeForm.jsp");
		}else if("insert".equals(action)) {
			System.out.println("[저장]");
			
			//dao --> 저장
			//파라미터를 꺼낸다. name, hp, company
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//vo로 묶어준다
			PersonVo personVo = new PersonVo(name, hp, company);
			System.out.println(personVo);
			
			//dao personInsert(vo)
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personInsert(personVo);
			
			//리다이렉트
			//response.sendRedirect("/phonebook2/pbc?action=list");
			WebUtil.redirect(request, response, "/phonebook2/pbc?action=list");
			
			
		}else if("uform".equals(action)) {
			
			PhoneDao phoneDao = new PhoneDao();   

		    String id = request.getParameter("id");
		    int personId = Integer.parseInt(id);
		    System.out.println(personId);
		    
		    PersonVo personVo = phoneDao.getPerson(personId);
		   
		    request.setAttribute("pId", personVo);
		    
		    /*
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/updateForm.jsp");
			rd.forward(request, response);
			*/
		    WebUtil.forward(request, response, "/WEB-INF/updateForm.jsp");
			
		}else if("update".equals(action)) {
			
			int id = Integer.parseInt(request.getParameter("id")) ;
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			PersonVo personVo = new PersonVo(id, name, hp, company);
			System.out.println(personVo);
			
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personUpdate(personVo);
			
			//response.sendRedirect("/phonebook2/pbc?action=list");
			WebUtil.redirect(request, response, "/phonebook2/pbc?action=list");
			
			
		}else if("delete".equals(action)) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personDelete(id);
			
			//response.sendRedirect("/phonebook2/pbc?action=list");
			WebUtil.redirect(request, response, "/phonebook2/pbc?action=list");
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
