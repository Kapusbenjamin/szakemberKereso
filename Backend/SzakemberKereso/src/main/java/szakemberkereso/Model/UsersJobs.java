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
import javax.xml.bind.annotation.XmlRootElement;
import szakemberkereso.Configuration.Database;

/**
 *
 * @author Sharkz
 */
@Entity
@Table(name = "users_jobs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersJobs.findAll", query = "SELECT u FROM UsersJobs u"),
    @NamedQuery(name = "UsersJobs.findById", query = "SELECT u FROM UsersJobs u WHERE u.id = :id"),
    @NamedQuery(name = "UsersJobs.findByUserId", query = "SELECT u FROM UsersJobs u WHERE u.userId = :userId"),
    @NamedQuery(name = "UsersJobs.findByJobTagId", query = "SELECT u FROM UsersJobs u WHERE u.jobTagId = :jobTagId")})
public class UsersJobs implements Serializable {

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

    //jogosultság miatt
    @Transient
    @JsonInclude
    private Integer currentUserId;
    
    public UsersJobs() {
    }

    public UsersJobs(Integer id) {
        this.id = id;
    }

    public UsersJobs(Integer id, int userId, int jobTagId) {
        this.id = id;
        this.userId = userId;
        this.jobTagId = jobTagId;
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
        if (!(object instanceof UsersJobs)) {
            return false;
        }
        UsersJobs other = (UsersJobs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "szakemberkereso.Model.UsersJobs[ id=" + id + " ]";
    }
    
    public static String addNewJobToUser(UsersJobs user_job){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewJobToUser");
            
            spq.registerStoredProcedureParameter("user_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("job_tag_id_in", Integer.class, ParameterMode.IN);

            spq.setParameter("user_id_in", user_job.getUserId());
            spq.setParameter("job_tag_id_in", user_job.getJobTagId());

            spq.execute();
            return "Sikeresen hozzáadta a userhez a szakmát";
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "HIBA: " + e.getMessage();
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
        
    }

    public static String deleteUserJob(UsersJobs user_job){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteUserJob");
            
            spq.registerStoredProcedureParameter("user_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("job_tag_id_in", Integer.class, ParameterMode.IN);

            spq.setParameter("user_id_in", user_job.getUserId());
            spq.setParameter("job_tag_id_in", user_job.getJobTagId());

            spq.execute();
            return "Sikeresen törölte a userhez tartozó szakmát";
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "HIBA: " + e.getMessage();
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
        
    }
    
    public static List<JobTags> getAllJobsByUser(Integer user_id_in){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllJobsByUser");
            
            spq.registerStoredProcedureParameter("user_id_in", Integer.class, ParameterMode.IN);

            spq.setParameter("user_id_in", user_id_in);

            spq.execute();
            
            List<Object[]> result = spq.getResultList();
            List<JobTags> job_tags = new ArrayList<>();
            
            for(Object[] job_tag : result){
                Integer job_tag_id = Integer.parseInt(job_tag[0].toString());
                String job_tag_name = job_tag[1].toString();
                
                JobTags jt = new JobTags(job_tag_id, job_tag_name);
                job_tags.add(jt);
            }
            
            return job_tags;
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
    
}
