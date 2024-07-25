package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UranaiServlet
 */
@WebServlet("/UranaiServlet")
public class UranaiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UranaiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] luckArray = { "超スッキリ", "スッキリ", "最悪"};
		int index = (int) (Math.random() * 3);
		String luck = luckArray [index];

		// 実行日を取得

		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
		String today = sdf.format(date);

		// HTMLを出力

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"ja\">");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\" />");
		out.println("<title>スッキリ占い</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>" + today + "の運勢は「" + luck + "」です</p>");
		out.println("</body>");
		out.println("</html>");
	}

}
