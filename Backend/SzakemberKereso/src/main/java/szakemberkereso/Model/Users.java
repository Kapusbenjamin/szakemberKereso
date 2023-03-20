/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.NotFoundException;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import szakemberkereso.Configuration.Database;
import szakemberkereso.Configuration.Email;
import szakemberkereso.Configuration.Email.EmailConfig;
import szakemberkereso.Exception.EmailException;
import szakemberkereso.Exception.PasswordException;

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
    private Integer accessType;
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
    private Integer addressId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private Integer status;
    @Column(name = "token")
    private String token;
    @Column(name = "token_expired_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tokenExpiredAt;
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
    private Integer deleted;

    //jogosultság miatt
    @Transient
    @JsonInclude
    private Integer currentUserId;
    //user létrehozásánál az address és cég adatokhoz (mivel csak address_id van)
    @Transient
    @JsonInclude
    private Addresses address;
    @Transient
    @JsonInclude
    private Companies company;
    //szakmák
    @Transient
    @JsonInclude
    private List<JobTags> jobTags;
    
    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Users(Integer id, String firstName, String lastName, Integer accessType, String email, String phone, String password, Integer companyId, Integer addressId, Integer status, String token, Date tokenExpiredAt, Date lastLoginAt, Date createdAt, Date activatedAt, Date updatedAt, Integer deleted) {
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
        this.token = token;
        this.tokenExpiredAt = tokenExpiredAt;
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

    public Integer getAccessType() {
        return accessType;
    }

    public void setAccessType(Integer accessType) {
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

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenExpiredAt() {
        return tokenExpiredAt;
    }

    public void setTokenExpiredAt(Date tokenExpiredAt) {
        this.tokenExpiredAt = tokenExpiredAt;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Addresses getAddress() {
        return address;
    }

    public void setAddress(Addresses address) {
        this.address = address;
    }

    public Companies getCompany() {
        return company;
    }

    public void setCompany(Companies company) {
        this.company = company;
    }

    public Integer getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Integer currentUserId) {
        this.currentUserId = currentUserId;
    }

    public List<JobTags> getJobTags() {
        return jobTags;
    }

    public void setJobTags(List<JobTags> jobTags) {
        this.jobTags = jobTags;
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
    
    public static Users objectToUser(Object[] o) throws Exception{
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
        String o_token = o[10] != null ? o[10].toString() : null;
        Date o_token_expired_at = o[11] != null ? Timestamp.valueOf(o[11].toString()) : null;
        Date o_last_login_at = o[12] != null ? Timestamp.valueOf(o[12].toString()) : null;
        Date o_created_at = Timestamp.valueOf(o[13].toString());
        Date o_activated_at = o[14] != null ? Timestamp.valueOf(o[14].toString()) : null;
        Date o_updated_at = Timestamp.valueOf(o[15].toString());
        Integer o_deleted = Integer.parseInt(o[16].toString());

        Users u = new Users(o_id, o_first_name, o_last_name, o_access_type, o_email, o_phone, o_password, o_company_id, o_address_id, o_status, o_token, o_token_expired_at, o_last_login_at, o_created_at, o_activated_at, o_updated_at, o_deleted);
//        
//        if(u.getCompanyId() != null){
//            u.setCompany(Companies.getCompanyById(u.getCompanyId()));
//        }
//        u.setAddress(Addresses.getAddressById(u.getAddressId()));
//        if(u.getAccessType() > 0){
//            u.setJobTags(UsersJobs.getAllJobsByUser(u.getId()));
//        }
//        
        return u;
    }
    
    public static Users getUserById(Integer id_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getUserById");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("id_in", id_in);
            
            spq.execute();
            List<Object[]> result = spq.getResultList();
            
            if(!result.isEmpty()){
                Object[] r = result.get(0);
                Users u = Users.objectToUser(r);

                u.setPassword(null);
                u.setToken(null);
                u.setTokenExpiredAt(null);
                
                return u;
            }
            else{
                throw new NotFoundException("Nincs ilyen felhasználó!");
            }
        } 
        catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        catch(Exception e){
            throw new Exception(e.getMessage() + "getUserById" + id_in);
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
    }
    
    public static Users loginUser(String us_in, String psw_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        psw_in = DigestUtils.sha256Hex(psw_in);
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("loginUser");
            
            spq.registerStoredProcedureParameter("us_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("psw_in", String.class, ParameterMode.IN);
            
            spq.setParameter("us_in", us_in);
            spq.setParameter("psw_in", psw_in);
            
            spq.execute();
            List<Object[]> result = spq.getResultList();
            
            if(!result.isEmpty()){
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
            else{
                throw new NotFoundException("Nincs ilyen felhasználó!");
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
    
    public static void logoutUser(Integer id_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("logoutUser");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("id_in", id_in);
            
            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen felhasználó!");
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
    
    public static void deleteUser(Integer id_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteUser");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("id_in", id_in);
            
            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen felhasználó!");
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
    
    public static void changeAccess(Integer id_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("changeAccess");
            
            spq.registerStoredProcedureParameter("user_id_in", Integer.class, ParameterMode.IN);
            spq.setParameter("user_id_in", id_in);
            
            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen felhasználó!");
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
    
    public static List<Users> getAllUsers() throws Exception{
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
    
    public static Integer createUser(Users user) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        //Generate a random token
        int length = 40;
        boolean useLetters = true;
        boolean useNumbers = true;
        String token = RandomStringUtils.random(length, useLetters, useNumbers);

        //jelszótitkosítás
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("createUser");
            
            spq.registerStoredProcedureParameter("first_name_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("last_name_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("email_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("phone_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("password_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("county_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("zip_code_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("city_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("street_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("number_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("staircase_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("floor_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("door_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("token_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("user_id_out", Integer.class, ParameterMode.OUT);

            spq.setParameter("first_name_in", user.getFirstName());
            spq.setParameter("last_name_in", user.getLastName());
            spq.setParameter("email_in", user.getEmail());
            spq.setParameter("phone_in", user.getPhone());
            spq.setParameter("password_in", user.getPassword());
            spq.setParameter("county_id_in", Counties.getCountyById(user.getAddress().getCountyId()).getId());
            spq.setParameter("zip_code_in", user.getAddress().getZipCode());
            spq.setParameter("city_in", user.getAddress().getCity());
            spq.setParameter("street_in", user.getAddress().getStreet());
            spq.setParameter("number_in", user.getAddress().getNumber());
            spq.setParameter("staircase_in", user.getAddress().getStaircase());
            spq.setParameter("floor_in", user.getAddress().getFloor());
            spq.setParameter("door_in", user.getAddress().getDoor());
            spq.setParameter("token_in", token);

            spq.execute();
            
            registrationEmail(user.getEmail(), token);
            
            Integer user_id = Integer.parseInt(spq.getOutputParameterValue("user_id_out").toString());
            return user_id;
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
    
    public static Integer createUserWorker(Users user) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        //Generate a random token
        int length = 40;
        boolean useLetters = true;
        boolean useNumbers = true;
        String token = RandomStringUtils.random(length, useLetters, useNumbers);
        
        //jelszótitkosítás
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("createUserWorker");
            
            spq.registerStoredProcedureParameter("first_name_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("last_name_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("email_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("phone_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("password_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("county_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("zip_code_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("city_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("street_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("number_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("staircase_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("floor_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("door_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("company_name_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("premise_county_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("premise_zip_code_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("premise_city_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("premise_street_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("premise_number_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("premise_staircase_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("premise_floor_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("premise_door_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("tax_number_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("token_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("user_id_out", Integer.class, ParameterMode.OUT);

            spq.setParameter("first_name_in", user.getFirstName());
            spq.setParameter("last_name_in", user.getLastName());
            spq.setParameter("email_in", user.getEmail());
            spq.setParameter("phone_in", user.getPhone());
            spq.setParameter("password_in", user.getPassword());
            spq.setParameter("county_id_in", Counties.getCountyById(user.getAddress().getCountyId()).getId());
            spq.setParameter("zip_code_in", user.getAddress().getZipCode());
            spq.setParameter("city_in", user.getAddress().getCity());
            spq.setParameter("street_in", user.getAddress().getStreet());
            spq.setParameter("number_in", user.getAddress().getNumber());
            spq.setParameter("staircase_in", user.getAddress().getStaircase());
            spq.setParameter("floor_in", user.getAddress().getFloor());
            spq.setParameter("door_in", user.getAddress().getDoor());
            spq.setParameter("company_name_in", user.getCompany().getName());
            spq.setParameter("premise_county_id_in", Counties.getCountyById(user.getCompany().getAddress().getCountyId()).getId());
            spq.setParameter("premise_zip_code_in", user.getCompany().getAddress().getZipCode());
            spq.setParameter("premise_city_in", user.getCompany().getAddress().getCity());
            spq.setParameter("premise_street_in", user.getCompany().getAddress().getStreet());
            spq.setParameter("premise_number_in", user.getCompany().getAddress().getNumber());
            spq.setParameter("premise_staircase_in", user.getCompany().getAddress().getStaircase());
            spq.setParameter("premise_floor_in", user.getCompany().getAddress().getFloor());
            spq.setParameter("premise_door_in", user.getCompany().getAddress().getDoor());
            spq.setParameter("tax_number_in", user.getCompany().getTaxNumber());
            spq.setParameter("token_in", token);
            
            spq.execute();
            
            registrationEmail(user.getEmail(), token);
            
            Integer user_id = Integer.parseInt(spq.getOutputParameterValue("user_id_out").toString());
            return user_id;
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
    
    public static void updateUser(Users user) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateUser");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("first_name_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("last_name_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("email_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("phone_in", String.class, ParameterMode.IN);

            spq.setParameter("id_in", user.getId());
            spq.setParameter("first_name_in", user.getFirstName());
            spq.setParameter("last_name_in", user.getLastName());
            spq.setParameter("email_in", user.getEmail());
            spq.setParameter("phone_in", user.getPhone());

            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen felhasználó!");
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
    
    public static void changePassword(Users user) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("changePassword");
            
            spq.registerStoredProcedureParameter("id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("password_in", String.class, ParameterMode.IN);

            spq.setParameter("id_in", user.getId());
            spq.setParameter("password_in", user.getPassword());

            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen felhasználó!");
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
    
    public static void resetPassword(String email, String password, String pwtoken) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();

        password = DigestUtils.sha256Hex(password);
        
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("resetPassword");

            spq.registerStoredProcedureParameter("email_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("token_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("psw_in", String.class, ParameterMode.IN);

            spq.setParameter("email_in", email);
            spq.setParameter("token_in", pwtoken);
            spq.setParameter("psw_in", password);
            
            spq.execute();

            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen felhasználó!");
            }
        } 
        catch(NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
        catch(Exception e){
            throw new Exception("Valami hiba történt! (" + e.getMessage() + ")");
        }
        finally{
            //clean up metods, and close connections
            em.clear();
            em.close();
            emf.close();
        }
    }
    
    public static void forgotPassword(String email) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();

        //Generate a random token
        int length = 40;
        boolean useLetters = true;
        boolean useNumbers = true;
        String token = RandomStringUtils.random(length, useLetters, useNumbers);
        
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("forgotPassword");

            spq.registerStoredProcedureParameter("email_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("token_in", String.class, ParameterMode.IN);

            spq.setParameter("email_in", email);
            spq.setParameter("token_in", token);
            
            spq.execute();
            
            if(spq.getUpdateCount() > 0){
                forgotPasswordEmail(email, token);
            }
            else{
                throw new NotFoundException("Nincs ilyen felhasználó!");
            }
        } 
        catch(NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
        catch(Exception e){
            throw new Exception("Valami hiba történt! (" + e.getMessage() + ")");
        }
        finally{
            //clean up metods, and close connections
            em.clear();
            em.close();
            emf.close();
        }
    }
    
    public static void forgotPasswordEmail(String email, String token) throws Exception{
        try{
            //who will get the email
            String to = email;
            //Who is the sender of the email
            String from = Email.EmailConfig.EMAIL.get();
            
            //Confirmation link
            System.err.println("Token: " + token);
            String link = EmailConfig.PWLINK.get() + token;
            
            Properties properties = System.getProperties();
            //setup mail server to properties
            properties.put("mail.smtp.host", Email.EmailConfig.HOST.get());
            properties.put("mail.smtp.port", Email.EmailConfig.PORT.get());
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");
            
            //Get session object, and pass username and password in
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Email.EmailConfig.EMAIL.get(), Email.EmailConfig.PASSWORD.get());
                }
            });
            session.setDebug(true);
            
            //Configure the email (message object)
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            
            message.setSubject("Password replacement");
            message.setText("Your new password link is: " + link);
            
            //Send the email
            Transport.send(message);
        }
        catch(Exception e){
            throw new Exception("Valami hiba történt! (" + e.getMessage() + ")");
        }
    }
    
    public static void validateEmailByToken(String token_in) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("validateEmailByToken");
            
            spq.registerStoredProcedureParameter("token_in", String.class, ParameterMode.IN);
            spq.setParameter("token_in", token_in);

            spq.execute();
            
            if(spq.getUpdateCount() < 1){
                throw new NotFoundException("Nincs ilyen felhasználó!");
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
    
    public static void registrationEmail(String email, String token) throws Exception{
        try{
            //who will get the email
            String to = email;
            //Who is the sender of the email
            String from = Email.EmailConfig.EMAIL.get();
            
            //Confirmation link
            System.err.println("Token: " + token);
            String link = EmailConfig.CONFIRMLINK.get() + token;
            
            Properties properties = System.getProperties();
            //setup mail server to properties
            properties.put("mail.smtp.host", Email.EmailConfig.HOST.get());
            properties.put("mail.smtp.port", Email.EmailConfig.PORT.get());
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");
            
            //Get session object, and pass username and password in
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Email.EmailConfig.EMAIL.get(), Email.EmailConfig.PASSWORD.get());
                }
            });
            session.setDebug(true);
            
            //Configure the email (message object)
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            
            message.setSubject("Welcome");
            message.setText("Thanks for the registration to our service, and enjoy your journey with our application! \n Confirm link : " + link);
            
            //Send the email
            Transport.send(message);    
        }
        catch(Exception e){
            throw new Exception("Valami hiba történt! (" + e.getMessage() + ")");
        }
    }
    
    public static void isEmailUnique(String email) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("isEmailUnique");
            
            spq.registerStoredProcedureParameter("email_in", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("result", Integer.class, ParameterMode.OUT);

            spq.setParameter("email_in", email);

            spq.execute();
            
            Integer result = Integer.parseInt(spq.getOutputParameterValue("result").toString());
            
            if(result == 0){
                throw new EmailException("Ilyen E-mail cím már létezik!");
            }
        } 
        catch(EmailException e){
            throw new EmailException(e.getMessage());
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
