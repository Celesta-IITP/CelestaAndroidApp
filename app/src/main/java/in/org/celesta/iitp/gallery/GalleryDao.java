package in.org.celesta.iitp.gallery;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface GalleryDao {

    @Query("SELECT * FROM Image")
    List<Image> loadAllImages();

    @Insert(onConflict = REPLACE)
    void insertImage(Image image);

    @Query("DELETE FROM Image")
    void deleteImages();

}
