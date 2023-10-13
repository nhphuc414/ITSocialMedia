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
@Table(name = "community")
@Data
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
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @OneToMany(mappedBy = "communityId")
    private Set<NotificationCommunity> notificationCommunitySet;
    @OneToMany(mappedBy = "communityId")
    private Set<Post> postSet;
    @OneToMany(mappedBy = "communityId")
    private Set<CommunityUser> communityUserSet;

}
