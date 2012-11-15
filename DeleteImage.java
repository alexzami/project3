/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 *
 * @author user
 */
public class DeleteImage extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
 
            String fileName = "/usr/share/tomcat6/webapps/WebApplication3/" + request.getParameter("filename");
            String fileNameXml = "/usr/share/tomcat6/webapps/WebApplication3/" + request.getParameter("filename")+".xml";

            // A File object to represent the filename
            File f = new File(fileName);
            File f2 = new File(fileNameXml);
                    
            // Make sure the file or directory exists and isn't write protected
            if (!f.exists()){
              throw new IllegalArgumentException("Delete: no such file or directory: " + fileName);
            }
            
            if (!f.canWrite()){
              throw new IllegalArgumentException("Delete: write protected: " + fileName);
            }
            
            // If it is a directory, make sure it is empty
            if (f.isDirectory()) {
              String[] files = f.list();
              if (files.length > 0){
                throw new IllegalArgumentException("Delete: directory not empty: " + fileName);
              }
            }

            // Attempt to delete it
            boolean success = f.delete();

            if (!success){
              throw new IllegalArgumentException("Delete: deletion failed");
            }
            
             // Make sure the file or directory exists and isn't write protected
            if (!f2.exists()){
              throw new IllegalArgumentException("Delete: no such file or directory: " + fileNameXml);
            }
            
            if (!f2.canWrite()){
              throw new IllegalArgumentException("Delete: write protected: " + fileNameXml);
            }
            
            // If it is a directory, make sure it is empty
            if (f2.isDirectory()) {
              String[] files2 = f2.list();
              if (files2.length > 0){
                throw new IllegalArgumentException("Delete: directory not empty: " + fileNameXml);
              }
            }

            // Attempt to delete it
            boolean success2 = f2.delete();

            if (!success2){
              throw new IllegalArgumentException("Delete: deletion failed");
            }
            
            response.sendRedirect("index.jsp");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
