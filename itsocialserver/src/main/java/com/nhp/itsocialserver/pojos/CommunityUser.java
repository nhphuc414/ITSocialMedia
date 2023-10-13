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
@Table(name = "community_user")
@Data
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
}
