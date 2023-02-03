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
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import szakemberkereso.Configuration.Database;

/**
 *
 * @author Sharkz
 */
@Entity
@Table(name = "counties")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Counties.findAll", query = "SELECT c FROM Counties c"),
    @NamedQuery(name = "Counties.findById", query = "SELECT c FROM Counties c WHERE c.id = :id"),
    @NamedQuery(name = "Counties.findByName", query = "SELECT c FROM Counties c WHERE c.name = :name")})
public class Counties implements Serializable {

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

    public Counties() {
    }

    public Counties(Integer id) {
        this.id = id;
    }

    public Counties(Integer id, String name) {
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
        if (!(object instanceof Counties)) {
            return false;
        }
        Counties other = (Counties) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "szakemberkereso.Model.Counties[ id=" + id + " ]";
    }
    
    public static List<Counties> getAllCounties(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        List<Counties> counties = new ArrayList();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllCounties");
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
            
            for(Object[] r : result){
                Integer r_id = Integer.parseInt(r[0].toString());
                String r_name = r[1].toString();
                
                Counties c = new Counties(r_id, r_name);
                counties.add(c);
            }
            
            return counties;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return counties;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
    }
    
}
