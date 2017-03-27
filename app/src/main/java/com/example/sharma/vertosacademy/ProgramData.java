package com.example.sharma.vertosacademy;

import com.loopeer.cardstack.StackAdapter;

/**
 * Created by sharma on 3/3/2017.
 */

public class ProgramData {
    //* Strings for programlist activity*//
    public String Name;
    public String ImageUrl;
    public String Id;

    //*Strings for Subjeclist fragment*//
    public String subject_name;

    //* Strings for topiclist fragment*//
    public String subject_id;
    public String topic_name;
    public String topic_description;

    //* Strings for searchlist fragment
    public String search_title;
    public String search_url;
    public String search_description;

    //*string for search fragment Autocomplete
    public String autocomplete_title;
    public String autocomplete_Id;

    // public  Integer[] TEST_DATAS ;


    //* String for gridview on main page*//
    public String menu_name;


    public static String URL = "https://vertoacademy.000webhostapp.com";
    //public static String URL ="http://192.168.88.44";

    //LoginPage URL
    public static final String URL_Login = URL + "/login.php";

    //Programlist file URLS
    public static final String DATA_URL = URL + "/department_master.php";

    public static final String DATA_URL_LIST = URL + "/getprograms.php?dept_id=";

    //SignUp activity URLS
    public static final String URL_POST = URL + "/Signup.php";
    public static final String URL_POST_type_USER_GET = URL + "/getusertype.php";

    //Subjectlist fragment URL
    public static final String DATA_SUBJECT_URL_LIST = URL + "/getSub.php?program_id=";

    //Content fragment URL
    public static final String DATA_CONTENT_URL_LIST = URL + "/getcontent.php";

    //Search fragment URL
    public static final String DATA_SEARCH_URL_LIST = URL + "/Search.php?title=";


    // public static final String  URL_POST_type_USER_POST = "https://vertoacademy.000webhostapp.com/Saveusertype.php";
}
