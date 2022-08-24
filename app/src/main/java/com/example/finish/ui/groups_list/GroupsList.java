package com.example.finish.ui.groups_list;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finish.R;
import com.example.finish.ui.Adapter.AdapterGroupChatList;
import com.example.finish.ui.Model.GroupChatList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GroupsList extends Fragment {
    private RecyclerView groupsRv;
    private FirebaseAuth firebaseAuth;

    private ArrayList<GroupChatList> groupsLists;
    private AdapterGroupChatList adapterGroupChatList;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.groups_list_fragment, container, false);
        
        groupsRv = view.findViewById(R.id.groupsRv);
        firebaseAuth = FirebaseAuth.getInstance();
        loadGroupChatList();
        
        

        return view;
    }

    private void loadGroupChatList() {
        groupsLists = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Groups");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                groupsLists.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    if(ds.child("Participants").child(firebaseAuth.getUid()).exists()){
                        GroupChatList model = ds.getValue(GroupChatList.class);
                        groupsLists.add(model);
                    }
                    adapterGroupChatList = new AdapterGroupChatList(getActivity(), groupsLists);
                    groupsRv.setAdapter(adapterGroupChatList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



   /* private void searchGroupChatList(final String query) {
        groupsLists = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Groups");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                groupsLists.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    if(ds.child("Participants").child(firebaseAuth.getUid()).exists()){
                        if(ds.child("groupTitle").toString().toLowerCase().contains(query.toLowerCase())){
                            GroupChatList model = ds.getValue(GroupChatList.class);
                            groupsLists.add(model);
                        }
                        
                    }
                    adapterGroupChatList = new AdapterGroupChatList(getActivity(), groupsLists);
                    groupsRv.setAdapter(adapterGroupChatList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/ //Планируется сделать
}
