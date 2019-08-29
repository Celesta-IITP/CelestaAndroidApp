package in.org.celesta.iitp.utils;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.AlarmClock;
import android.provider.ContactsContract;
import android.provider.MediaStore;

import androidx.annotation.Nullable;

public class IntentUtils {

    public static boolean writeSMS(Context context, String targetNumber, String message) {
        Uri uri = Uri.parse("smsto:" + targetNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", message);
        return safeOpenIntent(context, intent);
    }

    public static boolean openWebBrowser(Context context, String url) {
        Uri page = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, page);
        return safeOpenIntent(context, intent);
    }

    /**
     * @param settingsAction one of :
     *                       Settings.ACTION_SETTINGS;
     *                       Settings.ACTION_WIRELESS_SETTINGS;
     *                       Settings.ACTION_AIRPLANE_MODE_SETTINGS;
     *                       Settings.ACTION_WIFI_SETTINGS;
     *                       Settings.ACTION_APN_SETTINGS;
     *                       Settings.ACTION_BLUETOOTH_SETTINGS;
     *                       Settings.ACTION_DATE_SETTINGS;
     *                       Settings.ACTION_LOCALE_SETTINGS;
     *                       Settings.ACTION_INPUT_METHOD_SETTINGS;
     *                       Settings.ACTION_DISPLAY_SETTINGS;
     *                       Settings.ACTION_SECURITY_SETTINGS;
     *                       Settings.ACTION_LOCATION_SOURCE_SETTINGS;
     *                       Settings.ACTION_INTERNAL_STORAGE_SETTINGS;
     *                       Settings.ACTION_MEMORY_CARD_SETTINGS;
     * @return false if no activity able to do is found
     */
    public static boolean openSettingsPage(Context context, String settingsAction) {
        Intent intent = new Intent(settingsAction);
        return safeOpenIntent(context, intent);
    }

    public static boolean writeMail(Context context, String subject, String... recipientEmails) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, recipientEmails);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        return safeOpenIntent(context, intent);
    }

    public static boolean writeMail(Context context, String subject, Uri attachment, String... recipientEmails) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, recipientEmails);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_STREAM, attachment);
        return safeOpenIntent(context, intent);
    }

    public static boolean pickContactPhoneNumber(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);

        return safeOpenForResultIntent(activity, intent, requestCode);
    }

    public static boolean pickContact(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

        return safeOpenForResultIntent(activity, intent, requestCode);
    }

    public static boolean pickPhoto(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        return safeOpenForResultIntent(activity, intent, requestCode);
    }

    public static boolean pickPhotos(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

        return safeOpenForResultIntent(activity, intent, requestCode);
    }

    public static boolean createNewAlarm(Context context, String message, long hour, long minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);

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

    /**
     * This need permission     <uses-permission android:name="android.permission.CAMERA"/>
     */
    public static boolean takePhotoAndReturnBitmap(Activity activity, int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        return safeOpenForResultIntent(activity, intent, requestCode);
    }

    public static boolean takePhotoAndSaveAtPath(Activity activity, int requestCode, Uri destinationUri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, destinationUri);
        return safeOpenForResultIntent(activity, intent, requestCode);
    }

    /**
     * This need permission     <uses-permission android:name="android.permission.CAMERA"/>
     */
    public static boolean takePhotos(Context context) {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        return safeOpenIntent(context, intent);
    }

    public static boolean takeVideoAndReturnPath(Activity activity, int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        return safeOpenForResultIntent(activity, intent, requestCode);
    }

    public static boolean takeVideos(Context context) {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_VIDEO_CAMERA);
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
