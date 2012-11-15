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
import java.util.List;
import java.util.Iterator;
import java.io.File;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.*;
import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author user
 */
public class UploadImage extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {
            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                        items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                        e.printStackTrace();
                }
                Iterator itr = items.iterator();
                while (itr.hasNext()) {
                    FileItem item = (FileItem) itr.next();
                    if (item.isFormField()) {
                    } else {
                            try {
                                    
                                    String itemName = item.getName();
                                    
                                    File savedFile = new File(getServletContext().getRealPath("/")+"uploadedFiles/"+itemName);
                                    item.write(savedFile); 
                                    //out.println("<tr><td><b>Your file has been saved at the loaction:</b></td></tr><tr><td><b>"+getServletContext().getRealPath("/")+"uploadedFiles"+"\\"+itemName+"</td></tr>");
                                    
                                    FileWriter fstream  = new FileWriter(getServletContext().getRealPath("/")+"uploadedFiles/"+item.getName() + ".xml");
                                    item = (FileItem) itr.next();
                                    BufferedWriter file = new BufferedWriter(fstream);
                                    String caption = item.getString();
                                    
                                    response.sendRedirect("index.jsp");
                                    file.write("<?xml version=\"1.0\"?>");
                                    file.write("<image>");
                                    file.write("<caption>"+caption+"</caption>");                                    
                                    file.write("<size>400</size>");
                                    file.write("</image>");
                                    
                                    file.close();
                                    fstream.flush();
                                    fstream.close();
                             
                                   //response.sendRedirect("index.jsp");
                            } catch (Exception e) {
                                    e.printStackTrace();
                            }
                    }
                }
           }

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
