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
@Table(name = "jobs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobs.findAll", query = "SELECT j FROM Jobs j"),
    @NamedQuery(name = "Jobs.findById", query = "SELECT j FROM Jobs j WHERE j.id = :id"),
    @NamedQuery(name = "Jobs.findByTotal", query = "SELECT j FROM Jobs j WHERE j.total = :total"),
    @NamedQuery(name = "Jobs.findByStatus", query = "SELECT j FROM Jobs j WHERE j.status = :status"),
    @NamedQuery(name = "Jobs.findByCustomerId", query = "SELECT j FROM Jobs j WHERE j.customerId = :customerId"),
    @NamedQuery(name = "Jobs.findByWorkerId", query = "SELECT j FROM Jobs j WHERE j.workerId = :workerId"),
    @NamedQuery(name = "Jobs.findByCustomerAccepted", query = "SELECT j FROM Jobs j WHERE j.customerAccepted = :customerAccepted"),
    @NamedQuery(name = "Jobs.findByWorkerAccepted", query = "SELECT j FROM Jobs j WHERE j.workerAccepted = :workerAccepted"),
    @NamedQuery(name = "Jobs.findByUpdatedAt", query = "SELECT j FROM Jobs j WHERE j.updatedAt = :updatedAt"),
    @NamedQuery(name = "Jobs.findByDeleted", query = "SELECT j FROM Jobs j WHERE j.deleted = :deleted")})
public class Jobs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private int total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_id")
    private int customerId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "worker_id")
    private int workerId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_accepted")
    private int customerAccepted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "worker_accepted")
    private int workerAccepted;
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
    private Users customer;
    //id miatt
    @Transient
    @JsonInclude
    private Users worker;
    
    public Jobs() {
    }

    public Jobs(Integer id) {
        this.id = id;
    }

    public Jobs(Integer id, String description, int total, int status, int customerId, int workerId, int customerAccepted, int workerAccepted, Date updatedAt, int deleted) {
        this.id = id;
        this.description = description;
        this.total = total;
        this.status = status;
        this.customerId = customerId;
        this.workerId = workerId;
        this.customerAccepted = customerAccepted;
        this.workerAccepted = workerAccepted;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public int getCustomerAccepted() {
        return customerAccepted;
    }

    public void setCustomerAccepted(int customerAccepted) {
        this.customerAccepted = customerAccepted;
    }

    public int getWorkerAccepted() {
        return workerAccepted;
    }

    public void setWorkerAccepted(int workerAccepted) {
        this.workerAccepted = workerAccepted;
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

    public Users getCustomer() {
        return customer;
    }

    public void setCustomer(Users customer) {
        this.customer = customer;
    }

    public Users getWorker() {
        return worker;
    }

    public void setWorker(Users worker) {
        this.worker = worker;
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
        if (!(object instanceof Jobs)) {
            return false;
        }
        Jobs other = (Jobs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "szakemberkereso.Model.Jobs[ id=" + id + " ]";
    }
    
    
    public static Jobs objectToJob(Object[] o) throws Exception{
        Integer o_id = Integer.parseInt(o[0].toString());
        String o_description = o[1].toString();
        Integer o_total = Integer.parseInt(o[2].toString());
        Integer o_status = Integer.parseInt(o[3].toString());
        Integer o_customer_id = Integer.parseInt(o[4].toString());
        Integer o_worker_id = Integer.parseInt(o[5].toString());
        Integer o_customer_accepted = Integer.parseInt(o[6].toString());
        Integer o_worker_accepted = Integer.parseInt(o[7].toString());
        Date o_updated_at = Timestamp.valueOf(o[8].toString());
        Integer o_deleted = Integer.parseInt(o[9].toString());

        Jobs j = new Jobs(o_id, o_description, o_total, o_status, o_customer_id, o_worker_id, o_customer_accepted, o_worker_accepted, o_updated_at, o_deleted);
        j.setCustomer(Users.getUserById(j.getCustomerId()));
        j.setWorker(Users.getUserById(j.getWorkerId()));
        return j;
    }
    
    public static Jobs getJobById(Jobs job_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getJobById");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            
            spq.setParameter("id_in", job_in.getId());
            
            spq.execute();
            List<Object[]> result = spq.getResultList();
            
            if(!result.isEmpty()){
                Object[] r = result.get(0);
                Jobs j = Jobs.objectToJob(r);

                return j;
            }
            else{
                throw new NotFoundException("Nincs ilyen munka!");
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
    
    public static void deleteJob(Jobs job_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteJob");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("id_in", job_in.getId());
            
            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen munka!");
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
    
    public static void changeJobStatus(Jobs job) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("changeJobStatus");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("id_in", job.getId());
            
            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen munka!");
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
    
    public static List<Jobs> getAllJobs() throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        List<Jobs> jobs = new ArrayList<>();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllJobs");
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
           
            for(Object[] r : result){
                Jobs j = Jobs.objectToJob(r);
                jobs.add(j);
            }
            
            return jobs;
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
    
    public static List<Jobs> getAllJobsByWorker(Jobs job_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        List<Jobs> jobs = new ArrayList<>();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllJobsByWorker");
            
            spq.registerStoredProcedureParameter("worker_id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("worker_id_in", Users.getUserById(job_in.getWorkerId()).getId());
            
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
           
            for(Object[] r : result){
                Jobs j = Jobs.objectToJob(r);
                jobs.add(j);
            }
            
            return jobs;
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
    
    public static List<Jobs> getAllJobsByCustomer(Jobs job_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        List<Jobs> jobs = new ArrayList<>();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllJobsByCustomer");
            
            spq.registerStoredProcedureParameter("customer_id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("customer_id_in", Users.getUserById(job_in.getCustomerId()).getId());
            
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
           
            for(Object[] r : result){
                Jobs j = Jobs.objectToJob(r);
                jobs.add(j);
            }
            
            return jobs;
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
    
    public static void createJob(Jobs job) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("createJob");
            
            spq.registerStoredProcedureParameter("customer_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("worker_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("desc_in", String.class, ParameterMode.IN);
            
            spq.setParameter("customer_id_in", Users.getUserById(job.getCustomerId()).getId());
            spq.setParameter("worker_id_in", Users.getUserById(job.getWorkerId()).getId());
            spq.setParameter("desc_in", job.getDescription());

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
    
    public static void updateJobByWorker(Jobs job) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateJobByWorker");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("total_in", Integer.class, ParameterMode.IN);

            spq.setParameter("id_in", job.getId());
            spq.setParameter("total_in", job.getTotal());

            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen munka!");
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
    
    public static void updateJobByCustomer(Jobs job) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateJobByCustomer");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("desc_in", String.class, ParameterMode.IN);

            spq.setParameter("id_in", job.getId());
            spq.setParameter("desc_in", job.getDescription());

            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen munka!");
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
    
    public static void acceptByWorker(Jobs job) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("acceptByWorker");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("id_in", job.getId());

            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen munka!");
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
    
    public static void acceptByCustomer(Jobs job) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("acceptByCustomer");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("id_in", job.getId());

            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen munka!");
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
