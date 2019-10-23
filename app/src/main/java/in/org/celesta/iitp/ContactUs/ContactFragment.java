package in.org.celesta.iitp.ContactUs;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import in.org.celesta.iitp.R;
import in.org.celesta.iitp.sponsors.SponsorsAdapter;


public class ContactFragment extends AppCompatActivity implements View.OnClickListener {

    ImageButton dial;

    @Override
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

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            return;
        } else {
            if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                Toast.makeText(this, "Caller access is requird in order to make a call.", Toast.LENGTH_SHORT).show();
            }
        }

        switch (v.getId()) {
            case R.id.b1:
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("9931059201"));
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



}