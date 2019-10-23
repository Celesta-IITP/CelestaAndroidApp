package in.org.celesta.iitp.contact;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import in.org.celesta.iitp.R;
import in.org.celesta.iitp.utils.IntentUtils;

public class ContactFragment extends Fragment implements View.OnClickListener {

    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContext() != null)
            this.context = getContext();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_us, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        CardView bus = view.findViewById(R.id.bus_schedule);
        CardView fb = view.findViewById(R.id.connect_facebook);
        CardView inst = view.findViewById(R.id.connect_instagram);
        CardView twitter = view.findViewById(R.id.connect_twitter);
        CardView youtube = view.findViewById(R.id.connect_youtube);
        CardView link = view.findViewById(R.id.connect_linkedin);

        fb.setOnClickListener(this);
        inst.setOnClickListener(this);
        twitter.setOnClickListener(this);
        youtube.setOnClickListener(this);
        link.setOnClickListener(this);
        bus.setOnClickListener(this);


        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {
            case R.id.bus_schedule:
                IntentUtils.openWebBrowser(context, "https://www.iitp.ac.in/index.php?option=com_content&view=article&id=60&Itemid=66");
                break;
            case R.id.connect_facebook:
                IntentUtils.openWebBrowser(context, "https://www.facebook.com/CelestaIITP/");
                break;
            case R.id.connect_instagram:
                IntentUtils.openWebBrowser(context, "https://www.instagram.com/celestaiitp_official/");
                break;
            case R.id.connect_twitter:
                IntentUtils.openWebBrowser(context, "https://twitter.com/celesta_iitp");
                break;
            case R.id.connect_youtube:
                IntentUtils.openWebBrowser(context, "https://www.youtube.com/channel/UCd8RpmJktBOwqT4OehcCjjg");
                break;
            case R.id.connect_linkedin:
                IntentUtils.openWebBrowser(context, "https://www.linkedin.com/in/celesta-iit-patna-3722b6166/");
                break;
        }

    }
}