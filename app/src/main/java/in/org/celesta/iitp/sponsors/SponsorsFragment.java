package in.org.celesta.iitp.sponsors;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import in.org.celesta.iitp.R;
import in.org.celesta.iitp.network.RetrofitClientInstance;

public class SponsorsFragment extends Fragment {

    public SponsorsFragment() {
    }

    private SponsorsAdapter adapter;
    private RecyclerView recyclerView;
    private View emptyView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sponsors, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_sponsors);
        swipeRefreshLayout.setOnRefreshListener(this::updateData);

        recyclerView = view.findViewById(R.id.recycler_sponsors);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        adapter = new SponsorsAdapter(getContext());
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setRefreshing(true);
        updateData();

        return view;
    }

    private void updateData() {

        List<SponsorItem> sponsorItems = new ArrayList<>();
        sponsorItems.add(new SponsorItem("Associate Sponsor", "http://www.celesta.org.in/assets/images/sponsors/bihartourism.jpg", "http://www.bihartourism.gov.in/"));
        sponsorItems.add(new SponsorItem("Event Sponsor", "http://www.celesta.org.in/assets/images/sponsors/icetl.png", "http://icetl.com/"));
        sponsorItems.add(new SponsorItem("Beverage Partner", "http://www.celesta.org.in/assets/images/sponsors/coca.png", "https://www.coca-cola.com/"));
        sponsorItems.add(new SponsorItem("Coding Partners", "http://www.celesta.org.in/assets/images/sponsors/codingninjas.png", "https://www.codingninjas.in/"));
        sponsorItems.add(new SponsorItem("Coding Partners","http://www.celesta.org.in/assets/images/sponsors/hackerearth_new.png", "https://www.hackerearth.com"));
        sponsorItems.add(new SponsorItem("Health Partner", "http://www.celesta.org.in/assets/images/sponsors/ruban.png", "https://www.gargeehotels.com/"));
        sponsorItems.add(new SponsorItem("Hospitality Partner", "http://www.celesta.org.in/assets/images/sponsors/GargeeHotels.jpeg", "https://www.gargeehotels.com/"));
        sponsorItems.add(new SponsorItem("Audio Partner","http://www.celesta.org.in/assets/images/sponsors/zebronics2.png" , "https://zebronics.com"));
        sponsorItems.add(new SponsorItem("Radio Partner", "http://www.celesta.org.in/assets/images/sponsors/radiomirchi.jpeg", "https://www.radiomirchi.com"));
        sponsorItems.add(new SponsorItem("Food Partner", "http://www.celesta.org.in/assets/images/sponsors/jainfoodstall.jpeg", "https://www.justdial.com/Patna/Jain-Restaurants"));
        sponsorItems.add(new SponsorItem("Food Partner", "http://www.celesta.org.in/assets/images/sponsors/chicken.jpeg", "https://broaster.com/"));
        sponsorItems.add(new SponsorItem("Training Partner", "http://www.celesta.org.in/assets/images/sponsors/certstore.png", "https://in.linkedin.com/company/cert-store-solution"));
        sponsorItems.add(new SponsorItem("Implementation Partner", "http://www.celesta.org.in/assets/images/sponsors/youthindiafoundation.png", "https://youngindia.foundation/"));
        sponsorItems.add(new SponsorItem("Implementation Partner", "http://www.celesta.org.in/assets/images/sponsors/techprolabz.jpeg", "http://www.techprolabz.com/"));
        sponsorItems.add(new SponsorItem("Web&SMS Partner", "http://www.celesta.org.in/assets/images/sponsors/asap.png", "https://www.asapservices.co.in/services.html"));
        sponsorItems.add(new SponsorItem("Merchandise Partner", "http://www.celesta.org.in/assets/images/sponsors/awestruck.jpeg","https://www.getawestruck.com/" ));
        sponsorItems.add(new SponsorItem("Workshop Partner", "http://www.celesta.org.in/assets/images/sponsors/whataftercollege.png", "https://whataftercollege.com/"));
        sponsorItems.add(new SponsorItem("Blogger Outreach Partner", "http://www.celesta.org.in/assets/images/sponsors/blogadda2.png", "https://www.blogadda.com/"));
        sponsorItems.add(new SponsorItem("Magazine Partner", "http://www.celesta.org.in/assets/images/sponsors/scientificindia.png", "http://www.scind.org/"));
        sponsorItems.add(new SponsorItem("Online Media Partner", "http://www.celesta.org.in/assets/images/sponsors/festpav.png", "http://festpav.com/"));
        sponsorItems.add(new SponsorItem("Online Media Partner", "http://www.celesta.org.in/assets/images/sponsors/ntdindia.jpg", "http://ntdin.tv/"));
        sponsorItems.add(new SponsorItem("Online Media Partner", "http://www.celesta.org.in/assets/images/sponsors/The-Bihar-News.png", "https://www.thebiharnews.in/"));
        sponsorItems.add(new SponsorItem("Online Media Partner", "http://www.celesta.org.in/assets/images/sponsors/thecollegefever.png", "https://www.thecollegefever.com/"));
        sponsorItems.add(new SponsorItem("Voice Opinion Partner", "http://www.celesta.org.in/assets/images/sponsors/opentalk.png", "https://opentalk.to/"));
        sponsorItems.add(new SponsorItem("Gifting Partner", "http://www.celesta.org.in/assets/images/sponsors/thesouledstore.png", "https://www.thesouledstore.com/"));
        sponsorItems.add(new SponsorItem("Online Learning Partner", "http://www.celesta.org.in/assets/images/sponsors/learncodeonline.png","https://learncodeonline.in/" ));
        sponsorItems.add(new SponsorItem("Official Savings Partner", "http://www.celesta.org.in/assets/images/sponsors/grabon.png", "https://www.grabon.in/"));
        sponsorItems.add(new SponsorItem());





        adapter.setSponsorItemList(sponsorItems);

      swipeRefreshLayout.setRefreshing(false);
      //  String token = PreferenceManager.getDefaultSharedPreferences(getContext()).getString(USER_TOKEN, "0");
  //      Log.e("token", token);
//
//        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//
//        Call<EventItem> call = service.getNewFeed(token, latest);
//        call.enqueue(new Callback<EventItem.FeedItemSuper1>() {
//            @Override
//            public void onResponse(Call<EventItem.FeedItemSuper1> call, Response<EventItem.FeedItemSuper1> response) {
//
//                if (response.isSuccessful()) {

//                    List<SponsorItem> allItems = response.body().getSponsors();
//
//                        adapter.setSponsorItemList(allItems);
//
//                }
//                swipeRefreshLayout.setRefreshing(false);
//            }
//
//            @Override
//            public void onFailure(Call<EventItem.FeedItemSuper1> call, Throwable t) {
//                Log.e("failure", t.getMessage());
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });





    }

}
