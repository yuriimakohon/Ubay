package world.ucode.model.db;


import org.hibernate.Hibernate;
import world.ucode.model.db.dao.DAO;
import world.ucode.model.db.dao.DAObid;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Bid;
import world.ucode.model.db.entetis.Lot;
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
//    private DAObid bid;
    private DAOusers user;

    public void init() {
//        bid = new DAObid();
        user = new DAOusers();
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


        System.out.println("get");
//        System.out.println(existingUser.getusertname());

        Users existingUser = user.getUserandBidbyId(12345);


        List<Bid> mist = existingUser.getUserbids();
        for(Bid element : mist) {
            System.out.println(element.getBidderId());
        }

//        List<Bid> mist = bid.readbyToken("tnnjoken3");
//        for(Bid element : mist) {
//            System.out.println(element.getBidderId());
//        }
        System.out.println("endess***********************************************");

        Users existingUser3 = user.getUserandBidbyToken("token2");

        List<Bid> mist3 = existingUser3.getUserbids();
        for(Bid element : mist3) {
            System.out.println(element.getBidderId());
        }

        System.out.println("endess3***********************************************");

        Users existingUser4 = user.getUserandLotbyToken("token2");

        List<Lot> mist4 = existingUser4.getUserLots();
        for(Lot element : mist4) {
            System.out.println(element.getSellerId());
        }

        System.out.println("endess4***********************************************");

        Users existingUser5 = user.getUserandLotbyId(123456);

        List<Lot> mist5 = existingUser5.getUserLots();
        for(Lot element : mist5) {
            System.out.println(element.getSellerId());
        }

        System.out.println("endess5***********************************************");







//        System.out.println(existingUser.toString());
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
//        request.setAttribute("user", existingUser);
//        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = "first";

        Users newUser = new Users(name, "test_token", "pass", 1);
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
