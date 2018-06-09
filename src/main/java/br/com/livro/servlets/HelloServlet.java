package br.com.livro.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        String name =  req.getParameter("name");
        String surname = req.getParameter("surname");

        resp.getWriter().print("Hey boy, your name is " + name + " and your surname"
        + " is: " + surname);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        res.getWriter().print("Your name is: " + name + " " + surname);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {

        resp.getWriter().print("Hi Put");

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {

        resp.getWriter().print("Hi Delete");
    }

}
