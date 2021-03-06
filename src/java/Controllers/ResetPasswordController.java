package Controllers;

import Models.ClientDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResetPasswordController extends HttpServlet {

    private ClientDAO clientDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        clientDAO = new ClientDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String securityQuestion = request.getParameter("securityQuestion");
            String securityAnswer = request.getParameter("securityAnswer");
            String password = request.getParameter("password");
            boolean success = clientDAO.resetPassword(username, securityQuestion, securityAnswer, password);

            if (success) {
                response.sendRedirect("index.html");
            } else {
                throw new ServletException("Invalid input");
            }

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

}
