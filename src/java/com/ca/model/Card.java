/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author john
 */
@Entity
@Table(name = "card")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Card.findAll", query = "SELECT c FROM Card c"),
    @NamedQuery(name = "Card.findByCardId", query = "SELECT c FROM Card c WHERE c.cardId = :cardId"),
    @NamedQuery(name = "Card.findByShape", query = "SELECT c FROM Card c WHERE c.shape = :shape"),
    @NamedQuery(name = "Card.findByShade", query = "SELECT c FROM Card c WHERE c.shade = :shade"),
    @NamedQuery(name = "Card.findByNumber", query = "SELECT c FROM Card c WHERE c.number = :number"),
    @NamedQuery(name = "Card.findByColor", query = "SELECT c FROM Card c WHERE c.color = :color"),
    @NamedQuery(name = "Card.findByImageName", query = "SELECT c FROM Card c WHERE c.imageName = :imageName"),
    @NamedQuery(name = "Card.findByStatus", query = "SELECT c FROM Card c WHERE c.status = :status")})
public class Card implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "card")
    private Collection<Mappingpositioncard> mappingpositioncardCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cardId")
    private String cardId;
    @Size(max = 255)
    @Column(name = "shape")
    private String shape;
    @Size(max = 255)
    @Column(name = "shade")
    private String shade;
    @Size(max = 255)
    @Column(name = "number")
    private String number;
    @Size(max = 255)
    @Column(name = "color")
    private String color;
    @Size(max = 255)
    @Column(name = "imageName")
    private String imageName;
    @Size(max = 255)
    @Column(name = "status")
    private String status;

    public Card() {
    }

    public Card(String cardId) {
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getShade() {
        return shade;
    }

    public void setShade(String shade) {
        this.shade = shade;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getStatus() { 
        return status;
    }

        public void setStatus(String status) {
        //this.status = "None";
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cardId != null ? cardId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Card)) {
            return false;
        }
        Card other = (Card) object;
        if ((this.cardId == null && other.cardId != null) || (this.cardId != null && !this.cardId.equals(other.cardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ca.controller.Card[ cardId=" + cardId + " ]";
    }

    @XmlTransient
    public Collection<Mappingpositioncard> getMappingpositioncardCollection() {
        return mappingpositioncardCollection;
    }

    public void setMappingpositioncardCollection(Collection<Mappingpositioncard> mappingpositioncardCollection) {
        this.mappingpositioncardCollection = mappingpositioncardCollection;
    }
    
}
