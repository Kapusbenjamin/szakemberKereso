/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.NotFoundException;
import javax.xml.bind.annotation.XmlRootElement;
import szakemberkereso.Configuration.Database;

/**
 *
 * @author Sharkz
 */
@Entity
@Table(name = "ads")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ads.findAll", query = "SELECT a FROM Ads a"),
    @NamedQuery(name = "Ads.findById", query = "SELECT a FROM Ads a WHERE a.id = :id"),
    @NamedQuery(name = "Ads.findByUserId", query = "SELECT a FROM Ads a WHERE a.userId = :userId"),
    @NamedQuery(name = "Ads.findByJobTagId", query = "SELECT a FROM Ads a WHERE a.jobTagId = :jobTagId"),
    @NamedQuery(name = "Ads.findByUpdatedAt", query = "SELECT a FROM Ads a WHERE a.updatedAt = :updatedAt"),
    @NamedQuery(name = "Ads.findByStatus", query = "SELECT a FROM Ads a WHERE a.status = :status"),
    @NamedQuery(name = "Ads.findByDeleted", query = "SELECT a FROM Ads a WHERE a.deleted = :deleted")})
public class Ads implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "job_tag_id")
    private Integer jobTagId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private Integer status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deleted")
    private Integer deleted;

    //id miatt
    @Transient
    @JsonInclude
    private JobTags jobTag;
    
    //jogosultság miatt
    @Transient
    @JsonInclude
    private Integer currentUserId;
    
    //filter miatt
    @Transient
    @JsonInclude
    private Integer countyId;
    
    public Ads() {
    }

    public Ads(Integer id) {
        this.id = id;
    }

    public Ads(Integer id, Integer userId, Integer jobTagId, String description, Date updatedAt, Integer status, Integer deleted) {
        this.id = id;
        this.userId = userId;
        this.jobTagId = jobTagId;
        this.description = description;
        this.updatedAt = updatedAt;
        this.status = status;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getJobTagId() {
        return jobTagId;
    }

    public void setJobTagId(Integer jobTagId) {
        this.jobTagId = jobTagId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
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

    public JobTags getJobTag() {
        return jobTag;
    }

    public void setJobTag(JobTags jobTag) {
        this.jobTag = jobTag;
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
        if (!(object instanceof Ads)) {
            return false;
        }
        Ads other = (Ads) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "szakemberkereso.Model.Ads[ id=" + id + " ]";
    }
    
    public static Ads objectToAd(Object[] o) throws Exception{
        Integer o_id = Integer.parseInt(o[0].toString());
        Integer o_user_id = Integer.parseInt(o[1].toString());
        Integer o_job_tag_id = Integer.parseInt(o[2].toString());
        String o_desc = o[3].toString();
        Date o_updated_at = Timestamp.valueOf(o[4].toString());
        Integer o_status = Integer.parseInt(o[5].toString());
        Integer o_deleted = Integer.parseInt(o[6].toString());

        Ads ad = new Ads(o_id, o_user_id, o_job_tag_id, o_desc, o_updated_at, o_status, o_deleted);
        ad.setJobTag(JobTags.getJobTagById(ad.getJobTagId()));
        return ad;
    }
            
    public static Integer createAd(Ads ad) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("createAd");
            
            spq.registerStoredProcedureParameter("user_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("job_tag_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("desc_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("last_id_out", Integer.class, ParameterMode.OUT);

            spq.setParameter("user_id_in", ad.getUserId());
            spq.setParameter("job_tag_id_in", JobTags.getJobTagById(ad.getJobTagId()).getId());
            spq.setParameter("desc_in", ad.getDescription());

            spq.execute();
            
            Integer result = Integer.parseInt(spq.getOutputParameterValue("last_id_out").toString());
            return result;
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
    
    public static void updateAd(Ads ad) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateAd");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("desc_in", String.class, ParameterMode.IN);

            spq.setParameter("id_in", ad.getId());
            spq.setParameter("desc_in", ad.getDescription());

            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen hirdetés!");
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
    
    public static List<Ads> getAllAcceptedAds() throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        List<Ads> ads = new ArrayList<>();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllAcceptedAds");
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
            
            for(Object[] o : result){
                Ads ad = Ads.objectToAd(o);
                ads.add(ad);
            }
            
            return ads;
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
    
    public static List<Ads> getAllNonAcceptedAds() throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        List<Ads> ads = new ArrayList<>();
            
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllNonAcceptedAds");
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
            
            for(Object[] o : result){
                Ads ad = Ads.objectToAd(o);
                ads.add(ad);
            }
            
            return ads;
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
    
    public static void acceptAd(Integer id_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("acceptAd");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("id_in", id_in);
            
            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen hirdetés!");
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
    
    public static List<Ads> getAllAds() throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllAds");
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
            List<Ads> ads = new ArrayList<>();
            
            for(Object[] o : result){
                Ads ad = Ads.objectToAd(o);
                ads.add(ad);
            }
            
            return ads;
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
    
    public static Ads getAdsById(Integer id_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAdsById");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("id_in", id_in);
            
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
            if(!result.isEmpty()){
                Ads ad = Ads.objectToAd(result.get(0));
                return ad;
            }
            else{
                throw new NotFoundException("Nincs ilyen hirdetés!");
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
    
    public static List<Ads> getAllAdsByUserId(Integer user_id_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllAdsByUserId");
            
            spq.registerStoredProcedureParameter("user_id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("user_id_in", user_id_in);
            
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
            List<Ads> ads = new ArrayList<>();
            
            for(Object[] o : result){
                Ads ad = Ads.objectToAd(o);
                ads.add(ad);
            }
            
            return ads;
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
    
    public static List<Ads> filteringAds(Ads ad_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        String[] eljarasNevek = {"getAllAcceptedAds", "getJobFilteredAds", "getCountyFilteredAds", "getFilteredAds"};
        Integer eljarasSzama;
        //eldönteni melyik eljárást érdemes meghívni
        if(ad_in.getJobTagId() != null && ad_in.getCountyId() != null){
            eljarasSzama = 3;
        }
        else if(ad_in.getJobTagId() == null && ad_in.getCountyId() == null){
            eljarasSzama = 0;
        }
        else if(ad_in.getJobTagId() != null){
            eljarasSzama = 1;
        }
        else{
            eljarasSzama = 2;
        }
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery(eljarasNevek[eljarasSzama]);
            
            if(eljarasSzama != 0){
                if(eljarasSzama == 3 || eljarasSzama == 2){
                    spq.registerStoredProcedureParameter("county_id_in", Integer.class, ParameterMode.IN);
                    spq.setParameter("county_id_in", Counties.getCountyById(ad_in.getCountyId()).getId());
                }
                if(eljarasSzama == 3 || eljarasSzama == 1){
                    spq.registerStoredProcedureParameter("job_tag_id_in", Integer.class, ParameterMode.IN);
                    spq.setParameter("job_tag_id_in", JobTags.getJobTagById(ad_in.getJobTagId()).getId());
                }
            }
            
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
            List<Ads> ads = new ArrayList<>();
            
            for(Object[] o : result){
                Ads ad = Ads.objectToAd(o);
                ads.add(ad);
            }
            
            return ads;
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
    
    public static void deleteAd(Integer id_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteAd");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);

            spq.setParameter("id_in", id_in);

            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen hirdetés!");
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
    
}
