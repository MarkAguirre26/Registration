package virtual.software.registration;

public class Endpoint {

    private static String baseuRL = "http://192.168.254.108";
    public static String POST_USER = baseuRL + "/bio/Controller.php";
    public static String LOGIN = baseuRL + "/bio/Controller.php";
    public static String GET_USERS = baseuRL + "/bio/Controller.php?usersList";
    public static String GET_USER_PHOTO = baseuRL + "/bio/uploads/";
    public static String POST_UPLOAD_IMAGE = baseuRL + "/bio/upload.php";

}
