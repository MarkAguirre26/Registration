package virtual.software.registration;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.core.app.ActivityCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    @SuppressLint("HardwareIds")
    public static String getDeviceIMEI(Activity activity) {

        String deviceUniqueIdentifier = null;
        TelephonyManager tm = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
        if (null != tm) {
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
            else
                deviceUniqueIdentifier = tm.getDeviceId();
            if (null == deviceUniqueIdentifier || 0 == deviceUniqueIdentifier.length())
                deviceUniqueIdentifier = "0";
        }
        return deviceUniqueIdentifier;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }




}
