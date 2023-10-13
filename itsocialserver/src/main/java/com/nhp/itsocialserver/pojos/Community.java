/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhp.itsocialserver.pojos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ad
 */
@Entity
@Table(name = "community")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Community.findAll", query = "SELECT c FROM Community c"),
    @NamedQuery(name = "Community.findById", query = "SELECT c FROM Community c WHERE c.id = :id"),
    @NamedQuery(name = "Community.findByName", query = "SELECT c FROM Community c WHERE c.name = :name"),
    @NamedQuery(name = "Community.findByDescription", query = "SELECT c FROM Community c WHERE c.description = :description"),
    @NamedQuery(name = "Community.findByCreatedDate", query = "SELECT c FROM Community c WHERE c.createdDate = :createdDate"),
    @NamedQuery(name = "Community.findByUpdatedDate", query = "SELECT c FROM Community c WHERE c.updatedDate = :updatedDate")})
public class Community implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "description")
    private String description;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @OneToMany(mappedBy = "communityId")
    private List<NotificationCommunity> notificationCommunityList;
    @OneToMany(mappedBy = "communityId")
    private List<Post> postList;
    @OneToMany(mappedBy = "communityId")
    private List<CommunityUser> communityUserList;

    public Community() {
    }

    public Community(Integer id) {
        this.id = id;
    }

    public Community(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @XmlTransient
    public List<NotificationCommunity> getNotificationCommunityList() {
        return notificationCommunityList;
    }

    public void setNotificationCommunityList(List<NotificationCommunity> notificationCommunityList) {
        this.notificationCommunityList = notificationCommunityList;
    }

    @XmlTransient
    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    @XmlTransient
    public List<CommunityUser> getCommunityUserList() {
        return communityUserList;
    }

    public void setCommunityUserList(List<CommunityUser> communityUserList) {
        this.communityUserList = communityUserList;
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
        if (!(object instanceof Community)) {
            return false;
        }
        Community other = (Community) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nhp.itsocialserver.pojos.Community[ id=" + id + " ]";
    }
    
}
