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
@Table(name = "ratings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ratings.findAll", query = "SELECT r FROM Ratings r"),
    @NamedQuery(name = "Ratings.findById", query = "SELECT r FROM Ratings r WHERE r.id = :id"),
    @NamedQuery(name = "Ratings.findByRatingedUserId", query = "SELECT r FROM Ratings r WHERE r.ratingedUserId = :ratingedUserId"),
    @NamedQuery(name = "Ratings.findByRatingerUserId", query = "SELECT r FROM Ratings r WHERE r.ratingerUserId = :ratingerUserId"),
    @NamedQuery(name = "Ratings.findByRatingsStars", query = "SELECT r FROM Ratings r WHERE r.ratingsStars = :ratingsStars"),
    @NamedQuery(name = "Ratings.findByStatus", query = "SELECT r FROM Ratings r WHERE r.status = :status"),
    @NamedQuery(name = "Ratings.findByUpdatedAt", query = "SELECT r FROM Ratings r WHERE r.updatedAt = :updatedAt"),
    @NamedQuery(name = "Ratings.findByDeleted", query = "SELECT r FROM Ratings r WHERE r.deleted = :deleted")})
public class Ratings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ratinged_user_id")
    private int ratingedUserId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ratinger_user_id")
    private int ratingerUserId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ratings_stars")
    private int ratingsStars;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deleted")
    private int deleted;

    //jogosultság miatt
    @Transient
    @JsonInclude
    private Integer currentUserId;
    //id miatt
    @Transient
    @JsonInclude
    private Users ratinger;
    //id miatt
    @Transient
    @JsonInclude
    private Users ratinged;
    
    public Ratings() {
    }

    public Ratings(Integer id) {
        this.id = id;
    }

    public Ratings(Integer id, int ratingedUserId, int ratingerUserId, String description, int ratingsStars, int status, Date updatedAt, int deleted) {
        this.id = id;
        this.ratingedUserId = ratingedUserId;
        this.ratingerUserId = ratingerUserId;
        this.description = description;
        this.ratingsStars = ratingsStars;
        this.status = status;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRatingedUserId() {
        return ratingedUserId;
    }

    public void setRatingedUserId(int ratingedUserId) {
        this.ratingedUserId = ratingedUserId;
    }

    public int getRatingerUserId() {
        return ratingerUserId;
    }

    public void setRatingerUserId(int ratingerUserId) {
        this.ratingerUserId = ratingerUserId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRatingsStars() {
        return ratingsStars;
    }

    public void setRatingsStars(int ratingsStars) {
        this.ratingsStars = ratingsStars;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Integer getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Integer currentUserId) {
        this.currentUserId = currentUserId;
    }

    public Users getRatinger() {
        return ratinger;
    }

    public void setRatinger(Users ratinger) {
        this.ratinger = ratinger;
    }

    public Users getRatinged() {
        return ratinged;
    }

    public void setRatinged(Users ratinged) {
        this.ratinged = ratinged;
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
        if (!(object instanceof Ratings)) {
            return false;
        }
        Ratings other = (Ratings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "szakemberkereso.Model.Ratings[ id=" + id + " ]";
    }
    
    public static Ratings objectToRating(Object[] o) throws Exception{
        Integer o_id = Integer.parseInt(o[0].toString());
        Integer o_ratinged_user_id = Integer.parseInt(o[1].toString());
        Integer o_ratinger_user_id = Integer.parseInt(o[2].toString());
        String o_description = o[3].toString();
        Integer o_ratings_stars = Integer.parseInt(o[4].toString());
        Integer o_status = Integer.parseInt(o[5].toString());
        Date o_updated_at = Timestamp.valueOf(o[6].toString());
        Integer o_deleted = Integer.parseInt(o[7].toString());

        Ratings rating = new Ratings(o_id, o_ratinged_user_id, o_ratinger_user_id, o_description, o_ratings_stars, o_status, o_updated_at, o_deleted);
        
        rating.setRatinged(Users.getUserById(rating.getRatingedUserId()));
        rating.setRatinger(Users.getUserById(rating.getRatingerUserId()));
        
        return rating;
    }

    public static Ratings getRatingById(Integer id_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getRatingById");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("id_in", id_in);
            
            spq.execute();
            List<Object[]> result = spq.getResultList();
            
            if(!result.isEmpty()){
                Object[] r = result.get(0);

                Ratings rating = Ratings.objectToRating(r);
                return rating;
            }
            else{
                throw new NotFoundException("Nincs ilyen értékelés!");
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
    
    public static List<Ratings> getAllRatings() throws Exception, Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        List<Ratings> ratings = new ArrayList<>();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllRatings");            
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
            
            for(Object[] r : result){
                Ratings rating = Ratings.objectToRating(r);
                ratings.add(rating);
            }
            
            return ratings;
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
    
    public static List<Ratings> getAllNotAcceptedRatings() throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        List<Ratings> ratings = new ArrayList<>();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllNotAcceptedRatings");            
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
            
            for(Object[] r : result){
                Ratings rating = Ratings.objectToRating(r);
                ratings.add(rating);
            }
            
            return ratings;
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
    
    public static List<Ratings> getAllRatingsByRatinger(Integer user_id_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        List<Ratings> ratings = new ArrayList<>();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllRatingsByRatinger");            
            spq.registerStoredProcedureParameter("user_id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("user_id_in", Users.getIdIfUserValid(user_id_in));
            
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
            
            for(Object[] r : result){
                Ratings rating = Ratings.objectToRating(r);
                ratings.add(rating);
            }
            
            return ratings;
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
    
    public static List<Ratings> getAllRatingsByRatinged(Integer user_id_in) throws Exception, Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        List<Ratings> ratings = new ArrayList<>();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllRatingsByRatinged");            
            spq.registerStoredProcedureParameter("user_id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("user_id_in", Users.getIdIfUserValid(user_id_in));
            
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
            
            for(Object[] r : result){
                Ratings rating = Ratings.objectToRating(r);
                ratings.add(rating);
            }
            
            return ratings;
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
    
    public static void updateRatingById(Ratings rating) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateRatingById");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("desc_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ratings_stars_in", Integer.class, ParameterMode.IN);
            
            spq.setParameter("id_in", rating.getId());
            spq.setParameter("desc_in", rating.getDescription());
            spq.setParameter("ratings_stars_in", rating.getRatingsStars());
            
            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen értékelés!");
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
    
    public static void acceptRating(Integer id_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("acceptRating");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("id_in", id_in);
            
            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen értékelés!");
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
    
    public static void deleteRatingById(Integer id_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteRatingById");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("id_in", id_in);
            
            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen értékelés!");
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
    
    public static void createRating(Ratings rating) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("createRating");
            
            spq.registerStoredProcedureParameter("ratinged_user_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ratinger_user_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("desc_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ratings_stars_in", Integer.class, ParameterMode.IN);
            
            spq.setParameter("ratinged_user_id_in", Users.getIdIfUserValid(rating.getRatingedUserId()));
            spq.setParameter("ratinger_user_id_in", Users.getIdIfUserValid(rating.getRatingerUserId()));
            spq.setParameter("desc_in", rating.getDescription());
            spq.setParameter("ratings_stars_in", rating.getRatingsStars());
            
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
    
    public static Boolean canWriteRating(Ratings rating) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("canWriteRating");
            
            spq.registerStoredProcedureParameter("ratinged_user_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ratinger_user_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("result", Integer.class, ParameterMode.OUT);
            
            spq.setParameter("ratinged_user_id_in", Users.getIdIfUserValid(rating.getRatingedUserId()));
            spq.setParameter("ratinger_user_id_in", rating.getCurrentUserId());
           
            spq.execute();
            
            Integer result = Integer.parseInt(spq.getOutputParameterValue("result").toString());
            return result == 1;
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
