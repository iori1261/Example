package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.RegisterUserLogic;
import model.User;

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォワード先
        String forwardPath = null;

        // サーブレットクラスの動作を決定する「action」の値を
        // リクエストパラメータから取得
        String action = request.getParameter("action");

        // 登録の開始をリクエストされたときの処理
        if (action == null) {
            // フォワード先を設定
            forwardPath = "WEB-INF/jsp/registerForm.jsp";
        }
        // 登録確認画面から登録実行をリクエストされたときの処理
        else if (action.equals("done")) {
            // セッションスコープに保存された登録ユーザを取得
            HttpSession session = request.getSession();
            User registerUser = (User) session.getAttribute("registerUser");

            // 登録処理の呼び出し
            RegisterUserLogic logic = new RegisterUserLogic();
            logic.execute(registerUser);

            // 不要となったセッションスコープ内のインスタンスを削除
            session.removeAttribute("registerUser");

            // 登録後のフォワード先を設定
            forwardPath = "WEB-INF/jsp/registerDone.jsp";
        }

        // フォワード先にフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータの取得
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        // 登録するユーザの情報を設定
        User registerUser = new User(id, name, pass);

        // セッションスコープに登録ユーザを保存
        HttpSession session = request.getSession();
        session.setAttribute("registerUser", registerUser);

        // フォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerConfirm.jsp");
        dispatcher.forward(request, response);
    }
}
