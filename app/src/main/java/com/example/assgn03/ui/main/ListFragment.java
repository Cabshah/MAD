package com.example.assgn03.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.assgn03.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
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
    DatabaseReference databaseRefrence;
    FirebaseDatabase firebaseDatabase;
    List<Places> placesList = new ArrayList<>();
    Places places;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = view.findViewById(R.id.listView);
        firebaseDatabase  = FirebaseDatabase.getInstance("https://assgn03-default-rtdb.firebaseio.com/");
        databaseRefrence=firebaseDatabase.getReference("places-info");
        databaseRefrence.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
                Map<String,String> map = (Map<String, String>) snapshot.getValue();
               places = new Places();
                places.setCity(map.get("city"));
                places.setState(map.get("state"));
                places.setCountry(map.get("country"));
              placesList.add(places);
                MyAdapter myAdapter = new MyAdapter(getActivity(),placesList);
                listView.setAdapter(myAdapter);


            }

      //      city:
            //        "Pakistan "


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
         return view;
    }

    public class MyAdapter  extends BaseAdapter{
Context context;
List<Places> stringList;
TextView textView;

        public MyAdapter(Context context, List<Places> stringList) {
            this.context = context;
            this.stringList = stringList;
        }

        @Override
        public int getCount() {
            return stringList.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view= LayoutInflater.from(context).inflate(R.layout.places_layout,viewGroup,false);

            textView=view.findViewById(R.id.txt);
            textView.setText("City: "   +stringList.get(i).getCity() + " \n Country:"    +stringList.get(i).getCountry()+
          "\n State:"  +stringList.get(i).getState() );


            return view;
        }
    }
}