package edu.acc.jweb.bikeway;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"", "/bikes"})
public class BikeWayServlet extends HttpServlet {

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<Bike> bikes = null;
        BikeManager bikeManager = (BikeManager) getServletContext().getAttribute("bikeManager");
        bikes = bikeManager.getBikes();
        request.setAttribute("bikes", bikes);
        getServletContext().getRequestDispatcher("/WEB-INF/views/bikes.jsp").forward(request, response);
    }
}
