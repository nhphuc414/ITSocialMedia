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
@Table(name = "comment")
@Data
@NamedQueries({
        @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
        @NamedQuery(name = "Comment.findById", query = "SELECT c FROM Comment c WHERE c.id = :id"),
        @NamedQuery(name = "Comment.findByContent", query = "SELECT c FROM Comment c WHERE c.content = :content"),
        @NamedQuery(name = "Comment.findByImage", query = "SELECT c FROM Comment c WHERE c.image = :image"),
        @NamedQuery(name = "Comment.findByCreatedDate", query = "SELECT c FROM Comment c WHERE c.createdDate = :createdDate"),
        @NamedQuery(name = "Comment.findByUpdatedDate", query = "SELECT c FROM Comment c WHERE c.updatedDate = :updatedDate"),
        @NamedQuery(name = "Comment.findByStatus", query = "SELECT c FROM Comment c WHERE c.status = :status")})
public class Comment implements Serializable {

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
    @OneToMany(mappedBy = "commentId")
    private Set<Reaction> reactionSet;
    @OneToMany(mappedBy = "commentParentId")
    private Set<Comment> commentSet;
    @JoinColumn(name = "comment_parent_id", referencedColumnName = "id")
    @ManyToOne
    private Comment commentParentId;
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @ManyToOne
    private Post postId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
}
