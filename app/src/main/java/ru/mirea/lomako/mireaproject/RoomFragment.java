package ru.mirea.lomako.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    private EditText name;
    private EditText salary;
    private EditText address;
    private Button saveEmployee;
    private Button loadEmployee;
    private Button removeEmployee;
    private Button updateEmployee;



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


        name = v.findViewById(R.id.nameEdit);
        salary = v.findViewById(R.id.salaryEdit);
        address = v.findViewById(R.id.historyAddress);
        saveEmployee = v.findViewById(R.id.save);
        loadEmployee = v.findViewById(R.id.load);
        removeEmployee = v.findViewById(R.id.remove);
        updateEmployee = v.findViewById(R.id.update);

        saveEmployee.setOnClickListener(View ->{
            employee.name = String.valueOf(name.getText());
            employee.salary = String.valueOf(salary.getText());
            employee.address = String.valueOf(address.getText());
            employeeDao.insert(employee);
        });

        loadEmployee.setOnClickListener(View ->{
            employeeDao.getAll();
        });

        removeEmployee.setOnClickListener(View ->{
            try{
                employeeDao.delete(employeeDao.getByAddress(address.getText().toString()));
            } catch (Exception e) {
                System.out.println("Упси-дупси, вылет не прошёл");
            }
        });

        updateEmployee.setOnClickListener(View ->{
            try{
                employee.name = String.valueOf(name.getText());
                employee.salary = String.valueOf(salary.getText());
                employee.address = String.valueOf(address.getText());
                employeeDao.update(employee);
            }catch (Exception e) {
                System.out.println("Упси-дупси, вылет не прошёл");
            }
        });

        return v;
    }
}
