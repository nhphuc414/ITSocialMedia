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
@Table(name = "tag")
@Data
@NamedQueries({
        @NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t"),
        @NamedQuery(name = "Tag.findById", query = "SELECT t FROM Tag t WHERE t.id = :id"),
        @NamedQuery(name = "Tag.findByName", query = "SELECT t FROM Tag t WHERE t.name = :name"),
        @NamedQuery(name = "Tag.findByCreatedDate", query = "SELECT t FROM Tag t WHERE t.createdDate = :createdDate"),
        @NamedQuery(name = "Tag.findByUpdatedDate", query = "SELECT t FROM Tag t WHERE t.updatedDate = :updatedDate")})
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)

    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @OneToMany(mappedBy = "tagId")
    private Set<TagPost> tagPostSet;


}
