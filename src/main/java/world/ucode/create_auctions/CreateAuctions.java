package world.ucode.create_auctions;

import jdk.incubator.jpackage.internal.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/create_auctions")
public class CreateAuctions extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/create_auctions.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hello post request :)");
        resp.setStatus(201);
        resp.setContentType("text/plain");
        String s = "Hello World!";
        InputStream is =  new ByteArrayInputStream(s.getBytes());
        OutputStream os = resp.getOutputStream();
        byte[] buff = new byte[1024];
        int sizeBuff = 0;

        while ((sizeBuff = is.read(buff)) != -1) {
            os.write(buff, 0, sizeBuff);
        }
    }
}
