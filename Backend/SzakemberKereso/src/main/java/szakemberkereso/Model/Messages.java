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
@Table(name = "messages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Messages.findAll", query = "SELECT m FROM Messages m"),
    @NamedQuery(name = "Messages.findById", query = "SELECT m FROM Messages m WHERE m.id = :id"),
    @NamedQuery(name = "Messages.findByChatId", query = "SELECT m FROM Messages m WHERE m.chatId = :chatId"),
    @NamedQuery(name = "Messages.findBySenderId", query = "SELECT m FROM Messages m WHERE m.senderId = :senderId"),
    @NamedQuery(name = "Messages.findByReceiverId", query = "SELECT m FROM Messages m WHERE m.receiverId = :receiverId"),
    @NamedQuery(name = "Messages.findByChecked", query = "SELECT m FROM Messages m WHERE m.checked = :checked"),
    @NamedQuery(name = "Messages.findBySendedAt", query = "SELECT m FROM Messages m WHERE m.sendedAt = :sendedAt")})
public class Messages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "chat_id")
    private int chatId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sender_id")
    private int senderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "receiver_id")
    private int receiverId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "message")
    private String message;
    @Basic(optional = false)
    @NotNull
    @Column(name = "checked")
    private int checked;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sended_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendedAt;

    //jogosultság miatt
    @Transient
    @JsonInclude
    private Integer currentUserId;
    
    public Messages() {
    }

    public Messages(Integer id) {
        this.id = id;
    }

    public Messages(Integer id, int chatId, int senderId, int receiverId, String message, int checked, Date sendedAt) {
        this.id = id;
        this.chatId = chatId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.checked = checked;
        this.sendedAt = sendedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public Date getSendedAt() {
        return sendedAt;
    }

    public void setSendedAt(Date sendedAt) {
        this.sendedAt = sendedAt;
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
        if (!(object instanceof Messages)) {
            return false;
        }
        Messages other = (Messages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "szakemberkereso.Model.Messages[ id=" + id + " ]";
    }
    
    public static Messages objectToMessage(Object[] o){
        Integer o_id = Integer.parseInt(o[0].toString());
        Integer o_chat_id = Integer.parseInt(o[1].toString());
        Integer o_sender_id = Integer.parseInt(o[2].toString());
        Integer o_receiver_id = Integer.parseInt(o[3].toString());
        String o_message = o[4].toString();
        Integer o_checked = Integer.parseInt(o[5].toString());
        Date o_sended_at = Timestamp.valueOf(o[6].toString());

        return new Messages(o_id, o_chat_id, o_sender_id, o_receiver_id, o_message, o_checked, o_sended_at);
    }
    
    public static List<Messages> getAllMessagesBetweenUsers(Messages message) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        List<Messages> messages = new ArrayList<>();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllMessagesBetweenUsers");
            
            spq.registerStoredProcedureParameter("user1_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("user2_id_in", Integer.class, ParameterMode.IN);
            
            spq.setParameter("user1_id_in", Users.getUserById(message.getSenderId()).getId());
            spq.setParameter("user2_id_in", Users.getUserById(message.getReceiverId()).getId());
            
            spq.execute();
            List<Object[]> result = spq.getResultList();
            
            for(Object[] r : result){
                Messages m = Messages.objectToMessage(r);
                messages.add(m);
            }
            
            return messages;
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
    
    public static List<Messages> getAllMessages() throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        List<Messages> messages = new ArrayList<>();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllMessages");            
            spq.execute();
            
            List<Object[]> result = spq.getResultList();
            
            for(Object[] r : result){
                Messages m = Messages.objectToMessage(r);
                messages.add(m);
            }
            
            return messages;
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
    
    public static void checkMessage(Messages message) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("checkMessage");
            
            spq.registerStoredProcedureParameter("chat_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("user_id_in", Integer.class, ParameterMode.IN);
            
            spq.setParameter("chat_id_in", Chats.getChatById(message.getChatId()).getId());
            spq.setParameter("user_id_in", Users.getUserById(message.getReceiverId()).getId());
            
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
    
    public static void createMessage(Messages message) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("createMessage");
            
            spq.registerStoredProcedureParameter("chat_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("sender_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("receiver_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("message_in", String.class, ParameterMode.IN);
            
            spq.setParameter("chat_id_in", Chats.getChatById(message.getChatId()).getId());
            spq.setParameter("sender_id_in", Users.getUserById(message.getSenderId()).getId());
            spq.setParameter("receiver_id_in", Users.getUserById(message.getReceiverId()).getId());
            spq.setParameter("message_in", message.getMessage());
            
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
    
}
