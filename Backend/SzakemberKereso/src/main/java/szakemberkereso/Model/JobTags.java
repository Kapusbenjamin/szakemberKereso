/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Model;

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
@Table(name = "job_tags")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobTags.findAll", query = "SELECT j FROM JobTags j"),
    @NamedQuery(name = "JobTags.findById", query = "SELECT j FROM JobTags j WHERE j.id = :id"),
    @NamedQuery(name = "JobTags.findByName", query = "SELECT j FROM JobTags j WHERE j.name = :name")})
public class JobTags implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;

    public JobTags() {
    }

    public JobTags(Integer id) {
        this.id = id;
    }

    public JobTags(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof JobTags)) {
            return false;
        }
        JobTags other = (JobTags) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "szakemberkereso.Model.JobTags[ id=" + id + " ]";
    }
    
    public static List<JobTags> getAllJobTags() throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        List<JobTags> jobTags = new ArrayList();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllJobTags");
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
            
            for(Object[] r : result){
                Integer r_id = Integer.parseInt(r[0].toString());
                String r_name = r[1].toString();
                
                JobTags jt = new JobTags(r_id, r_name);
                jobTags.add(jt);
            }
            
            return jobTags;
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
    
    public static JobTags getJobTagById(Integer id_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getJobTagById");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("id_in", id_in);
            
            spq.execute();
            List<Object[]> result = spq.getResultList();
            
            if(!result.isEmpty()){
                Object[] r = result.get(0);

                Integer r_id = Integer.parseInt(r[0].toString());
                String r_name = r[1].toString();

                JobTags jt = new JobTags(r_id, r_name);
                return jt;
            }
            else{
                throw new NotFoundException("Nincs ilyen szakma!");
            }
        } 
        catch(NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
        catch(Exception e){
            throw new Exception("Valami hiba történt! (" + e.getMessage() + ")" + "getJobTagById");
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
    }
    
}
