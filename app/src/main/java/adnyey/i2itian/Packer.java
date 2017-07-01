package adnyey.i2itian;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mahajan-PC on 30-04-2017.
 */
@IgnoreExtraProperties
public class Packer implements Serializable {

    public String pro_title;

    public String pro_abstract;

    public String pro_statement;

    public String pro_domain;

    public String pro_spon;

    public Packer() {    }

    public Packer(String pro_abstract, String pro_domain, String pro_statement, String pro_spon, String pro_title ) {

        this.pro_abstract = pro_abstract;
        this.pro_statement = pro_statement;
        this.pro_title = pro_title;
        this.pro_spon = pro_spon;
        this.pro_domain = pro_domain;
    }

    public String getPro_statement() {
        return pro_statement;
    }

    public String getPro_spon() {
        return pro_spon;
    }

    public String getPro_domain() {
        return pro_domain;
    }

    public String getPro_title() {
        return pro_title;
    }

    public String getPro_abstract() {
        return pro_abstract;
    }
}
