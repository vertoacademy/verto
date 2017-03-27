package com.example.sharma.vertosacademy.Account_files;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sharma.vertosacademy.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendList_fragment extends Fragment {
    ListView listView;
    FriendsAdapter friendsAdapter;
    List<Friends> friendsList;

    public FriendList_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.friend_list, container, false);
        listView=(ListView) view.findViewById(R.id.friendlist1);

        String userName= SharedprefFacebook.getmInstance(getActivity()).getUserName();
        String userId=SharedprefFacebook.getmInstance(getActivity()).getUserId();
        Toast.makeText(getActivity(), "Friends"+userName, Toast.LENGTH_SHORT).show();
        String s = userName.replaceAll("\\[","");
        s = s.replaceAll("\\]","");
        String[] nameList = s.split(",");

        String s1 = userId.replaceAll("\\[","");
        s1 = s1.replaceAll("\\]","");
        String[] idList = s1.replaceAll(" ","").split(",");

        List<String> id = Arrays.asList(idList);
        List<String> name = Arrays.asList(nameList);
        friendsList = new ArrayList<>();
        for (int i = 0; i < id.size(); i++) {
            Friends friends = new Friends();
            friends.id = id.get(i);
            friends.name = name.get(i);
            friendsList.add(friends);
        }
        friendsAdapter = new FriendsAdapter(friendsList,getActivity());
        listView.setAdapter(friendsAdapter);
        friendsAdapter.notifyDataSetChanged();
        return view;
    }

}
