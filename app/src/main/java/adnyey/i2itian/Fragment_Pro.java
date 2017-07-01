package adnyey.i2itian;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Mahajan-PC on 01-05-2017.
 */

public class Fragment_Pro extends Fragment {

    FirebaseAuth author;
    FirebaseUser person;
    List<Packer> packit;
    DatabaseReference referer;
    LinearLayout loading_screen, error_screen;
    ListView lister;
    ProgressBar progress;
    String branch;
    String year;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_prolist, container, false);

        lister = (ListView) view.findViewById(R.id.main_list);
        loading_screen = (LinearLayout) view.findViewById(R.id.linear_load);
        error_screen = (LinearLayout) view.findViewById(R.id.data_error);
        progress = (ProgressBar) view.findViewById(R.id.load_progress);
        progress.setMax(100);
        author = FirebaseAuth.getInstance();
        packit = new ArrayList<Packer>();
        loading_screen.setVisibility(VISIBLE);
        Bundle b = getArguments();
        int s = b.getInt("YEAR");
        year = "" + s;
        if (!PreferenceManager.getDefaultSharedPreferences(getContext()).contains("BRANCH")) {
            Diag_Starter lang_select = new Diag_Starter(getContext());
            lang_select.setCancelable(false);
            lang_select.show();
            lang_select.setDialogResult(new Diag_Starter.OnMyDialogResult() {
                public void finish(int result) {
                    if (result > 0)
                        loader();
                }
            });

        } else {
            loader();
        }


        return view;
    }

    void loader() {
        branch = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("BRANCH", "COMP");
        try {

            author.signInAnonymously()
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            final ValueEventListener postListener = new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    loading_screen.setVisibility(VISIBLE);
                                    try {
                                        if (dataSnapshot.exists()) {
                                            packit.clear();
                                            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                                Packer obj = postSnapshot.getValue(Packer.class);
                                                packit.add(obj);
                                            }
                                            list_populator(packit);
                                            loading_screen.setVisibility(GONE);
                                        }
                                    }catch(NullPointerException e){
                                        error_screen.setVisibility(VISIBLE);
                                    }
                                    // ...
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    // ...
                                }
                            };

                            referer = FirebaseDatabase.getInstance().getReference(branch).child(year);
                            referer.addValueEventListener(postListener);

                        }
                    });

        } catch (NullPointerException e) {
        }
    }

    void list_populator(final List<Packer> pack) {
        PackerAdapter adapter = new PackerAdapter(getContext(),
                R.layout.list_packer, pack);

        lister.setAdapter(adapter);

        lister.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent i = new Intent(getContext(), Disper.class);
                i.putExtra("MyClass", pack.get(position));
                startActivity(i);
            }
        });
    }

}
