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
@Table(name = "ads_counties")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdsCounties.findAll", query = "SELECT a FROM AdsCounties a"),
    @NamedQuery(name = "AdsCounties.findById", query = "SELECT a FROM AdsCounties a WHERE a.id = :id"),
    @NamedQuery(name = "AdsCounties.findByAdId", query = "SELECT a FROM AdsCounties a WHERE a.adId = :adId"),
    @NamedQuery(name = "AdsCounties.findByCountyId", query = "SELECT a FROM AdsCounties a WHERE a.countyId = :countyId")})
public class AdsCounties implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ad_id")
    private int adId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "county_id")
    private int countyId;

    public AdsCounties() {
    }

    public AdsCounties(Integer id) {
        this.id = id;
    }

    public AdsCounties(Integer id, int adId, int countyId) {
        this.id = id;
        this.adId = adId;
        this.countyId = countyId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
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
        if (!(object instanceof AdsCounties)) {
            return false;
        }
        AdsCounties other = (AdsCounties) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "szakemberkereso.Model.AdsCounties[ id=" + id + " ]";
    }
    
}
