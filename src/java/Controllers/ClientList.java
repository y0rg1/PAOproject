package Controllers;

import Models.Client;
import Models.ClientDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientList extends HttpServlet {

    private ClientDAO clientDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        clientDAO = new ClientDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            List<Client> clientList = clientDAO.getClientList();
     
            request.setAttribute("clientList",clientList);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("clients.jsp");
            dispatcher.forward(request,response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
            
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
