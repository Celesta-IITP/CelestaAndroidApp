package in.org.celesta.iitp.events;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import in.org.celesta.iitp.Auth.LoginRegisterActivity;
import in.org.celesta.iitp.Auth.LogoutResponse;
import in.org.celesta.iitp.R;
import in.org.celesta.iitp.network.EventsRoutes;
import in.org.celesta.iitp.network.RetrofitClientInstance;
import in.org.celesta.iitp.utils.ImageViewerActivity;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailsFragment extends BottomSheetDialogFragment {

    public EventDetailsFragment() {
    }

    private EventItem current;
    private Context context;
    private int[] color;
    private SharedPreferences preferences;
    private ProgressDialog progressDialog;

    @Override
    public int getTheme() {
        return R.style.BaseBottomSheetDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(this.requireContext(), this.getTheme());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventsViewModel viewModel = ViewModelProviders.of(this).get(EventsViewModel.class);

        if (getContext() != null)
            this.context = getContext();
        else this.dismiss();

        if (getArguments() != null) {
            String id = getArguments().getString("data");
            color = getArguments().getIntArray("color");
            current = viewModel.getEventById(id);
            if (current == null) this.dismiss();
        } else this.dismiss();

        preferences = PreferenceManager.getDefaultSharedPreferences(context);

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        ImageView poster = view.findViewById(R.id.poster);
        Glide.with(context)
                .asBitmap()
                .load(current.getEvPosterUrl())
                .centerCrop()
                .placeholder(R.drawable.events_icon_2)
                .into(poster);

        poster.setOnClickListener(view14 -> {
            Intent i = new Intent(context, ImageViewerActivity.class);
            i.putExtra("image_url", current.getEvPosterUrl());
            startActivity(i);
        });

        CardView cardPrimary = view.findViewById(R.id.card_event_details_primary);
        CardView cardSecondary = view.findViewById(R.id.card_event_details_sec);
        CardView cardTer = view.findViewById(R.id.card_event_details_ter);
        CardView cardQua = view.findViewById(R.id.card_event_details_qua);
        CardView cardPen = view.findViewById(R.id.card_event_details_pen);

        Button register = view.findViewById(R.id.button_register);

        if (color[0] != 0) {
            cardPrimary.setCardBackgroundColor(color[0]);
            cardSecondary.setCardBackgroundColor(color[0]);
            cardTer.setCardBackgroundColor(color[0]);
            cardQua.setCardBackgroundColor(color[0]);
            cardPen.setCardBackgroundColor(color[0]);
            register.setBackgroundTintList(ColorStateList.valueOf(color[0]));
        }

        TextView name = view.findViewById(R.id.name);
        TextView date = view.findViewById(R.id.date);
        TextView venue = view.findViewById(R.id.venue);
        ImageButton map = view.findViewById(R.id.button_map);
        TextView time = view.findViewById(R.id.time);
        TextView description = view.findViewById(R.id.description);
        TextView organisers = view.findViewById(R.id.organisers);
        ImageView organiserPhone = view.findViewById(R.id.organiser_phone);
        TextView rulebook = view.findViewById(R.id.rulebook);
        ImageView rulebookImg = view.findViewById(R.id.rulebook_img);

        if (color[2] != 0) {
            name.setTextColor(color[2]);
            date.setTextColor(color[2]);
            venue.setTextColor(color[2]);
            map.setImageTintList(ColorStateList.valueOf(color[2]));
            time.setTextColor(color[2]);
            description.setTextColor(color[2]);
            organisers.setTextColor(color[2]);
            organiserPhone.setImageTintList(ColorStateList.valueOf(color[2]));
            rulebook.setTextColor(color[2]);
            rulebookImg.setImageTintList(ColorStateList.valueOf(color[2]));
            register.setTextColor(color[2]);
        }

        name.setText(current.getEvName());

        if (current.getEvDescription() != null && !current.getEvDescription().isEmpty())
            description.setText(current.getEvDescription());
        else cardSecondary.setVisibility(View.GONE);

        if (current.getEvDate() != null && !current.getEvDate().isEmpty())
            date.setText(String.format("Date : %s", current.getEvDate()));

        if (current.getEvVenue() != null && !current.getEvVenue().isEmpty())
            venue.setText(String.format("Location : %s", current.getEvVenue()));

        if (current.getMapUrl() != null && !current.getMapUrl().isEmpty()) {
            map.setOnClickListener(view13 -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(current.getMapUrl()));
                startActivity(intent);
            });
        }

        if (current.getEvStartTime() != null && !current.getEvStartTime().isEmpty())
            time.setText(String.format("Timing : %s  -  %s", current.getEvStartTime(), current.getEvEndTime()));

        if (current.getEvOrganiser() != null && !current.getEvOrganiser().isEmpty())
            organisers.setText(String.format("Organisers : %s", current.getEvOrganiser()));

        if (current.getEvOrgPhone() != null && !current.getEvOrgPhone().isEmpty()) {
            cardQua.setOnClickListener(view1 -> {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + current.getEvOrgPhone().split(",")[0]));
                startActivity(intent);
            });
        } else {
            organiserPhone.setVisibility(View.GONE);
        }

        if (current.getEvRuleBookUrl() != null && !current.getEvRuleBookUrl().isEmpty()) {
            cardPen.setOnClickListener(view12 -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(current.getEvRuleBookUrl()));
                startActivity(intent);
            });
        } else {
            cardPen.setVisibility(View.GONE);
        }

        if (current.getEvAmount() != null && !current.getEvAmount().isEmpty()) {
            register.setText(String.format("Register (₹ %s)", current.getEvAmount()));
        }

        register.setOnClickListener(view15 -> {
            if (preferences.getBoolean("login_status", false)) {
                if ("0".equals(current.getIsTeamEvent())) {

                    new AlertDialog.Builder(requireContext())
                            .setTitle("Register")
                            .setMessage("Register for solo event: " + current.getEvName() + " for ₹ " + current.getEvAmount())
                            .setNegativeButton("Cancel", (dialog, id) -> {
                                if (dialog != null) dialog.dismiss();
                            })
                            .setPositiveButton("Yes", (dialogInterface, i) -> registerSoloEvent())
                            .show();

                } else {
                    showAlertDialog();
                }
            } else {
                Toast.makeText(context, "You need to login first...", Toast.LENGTH_LONG).show();
                Intent i = new Intent(context, LoginRegisterActivity.class);
                startActivity(i);
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    private void registerSoloEvent() {

        progressDialog.setMessage("Registering...");
        if (progressDialog != null) progressDialog.show();

        EventsRoutes api = RetrofitClientInstance.getRetrofitInstance().create(EventsRoutes.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("celestaid", preferences.getString("celesta_id", ""))
                .addFormDataPart("eventid", current.getEvId())
                .addFormDataPart("access_token", preferences.getString("access_token", ""))
                .build();

        Call<LogoutResponse> call = api.registerSoloEvent(requestBody);
        call.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(@NonNull Call<LogoutResponse> call, @NonNull Response<LogoutResponse> response) {
                if (progressDialog != null) progressDialog.dismiss();

                if (response.isSuccessful() && response.body() != null) {

                    LogoutResponse registerResponse = response.body();

                    List<String> message = registerResponse.getMessage();
                    if (message != null && message.size() > 0)
                        Toast.makeText(context, message.get(0), Toast.LENGTH_LONG).show();

                } else Toast.makeText(context, "Something went wrong!!!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<LogoutResponse> call, @NonNull Throwable t) {
                if (progressDialog != null) progressDialog.dismiss();
                Log.e("Error", "onFailure: " + t.getMessage());
                Toast.makeText(context, "Something went wrong!!!", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void showAlertDialog() {
        final View dialogView = getLayoutInflater().inflate(R.layout.dialog_register_team_event, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(dialogView);
        builder.setCancelable(false);
        builder.setNegativeButton("Cancel", (dialog, id) -> {
            if (dialog != null) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();

        int memberCount = Integer.parseInt(current.getTeamMembers());

        TextView teamCaptain = dialogView.findViewById(R.id.team_captain_celesta_id);
        teamCaptain.setText(preferences.getString("celesta_id", ""));

        EditText celestaId2 = dialogView.findViewById(R.id.input_celesta_id_2);
        EditText celestaId3 = dialogView.findViewById(R.id.input_celesta_id_3);
        EditText celestaId4 = dialogView.findViewById(R.id.input_celesta_id_4);
        EditText celestaId5 = dialogView.findViewById(R.id.input_celesta_id_5);
        EditText celestaId6 = dialogView.findViewById(R.id.input_celesta_id_6);

        if (memberCount > 1)
            celestaId2.setVisibility(View.VISIBLE);
        if (memberCount > 2)
            celestaId3.setVisibility(View.VISIBLE);
        if (memberCount > 3)
            celestaId4.setVisibility(View.VISIBLE);
        if (memberCount > 4)
            celestaId5.setVisibility(View.VISIBLE);
        if (memberCount > 5)
            celestaId6.setVisibility(View.VISIBLE);

        Button registerButton = dialogView.findViewById(R.id.register_team_event);
        registerButton.setOnClickListener(view -> {
            EditText teamName = dialogView.findViewById(R.id.input_team_name);

            if (teamName.getText().toString().isEmpty()) {
                teamName.setError("Enter a valid team name!");
                return;
            }

            registerTeamEvent(teamName.getText().toString(), celestaId2.getText().toString(), celestaId3.getText().toString(),
                    celestaId4.getText().toString(), celestaId5.getText().toString(), celestaId6.getText().toString(), alertDialog);
        });

        alertDialog.show();
    }

    private void registerTeamEvent(String teamName, String c1, String c2, String c3, String c4, String c5, AlertDialog alertDialog) {

        progressDialog.setMessage("Registering...");
        if (progressDialog != null) progressDialog.show();

        EventsRoutes api = RetrofitClientInstance.getRetrofitInstance().create(EventsRoutes.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("celestaid", preferences.getString("celesta_id", ""))
                .addFormDataPart("eventid", current.getEvId())
                .addFormDataPart("access_token", preferences.getString("access_token", ""))
                .addFormDataPart("team_name", teamName)
                .addFormDataPart("member1", c1)
                .addFormDataPart("member2", c2)
                .addFormDataPart("member3", c3)
                .addFormDataPart("member4", c4)
                .addFormDataPart("member5", c5)
                .build();

        Call<LogoutResponse> call = api.registerTeamEvent(requestBody);
        call.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(@NonNull Call<LogoutResponse> call, @NonNull Response<LogoutResponse> response) {
                if (progressDialog != null) progressDialog.dismiss();

                if (response.isSuccessful() && response.body() != null) {

                    LogoutResponse registerResponse = response.body();

                    if (registerResponse.getStatus() == 202)
                        if (alertDialog != null) alertDialog.dismiss();

                    List<String> message = registerResponse.getMessage();
                    if (message != null && message.size() > 0)
                        Toast.makeText(context, message.get(0), Toast.LENGTH_LONG).show();

                } else Toast.makeText(context, "Something went wrong!!!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<LogoutResponse> call, @NonNull Throwable t) {
                if (progressDialog != null) progressDialog.dismiss();
                Log.e("Error", "onFailure: " + t.getMessage());
                Toast.makeText(context, "Something went wrong!!!", Toast.LENGTH_LONG).show();
            }
        });

    }
}
