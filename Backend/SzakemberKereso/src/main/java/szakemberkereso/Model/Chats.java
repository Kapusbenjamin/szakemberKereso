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
import javax.xml.bind.annotation.XmlRootElement;
import szakemberkereso.Configuration.Database;

/**
 *
 * @author Sharkz
 */
@Entity
@Table(name = "chats")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chats.findAll", query = "SELECT c FROM Chats c"),
    @NamedQuery(name = "Chats.findById", query = "SELECT c FROM Chats c WHERE c.id = :id"),
    @NamedQuery(name = "Chats.findBySenderId", query = "SELECT c FROM Chats c WHERE c.senderId = :senderId"),
    @NamedQuery(name = "Chats.findByReceiverId", query = "SELECT c FROM Chats c WHERE c.receiverId = :receiverId")})
public class Chats implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sender_id")
    private int senderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "receiver_id")
    private int receiverId;

    public Chats() {
    }

    public Chats(Integer id) {
        this.id = id;
    }

    public Chats(Integer id, int senderId, int receiverId) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chats)) {
            return false;
        }
        Chats other = (Chats) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "szakemberkereso.Model.Chats[ id=" + id + " ]";
    }
    
    public static List<Chats> getAllChatsByUserId(Integer id_in){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        List<Chats> chats = new ArrayList<>();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllChatsByUserId");
            
            spq.registerStoredProcedureParameter("user_id_in", Integer.class, ParameterMode.IN);
            
            spq.setParameter("user_id_in", id_in);
            
            spq.execute();
            List<Object[]> result = spq.getResultList();
            
            for(Object[] r : result){
                Integer r_id = Integer.parseInt(r[0].toString());
                Integer r_sender_id = Integer.parseInt(r[1].toString());
                Integer r_receiver_id = Integer.parseInt(r[2].toString());
                
                Chats c = new Chats(r_id, r_sender_id, r_receiver_id);
                chats.add(c);
            }
            
            return chats;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return chats;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
    }
    
    public static String createChat(Chats chat){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try {            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("createChat");
            
            spq.registerStoredProcedureParameter("sender_id_in", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("receiver_id_in", Integer.class, ParameterMode.IN);

            spq.setParameter("sender_id_in", chat.getSenderId());
            spq.setParameter("receiver_id_in", chat.getReceiverId());

            spq.execute();
            return "Sikeresen létrejött a chat";
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
            
    
}
