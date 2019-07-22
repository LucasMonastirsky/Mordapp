package frise.project.mordapp.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import frise.project.mordapp.R;
import frise.project.mordapp.controller.ItemController;
import frise.project.mordapp.model.Item;
import frise.project.mordapp.model.Row;
import frise.project.mordapp.model.RowContainer;
import frise.project.mordapp.retrofit.HELPER;
import frise.project.mordapp.retrofit.ItemDAO;
import frise.project.mordapp.retrofit.ResultListener;
import frise.project.mordapp.room.RoomDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragWpnList extends Fragment {

    RecyclerView.LayoutManager layoutManager;
    RecyclerView recycler;
    AdapterWpn adapter;
    private TextView lblErrorMessage;

    public FragWpnList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_wpn_list, container, false);

        recycler = view.findViewById(R.id.frag_wpn_list_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutManager);

        lblErrorMessage = view.findViewById(R.id.frag_wpn_list_lbl_error_message);

        //recyclerview stuff...


        final ItemController controller = new ItemController(getContext());
        //load items from cache
        adapter = new AdapterWpn(controller.getItemsRoom(), (AdapterWpn.Listener)getActivity());
        recycler.setAdapter(adapter);
        //then load items from internet, in case there was an update
        controller.getItems(new ResultListener<List<Item>>() {
            @Override public void finish(List<Item> items) {
                gotItems(items); }

            @Override public void error(String message) {
                lblErrorMessage.setText(message); }});


        return view;
    }

    private void gotItems(List<Item> items) {
        adapter.setItems(items);
    }
}
