package todopkg.todolist;
import todopkg.todo.*;
import java.util.*;

public class toDoList{
    ArrayList<toDo> myToDoList = new ArrayList<toDo>();
    
    public toDoList(){
        
    }

    public toDoList(String task, String details, boolean completedTask, String deadline){
        myToDoList.add(new toDo(task, details,completedTask, deadline));
    }
    
    public Integer lengthOfToDo(){
        return myToDoList.size();
    }
    
    public void addTask(String task, String details, boolean completedTask, String deadline){
        myToDoList.add(new toDo(task, details,completedTask, deadline));
    }
    
    public void add(toDo itemToAdd){
        myToDoList.add(itemToAdd);
    }
    
    public toDo getToDo(int i){
        return myToDoList.get(i);
    }
    
    public String showList(){
        String stringList = "";
        for(int i=0; i < myToDoList.size(); i++){
            stringList += "<tr><td>" + i + ": " + myToDoList.get(i) + "<input type=button name=deleteButton value=i></tr></td>";
        }
        return stringList;
    }
    
    public void deleteToDo(int i){
        myToDoList.remove(i);
    }
    
    
    public static void main(String[] args) {
        toDo myToDo = new toDo("run", "run the opposite direction", true, "11/25/2017");
        toDo myOtherToDo = new toDo();
        myOtherToDo.setToDoDeadline("hi dad");
        toDoList myToDoList = new toDoList("run", "run the opposite direction", true, "11/25/2017");
        myToDoList.addTask("go to store", "get canned soup", false, "11/22/2017");
        myToDoList.addTask("walk the dog", "don't forget bags", true, "7/03/2018");
        myToDoList.addTask("repair the counter", " ", false, "8/30/2018");
        myToDoList.addTask("shop for plates", "not on a sunday", true, "1/31/2018");
        // System.out.println(myToDo.toString());
        System.out.println(myToDoList.showList());
        myToDoList.deleteToDo(3);
        System.out.println(myToDoList.showList());
        myToDoList.getToDo(1).overdue();
        System.out.println("size: " + myToDoList.lengthOfToDo());
        
        
    }
}
