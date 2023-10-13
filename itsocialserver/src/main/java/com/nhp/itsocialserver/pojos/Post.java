/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhp.itsocialserver.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import jakarta.persistence.*;
import lombok.Data;

/**
 *
 * @author ad
 */
@Entity
@Table(name = "post")
@Data
@NamedQueries({
        @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p"),
        @NamedQuery(name = "Post.findById", query = "SELECT p FROM Post p WHERE p.id = :id"),
        @NamedQuery(name = "Post.findByContent", query = "SELECT p FROM Post p WHERE p.content = :content"),
        @NamedQuery(name = "Post.findByImage", query = "SELECT p FROM Post p WHERE p.image = :image"),
        @NamedQuery(name = "Post.findByCreatedDate", query = "SELECT p FROM Post p WHERE p.createdDate = :createdDate"),
        @NamedQuery(name = "Post.findByUpdatedDate", query = "SELECT p FROM Post p WHERE p.updatedDate = :updatedDate"),
        @NamedQuery(name = "Post.findByStatus", query = "SELECT p FROM Post p WHERE p.status = :status")})
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "postId")
    private Set<TagPost> tagPostSet;
    @OneToMany(mappedBy = "postId")
    private Set<Reaction> reactionSet;
    @JoinColumn(name = "community_id", referencedColumnName = "id")
    @ManyToOne
    private Community communityId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;
    @OneToMany(mappedBy = "postId")
    private Set<Comment> commentSet;
}
