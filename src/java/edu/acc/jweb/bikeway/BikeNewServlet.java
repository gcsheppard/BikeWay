package edu.acc.jweb.bikeway;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/new"})
public class BikeNewServlet extends HttpServlet {
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/new.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String model = request.getParameter("model");
        String manufacturer = request.getParameter("manufacturer");
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        
        Bike bike = new Bike(model, manufacturer, name, type);
        BikeManager bikeManager = (BikeManager) getServletContext().getAttribute("bikeManager");
        HashMap<String,String> errors = bikeManager.validBike(bike, "new");
        
        if (errors.isEmpty()) {
            bikeManager.addBike(model, manufacturer, name, type);
            response.sendRedirect("/BikeWay/bikes"); 
        }
        else {
            request.setAttribute("bike", bike);
            request.setAttribute("errors", errors);
            getServletContext().getRequestDispatcher("/WEB-INF/views/new.jsp").forward(request, response);
        }
    }              
}