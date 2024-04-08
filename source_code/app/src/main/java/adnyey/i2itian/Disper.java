package adnyey.i2itian;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Mahajan-PC on 25-06-2017.
 */

public class Disper extends AppCompatActivity {

    Packer obj;
    TextView title, domain, sponsor, statement, abs;
    LinearLayout sponss;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_disper);

        obj = (Packer)getIntent().getSerializableExtra("MyClass");

        title = (TextView)findViewById(R.id.proj_title);
        domain = (TextView)findViewById(R.id.proj_domain);
        sponsor = (TextView)findViewById(R.id.proj_spon);
        statement = (TextView)findViewById(R.id.proj_state);
        abs = (TextView)findViewById(R.id.proj_abstract);
        sponss = (LinearLayout)findViewById(R.id.card_spon);

        try {

            title.setText(obj.getPro_title());
            domain.setText(obj.getPro_domain());
            statement.setText(obj.getPro_statement());
            abs.setText(obj.getPro_abstract());

            if (!obj.getPro_spon().equals("Internal")) {
                sponsor.setText("Project sponsored by " + obj.getPro_spon());
            } else {
                sponss.setVisibility(View.GONE);
            }
        }
        catch(NullPointerException e){e.printStackTrace();}
    }
}
