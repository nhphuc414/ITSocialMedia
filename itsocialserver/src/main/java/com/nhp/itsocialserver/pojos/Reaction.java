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
@Table(name = "reaction")
@Data
@NamedQueries({
        @NamedQuery(name = "Reaction.findAll", query = "SELECT r FROM Reaction r"),
        @NamedQuery(name = "Reaction.findById", query = "SELECT r FROM Reaction r WHERE r.id = :id"),
        @NamedQuery(name = "Reaction.findByType", query = "SELECT r FROM Reaction r WHERE r.type = :type"),
        @NamedQuery(name = "Reaction.findByCreatedDate", query = "SELECT r FROM Reaction r WHERE r.createdDate = :createdDate")})
public class Reaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)

    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    @ManyToOne
    private Comment commentId;
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @ManyToOne
    private Post postId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
}
