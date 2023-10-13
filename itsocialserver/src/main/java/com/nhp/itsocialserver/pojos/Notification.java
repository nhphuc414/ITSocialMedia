/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhp.itsocialserver.pojos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import jakarta.persistence.*;

/**
 *
 * @author ad
 */
@Entity
@Table(name = "notification")
@Data
@NamedQueries({
        @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n"),
        @NamedQuery(name = "Notification.findById", query = "SELECT n FROM Notification n WHERE n.id = :id"),
        @NamedQuery(name = "Notification.findBySubject", query = "SELECT n FROM Notification n WHERE n.subject = :subject"),
        @NamedQuery(name = "Notification.findByContent", query = "SELECT n FROM Notification n WHERE n.content = :content"),
        @NamedQuery(name = "Notification.findByCreatedDate", query = "SELECT n FROM Notification n WHERE n.createdDate = :createdDate")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)

    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "subject")
    private String subject;
    @Basic(optional = false)
    @Column(name = "content")
    private String content;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;
    @OneToMany(mappedBy = "notificationId")
    private Set<NotificationUser> notificationUserSet;
    @OneToMany(mappedBy = "notificationId")
    private Set<NotificationCommunity> notificationCommunitySet;
}
