package ru.mirea.lomako.mireaproject;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RoomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoomFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = MainActivity.class.getSimpleName();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private EditText name;
    private EditText salary;
    private EditText address;
    private Button saveEmployee;
    private Button loadEmployee;
    private Button removeEmployee;
    private Button updateEmployee;
    private  TextView textView;
    private FloatingActionButton addRoom;
    private RecyclerView recyclerView;
    private boolean iscreate = false;
    private int id = 0;
    List<Employee> roomlist;


    public RoomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Room.
     */
    // TODO: Rename and change types and number of parameters
    public static RoomFragment newInstance(String param1, String param2) {
        RoomFragment fragment = new RoomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_room, null);

        AppDatabase db = App.getInstance().getDatabase();
        Employee.EmployeeDao employeeDao = db.employeeDao();
        Employee employee = new Employee();
        addRoom = v.findViewById(R.id.floatingActionButton);
        addRoom.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus));
        textView1 =v.findViewById((R.id.textView5));
        textView2 =v.findViewById((R.id.textView6));
        textView3 =v.findViewById((R.id.textView8));
        textView1.setVisibility(v.GONE);
        textView2.setVisibility(v.GONE);
        textView3.setVisibility(v.GONE);
        name = v.findViewById(R.id.nameEdit);
        salary = v.findViewById(R.id.salaryEdit);
        address = v.findViewById(R.id.historyAddress);
        saveEmployee = v.findViewById(R.id.save);
        loadEmployee = v.findViewById(R.id.load);
        removeEmployee = v.findViewById(R.id.remove);
        updateEmployee = v.findViewById(R.id.update);
        name.setVisibility(v.GONE);
        salary.setVisibility(v.GONE);
        address.setVisibility(v.GONE);
        name.setVisibility(v.GONE);
        saveEmployee.setVisibility(v.GONE);
        loadEmployee.setVisibility(v.GONE);
        removeEmployee.setVisibility(v.GONE);
        updateEmployee.setVisibility(v.GONE);
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setVisibility(v.VISIBLE);
        textView =v.findViewById(R.id.textView2);
        textView.setText("Room");

        roomlist=employeeDao.getAll();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        EmployeeAdapter adapter = new EmployeeAdapter(getActivity(),roomlist);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
        addRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iscreate) {
                    textView1.setVisibility(v.GONE);
                    textView2.setVisibility(v.GONE);
                    textView3.setVisibility(v.GONE);
                    name.setVisibility(v.GONE);
                    salary.setVisibility(v.GONE);
                    address.setVisibility(v.GONE);
                    name.setVisibility(v.GONE);
                    saveEmployee.setVisibility(v.GONE);
                    loadEmployee.setVisibility(v.GONE);
                    removeEmployee.setVisibility(v.GONE);
                    updateEmployee.setVisibility(v.GONE);
                    addRoom.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus));
                    recyclerView.setVisibility(v.VISIBLE);
                    roomlist=employeeDao.getAll();
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    EmployeeAdapter adapter = new EmployeeAdapter(getActivity(),roomlist);
                    // устанавливаем для списка адаптер
                    recyclerView.setAdapter(adapter);
                    textView.setText("Room");

                    iscreate=false;

                } else {
                    textView1.setVisibility(v.VISIBLE);
                    textView2.setVisibility(v.VISIBLE);
                    textView3.setVisibility(v.VISIBLE);
                    name.setVisibility(v.VISIBLE);
                    salary.setVisibility(v.VISIBLE);
                    address.setVisibility(v.VISIBLE);
                    name.setVisibility(v.VISIBLE);
                    saveEmployee.setVisibility(v.VISIBLE);
                    loadEmployee.setVisibility(v.VISIBLE);
                    removeEmployee.setVisibility(v.VISIBLE);
                    updateEmployee.setVisibility(v.VISIBLE);
                    addRoom.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_cancel));
                    recyclerView.setVisibility(v.GONE);
                    textView.setText("Создание Room");
                    roomlist.clear();
                    roomlist=employeeDao.getAll();
                    adapter.notifyDataSetChanged();
                    iscreate=true;
                }
            }
        });
        saveEmployee.setOnClickListener(View ->{
            employee.name = String.valueOf(name.getText());
            employee.salary = String.valueOf(salary.getText());
            employee.address = String.valueOf(address.getText());
            employeeDao.insert(employee);
            roomlist.clear();
            roomlist=employeeDao.getAll();
            adapter.notifyDataSetChanged();
        });

        loadEmployee.setOnClickListener(View ->{
            roomlist.clear();
            employeeDao.deleteTable();
            adapter.notifyDataSetChanged();
        });

        removeEmployee.setOnClickListener(View ->{
            try{
                roomlist.clear();
                employeeDao.delete(employeeDao.getByAddress(address.getText().toString()));
                roomlist=employeeDao.getAll();
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                System.out.println("Ошибка");
            }
        });

        updateEmployee.setOnClickListener(View ->{
            try{roomlist.clear();
                employee.name = String.valueOf(name.getText());
                employee.salary = String.valueOf(salary.getText());
                employee.address = String.valueOf(address.getText());
                employeeDao.update(employee);
                roomlist=employeeDao.getAll();
                adapter.notifyDataSetChanged();
            }catch (Exception e) {
                System.out.println("Ошибка");
            }
        });

        return v;
    }
}
