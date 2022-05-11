package hcmute.danbaonguyen19110036.foody.Database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(nameInDb = "Shop")
public class Shop {
    @Id(autoincrement = true)
    private Long id;
    private String shopname;
    private String openDoor;
    private String pricerange;
    private String address;
    @ToMany(referencedJoinProperty = "shopId")
    private List<Food> foods;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 173397329)
    private transient ShopDao myDao;
    @Generated(hash = 1195645388)
    public Shop(Long id, String shopname, String openDoor, String pricerange, String address) {
        this.id = id;
        this.shopname = shopname;
        this.openDoor = openDoor;
        this.pricerange = pricerange;
        this.address = address;
    }
    @Generated(hash = 633476670)
    public Shop() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getShopname() {
        return this.shopname;
    }
    public void setShopname(String shopname) {
        this.shopname = shopname;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 884663848)
    public List<Food> getFoods() {
        if (foods == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FoodDao targetDao = daoSession.getFoodDao();
            List<Food> foodsNew = targetDao._queryShop_Foods(id);
            synchronized (this) {
                if (foods == null) {
                    foods = foodsNew;
                }
            }
        }
        return foods;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1950966)
    public synchronized void resetFoods() {
        foods = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1040006210)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getShopDao() : null;
    }
    public String getOpenDoor() {
        return this.openDoor;
    }
    public void setOpenDoor(String openDoor) {
        this.openDoor = openDoor;
    }
    public String getPricerange() {
        return this.pricerange;
    }
    public void setPricerange(String pricerange) {
        this.pricerange = pricerange;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
