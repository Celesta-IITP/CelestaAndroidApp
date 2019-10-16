package in.org.celesta.iitp.gallery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Gallery {

    @SerializedName("img")
    @Expose
    private List<Image> image = null;

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }
}