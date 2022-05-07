package hcmute.danbaonguyen19110036.foody.Database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "Category")
public class Category {
    @Id(autoincrement = true)
    private Long id;
    private String categoryname;
    @Generated(hash = 2135702606)
    public Category(Long id, String categoryname) {
        this.id = id;
        this.categoryname = categoryname;
    }
    @Generated(hash = 1150634039)
    public Category() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCategoryname() {
        return this.categoryname;
    }
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
}
