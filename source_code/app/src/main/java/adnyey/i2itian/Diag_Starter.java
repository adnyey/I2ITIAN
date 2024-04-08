package adnyey.i2itian;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Mahajan-PC on 30-06-2017.
 */

public class Diag_Starter extends Dialog {

    OnMyDialogResult mDialogResult;
    CardView it, entc, comp;

    public Diag_Starter(Context context) {
        super(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // same you have
        setContentView(R.layout.diag_starter);
        setTitle("Choose branch");

        it = (CardView) findViewById(R.id.branch_it);
        comp = (CardView) findViewById(R.id.branch_comp);
        entc = (CardView) findViewById(R.id.branch_entc);

        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("BRANCH", "IT").apply();
                mDialogResult.finish(1);
                dismiss();
            }
        });

        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("BRANCH", "COMP").apply();
                mDialogResult.finish(2);
                dismiss();
            }
        });

        entc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("BRANCH", "ENTC").apply();
                mDialogResult.finish(3);
                dismiss();
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = getWindow();
        lp.copyFrom(window.getAttributes());

        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);


    }

    public void setDialogResult(OnMyDialogResult dialogResult) {
        mDialogResult = dialogResult;
    }

    public interface OnMyDialogResult {
        void finish(int result);
    }

}
