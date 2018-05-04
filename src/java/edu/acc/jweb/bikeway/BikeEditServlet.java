package edu.acc.jweb.bikeway;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/edit"})
public class BikeEditServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Integer id = integerFromParameter(request);
        if (id == null) {
            response.sendError(404, "Not Found");
        } else {
            BikeManager bikeManager = (BikeManager) getServletContext().getAttribute("bikeManager");
            Bike bike = bikeManager.findBikeById(id);
            if (bike == null) {
                response.sendError(404, "Not Found");
            }
            else {
                request.setAttribute("bike", bike);
                getServletContext().getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        getServletContext().getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(request, response);
    }      
    
    private Integer integerFromParameter(HttpServletRequest request) {
        String str = request.getParameter("id");
        if (str == null) {
            return null;
        } else if (str.isEmpty()) {
            return null;
        } else try {
            return Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            return null;
        }
    }

}
