package com.company.catalog.view;

import com.company.catalog.controller.Controller;
import com.company.catalog.controller.exception.ControllerException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ControllerException {
	    System.out.println("Choose action (write a number)\n1. Add news\n2. Search news");
        Scanner scan = new Scanner(System.in);
        String request = scan.nextLine();
        Controller controller = new Controller();
        switch (request.charAt(0)){
            case '1':
                System.out.println("What you want to add (write a word): album(a), movie(m) or book(b)");
                request = scan.nextLine();
                if (request.equals("")){
                    System.out.println("We can't execute this command");
                    return;
                }
                char s = request.charAt(0);
                if ((s!='a') && (s!='m') && (s!='b')){
                    System.out.println("Write correctly");
                    return;
                }
                request += addStaticInfo();
                switch (s){
                    case 'a':
                        request += addInfo("author") + addInfo("duration");
                        break;
                    case 'm':
                        request += addInfo("director") + addInfo("duration");
                        break;
                    case 'b':
                        request += addInfo("author") + addInfo("publisher");
                        break;
                }
                System.out.println(controller.executeTask("1",request));
                break;
            case '2':
                try {
                    System.out.println("Choose criterion ( write date or genre or country)");
                    request = scan.nextLine() + " ";
                    if (!(request.equals("date ")) && !(request.equals("genre ")) && !(request.equals("country "))){
                        throw new ControllerException();
                    }
                    System.out.println("Write data");
                    String request2 = scan.nextLine();
                    if (request2.equals("")) {
                        throw new ControllerException();
                    }
                    System.out.println(controller.executeTask("2", request+request2));
                    break;
                }
                catch (ControllerException e){
                    System.out.println("Incorrect criterion");
                }
            default:
                System.out.println("We can't execute this command");
        }
    }

    private static String addStaticInfo(){
        String result = "";
        result = addInfo("date") + addInfo("genre") + addInfo("description") + addInfo("country");
        return result;
    }

    private static String addInfo(String criterion){
        Scanner scan = new Scanner(System.in);
        System.out.println("Write a " + criterion);
        String result="";
        result += "\"" + scan.nextLine();
        if (!result.equals("\"")) {
            return result;
        }
        else{
            return "\" ";
        }
    }
}
