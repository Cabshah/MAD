package com.example.assgn03.ui.main;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.assgn03.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

   /* EditText edCountry,edStreet,edCity;
    private Button sendData;
    FirebaseDatabase firebaseDatabase;
    public static DatabaseReference databaseReference,databaseReference1;
    public static List<String> list = new ArrayList<>();
    Places places;*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }
//ListView listView;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
      /*  edStreet=root.findViewById(R.id.idStreet);
        edCity=root.findViewById(R.id.idCity);
        edCountry=root.findViewById(R.id.idCountry);
        listView=root.findViewById(R.id.listView);*/
      //  pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
         //   @Override
           // public void onChanged(@Nullable String s) {
              //  textView.setText(s);
           // }
       // });
    //    firebaseDatabase  = FirebaseDatabase.getInstance("https://assgn03-default-rtdb.firebaseio.com/");
      //  databaseReference1=firebaseDatabase.getReference("places-info");

      /*  databaseReference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
                Map<String,String> map =(Map <String , String >) snapshot.getValue();
        list.add(map.get("city") + "," + map.get("state") + "," + map.get("country"));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        places = new Places();
        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference=firebaseDatabase.getReference("places-info").child(list.size()+"");
                String StreetAddress = edStreet.getText().toString();
                String City = edCity.getText().toString();
                String Country = edCountry.getText().toString();
                if (TextUtils.isEmpty(StreetAddress) && TextUtils.isEmpty(City) && TextUtils.isEmpty(Country)){
                    Toast.makeText(getActivity(), " ALL FIELDS NEED TO BE FILLED", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        return root;
    }
}