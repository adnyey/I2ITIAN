package adnyey.i2itian;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by Mahajan-PC on 01-07-2017.
 */

public class Fragment_Custom extends Fragment {

    CardView branch_selec;
    TextView current_branch;
    ImageView current_branch_img;
    ToggleButton notifs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_custom, container, false);

        branch_selec = (CardView) view.findViewById(R.id.branch_selector);
        current_branch_img = (ImageView) view.findViewById(R.id.branch_img);
        current_branch = (TextView) view.findViewById(R.id.current_branch);
        notifs = (ToggleButton)view.findViewById(R.id.notifs);

        if (PreferenceManager.getDefaultSharedPreferences(getContext()).getString("BRANCH", "COMP").equals("COMP")) {
            current_branch_img.setImageResource(R.drawable.comp);
            current_branch.setText("Computer\nScience");
        } else if (PreferenceManager.getDefaultSharedPreferences(getContext()).getString("BRANCH", "COMP").equals("IT")) {
            current_branch_img.setImageResource(R.drawable.it);
            current_branch.setText("Information\nTechnology");
        } else if (PreferenceManager.getDefaultSharedPreferences(getContext()).getString("BRANCH", "COMP").equals("ENTC")) {
            current_branch_img.setImageResource(R.drawable.entc);
            current_branch.setText("Electronics");
        } else {
            current_branch_img.setImageResource(R.drawable.it);
            current_branch.setText("Select Branch");
        }

        notifs.setTextOff("Notifications\nOFF");
        notifs.setTextOn("Notifications\nON");

        notifs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifs.setChecked(false);
                Toast.makeText(getActivity(),"Under development",Toast.LENGTH_SHORT).show();
            }
        });

        notifs.setChecked(false);

        branch_selec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Diag_Starter lang_select = new Diag_Starter(getContext());
                lang_select.show();
                lang_select.setDialogResult(new Diag_Starter.OnMyDialogResult() {
                    public void finish(int result) {
                        if (result == 1){
                            current_branch_img.setImageResource(R.drawable.it);
                            current_branch.setText("Information\nTechnology");
                        }
                        else if(result ==2)
                        {
                            current_branch_img.setImageResource(R.drawable.comp);
                            current_branch.setText("Computer\nScience");
                        }
                        else if(result==3)
                        {
                            current_branch_img.setImageResource(R.drawable.entc);
                            current_branch.setText("Electronics");
                        }

                    }
                });
            }
        });

        return view;
    }
}