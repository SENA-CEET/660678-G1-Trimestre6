<%-- 
    Document   : newjsp1
    Created on : 25/05/2015, 10:18:23 PM
    Author     : Enrique
--%>

<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        newjsp1.jsp<br>
        <%

            session.setAttribute("a", "objeto1 session");
            application.setAttribute("a", "objeto2 aplicacion");
            pageContext.setAttribute("a", "objeto3 pagina");
            request.setAttribute("a", "objeto 4 requesto");

            pageContext.setAttribute("b", "objeto5 pagina",pageContext.PAGE_SCOPE);
            pageContext.setAttribute("b", "objeto6 pagina",pageContext.REQUEST_SCOPE);
            pageContext.setAttribute("b", "objeto7 pagina",pageContext.SESSION_SCOPE);
            pageContext.setAttribute("b", "objeto8 pagina",pageContext.APPLICATION_SCOPE);
            
            Enumeration listadoObjetosSession = session.getAttributeNames();
            Enumeration listadoObjetosRequest = request.getAttributeNames();
            Enumeration listadoObjetosAplicacion = application.getAttributeNames();
                        

            Enumeration listadoObjetosPaginaPagina = pageContext.getAttributeNamesInScope(pageContext.PAGE_SCOPE);
            Enumeration listadoObjetosPaginaRequest = pageContext.getAttributeNamesInScope(pageContext.REQUEST_SCOPE);
            Enumeration listadoObjetosPaginaSession = pageContext.getAttributeNamesInScope(pageContext.SESSION_SCOPE);
            Enumeration listadoObjetosPaginaAplication = pageContext.getAttributeNamesInScope(pageContext.APPLICATION_SCOPE);

            out.println("REQUEST--------------------------------------------------------------</br>");
            while (listadoObjetosRequest.hasMoreElements()) {
                String textoTemp = (String) listadoObjetosRequest.nextElement();
                out.println("Nombre: " + textoTemp + " --- " + request.getAttribute(textoTemp) + "</br>");
            }
            out.println("REQUEST--------------------------------------------------------------</br></br>");
            
            out.println("Session:--------------------------------------------------------------</br>");
            while (listadoObjetosSession.hasMoreElements()) {
                String textoTemp = (String) listadoObjetosSession.nextElement();
                out.println("Nombre: " + textoTemp + " --- " + session.getAttribute(textoTemp) + "</br>");
            }
            out.println("Session:--------------------------------------------------------------</br></br>");

            out.println("Aplication:-----------------------------------------------------------</br>");
            while (listadoObjetosAplicacion.hasMoreElements()) {
                String textoTemp = (String) listadoObjetosAplicacion.nextElement();
                out.println("Nombre: " + textoTemp + " --- " + application.getAttribute(textoTemp) + "</br>");
            }
            out.println("Aplication:-----------------------------------------------------------</br></br>");

            out.println("Contexto PAGINA Ambito PAGINA: -------------------------------------</br>");
            while (listadoObjetosPaginaPagina.hasMoreElements()) {
                String textoTemp = (String) listadoObjetosPaginaPagina.nextElement();
                out.println("Nombre: " + textoTemp + " --- " + pageContext.getAttribute(textoTemp, pageContext.PAGE_SCOPE) + "</br>");
            }
            out.println("Contexto PAGINA Ambito PAGINA: -------------------------------------</br></br>");

            out.println("Contexto PAGINA Ambito REQUEST: -------------------------------------</br>");
            while (listadoObjetosPaginaRequest.hasMoreElements()) {
                String textoTemp = (String) listadoObjetosPaginaRequest.nextElement();
                out.println("Nombre: " + textoTemp + " --- " + pageContext.getAttribute(textoTemp, pageContext.REQUEST_SCOPE) + "</br>");
            }
            out.println("Contexto PAGINA Ambito REQUEST: -------------------------------------</br></br>");

            out.println("Contexto PAGINA Ambito SESSION: -------------------------------------</br>");
            while (listadoObjetosPaginaSession.hasMoreElements()) {
                String textoTemp = (String) listadoObjetosPaginaSession.nextElement();
                out.println("Nombre: " + textoTemp + " --- " + pageContext.getAttribute(textoTemp, pageContext.SESSION_SCOPE) + "</br>");
            }
            out.println("Contexto PAGINA Ambito SESSION -------------------------------------</br></br>");

            out.println("Contexto PAGINA Ambito APLICATION: -------------------------------------</br>");
            while (listadoObjetosPaginaAplication.hasMoreElements()) {
                String textoTemp = (String) listadoObjetosPaginaAplication.nextElement();
                out.println("Nombre: " + textoTemp + " --- " + pageContext.getAttribute(textoTemp, pageContext.APPLICATION_SCOPE) + "</br>");
            }
            out.println("Contexto PAGINA Ambito APLICATION -------------------------------------</br></br>");
            
            //request.getRequestDispatcher("otro_1.jsp").forward(request, response);
            //response.sendRedirect("otro_1.jsp");
            out.flush();
        %>
        <jsp:forward page="otro_1.jsp"/>
        <form action="otro.jsp">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
