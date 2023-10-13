/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhp.itsocialserver.pojos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ad
 */
@Entity
@Table(name = "community_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommunityUser.findAll", query = "SELECT c FROM CommunityUser c"),
    @NamedQuery(name = "CommunityUser.findById", query = "SELECT c FROM CommunityUser c WHERE c.id = :id"),
    @NamedQuery(name = "CommunityUser.findByRole", query = "SELECT c FROM CommunityUser c WHERE c.role = :role"),
    @NamedQuery(name = "CommunityUser.findByCreatedDate", query = "SELECT c FROM CommunityUser c WHERE c.createdDate = :createdDate"),
    @NamedQuery(name = "CommunityUser.findByUpdatedDate", query = "SELECT c FROM CommunityUser c WHERE c.updatedDate = :updatedDate")})
public class CommunityUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 6)
    @Column(name = "role")
    private String role;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @JoinColumn(name = "community_id", referencedColumnName = "id")
    @ManyToOne
    private Community communityId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;

    public CommunityUser() {
    }

    public CommunityUser(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Community getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Community communityId) {
        this.communityId = communityId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof CommunityUser)) {
            return false;
        }
        CommunityUser other = (CommunityUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nhp.itsocialserver.pojos.CommunityUser[ id=" + id + " ]";
    }
    
}
