package in.org.celesta.iitp.gallery;

import androidx.annotation.Keep;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Keep
@Entity
public class Image {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("normal")
    @Expose
    private String normal;
    @SerializedName("reduced")
    @Expose
    private String reduced;

    public Image() {}

    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    public String getReduced() {
        return reduced;
    }

    public void setReduced(String reduced) {
        this.reduced = reduced;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
