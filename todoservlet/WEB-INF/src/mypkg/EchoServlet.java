// To save as "<CATALINA_HOME>\webapps\hello\WEB-INF\src\mypkg\EchoServlet.java"
package mypkg;
import todopkg.todolist.*;
import todopkg.todo.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class EchoServlet extends HttpServlet {
    static toDoList myToDoList = new toDoList();
    Integer toDoToExpand = -1;
    final String deleteValue = "delete";
    final String showDetailsValue = "show";
    
public EchoServlet(){}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
                   throws IOException, ServletException {
        // Set the response message's MIME type
        response.setContentType("text/html; charset=UTF-8");
        // Allocate a output writer to write the response message into the network socket
        PrintWriter out = response.getWriter();
          
        //create new todo object from input
        toDo myToDo = new toDo();

        // Write the response message, in an HTML page
        //Recieve parameters needed to create a toDo item and set them as the toDo item
        try {
            //provide the for index a task to delete show details
            String rowIndex = request.getParameter("rowIndex");
            String act = request.getParameter("act");
           if (Objects.equals(act, deleteValue)){
                int i = Integer.parseInt(rowIndex);
                myToDoList.deleteToDo(i);
            } else if (Objects.equals(act, showDetailsValue)){
                toDoToExpand = Integer.parseInt(rowIndex);
            } else {
                // Retrieve the value of the query parameter "task" (from text field)
                String task = request.getParameter("task");
                // Get null if the parameter is missing from query string.
                // Get empty string or string of white spaces if user did not fill in
                if (task == null
                    || (task = htmlFilter(task.trim())).length() == 0) {
                    out.println("<p>Name: MISSING</p>");
                } else {
                    myToDo.setToDoTask(task);
                }
         
                // Retrieve the value of the query parameter "moreDetails" (from text area)
                String details = request.getParameter("moreDetails");
                // Get null if the parameter is missing from query string.
                if (details == null
                    || (details = htmlFilter(details.trim())).length() == 0
                    || details.equals("Enter task details...")) {
                    out.println("<p>Details: NONE</p>");
                } else {
                    myToDo.setToDoDetails(details);
                }

                // Retrieve the value of the query parameter "completed" (from checkboxes).
                boolean completedTask = Boolean.parseBoolean(request.getParameter("completed"));
                
                myToDo.setToDoCompleted(completedTask);

                // Retrieve the value of the query parameter "deadline" (from text field)
                String deadline = request.getParameter("deadline");
                // Get null if the parameter is missing from query string.
                if (deadline == "") {
                    out.println("<p>Deadline: MISSING</p>");
                } else {
                    myToDo.setToDoDeadline(deadline);
                }
                myToDoList.add(myToDo);
            }
            doGet(request, response);
        } finally {
            out.close();  // Always close the output writer
        }
    }

@Override
public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws IOException, ServletException {
    response.setContentType("text/html; charset=UTF-8");
    // Allocate a output writer to write the response message into the network socket
    PrintWriter out = response.getWriter();
    boolean showDetails = false;
    try {
        out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>ToDo List</title></head>");
        out.println("<body><h2>ToDo List</h2>");
        out.println( "<table>");
        // print out ToDo list, one ToDo item per table cell
        for (Integer i = 0; i < myToDoList.lengthOfToDo(); i++) {
            out.println("<tr><td>");
            toDo currentToDo = myToDoList.getToDo(i);
            if (toDoToExpand == i){
                showDetails = true;
                toDoToExpand = -1;
            } else{
                showDetails = false;
            }
            out.println(currentToDo.toString(showDetails, currentToDo.overdue()));
            out.println("<form action=\"");
            out.println("\" ");
            out.println("echo");
            out.println("method=POST>");
            //print out delete and show details buttons
            Integer currenti = i;
            String stringi = currenti.toString();
            out.println("<input type=hidden name=\"rowIndex\" value=" + currenti.toString()+">");
            out.println("<input type=submit name=act value=" + deleteValue +">");
            out.println("<input type=submit name=act value=" + showDetailsValue + ">");
            out.println("</form>");
            out.println("</tr></td>");
            out.println( "</table>");
            out.println("<br>");
        }
        out.println("<a href='form_input.html'>Add ToDo task</a>");
        out.println("</body></html>");
    } finally{
        out.close();
    }
}

   // Filter the string for special HTML characters to prevent
   // command injection attack
   private static String htmlFilter(String message) {
      if (message == null) return null;
      int len = message.length();
      StringBuffer result = new StringBuffer(len + 20);
      char aChar;
 
      for (int i = 0; i < len; ++i) {
         aChar = message.charAt(i);
         switch (aChar) {
             case '<': result.append("&lt;"); break;
             case '>': result.append("&gt;"); break;
             case '&': result.append("&amp;"); break;
             case '"': result.append("&quot;"); break;
             default: result.append(aChar);
         }
      }
      return (result.toString());
   }
}
