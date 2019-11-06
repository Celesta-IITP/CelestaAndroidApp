package in.org.celesta.iitp.sponsors;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.org.celesta.iitp.R;

public class SponsorsFragment extends Fragment {

    public SponsorsFragment() {
    }

    private SponsorsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sponsors, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_sponsors);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        adapter = new SponsorsAdapter(getContext());
        recyclerView.setAdapter(adapter);

        populateData();

        return view;
    }

    private void populateData() {

        List<SponsorItem> sponsorItems = new ArrayList<>();

        sponsorItems.add(new SponsorItem("Associate Sponsor", "http://www.celesta.org.in/assets/images/sponsors/beltron.png", "http://www.rubanpatliputrahospital.com/"));
        sponsorItems.add(new SponsorItem("Associate Sponsor", "http://www.celesta.org.in/assets/images/sponsors/ruban.png", "http://www.bihartourism.gov.in/"));

        sponsorItems.add(new SponsorItem("Event Sponsor", "http://www.celesta.org.in/assets/images/sponsors/icetl.png", "http://icetl.com/"));
        sponsorItems.add(new SponsorItem("Event Sponsor", "http://www.celesta.org.in/assets/images/sponsors/engconvo.png", "https://www.engconvo.com/"));
        sponsorItems.add(new SponsorItem("Event Sponsor", "http://www.celesta.org.in/assets/images/sponsors/quadnation.png", "https://quadnationdrone.business.site/?utm_source=gmb&utm_medium=referral"));

        sponsorItems.add(new SponsorItem("Workshop Partner", "http://www.celesta.org.in/assets/images/sponsors/techobyte.png", "https://techobytes.com/"));

        sponsorItems.add(new SponsorItem("Key Partner", "http://www.celesta.org.in/assets/images/sponsors/hero.png", "https://www.heromotocorp.com/en-in/"));

        sponsorItems.add(new SponsorItem("Strategic Partner", "http://www.celesta.org.in/assets/images/sponsors/startupbihar.png", "http://www.startup.bihar.gov.in/"));

        sponsorItems.add(new SponsorItem("Advisory Partner", "http://www.celesta.org.in/assets/images/sponsors/eventom.png", "https://eventomindia.jimdofree.com/"));

        sponsorItems.add(new SponsorItem("Hospitality Partner", "http://www.celesta.org.in/assets/images/sponsors/AmalfiGrand.png", "http://www.amalfigrand.com/"));

        sponsorItems.add(new SponsorItem("Coding Partners", "http://www.celesta.org.in/assets/images/sponsors/hackerearth_new.png", "https://www.hackerearth.com"));

        sponsorItems.add(new SponsorItem("Implementation Partner", "https://celesta.org.in/assets/images/sponsors/techprolabz.png", "http://www.techprolabz.com/"));
        sponsorItems.add(new SponsorItem("Implementation Partner", "http://www.celesta.org.in/assets/images/sponsors/sybyline.png", "http://sybytech.com/"));
        sponsorItems.add(new SponsorItem("Implementation Partner", "http://www.celesta.org.in/assets/images/sponsors/eduquis.png", "https://www.facebook.com/Eduquis-Technology-114371789937992/"));

        sponsorItems.add(new SponsorItem("Beverage Partner", "http://www.celesta.org.in/assets/images/sponsors/coca.png", "https://www.coca-colaindia.com/"));

        sponsorItems.add(new SponsorItem("Audio Partner", "http://www.celesta.org.in/assets/images/sponsors/zebronics2.png", "https://zebronics.com"));

        sponsorItems.add(new SponsorItem("Merchandise Partner", "http://www.celesta.org.in/assets/images/sponsors/layyon.png", "http://www.layyon.com/"));

        sponsorItems.add(new SponsorItem("Online Media Partner", "http://www.celesta.org.in/assets/images/sponsors/patnaites.png", "http://patnaites.com/"));
        sponsorItems.add(new SponsorItem("Online Media Partner", "http://www.celesta.org.in/assets/images/sponsors/ABJ.png", "https://www.facebook.com/AmazingBiharJharkhand/"));

        sponsorItems.add(new SponsorItem("Printing Partner", "http://www.celesta.org.in/assets/images/sponsors/eventom.png", "https://eventomindia.jimdofree.com/"));

        sponsorItems.add(new SponsorItem("Gifting Partner", "http://www.celesta.org.in/assets/images/sponsors/thesouledstore.png", "https://www.thesouledstore.com/"));

        sponsorItems.add(new SponsorItem("Online Savings Partner", "http://www.celesta.org.in/assets/images/sponsors/grabon.png", "https://www.grabon.in/"));

        sponsorItems.add(new SponsorItem("Privilege Partner", "http://www.celesta.org.in/assets/images/sponsors/swiggy.png", "https://www.swiggy.com/"));

        adapter.setSponsorItemList(sponsorItems);

    }

}
