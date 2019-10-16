package in.org.celesta.iitp.gallery;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;
import java.util.concurrent.ExecutionException;

import in.org.celesta.iitp.R;
import in.org.celesta.iitp.database.AppDatabase;
import in.org.celesta.iitp.network.OtherRoutes;
import in.org.celesta.iitp.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryFragment extends Fragment implements GalleryAdapter.OnImageSelectedListener {

    public GalleryFragment() {
    }

    private SwipeRefreshLayout swipeRefreshLayout;
    private Context context;
    private GalleryAdapter adapter;
    private GalleryDao dao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getContext() != null)
            this.context = getContext();
        else
            NavHostFragment.findNavController(this).navigateUp();

        setEnterTransition(TransitionInflater.from(context).inflateTransition(android.R.transition.fade));

        dao = AppDatabase.getDatabase(context).galleryDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_gallery);
        swipeRefreshLayout.setOnRefreshListener(this::updateData);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_gallery);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));

        adapter = new GalleryAdapter(context, this);
        recyclerView.setAdapter(adapter);

        populateData();

        super.onViewCreated(view, savedInstanceState);
    }

    private void populateData() {

        try {
            List<Image> allImages = new GetImagesTask(dao).execute().get();
            adapter.setImageList(allImages);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void updateData() {

        OtherRoutes service = RetrofitClientInstance.getRetrofitInstance().create(OtherRoutes.class);

        Call<Gallery> call = service.getImages();

        call.enqueue(new Callback<Gallery>() {
            @Override
            public void onResponse(@NonNull Call<Gallery> call, @NonNull Response<Gallery> response) {
                if (response.isSuccessful()) {

                    Gallery gallery = response.body();

                    if (gallery != null && gallery.getImage() != null) {
                        new DeleteImagesTask(dao).execute();

                        for (Image image : gallery.getImage()) {
                            new InsertImageTask(dao).execute(image);
                            Log.e("data", image.getNormal());
                        }

                        populateData();
                    } else                             Log.e("data", "hhh");


                } else {
                    Log.e(getClass().getSimpleName(), "no data");
                }

                if (swipeRefreshLayout != null) swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(@NonNull Call<Gallery> call, @NonNull Throwable t) {
                Log.e(getClass().getSimpleName(), "f");
                if (swipeRefreshLayout != null) swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public void onEventSelected(String reduced, String url) {

    }


    private static class InsertImageTask extends AsyncTask<Image, Void, Void> {
        private GalleryDao mAsyncTaskDao;

        InsertImageTask(GalleryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Image... params) {
            mAsyncTaskDao.insertImage(params[0]);
            return null;
        }
    }

    private static class GetImagesTask extends AsyncTask<Void, Void, List<Image>> {
        private GalleryDao mAsyncTaskDao;

        GetImagesTask(GalleryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<Image> doInBackground(Void... params) {
            return mAsyncTaskDao.loadAllImages();
        }
    }

    private static class DeleteImagesTask extends AsyncTask<Void, Void, Void> {
        private GalleryDao mAsyncTaskDao;

        DeleteImagesTask(GalleryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... params) {
            mAsyncTaskDao.deleteImages();
            return null;
        }
    }


}
