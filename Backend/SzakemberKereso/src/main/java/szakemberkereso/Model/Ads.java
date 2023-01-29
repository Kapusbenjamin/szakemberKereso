/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Model;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "job_tag_id")
    private int jobTagId;
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
    private int status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deleted")
    private int deleted;

    public Ads() {
    }

    public Ads(Integer id) {
        this.id = id;
    }

    public Ads(Integer id, int userId, int jobTagId, String description, Date updatedAt, int status, int deleted) {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getJobTagId() {
        return jobTagId;
    }

    public void setJobTagId(int jobTagId) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
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
    
    public static Ads objectToAd(Object[] o){
        Integer o_id = Integer.parseInt(o[0].toString());
        Integer o_user_id = Integer.parseInt(o[1].toString());
        Integer o_job_tag_id = Integer.parseInt(o[2].toString());
        String o_desc = o[3].toString();
        Date o_updated_at = Timestamp.valueOf(o[4].toString());
        Integer o_status = Integer.parseInt(o[5].toString());
        Integer o_deleted = Integer.parseInt(o[6].toString());

        return new Ads(o_id, o_user_id, o_job_tag_id, o_desc, o_updated_at, o_status, o_deleted);
    }
            
    public static Integer createAd(Ads ad){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("createAd");
            
            spq.registerStoredProcedureParameter("user_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("job_tag_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("desc_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("last_id_out", Integer.class, ParameterMode.OUT);

            spq.setParameter("user_id_in", ad.getUserId());
            spq.setParameter("job_tag_id_in", ad.getJobTagId());
            spq.setParameter("desc_in", ad.getDescription());

            spq.execute();
            
            Integer result = Integer.parseInt(spq.getOutputParameterValue("last_id_out").toString());
            return result;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
        
    }
    
    public static String updateAd(Ads ad){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateAd");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("desc_in", String.class, ParameterMode.IN);

            spq.setParameter("id_in", ad.getId());
            spq.setParameter("desc_in", ad.getDescription());

            spq.execute();
            
            return "Sikeresen módosult a hirdetés";
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
        
    }
    
    public static List<Ads> getAllAcceptedAds(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllAcceptedAds");
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
            List<Ads> ads = new ArrayList<>();
            
            for(Object[] o : result){
                Ads ad = Ads.objectToAd(o);
                ads.add(ad);
            }
            
            return ads;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
        
    }
    
    public static List<Ads> getAllNonAcceptedAds(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllNonAcceptedAds");
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
            List<Ads> ads = new ArrayList<>();
            
            for(Object[] o : result){
                Ads ad = Ads.objectToAd(o);
                ads.add(ad);
            }
            
            return ads;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
        
    }
    
    public static Boolean acceptAds(Integer id_in){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("acceptAds");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("id_in", id_in);
            
            spq.execute();
            
            return true;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
        
    }
    
    public static List<Ads> getAllAds(){
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
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
        
    }
    
    public static Ads getAdsById(Integer id_in){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAdsById");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("id_in", id_in);
            
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
            Ads ad = Ads.objectToAd(result.get(0));
            return ad;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
        
    }
    
    public static List<Ads> getAllAdsByUserId(Integer user_id_in){
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
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
        
    }
    
    public static Boolean deleteAd(Integer id_in){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteAd");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);

            spq.setParameter("id_in", id_in);

            spq.execute();
            
            return true;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
        
    }
    
}
