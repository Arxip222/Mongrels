package com.example.finish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;

import com.example.finish.ui.Adapter.AdapterParticipantAdd;
import com.example.finish.ui.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GroupParticipantAddActivity extends AppCompatActivity {
    private RecyclerView usersRv;
    private ActionBar actionBar;
    private FirebaseAuth firebaseAuth;
    private String groupId;
    private String myGroupRole;
    private ArrayList<User> userList;
    private AdapterParticipantAdd adapterParticipantAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_participant_add);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Add Participants");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        firebaseAuth = FirebaseAuth.getInstance();
        usersRv = findViewById(R.id.usersRv);
        groupId = getIntent().getStringExtra("groupId");
        loadGroupInfo();

    }

    private void getAllUsers() {
        userList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    User modelUser = ds.getValue(User.class);
                    if(!firebaseAuth.getUid().equals(modelUser.getUid())){
                        userList.add(modelUser);
                    }
                }
                adapterParticipantAdd = new AdapterParticipantAdd(GroupParticipantAddActivity.this, userList, ""+groupId, ""+myGroupRole);
                usersRv.setAdapter(adapterParticipantAdd);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadGroupInfo() {
        final DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Groups");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Groups");
        ref.orderByChild("groupId").equalTo(groupId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String groupId = ""+ds.child("groupId").getValue();
                    final String groupTitle = ""+ds.child("groupTitle").getValue();
                    String groupDescription = ""+ds.child("groupDescription").getValue();
                    String groupIcon = ""+ds.child("groupIcon").getValue();
                    String createdBy = ""+ds.child("createdBy").getValue();
                    String timestamp = ""+ds.child("timestamp").getValue();
                    actionBar.setTitle("Add Participants");
                    ref1.child(groupId).child("Participants").child(firebaseAuth.getUid())
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.exists()){
                                        myGroupRole = ""+dataSnapshot.child("role").getValue();
                                        actionBar.setTitle(groupTitle + "("+myGroupRole+")");
                                        getAllUsers();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
