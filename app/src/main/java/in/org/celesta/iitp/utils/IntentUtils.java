package in.org.celesta.iitp.utils;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.Nullable;

public class IntentUtils {

    public static boolean openWebBrowser(Context context, String url) {
        Uri page = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, page);
        return safeOpenIntent(context, intent);
    }

    public static boolean writeMail(Context context, String subject, String... recipientEmails) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, recipientEmails);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        return safeOpenIntent(context, intent);
    }

    public static boolean openMap(Context context, String lat, String lng, @Nullable Float zoom) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        StringBuilder query = new StringBuilder();
        query.append(lat).append(",").append(lng);

        if (zoom != null) {
            query.append("?z=").append(zoom);
        }
        Uri geoLocation = Uri.parse("geo:" + query.toString());
        intent.setData(geoLocation);
        return safeOpenIntent(context, intent);
    }

    public static boolean openMap(Context context, String lat, String lng, boolean showLocationPin) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (showLocationPin) {
            Uri geoLocation = Uri.parse("geo:0,0?q=" + lat + "," + lng);
            intent.setData(geoLocation);
            return safeOpenIntent(context, intent);
        } else {
            return openMap(context, lat, lng, null);
        }

    }

    public static boolean openMapAtAddress(Context context, String address) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String encodedAddress = Uri.encode(address);
        Uri geoLocation = Uri.parse("geo:0,0?q=" + encodedAddress);
        intent.setData(geoLocation);
        return safeOpenIntent(context, intent);
    }

    public static boolean openWebSearch(Context context, String query) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, query);
        return safeOpenIntent(context, intent);
    }

    private static boolean safeOpenForResultIntent(Activity activity, Intent intent, int requestCode) {
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(intent, requestCode);
            return true;
        } else {
            return false;
        }
    }

    private static boolean safeOpenIntent(Context context, Intent intent) {
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
            return true;
        } else {
            return false;
        }
    }
}
