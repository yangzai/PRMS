package sg.edu.nus.iss.phoenix.core.controller;

public class FCUtilities {
	//Get userId from the servlet pathInfo    
    public static String stripPath(String pathInfo){
        int pos = pathInfo.indexOf("/");
        int len = pathInfo.length();
        String userId = pathInfo.substring(pos+1,len);
        System.out.println("Path: " + userId);
        return userId;
    }
}
