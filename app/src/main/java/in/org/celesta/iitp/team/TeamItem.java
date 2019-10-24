package in.org.celesta.iitp.team;

import androidx.annotation.Keep;

@Keep
public class TeamItem {

    private String name;
    private int image;
    private String post;
    private String phone;
    private String facebook;

    public TeamItem() {}

    public TeamItem(String name, String post, String phone, String facebook, int image) {
        this.name = name;
        this.post = post;
        this.phone = phone;
        this.facebook = facebook;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
}
