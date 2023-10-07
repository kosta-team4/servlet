package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Member;
import service.MemberService;
import service.MemberServiceImpl;

/**
 * Servlet implementation class ChangeInfo
 */
@WebServlet("/changeinfo")
public class ChangeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("changeinfo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		
		if(member==null) {
			request.setAttribute("err", "로그인이 되지 않았습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
		if(!(request.getParameter("password1").equals(request.getParameter("password2")))) {
			request.setAttribute("err", "비밀번호가 일치하지 않습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
		member.setNickname(request.getParameter("nickname"));
		member.setPassword(request.getParameter("password1"));

		try {
			MemberService memberService = new MemberServiceImpl();
			memberService.memberchange(member);
			session.setAttribute("member", member);
			request.getRequestDispatcher("mypage").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

}
