package virtual.software.registration;

import android.graphics.Bitmap;

import org.json.JSONObject;

public class TemporaryData {

    public static ModelPerson modelPerson;
    public static Bitmap bitmap;
    public  static  boolean isSaved;
    public  static  boolean isFromLogin;

    public static String encodedImage;
    public static String saveTag;
    public static JSONObject jsonObject = new JSONObject();
}
