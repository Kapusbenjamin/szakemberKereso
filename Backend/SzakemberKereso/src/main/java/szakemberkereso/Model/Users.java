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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByFirstName", query = "SELECT u FROM Users u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "Users.findByLastName", query = "SELECT u FROM Users u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "Users.findByAccessType", query = "SELECT u FROM Users u WHERE u.accessType = :accessType"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByCompanyId", query = "SELECT u FROM Users u WHERE u.companyId = :companyId"),
    @NamedQuery(name = "Users.findByAddressId", query = "SELECT u FROM Users u WHERE u.addressId = :addressId"),
    @NamedQuery(name = "Users.findByStatus", query = "SELECT u FROM Users u WHERE u.status = :status"),
    @NamedQuery(name = "Users.findByLastLoginAt", query = "SELECT u FROM Users u WHERE u.lastLoginAt = :lastLoginAt"),
    @NamedQuery(name = "Users.findByCreatedAt", query = "SELECT u FROM Users u WHERE u.createdAt = :createdAt"),
    @NamedQuery(name = "Users.findByActivatedAt", query = "SELECT u FROM Users u WHERE u.activatedAt = :activatedAt"),
    @NamedQuery(name = "Users.findByUpdatedAt", query = "SELECT u FROM Users u WHERE u.updatedAt = :updatedAt"),
    @NamedQuery(name = "Users.findByDeleted", query = "SELECT u FROM Users u WHERE u.deleted = :deleted")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "access_type")
    private int accessType;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "email")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Column(name = "company_id")
    private Integer companyId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "address_id")
    private int addressId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Column(name = "last_login_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "activated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activatedAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deleted")
    private int deleted;

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Users(Integer id, String firstName, String lastName, int accessType, String email, String phone, String password, Integer companyId, int addressId, int status, Date lastLoginAt, Date createdAt, Date activatedAt, Date updatedAt, int deleted) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accessType = accessType;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.companyId = companyId;
        this.addressId = addressId;
        this.status = status;
        this.lastLoginAt = lastLoginAt;
        this.createdAt = createdAt;
        this.activatedAt = activatedAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAccessType() {
        return accessType;
    }

    public void setAccessType(int accessType) {
        this.accessType = accessType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(Date lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getActivatedAt() {
        return activatedAt;
    }

    public void setActivatedAt(Date activatedAt) {
        this.activatedAt = activatedAt;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "szakemberkereso.Model.Users[ id=" + id + " ]";
    }
    
    public static Users objectToUser(Object[] o){
        Integer o_id = o[0] != null ? Integer.parseInt(o[0].toString()) : null;
        String o_first_name = o[1].toString();
        String o_last_name = o[2].toString();
        Integer o_access_type = Integer.parseInt(o[3].toString());
        String o_email = o[4].toString();
        String o_phone = o[5].toString();
        String o_password = o[6].toString();
        Integer o_company_id = o[7] != null ? Integer.parseInt(o[7].toString()) : null;
        Integer o_address_id = Integer.parseInt(o[8].toString());
        Integer o_status = Integer.parseInt(o[9].toString());
        Date o_last_login_at = o[10] != null ? Timestamp.valueOf(o[10].toString()) : null;
        Date o_created_at = Timestamp.valueOf(o[11].toString());
        Date o_activated_at = o[12] != null ? Timestamp.valueOf(o[12].toString()) : null;
        Date o_updated_at = Timestamp.valueOf(o[13].toString());
        Integer o_deleted = Integer.parseInt(o[14].toString());

        return new Users(o_id, o_first_name, o_last_name, o_access_type, o_email, o_phone, o_password, o_company_id, o_address_id, o_status, o_last_login_at, o_created_at, o_activated_at, o_updated_at, o_deleted);
    }
    
    public static Users getUserById(Integer id_in){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getUserById");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            
            spq.setParameter("id_in", id_in);
            
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
            Object[] r = result.get(0);
            Users u = Users.objectToUser(r);
            
            return u;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            //return new Users();
            return new Users();
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
    }
    
    public static Users loginUser(String us_in, String psw_in){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("loginUser");
            
            spq.registerStoredProcedureParameter("us_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("psw_in", String.class, ParameterMode.IN);
            
            spq.setParameter("us_in", us_in);
            spq.setParameter("psw_in", psw_in);
            
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
            Object[] r = result.get(0);
            
            Integer r_id = Integer.parseInt(r[0].toString());
            String r_first_name = r[1].toString();
            String r_last_name = r[2].toString();
            Integer r_access_type = Integer.parseInt(r[3].toString());
            
            Users u = new Users();
            u.setId(r_id);
            u.setFirstName(r_first_name);
            u.setLastName(r_last_name);
            u.setAccessType(r_access_type);
            
            return u;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            //return new Users();
            return new Users();
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
    }
    
    public static Boolean logoutUser(Integer id_in){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("logoutUser");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            
            spq.setParameter("id_in", id_in);
            
            spq.execute();
            
            return true;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            //return new Users();
            return false;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
    }
    
    public static Boolean deleteUser(Integer id_in){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteUser");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            
            spq.setParameter("id_in", id_in);
            
            spq.execute();
            
            return true;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            //return new Users();
            return false;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
    }
    
    public static Boolean changeAccess(Integer id_in){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("changeAccess");
            
            spq.registerStoredProcedureParameter("user_id_in", Integer.class, ParameterMode.IN);
            
            spq.setParameter("user_id_in", id_in);
            
            spq.execute();
            
            return true;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            //return new Users();
            return false;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
    }
    
    public static List<Users> getAllUsers(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        List<Users> users = new ArrayList<>();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllUsers");
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
           
            for(Object[] r : result){
                Users u = Users.objectToUser(r);
                users.add(u);
            }
            
            return users;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            //return new Users();
            return users;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
    }
    
//    public static String createAddress(Addresses a){
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
//        EntityManager em = emf.createEntityManager();
//        
//        try {            
//            StoredProcedureQuery spq = em.createStoredProcedureQuery("createAddress");
//            
//            spq.registerStoredProcedureParameter("county_id_in", Integer.class, ParameterMode.IN);
//            spq.registerStoredProcedureParameter("zip_code_in", Integer.class, ParameterMode.IN);
//            spq.registerStoredProcedureParameter("city_in", String.class, ParameterMode.IN);
//            spq.registerStoredProcedureParameter("street_in", String.class, ParameterMode.IN);
//            spq.registerStoredProcedureParameter("number_in", String.class, ParameterMode.IN);
//            spq.registerStoredProcedureParameter("staircase_in", String.class, ParameterMode.IN);
//            spq.registerStoredProcedureParameter("floor_in", Integer.class, ParameterMode.IN);
//            spq.registerStoredProcedureParameter("door_in", Integer.class, ParameterMode.IN);
//
//            spq.setParameter("county_id_in", a.getCountyId());
//            spq.setParameter("zip_code_in", a.getZipCode());
//            spq.setParameter("city_in", a.getCity());
//            spq.setParameter("street_in", a.getStreet());
//            spq.setParameter("number_in", a.getNumber());
//            spq.setParameter("staircase_in", a.getStaircase());
//            spq.setParameter("floor_in", a.getFloor());
//            spq.setParameter("door_in", a.getDoor());
//
//            spq.execute();
//            return "Sikeresen létrejött az address";
//        } 
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//            return "HIBA: " + e.getMessage();
//        }
//        finally{
//            em.clear();
//            em.close();
//            emf.close();
//        }
//        
//    }
//    
//    public static String updateAddressById(Addresses a){
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
//        EntityManager em = emf.createEntityManager();
//        
//        try {            
//            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateAddressById");
//            
//            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
//            spq.registerStoredProcedureParameter("county_id_in", Integer.class, ParameterMode.IN);
//            spq.registerStoredProcedureParameter("zip_code_in", Integer.class, ParameterMode.IN);
//            spq.registerStoredProcedureParameter("city_in", String.class, ParameterMode.IN);
//            spq.registerStoredProcedureParameter("street_in", String.class, ParameterMode.IN);
//            spq.registerStoredProcedureParameter("number_in", String.class, ParameterMode.IN);
//            spq.registerStoredProcedureParameter("staircase_in", String.class, ParameterMode.IN);
//            spq.registerStoredProcedureParameter("floor_in", Integer.class, ParameterMode.IN);
//            spq.registerStoredProcedureParameter("door_in", Integer.class, ParameterMode.IN);
//
//            spq.setParameter("id_in", a.getId());
//            spq.setParameter("county_id_in", a.getCountyId());
//            spq.setParameter("zip_code_in", a.getZipCode());
//            spq.setParameter("city_in", a.getCity());
//            spq.setParameter("street_in", a.getStreet());
//            spq.setParameter("number_in", a.getNumber());
//            spq.setParameter("staircase_in", a.getStaircase());
//            spq.setParameter("floor_in", a.getFloor());
//            spq.setParameter("door_in", a.getDoor());
//
//            spq.execute();
//            return "Sikeresen módosult az address";
//        } 
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//            return "HIBA: " + e.getMessage();
//        }
//        finally{
//            em.clear();
//            em.close();
//            emf.close();
//        }
//        
//    }
//    
//    public static Boolean deleteAddressById(Integer id_in){
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
//        EntityManager em = emf.createEntityManager();
//        
//        try {            
//            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteAddressById");
//            
//            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
//
//            spq.setParameter("id_in", id_in);
//
//            spq.execute();
//            return true;
//        } 
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//        finally{
//            em.clear();
//            em.close();
//            emf.close();
//        }
//        
//    }
    
}
