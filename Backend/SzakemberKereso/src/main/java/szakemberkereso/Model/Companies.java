/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.ws.rs.NotFoundException;
import javax.xml.bind.annotation.XmlRootElement;
import szakemberkereso.Configuration.Database;

/**
 *
 * @author Sharkz
 */
@Entity
@Table(name = "companies")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Companies.findAll", query = "SELECT c FROM Companies c"),
    @NamedQuery(name = "Companies.findById", query = "SELECT c FROM Companies c WHERE c.id = :id"),
    @NamedQuery(name = "Companies.findByName", query = "SELECT c FROM Companies c WHERE c.name = :name"),
    @NamedQuery(name = "Companies.findByAddressId", query = "SELECT c FROM Companies c WHERE c.addressId = :addressId"),
    @NamedQuery(name = "Companies.findByTaxNumber", query = "SELECT c FROM Companies c WHERE c.taxNumber = :taxNumber")})
public class Companies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "address_id")
    private int addressId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "tax_number")
    private String taxNumber;
    
    //jogosultság miatt
    @Transient
    @JsonInclude
    private Integer currentUserId;
    //address adatokhoz (id miatt)
    @Transient
    @JsonInclude
    private Addresses address;
    
    public Companies() {
    }

    public Companies(Integer id) {
        this.id = id;
    }

    public Companies(Integer id, String name, int addressId, String taxNumber) {
        this.id = id;
        this.name = name;
        this.addressId = addressId;
        this.taxNumber = taxNumber;
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public Addresses getAddress() {
        return address;
    }

    public void setAddress(Addresses address) {
        this.address = address;
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
        if (!(object instanceof Companies)) {
            return false;
        }
        Companies other = (Companies) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "szakemberkereso.Model.Companies[ id=" + id + " ]";
    }
    
    public static Companies getCompanyById(Integer id_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getCompanyById");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            
            spq.setParameter("id_in", id_in);
            
            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen cég!");
            }
            else{
                List<Object[]> result = spq.getResultList();
                Object[] r = result.get(0);

                Integer r_id = Integer.parseInt(r[0].toString());
                String r_name = r[1].toString();
                Integer r_address_id = Integer.parseInt(r[2].toString());
                String r_tax_number = r[3].toString();

                Companies c = new Companies(r_id, r_name, r_address_id, r_tax_number);
                c.setAddress(Addresses.getAddressById(c.getAddressId()));
                return c;
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
    
    public static String createCompany(Companies company) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("createCompany");
            
            spq.registerStoredProcedureParameter("company_name_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("county_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("zip_code_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("city_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("street_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("number_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("staircase_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("floor_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("door_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("tax_number_in", String.class, ParameterMode.IN);

            spq.setParameter("company_name_in", company.getName());
            spq.setParameter("county_id_in", company.getAddress().getCountyId());
            spq.setParameter("zip_code_in", company.getAddress().getZipCode());
            spq.setParameter("city_in", company.getAddress().getCity());
            spq.setParameter("street_in", company.getAddress().getStreet());
            spq.setParameter("number_in", company.getAddress().getNumber());
            spq.setParameter("staircase_in", company.getAddress().getStaircase());
            spq.setParameter("floor_in", company.getAddress().getFloor());
            spq.setParameter("door_in", company.getAddress().getDoor());
            spq.setParameter("tax_number_in", company.getTaxNumber());

            spq.execute();
            return "Sikeresen létrejött a cég";
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
    
    public static String updateCompanyById(Companies company) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateCompanyById");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("name_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("tax_number_in", String.class, ParameterMode.IN);

            spq.setParameter("id_in", company.getId());
            spq.setParameter("name_in", company.getName());
            spq.setParameter("tax_number_in", company.getTaxNumber());

            spq.execute();
            return "Sikeresen módosult a cég";
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
    
    public static Boolean deleteCompanyById(Companies company_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteCompanyById");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);

            spq.setParameter("id_in", company_in.getId());

            spq.execute();
            return true;
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
