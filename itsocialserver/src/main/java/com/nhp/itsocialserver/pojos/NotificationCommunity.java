/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhp.itsocialserver.pojos;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ad
 */
@Entity
@Table(name = "notification_community")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotificationCommunity.findAll", query = "SELECT n FROM NotificationCommunity n"),
    @NamedQuery(name = "NotificationCommunity.findById", query = "SELECT n FROM NotificationCommunity n WHERE n.id = :id"),
    @NamedQuery(name = "NotificationCommunity.findByCreatedDate", query = "SELECT n FROM NotificationCommunity n WHERE n.createdDate = :createdDate")})
public class NotificationCommunity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "community_id", referencedColumnName = "id")
    @ManyToOne
    private Community communityId;
    @JoinColumn(name = "notification_id", referencedColumnName = "id")
    @ManyToOne
    private Notification notificationId;

    public NotificationCommunity() {
    }

    public NotificationCommunity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Community getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Community communityId) {
        this.communityId = communityId;
    }

    public Notification getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Notification notificationId) {
        this.notificationId = notificationId;
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
        if (!(object instanceof NotificationCommunity)) {
            return false;
        }
        NotificationCommunity other = (NotificationCommunity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nhp.itsocialserver.pojos.NotificationCommunity[ id=" + id + " ]";
    }
    
}
