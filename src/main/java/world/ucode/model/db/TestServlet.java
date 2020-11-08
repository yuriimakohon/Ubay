package world.ucode.model.db;


import world.ucode.model.db.dao.DAO;
import world.ucode.model.db.dao.DAObid;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Bid;
import world.ucode.model.db.entetis.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/db")
public class TestServlet extends HttpServlet {
    private DAObid bid;

    public void init() {
        bid = new DAObid();
        System.out.println("init serv");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println("action");

        try {
            switch (action) {
//                case "/new":
//                    showNewForm(request, response);
//                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
//                case "/delete":
//                    deleteUser(request, response);
//                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
//                case "/update":
//                    updateUser(request, response);
//                    break;
                default:
                    showEditForm(request, response);
//                    insertUser(request, response);
//                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("get list1");
//        List<Users> listUser = users.getAllUser();
//        System.out.println(listUser);
        System.out.println("get list2");
//        request.setAttribute("listUser", listUser);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
//        dispatcher.forward(request, response);
        System.out.println("get list3");
        System.out.println();
    }

//    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
//        dispatcher.forward(request, response);
//    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));

//        Users existingUser = users.read(61515);
        System.out.println("get");
//        System.out.println(existingUser.getusertname());

        List<Bid> mist = bid.readbyToken("tnnjoken3");
        for(Bid element : mist) {
            System.out.println(element.getBidderId());
        }
        System.out.println("end");
//        System.out.println(existingUser.toString());
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
//        request.setAttribute("user", existingUser);
//        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = "first";

        Users newUser = new Users(name, "test_token");
//        users.saveUser(newUser);
//        response.sendRedirect("list");
        System.out.println("insert");
    }

//    private void updateUser(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String country = request.getParameter("country");
//
//        User user = new User(id, name, email, country);
//        userDao.updateUser(user);
//        response.sendRedirect("list");
//    }
//
//    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        userDao.deleteUser(id);
//        response.sendRedirect("list");
//    }
}
