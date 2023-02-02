/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sharkz
 */
@Entity
@Table(name = "ratings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ratings.findAll", query = "SELECT r FROM Ratings r"),
    @NamedQuery(name = "Ratings.findById", query = "SELECT r FROM Ratings r WHERE r.id = :id"),
    @NamedQuery(name = "Ratings.findByRatingedUserId", query = "SELECT r FROM Ratings r WHERE r.ratingedUserId = :ratingedUserId"),
    @NamedQuery(name = "Ratings.findByRatingerUserId", query = "SELECT r FROM Ratings r WHERE r.ratingerUserId = :ratingerUserId"),
    @NamedQuery(name = "Ratings.findByRatingsStars", query = "SELECT r FROM Ratings r WHERE r.ratingsStars = :ratingsStars"),
    @NamedQuery(name = "Ratings.findByStatus", query = "SELECT r FROM Ratings r WHERE r.status = :status"),
    @NamedQuery(name = "Ratings.findByUpdatedAt", query = "SELECT r FROM Ratings r WHERE r.updatedAt = :updatedAt"),
    @NamedQuery(name = "Ratings.findByDeleted", query = "SELECT r FROM Ratings r WHERE r.deleted = :deleted")})
public class Ratings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ratinged_user_id")
    private int ratingedUserId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ratinger_user_id")
    private int ratingerUserId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ratings_stars")
    private int ratingsStars;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deleted")
    private int deleted;

    public Ratings() {
    }

    public Ratings(Integer id) {
        this.id = id;
    }

    public Ratings(Integer id, int ratingedUserId, int ratingerUserId, String description, int ratingsStars, int status, Date updatedAt, int deleted) {
        this.id = id;
        this.ratingedUserId = ratingedUserId;
        this.ratingerUserId = ratingerUserId;
        this.description = description;
        this.ratingsStars = ratingsStars;
        this.status = status;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRatingedUserId() {
        return ratingedUserId;
    }

    public void setRatingedUserId(int ratingedUserId) {
        this.ratingedUserId = ratingedUserId;
    }

    public int getRatingerUserId() {
        return ratingerUserId;
    }

    public void setRatingerUserId(int ratingerUserId) {
        this.ratingerUserId = ratingerUserId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRatingsStars() {
        return ratingsStars;
    }

    public void setRatingsStars(int ratingsStars) {
        this.ratingsStars = ratingsStars;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        if (!(object instanceof Ratings)) {
            return false;
        }
        Ratings other = (Ratings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "szakemberkereso.Model.Ratings[ id=" + id + " ]";
    }
    
}
