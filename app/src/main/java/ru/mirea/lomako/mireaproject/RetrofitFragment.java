package ru.mirea.lomako.mireaproject;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import ru.mirea.lomako.mireaproject.databinding.FragmentHomeBinding;


public class RetrofitFragment extends Fragment {
    private TextView courseNameTV, courseTracksTV, courseBatchTV;
    private ImageView courseIV;
    private ProgressBar loadingPB;
    private CardView courseCV;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_retrofit, null);
        loadingPB = v.findViewById(R.id.idLoadingPB);
        courseCV = v.findViewById(R.id.idCVCourse);
        courseNameTV = v.findViewById(R.id.idTVCourseName);
        courseTracksTV = v.findViewById(R.id.idTVTracks);
        courseBatchTV = v.findViewById(R.id.idTVBatch);
        courseIV = v.findViewById(R.id.idIVCourse);
        getCourse();
        return v;
    }
    private void getCourse() {

        // on below line we are creating a retrofit
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonkeeper.com/b/")
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<RecyclerData> call = retrofitAPI.getCourse();
        call.enqueue(new Callback<RecyclerData>() {
            @Override
            public void onResponse(Call<RecyclerData> call, Response<RecyclerData> response) {
                if (response.isSuccessful()) {
                    // inside the on response method.
                    // we are hiding our progress bar.
                    loadingPB.setVisibility(View.GONE);
                    // in below line we are making our card
                    // view visible after we get all the data.
                    courseCV.setVisibility(View.VISIBLE);
                    RecyclerData modal = response.body();
                    // after extracting all the data we are
                    // setting that data to all our views.
                    courseNameTV.setText(modal.getCourseName());
                    courseTracksTV.setText(modal.getcourseLabs());
                    courseBatchTV.setText(modal.getCourseMode());
                    Picasso.get().load(modal.getCourseimg()).into(courseIV);
                    // we are using picasso to load the image from url.

                }
            }

            @Override
            public void onFailure(Call<RecyclerData> call, Throwable t) {
                // displaying an error message in toast
                Toast.makeText(getActivity(), "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

