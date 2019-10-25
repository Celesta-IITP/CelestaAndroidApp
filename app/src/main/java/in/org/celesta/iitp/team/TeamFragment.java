package in.org.celesta.iitp.team;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.org.celesta.iitp.R;

public class TeamFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_team, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        RecyclerView recyclerView = view.findViewById(R.id.recycler_team);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        TeamRecyclerAdapter adapter = new TeamRecyclerAdapter(getContext());
        recyclerView.setAdapter(adapter);

        List<TeamItem> allMembers = new ArrayList<>();

        allMembers.add(new TeamItem("Rakshit Maheshwari", "Overall Fest Coordinator", "9939512017", "https://www.facebook.com/rakshitm123", R.drawable.rakshit_circle2));
        allMembers.add(new TeamItem("Priyansh Singh Rao", "Coordinator - Media and Public Relations", "8058501770", "https://www.facebook.com/priyansh.rao.56", R.drawable.psr_c1));
        allMembers.add(new TeamItem("Roushan Kumar", "Coordinator - Marketing and Sponsorship", "9610098566", "", R.drawable.roushan_c));
        allMembers.add(new TeamItem("Aman Deep", "Coordinator - Media and Public Relations", "9931059201", "https://www.facebook.com/adeep02", R.drawable.aman_deep_c));
        allMembers.add(new TeamItem("Piyush Tiwari", "Coordinator - Events and Operations", "9834943057", "https://www.facebook.com/thebackpropogator", R.drawable.piytwr_c1));
        allMembers.add(new TeamItem("Mohit Kishore", "Coordinator - Events and Operations", "9570566557", "https://www.facebook.com/originalmk7", R.drawable.mohit_c1));
        allMembers.add(new TeamItem("Vatsal Singhal", "Coordinator - Development Team", "8585992062", "https://www.facebook.com/vatsalsinghal1", R.drawable.vatsal_c1));
        allMembers.add(new TeamItem("Deepanjan Datta", "Coordinator - Development Team", "7044170063", "https://www.facebook.com/deepanjan05", R.drawable.deepanjan_c1));
        allMembers.add(new TeamItem("Srikar Nayak", "Coordinator - Creatives And Design", "9666663764", "https://www.facebook.com/srikar.nayak.12", R.drawable.srikar_c1));
        allMembers.add(new TeamItem("Shubham Mondal", "Coordinator - Creatives And Design", "8967654843", "https://www.facebook.com/subham.mondal.7547031", R.drawable.shubham_c1));
        allMembers.add(new TeamItem("Raghu Vamsi", "Coordinator - Creatives And Design", "9705471944", "https://www.facebook.com/veerapaneni.raghuvamsi", R.drawable.raghu_c));
        allMembers.add(new TeamItem("Prateek Rai", "Coordinator - Management And Planning", "7222998383", "https://www.facebook.com/profile.php?id=100019405147477", R.drawable.prai_c));
        allMembers.add(new TeamItem("Yashwanth Chowdhary", "Coordinator - Management And Planning", "9182994302", "https://www.facebook.com/yaswanthchowdary.muppalla.7", R.drawable.yashwanth_c1));
        allMembers.add(new TeamItem("Rama Krishna", "Coordinator - Management And Planning", "8985265942", "https://www.facebook.com/ramakrishna.rapelly", R.drawable.rama_c1));
        allMembers.add(new TeamItem("Pranshu Chandani", "Coordinator - Hospitality", "8791838088", "https://www.facebook.com/pranshu.chandani.5", R.drawable.pranshu_c1));
//        allMembers.add(new TeamItem("Vineet Mishra", "Coordinator - Hospitality", "7355154998", "", R.drawable.vineet_c1));
        allMembers.add(new TeamItem("Nikhil Bommera", "Coordinator - Hospitality", "6205559807", "https://www.facebook.com/nikhil.bommera.520", R.drawable.nikhil_c1));
        allMembers.add(new TeamItem("Manoj Kumar", "Coordinator - Registrations And Security", "8328864643", "https://www.facebook.com/profile.php?id=100007306956302", R.drawable.manoj_c1));
        allMembers.add(new TeamItem("Aditya Ranjan", "Coordinator - Registrations And Security", "8271572990", "https://www.facebook.com/adityaranjan04", R.drawable.aditya_ranjan_c1));

        adapter.setTeamItemList(allMembers);

        super.onViewCreated(view, savedInstanceState);
    }

}
