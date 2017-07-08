package adnyey.i2itian;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Mahajan-PC on 01-05-2017.
 */

public class DetectConnection {
    public static boolean checkInternetConnection(Context context) {

        ConnectivityManager con_manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (con_manager.getActiveNetworkInfo() != null
                && con_manager.getActiveNetworkInfo().isAvailable()
                && con_manager.getActiveNetworkInfo().isConnected()) {
            //test
            return true;
        } else {
            return false;
        }
    }
}