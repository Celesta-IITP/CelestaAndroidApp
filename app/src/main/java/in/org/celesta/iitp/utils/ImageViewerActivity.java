package in.org.celesta.iitp.utils;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import in.org.celesta.iitp.R;

public class ImageViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        final ImageView image = findViewById(R.id.image);

        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("image_reduced")) {
            String url = getIntent().getStringExtra("image_url");
            Glide.with(this)
                    .load(url)
                    .thumbnail(Glide.with(this).load(getIntent().getStringExtra("image_reduced")))
                    .into(image);

        } else if (getIntent().hasExtra("image_url")) {
            String url = getIntent().getStringExtra("image_url");
            Glide.with(this)
                    .load(url)
                    .placeholder(R.drawable.logo_alt)
                    .into(image);

        } else {
            Toast.makeText(this, "No image found!!!", Toast.LENGTH_LONG).show();
        }
    }
}
