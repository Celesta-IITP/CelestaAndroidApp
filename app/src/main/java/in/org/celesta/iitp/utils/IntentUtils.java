package in.org.celesta.iitp.utils;

import android.Manifest;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import in.org.celesta.iitp.R;

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

    private class ContactFragment extends AppCompatActivity implements View.OnClickListener {
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_contact_us);

            ImageButton b1 = findViewById(R.id.b1);
            ImageButton b2 = findViewById(R.id.b2);
            ImageButton b3 = findViewById(R.id.b3);

            b1.setOnClickListener(this);
            b2.setOnClickListener(this);
            b3.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.b1:
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("9931059201"));

                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    startActivity(intent);
                    break;
                case R.id.b2:
                    Intent intent2 = new Intent(Intent.ACTION_CALL);
                    intent2.setData(Uri.parse("8058501770"));

                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    startActivity(intent2);
                    break;
                case R.id.b3:
                    Intent intent3 = new Intent(Intent.ACTION_CALL);
                    intent3.setData(Uri.parse("9610098566"));

                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    startActivity(intent3);
                    break;


            }

        }

        public void onCreate(View view) {
        }
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
