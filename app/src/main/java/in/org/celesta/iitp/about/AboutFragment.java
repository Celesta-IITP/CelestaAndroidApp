package in.org.celesta.iitp.about;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import in.org.celesta.iitp.R;
import in.org.celesta.iitp.utils.IntentUtils;

public class AboutFragment extends Fragment implements View.OnClickListener {


    public AboutFragment() {
    }

    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContext() != null)
            this.context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        ImageView image1 = view.findViewById(R.id.image1);
        ImageView image2 = view.findViewById(R.id.image2);
        ImageView image3 = view.findViewById(R.id.image3);
        ImageView image4 = view.findViewById(R.id.image4);

        CardView call1 = view.findViewById(R.id.card_call_1);
        CardView call2 = view.findViewById(R.id.card_call_2);
        CardView call3 = view.findViewById(R.id.card_call_3);
        CardView call4 = view.findViewById(R.id.card_call_4);

        call1.setOnClickListener(this);
        call2.setOnClickListener(this);
        call3.setOnClickListener(this);
        call4.setOnClickListener(this);

        CardView facebook1 = view.findViewById(R.id.card_facebook_1);
        CardView facebook2 = view.findViewById(R.id.card_facebook_2);
        CardView facebook3 = view.findViewById(R.id.card_facebook_3);
        CardView facebook4 = view.findViewById(R.id.card_facebook_4);

        facebook1.setOnClickListener(this);
        facebook2.setOnClickListener(this);
        facebook3.setOnClickListener(this);
        facebook4.setOnClickListener(this);


        CardView github1 = view.findViewById(R.id.card_github_1);
        CardView github2 = view.findViewById(R.id.card_github_2);
        CardView github3 = view.findViewById(R.id.card_github_3);
        CardView github4 = view.findViewById(R.id.card_github_4);

        github1.setOnClickListener(this);
        github2.setOnClickListener(this);
        github3.setOnClickListener(this);
        github4.setOnClickListener(this);

        Glide.with(context).load("https://scontent.fdel29-1.fna.fbcdn.net/v/t1.0-9/61836104_1185350021642288_3218647400700706816_n.jpg?_nc_cat=102&_nc_oc=AQks_Avrh-QmlQFcHGi2ug38TMWNY4xy6qpFT8fxfz451NDS3ahAIUOVwKIbn-O6KmE&_nc_ht=scontent.fdel29-1.fna&oh=ba5d514048981767bdc4b4c909b6708a&oe=5E35BDD7")
                .placeholder(R.mipmap.celesta_icon_round).into(image1);

        Glide.with(context).load("https://scontent.fdel29-1.fna.fbcdn.net/v/t1.0-9/69644261_2115104492128356_6176772960083247104_n.jpg?_nc_cat=107&_nc_oc=AQk4WEUseafFdQvhhWhu0YFH6_9o8nBkkjTU7UJPzeK0NPWeppaKVIVUMtJUAGIZ6Yk&_nc_ht=scontent.fdel29-1.fna&oh=818707c46121857a62041b44ed4bd17c&oe=5DF2FCE3")
                .placeholder(R.mipmap.celesta_icon_round).into(image2);

        Glide.with(context).load("https://scontent.fdel29-1.fna.fbcdn.net/v/t1.0-9/18813995_1182737558521894_2753272857627730485_n.jpg?_nc_cat=101&_nc_oc=AQlG0F8XVl3PnNTA1LnzMcfZ3p4cElt7WOpCtOADvw6N7eQcw-97L2RhxGWq-T2VDGk&_nc_ht=scontent.fdel29-1.fna&oh=70217968ca8de55d0a842ceac8ab8f41&oe=5E3796AA")
                .placeholder(R.mipmap.celesta_icon_round).into(image3);

        Glide.with(context).load("https://scontent.fdel29-1.fna.fbcdn.net/v/t1.0-9/33490969_969879943175001_7316512759087104000_n.jpg?_nc_cat=105&_nc_oc=AQn_AxS6IX1lXlZZyVmn8sBqsAwQuU69Qg3VmDV65xgAmC_J9_zHJ5AkbPWEccCVU3A&_nc_ht=scontent.fdel29-1.fna&oh=871245e026dcfd1041ed75995ebe9deb&oe=5DF6A107")
                .placeholder(R.mipmap.celesta_icon_round).into(image4);


        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        Intent intent = new Intent();
        switch (id) {
            case R.id.card_call_1:
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+918299735195"));
                break;
            case R.id.card_call_2:
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+918967570983"));
                break;
            case R.id.card_call_3:
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+918013054710"));
                break;
            case R.id.card_call_4:
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+917424904721"));
                break;
            case R.id.card_facebook_1:
                IntentUtils.openWebBrowser(context, "https://www.facebook.com/ashwani.yadav9499");
                return;
            case R.id.card_facebook_2:
                IntentUtils.openWebBrowser(context, "https://www.facebook.com/atm1504");
                return;
            case R.id.card_facebook_3:
                IntentUtils.openWebBrowser(context, "https://www.facebook.com/somenath.sarkar.39");
                return;
            case R.id.card_facebook_4:
                IntentUtils.openWebBrowser(context, "https://www.facebook.com/amangrobo");
                return;
            case R.id.card_github_1:
                IntentUtils.openWebBrowser(context, "https://github.com/ashwaniYDV");
                return;
            case R.id.card_github_2:
                IntentUtils.openWebBrowser(context, "https://github.com/atm1504");
                return;
            case R.id.card_github_3:
                IntentUtils.openWebBrowser(context, "https://github.com/somenath1435");
                return;
            case R.id.card_github_4:
                IntentUtils.openWebBrowser(context, "https://github.com/amangrobo/");
                return;
        }

        startActivity(intent);
    }
}
