/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhp.itsocialserver.pojos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;


/**
 *
 * @author ad
 */
@Entity
@Table(name = "notification_user")
@Data
@NamedQueries({
        @NamedQuery(name = "NotificationUser.findAll", query = "SELECT n FROM NotificationUser n"),
        @NamedQuery(name = "NotificationUser.findById", query = "SELECT n FROM NotificationUser n WHERE n.id = :id"),
        @NamedQuery(name = "NotificationUser.findByCreatedDate", query = "SELECT n FROM NotificationUser n WHERE n.createdDate = :createdDate")})
public class NotificationUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "notification_id", referencedColumnName = "id")
    @ManyToOne
    private Notification notificationId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;
}
