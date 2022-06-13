package ru.mirea.lomako.mireaproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.mirea.lomako.mireaproject.databinding.FragmentHomeBinding;
import ru.mirea.lomako.mireaproject.ui.home.HomeViewModel;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitFragment extends Fragment {
    private TextView textView;
    private FirebaseAuth mAuth;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_retrofit, null);
        textView = v.findViewById(R.id.textView_Retrofit);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://rawgit.com/startandroid/data/master/messages/")
                .build();

        ru.mirea.lomako.mireaproject.RetrofitFragment.WebApi webApi = retrofit.create(ru.mirea.lomako.mireaproject.RetrofitFragment.WebApi.class);
        Observable<List<Message>> observable = webApi.messages(1);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Message>>() {
                    @Override
                    public void onCompleted() {

                        textView.setText("Completed");
                    }

                    @Override
                    public void onError(Throwable e) {

                        textView.setText("onError " + e);
                    }

                    @Override
                    public void onNext(List<Message> messages) {

                        textView.setText("onNext " + messages.size());
                    }
                });
        return v;
    }
    public interface WebApi {

        @GET("messages{page}.json")
        Observable<List<Message>> messages(@Path("page") int page);

    }}

