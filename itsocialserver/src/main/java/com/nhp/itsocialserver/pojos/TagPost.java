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
@Table(name = "tag_post")
@Data
@NamedQueries({
        @NamedQuery(name = "TagPost.findAll", query = "SELECT t FROM TagPost t"),
        @NamedQuery(name = "TagPost.findById", query = "SELECT t FROM TagPost t WHERE t.id = :id"),
        @NamedQuery(name = "TagPost.findByCreatedDate", query = "SELECT t FROM TagPost t WHERE t.createdDate = :createdDate")})
public class TagPost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @ManyToOne
    private Post postId;
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    @ManyToOne
    private Tag tagId;
}
