/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sharkz
 */
@Entity
@Table(name = "users_jobs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersJobs.findAll", query = "SELECT u FROM UsersJobs u"),
    @NamedQuery(name = "UsersJobs.findById", query = "SELECT u FROM UsersJobs u WHERE u.id = :id"),
    @NamedQuery(name = "UsersJobs.findByUserId", query = "SELECT u FROM UsersJobs u WHERE u.userId = :userId"),
    @NamedQuery(name = "UsersJobs.findByJobTagId", query = "SELECT u FROM UsersJobs u WHERE u.jobTagId = :jobTagId")})
public class UsersJobs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "job_tag_id")
    private int jobTagId;

    public UsersJobs() {
    }

    public UsersJobs(Integer id) {
        this.id = id;
    }

    public UsersJobs(Integer id, int userId, int jobTagId) {
        this.id = id;
        this.userId = userId;
        this.jobTagId = jobTagId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getJobTagId() {
        return jobTagId;
    }

    public void setJobTagId(int jobTagId) {
        this.jobTagId = jobTagId;
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
        if (!(object instanceof UsersJobs)) {
            return false;
        }
        UsersJobs other = (UsersJobs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "szakemberkereso.Model.UsersJobs[ id=" + id + " ]";
    }
    
}
