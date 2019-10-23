package in.org.celesta.iitp.Auth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

import in.org.celesta.iitp.R;
import in.org.celesta.iitp.utils.IntentUtils;

public class RegisteredEventsAdapter extends RecyclerView.Adapter<RegisteredEventsAdapter.ViewHolder>{

    private Context context;
    private List<RegisteredEventItem> eventList;

    public RegisteredEventsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RegisteredEventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_registered_events, parent, false);
        return new RegisteredEventsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RegisteredEventsAdapter.ViewHolder holder, int position) {

        if (eventList != null) {
            final RegisteredEventItem current = eventList.get(position);

            holder.name.setText(current.getEventName());

            if (current.getAmountToBePaid() > 0 && current.getAmountPaid() == 0) {
                holder.button.setText(String.format(Locale.getDefault(), "Pay\n(₹%d)", current.getAmountToBePaid()));
                holder.button.setVisibility(View.VISIBLE);
            }

            if (current.getTeamName() != null && !current.getTeamName().isEmpty()) {
                holder.team.setText(String.format("Team: %s", current.getTeamName()));
                holder.team.setVisibility(View.VISIBLE);
            }

            if (current.getAmountToBePaid() > 0) {
                holder.paid.setText(String.format("Paid: ₹ %s", String.valueOf(current.getAmountPaid())));
            } else holder.paid.setText("Free Event");

            holder.button.setOnClickListener(view -> {
                String url = "https://celesta.org.in/events/eventsdetails.php?id=" + current.getEventId();
                IntentUtils.openWebBrowser(context, url);
            });

        } else {
            holder.name.setText("Loading ...");
        }
    }

    @Override
    public int getItemCount() {
        if (eventList != null)
            return eventList.size();
        else return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView paid;
        TextView button;
        TextView team;
        View rootLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_event_name);
            rootLayout = itemView.findViewById(R.id.root_registered_events);
            paid = itemView.findViewById(R.id.tv_paid_amount);
            team = itemView.findViewById(R.id.team_name);
            button = itemView.findViewById(R.id.button_pay_now);
        }
    }

    public void setEventList(List<RegisteredEventItem> eventList) {
        this.eventList = eventList;
        notifyDataSetChanged();
    }
}
