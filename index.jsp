<%@page import="java.io.File"%>
<%@ page language="java" %>
<%@ page import="org.w3c.dom.*" %>
<%@ page import="javax.xml.parsers.DocumentBuilder" %>
<%@ page import="javax.xml.parsers.DocumentBuilderFactory" %>



<html>
 <head><title>Upload page</title></head>
 <body>
 <form action="UploadImage" method="post" enctype="multipart/form-data" name="form1" id="form">
   <center>
   <table border="2">
        <tr>
            <td align="center"><b>Image Upload</td>
        </tr>
        <tr>
            <td>
                Specify file: <input name="file" type="file" id="file">
            <td>
	</tr>
        
        <tr>
            <td>
                Caption: <input name="text" type="text" id="caption" value="caption">
            <td>
	</tr>
	   
	<tr>
            <td align="center">
                <input type="submit" name="Submit" value="Submit files"/>
            </td>
        </tr>

        
    </table>
	<center>           
 </form>
     
     
<% File imageDir = new File(config.getServletContext().getRealPath("/")+"uploadedFiles");

    
    for(File imageFile : imageDir.listFiles()) {         
        String imageFileName = "uploadedFiles/" + imageFile.getName();
        String[] splits = imageFileName.split("\\.");
        if(!splits[splits.length-1].equals("xml")) {
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db =dbf.newDocumentBuilder();
            Document doc=db.parse("/usr/share/tomcat6/webapps/WebApplication3/uploadedFiles/"+imageFile.getName()+".xml");
                NodeList nl = doc.getElementsByTagName("image");
                NodeList nameNlc=doc.getElementsByTagName("caption");
                Element nameElements=(Element)nameNlc.item(0);
                String nameTagValue=nameElements.getChildNodes().item(0).getNodeValue();
               
             %>
            <img src="<%= imageFileName %>"  width="400">
            <p><%=nameTagValue%></p>
            
            <form action="DeleteImage" method="post" name="form1" id="form1">
                <input name="filename" type="hidden" value="<%= imageFileName %>" />
                <span><input type="submit" name="Submit" value="DeleteImage"/></span>
            </form>
                
            <form action="Edit" method="post" name="form1" id="form2">
                <input name="filename" type="hidden" value="<%= imageFileName %>" />
                <input name="text" type="text" id="caption" value="Edit"/>
                <span><input type="submit" name="Edit" value="Edit"/></span>
            </form>    
                
                
            <%
            
        
        }
    }                   

%>
 </body>
 </html>