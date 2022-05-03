package controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.LoginVO;
import domain.MyPageDTO;
import service.MyPageService;
import service.MyPageServiceImpl;

/**
 * Servlet implementation class MyModiController
 */
@WebServlet("/MyModi")
public class MyModiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyModiController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MyPageDTO dto = new MyPageDTO();
		dto.setUid((String)session.getAttribute("sessId"));
		
		MyPageService service = new MyPageServiceImpl();
		
		LoginVO vo = service.read(dto);
		
		request.setAttribute("vo", vo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/mymodi.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//포스트 방식 한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		
		//파라미터 다 있는 곳에서 들고 오기
		LoginVO vo = new LoginVO();
		
		//넘어오면서 바로 값을 주는 것.
		//uid는 히든 값에 있음. <form hidden>
		vo.setUid(request.getParameter("uid"));
		vo.setUname(request.getParameter("uname"));
		vo.setSchoolname(request.getParameter("schoolname"));
		vo.setGradeclass(request.getParameter("gradeclass"));
		vo.setRoute(request.getParameter("route"));
		vo.setBoardingplace(request.getParameter("boardingplace"));
		
		MyPageServiceImpl service = new MyPageServiceImpl();
		//update한다 vo값을
		service.update(vo);
		
		//.jsp는 뷰 페이지, 이동할 때는 확장자 없음
		response.sendRedirect("Mypage");
	}

}
