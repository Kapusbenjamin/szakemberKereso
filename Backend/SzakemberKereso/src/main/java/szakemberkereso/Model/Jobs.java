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
@Table(name = "jobs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobs.findAll", query = "SELECT j FROM Jobs j"),
    @NamedQuery(name = "Jobs.findById", query = "SELECT j FROM Jobs j WHERE j.id = :id"),
    @NamedQuery(name = "Jobs.findByCustomerId", query = "SELECT j FROM Jobs j WHERE j.customerId = :customerId"),
    @NamedQuery(name = "Jobs.findByWorkerId", query = "SELECT j FROM Jobs j WHERE j.workerId = :workerId"),
    @NamedQuery(name = "Jobs.findByTotal", query = "SELECT j FROM Jobs j WHERE j.total = :total"),
    @NamedQuery(name = "Jobs.findByStatus", query = "SELECT j FROM Jobs j WHERE j.status = :status"),
    @NamedQuery(name = "Jobs.findByWorkerAccepted", query = "SELECT j FROM Jobs j WHERE j.workerAccepted = :workerAccepted"),
    @NamedQuery(name = "Jobs.findByCustomerAccepted", query = "SELECT j FROM Jobs j WHERE j.customerAccepted = :customerAccepted"),
    @NamedQuery(name = "Jobs.findByUpdatedAt", query = "SELECT j FROM Jobs j WHERE j.updatedAt = :updatedAt"),
    @NamedQuery(name = "Jobs.findByDeleted", query = "SELECT j FROM Jobs j WHERE j.deleted = :deleted")})
public class Jobs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_id")
    private int customerId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "worker_id")
    private int workerId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "desc")
    private String desc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private int total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "worker_accepted")
    private int workerAccepted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_accepted")
    private int customerAccepted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deleted")
    private int deleted;

    public Jobs() {
    }

    public Jobs(Integer id) {
        this.id = id;
    }

    public Jobs(Integer id, int customerId, int workerId, String desc, int total, int status, int workerAccepted, int customerAccepted, Date updatedAt, int deleted) {
        this.id = id;
        this.customerId = customerId;
        this.workerId = workerId;
        this.desc = desc;
        this.total = total;
        this.status = status;
        this.workerAccepted = workerAccepted;
        this.customerAccepted = customerAccepted;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getWorkerAccepted() {
        return workerAccepted;
    }

    public void setWorkerAccepted(int workerAccepted) {
        this.workerAccepted = workerAccepted;
    }

    public int getCustomerAccepted() {
        return customerAccepted;
    }

    public void setCustomerAccepted(int customerAccepted) {
        this.customerAccepted = customerAccepted;
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
        if (!(object instanceof Jobs)) {
            return false;
        }
        Jobs other = (Jobs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "szakemberkereso.Model.Jobs[ id=" + id + " ]";
    }
    
}
