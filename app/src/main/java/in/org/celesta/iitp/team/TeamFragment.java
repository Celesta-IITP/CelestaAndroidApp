package in.org.celesta.iitp.team;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import in.org.celesta.iitp.R;

public class TeamFragment extends Fragment {
    private ImageView parth, rakshit, priyansh, roushan, aman, piyush, mohit_kishore, vatsal, deepanjan, srikar, raghu, shubham, prateek;
    private ImageView yashwanth, rama, pranshu, vineet, nikhil, manoj, aditya;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_team, container, false);
        parth = rootview.findViewById(R.id.parth);
        rakshit = rootview.findViewById(R.id.rakshit);
        priyansh = rootview.findViewById(R.id.priyansh);
        roushan = rootview.findViewById(R.id.roushan);
        aman = rootview.findViewById(R.id.aman);
        piyush = rootview.findViewById(R.id.piyush);
        mohit_kishore = rootview.findViewById(R.id.mohit);
        vatsal = rootview.findViewById(R.id.vatsal);
        deepanjan = rootview.findViewById(R.id.deepanjan);
        srikar = rootview.findViewById(R.id.srikar);
        raghu = rootview.findViewById(R.id.raghu);
        shubham = rootview.findViewById(R.id.shubham);
        prateek = rootview.findViewById(R.id.prateek);
        yashwanth = rootview.findViewById(R.id.yashwanth);
        rama = rootview.findViewById(R.id.rama);
        pranshu = rootview.findViewById(R.id.pranshu);
        vineet = rootview.findViewById(R.id.vineet);
        nikhil = rootview.findViewById(R.id.nikhil);
        manoj = rootview.findViewById(R.id.manoj);
        aditya = rootview.findViewById(R.id.aditya);
        setImages();
        return rootview;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    void setImages() {
        parth.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.perth3, 120, 120));
        rakshit.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.rakshit_circle2, 120, 120));
        priyansh.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.psr_c1, 120, 120));
        roushan.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.roushan_c, 120, 120));
        aman.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.aman_deep_c, 120, 120));
        piyush.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.piytwr_c1, 120, 120));
        mohit_kishore.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.mohit_c1, 120, 120));
        vatsal.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.vatsal_c1, 120, 120));
        deepanjan.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.deepanjan_c1, 120, 120));
        srikar.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.srikar_c1, 120, 120));
        shubham.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.shubham_c1, 120, 120));
        raghu.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.raghu_c, 120, 120));
        prateek.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.prai_c, 120, 120));
        yashwanth.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.yashwanth_c1, 120, 120));
        rama.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.rama_c1, 120, 120));
        pranshu.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.pranshu_c1, 120, 120));
        vineet.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.vineet_c1, 120, 120));
        nikhil.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.nikhil_c1, 120, 120));
        manoj.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.manoj_c1, 120, 120));
        aditya.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.aditya_ranjan_c1, 120, 120));
    }

}
