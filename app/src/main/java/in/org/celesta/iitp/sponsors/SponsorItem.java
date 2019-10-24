package in.org.celesta.iitp.sponsors;

import androidx.annotation.Keep;

@Keep
public class SponsorItem {

    private String website;
    private String name;
    private String image;

    public SponsorItem(){

    }

    public SponsorItem(String name, String image, String website) {
        this.name = name;
        this.image = image;
        this.website = website;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
