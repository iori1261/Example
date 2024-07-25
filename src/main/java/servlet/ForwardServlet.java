package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/ForwardServlet")
public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ForwardServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*RequestDispatcherは指定した行先に自動で移動する。
		 * 以下2行でフォワードする際は("WEB-INF/jsp/forward.jsp")の部分
		 * のみを変更するだけ
		 */
		RequestDispatcher dispatcher = request.getRequestDispatcher ("WEB-INF/jsp/forward.jsp");
		dispatcher.forward(request, response);
	}
	/*JSP→画面周りが得意
	 * Servlet→内部周りが得意
	 */
}
