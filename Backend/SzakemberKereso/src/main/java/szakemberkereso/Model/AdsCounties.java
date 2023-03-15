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
@Table(name = "ads_counties")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdsCounties.findAll", query = "SELECT a FROM AdsCounties a"),
    @NamedQuery(name = "AdsCounties.findById", query = "SELECT a FROM AdsCounties a WHERE a.id = :id"),
    @NamedQuery(name = "AdsCounties.findByAdId", query = "SELECT a FROM AdsCounties a WHERE a.adId = :adId"),
    @NamedQuery(name = "AdsCounties.findByCountyId", query = "SELECT a FROM AdsCounties a WHERE a.countyId = :countyId")})
public class AdsCounties implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ad_id")
    private Integer adId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "county_id")
    private Integer countyId;

    //jogosultság miatt
    @Transient
    @JsonInclude
    private Integer currentUserId;
    
    public AdsCounties() {
    }

    public AdsCounties(Integer id) {
        this.id = id;
    }

    public AdsCounties(Integer id, Integer adId, Integer countyId) {
        this.id = id;
        this.adId = adId;
        this.countyId = countyId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public Integer getCountyId() {
        return countyId;
    }

    public void setCountyId(Integer countyId) {
        this.countyId = countyId;
    }

    public Integer getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Integer currentUserId) {
        this.currentUserId = currentUserId;
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
        if (!(object instanceof AdsCounties)) {
            return false;
        }
        AdsCounties other = (AdsCounties) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "szakemberkereso.Model.AdsCounties[ id=" + id + " ]";
    }
    
    public static void addNewCountyToAd(AdsCounties ad_county) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewCountyToAd");
            
            spq.registerStoredProcedureParameter("ad_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("county_id_in", Integer.class, ParameterMode.IN);

            spq.setParameter("ad_id_in", Ads.getAdById(ad_county.getAdId()).getId());
            spq.setParameter("county_id_in", Counties.getCountyById(ad_county.getCountyId()).getId());

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

    public static void deleteCountyFromAd(AdsCounties ad_county) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteCountyFromAd");
            
            spq.registerStoredProcedureParameter("ad_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("county_id_in", Integer.class, ParameterMode.IN);

            spq.setParameter("ad_id_in", Ads.getAdById(ad_county.getAdId()).getId());
            spq.setParameter("county_id_in", Counties.getCountyById(ad_county.getCountyId()).getId());

            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen hirdetés-megye!");
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
    
    public static List<Counties> getAllCountiesByAd(Integer ad_id_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllCountiesByAd");
            
            spq.registerStoredProcedureParameter("ad_id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("ad_id_in", Ads.getAdById(ad_id_in).getId());

            spq.execute();
            
            List<Object[]> result = spq.getResultList();
            List<Counties> counties = new ArrayList<>();

            for(Object[] county : result){
                Integer county_id = Integer.parseInt(county[0].toString());
                String county_name = county[1].toString();

                Counties c = new Counties(county_id, county_name);
                counties.add(c);
            }

            return counties;
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
