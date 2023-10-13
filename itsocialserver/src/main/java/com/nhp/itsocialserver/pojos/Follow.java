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
@Table(name = "follow")
@Data
@NamedQueries({
        @NamedQuery(name = "Follow.findAll", query = "SELECT f FROM Follow f"),
        @NamedQuery(name = "Follow.findById", query = "SELECT f FROM Follow f WHERE f.id = :id"),
        @NamedQuery(name = "Follow.findByCreatedDate", query = "SELECT f FROM Follow f WHERE f.createdDate = :createdDate")})
public class Follow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "following_id", referencedColumnName = "id")
    @ManyToOne
    private User followingId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;
}
