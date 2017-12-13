package todopkg.todo;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class toDo {
    String toDoTask;
    String toDoDetails;
    boolean toDoCompleted;
    String toDoDeadline;
    
    public toDo(){
        
    }
    public toDo(String task, String details, boolean completedTask, String deadline){
        toDoTask = task;
        toDoDetails = details;
        toDoCompleted = completedTask;
        toDoDeadline = deadline;
    }
    
    public String getToDoTask(){
        return toDoTask;
    }
    
    public String getToDoDetails(){
        return toDoDetails;
    }
    
    public boolean getToDoCompleted(){
        return toDoCompleted;
    }
    
    public String getToDoDeadline(){
        return toDoDeadline;
    }
    
    public boolean overdue(){
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date formatDeadline;
        Date today = new Date();
        today.setHours(0);
        boolean result = false;
    
        try{
            formatDeadline = format.parse(toDoDeadline);
            if (today.compareTo(formatDeadline)>0){
                result = true;
            } else {
                result = false;
            }
        }catch(ParseException e){
            System.out.println("deadline date error");
        }
        return result;
    }
    
    public void setToDoTask(String task){
        toDoTask = task;
    }
    
    public void setToDoDetails(String details){
        toDoDetails = details;
    }
    
    public void setToDoCompleted(boolean completedTask){
        toDoCompleted = completedTask;
    }
    
    public void setToDoDeadline(String deadline){
        toDoDeadline = deadline;
    }
  
    public String toString(boolean showDetails, boolean highlightDeadline) {
        final String beginSpan = "<span style=\"background-color:yellow;\">";
        final String endSpan = "</span>";
        return "task: " + toDoTask + "<br>" + ((showDetails)? (" task details: " + toDoDetails + "<br>") : ("")) +
        " completed: " + toDoCompleted + "<br>" + (highlightDeadline ? (beginSpan) : ("")) + " deadline: " + toDoDeadline + (highlightDeadline ? (endSpan) : ("")) + "<br>";
    }
}

