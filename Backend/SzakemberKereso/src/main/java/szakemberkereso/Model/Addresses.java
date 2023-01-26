/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Model;

import java.io.Serializable;
import java.sql.CallableStatement;
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
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import szakemberkereso.Configuration.Database;
/**
 *
 * @author Sharkz
 */
@Entity
@Table(name = "addresses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Addresses.findAll", query = "SELECT a FROM Addresses a"),
    @NamedQuery(name = "Addresses.findById", query = "SELECT a FROM Addresses a WHERE a.id = :id"),
    @NamedQuery(name = "Addresses.findByCountyId", query = "SELECT a FROM Addresses a WHERE a.countyId = :countyId"),
    @NamedQuery(name = "Addresses.findByZipCode", query = "SELECT a FROM Addresses a WHERE a.zipCode = :zipCode"),
    @NamedQuery(name = "Addresses.findByCity", query = "SELECT a FROM Addresses a WHERE a.city = :city"),
    @NamedQuery(name = "Addresses.findByStreet", query = "SELECT a FROM Addresses a WHERE a.street = :street"),
    @NamedQuery(name = "Addresses.findByNumber", query = "SELECT a FROM Addresses a WHERE a.number = :number"),
    @NamedQuery(name = "Addresses.findByStaircase", query = "SELECT a FROM Addresses a WHERE a.staircase = :staircase"),
    @NamedQuery(name = "Addresses.findByFloor", query = "SELECT a FROM Addresses a WHERE a.floor = :floor"),
    @NamedQuery(name = "Addresses.findByDoor", query = "SELECT a FROM Addresses a WHERE a.door = :door")})
public class Addresses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "county_id")
    private int countyId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "zip_code")
    private int zipCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "street")
    private String street;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "number")
    private String number;
    @Size(max = 30)
    @Column(name = "staircase")
    private String staircase;
    @Column(name = "floor")
    private Integer floor;
    @Column(name = "door")
    private Integer door;

    public Addresses() {
    }

    public Addresses(Integer id) {
        this.id = id;
    }

    public Addresses(Integer id, int countyId, int zipCode, String city, String street, String number, String staircase, Integer floor, Integer door) {
        this.id = id;
        this.countyId = countyId;
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.number = number;
        this.staircase = staircase;
        this.floor = floor;
        this.door = door;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStaircase() {
        return staircase;
    }

    public void setStaircase(String staircase) {
        this.staircase = staircase;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getDoor() {
        return door;
    }

    public void setDoor(Integer door) {
        this.door = door;
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
        if (!(object instanceof Addresses)) {
            return false;
        }
        Addresses other = (Addresses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "szakemberkereso.Model.Addresses[ id=" + id + " ]";
    }
    
    public static Addresses getAddressById(Integer id_in){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAddressById");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            
            spq.setParameter("id_in", id_in);
            
            spq.execute();
            List<Object[]> result = spq.getResultList();
            Object[] r = result.get(0);
            
            Integer r_id = Integer.parseInt(r[0].toString());
            Integer r_county_id = Integer.parseInt(r[1].toString());
            Integer r_zip_code = Integer.parseInt(r[2].toString());
            String r_city = r[3].toString();
            String r_street = r[4].toString();
            String r_number = r[5].toString();
            String r_staircase = r[6] != null ? r[6].toString() : null;
            Integer r_floor = r[7] != null ? Integer.parseInt(r[7].toString()) : null;
            Integer r_door = r[8] != null ? Integer.parseInt(r[8].toString()) : null;
            
            return new Addresses(r_id, r_county_id, r_zip_code, r_city, r_street, r_number, r_staircase, r_floor, r_door);
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new Addresses();
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
    }
    
    public static String createAddress(Addresses a){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("createAddress");
            
            spq.registerStoredProcedureParameter("county_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("zip_code_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("city_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("street_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("number_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("staircase_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("floor_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("door_in", Integer.class, ParameterMode.IN);

            spq.setParameter("county_id_in", a.getCountyId());
            spq.setParameter("zip_code_in", a.getZipCode());
            spq.setParameter("city_in", a.getCity());
            spq.setParameter("street_in", a.getStreet());
            spq.setParameter("number_in", a.getNumber());
            spq.setParameter("staircase_in", a.getStaircase());
            spq.setParameter("floor_in", a.getFloor());
            spq.setParameter("door_in", a.getDoor());

            spq.execute();
            return "Sikeresen létrejött az address";
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
    
    public static String updateAddressById(Addresses a){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateAddressById");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("county_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("zip_code_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("city_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("street_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("number_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("staircase_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("floor_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("door_in", Integer.class, ParameterMode.IN);

            spq.setParameter("id_in", a.getId());
            spq.setParameter("county_id_in", a.getCountyId());
            spq.setParameter("zip_code_in", a.getZipCode());
            spq.setParameter("city_in", a.getCity());
            spq.setParameter("street_in", a.getStreet());
            spq.setParameter("number_in", a.getNumber());
            spq.setParameter("staircase_in", a.getStaircase());
            spq.setParameter("floor_in", a.getFloor());
            spq.setParameter("door_in", a.getDoor());

            spq.execute();
            return "Sikeresen módosult az address";
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
    
    public static Boolean deleteAddressById(Integer id_in){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteAddressById");
            
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
