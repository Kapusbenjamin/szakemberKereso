/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.ws.rs.NotFoundException;
import javax.xml.bind.annotation.XmlRootElement;
import szakemberkereso.Configuration.Database;

/**
 *
 * @author Sharkz
 */
@Entity
@Table(name = "favorites")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Favorites.findAll", query = "SELECT f FROM Favorites f"),
    @NamedQuery(name = "Favorites.findById", query = "SELECT f FROM Favorites f WHERE f.id = :id"),
    @NamedQuery(name = "Favorites.findByUserId", query = "SELECT f FROM Favorites f WHERE f.userId = :userId"),
    @NamedQuery(name = "Favorites.findByAdId", query = "SELECT f FROM Favorites f WHERE f.adId = :adId")})
public class Favorites implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ad_id")
    private int adId;

    //jogosultság miatt
    @Transient
    @JsonInclude
    private Integer currentUserId;
    //id miatt
    @Transient
    @JsonInclude
    private Ads ad;
    
    public Favorites() {
    }

    public Favorites(Integer id) {
        this.id = id;
    }

    public Favorites(Integer id, int userId, int adId) {
        this.id = id;
        this.userId = userId;
        this.adId = adId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public Integer getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Integer currentUserId) {
        this.currentUserId = currentUserId;
    }

    public Ads getAd() {
        return ad;
    }

    public void setAd(Ads ad) {
        this.ad = ad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Favorites)) {
            return false;
        }
        Favorites other = (Favorites) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "szakemberkereso.Model.Favorites[ id=" + id + " ]";
    }
    
    public static Favorites objectToFavorite(Object[] o) throws Exception{
        Integer o_id = Integer.parseInt(o[0].toString());
        Integer o_user_id = Integer.parseInt(o[1].toString());
        Integer o_ad_id = Integer.parseInt(o[2].toString());

        Favorites f = new Favorites(o_id, o_user_id, o_ad_id);
        f.setAd(Ads.getAdById(f.getAdId()));
        return f;
    }
    
    public static List<Favorites> getAllfavoritesByUserId(Integer user_id_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        List<Favorites> favorites = new ArrayList<>();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllfavoritesByUserId");
            
            spq.registerStoredProcedureParameter("user_id_in", Integer.class, ParameterMode.IN);
            
            spq.setParameter("user_id_in", user_id_in);
            
            spq.execute();
            List<Object[]> result = spq.getResultList();
            
            for(Object[] r : result){
                Favorites f = Favorites.objectToFavorite(r);
                favorites.add(f);
            }
            
            return favorites;
        }
        catch(NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
        catch(Exception e){
            throw new Exception("Valami hiba történt! (" + e.getMessage() + ")" + "getAllfavoritesByUserId");
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
    }
    
    public static Favorites getFavoriteById(Integer id_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getFavoriteById");  
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("id_in", id_in);
            
            spq.execute();
            List<Object[]> result = spq.getResultList();
            
            if(!result.isEmpty()){
                Object[] r = result.get(0);

                Favorites f = Favorites.objectToFavorite(r);
                return f;
            }
            else{
                throw new NotFoundException("Nincs ilyen kedvenc!");
            }
        } 
        catch(NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
        catch(Exception e){
            throw new Exception("Valami hiba történt! (" + e.getMessage() + ")");
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
    }
    
    public static void deleteFavorite(Favorites favorite) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteFavorite");
            
            spq.registerStoredProcedureParameter("user_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ad_id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("user_id_in", favorite.getCurrentUserId());
            spq.setParameter("ad_id_in", Ads.getAdById(favorite.getAdId()).getId());
            
            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen kedvenc!");
            }
        } 
        catch(NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
        catch(Exception e){
            throw new Exception("Valami hiba történt! (" + e.getMessage() + ")");
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
    }
    
    public static void addFavorite(Favorites favorite) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addFavorite");
            
            spq.registerStoredProcedureParameter("user_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ad_id_in", Integer.class, ParameterMode.IN);
            
            spq.setParameter("user_id_in", favorite.getUserId());
            spq.setParameter("ad_id_in", Ads.getAdById(favorite.getAdId()).getId());
            
            spq.execute();
        } 
        catch(NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
        catch(Exception e){
            throw new Exception("Valami hiba történt! (" + e.getMessage() + ")");
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
    }
    
}
