/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author john
 */
@Entity
@Table(name = "tempcard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tempcard.findAll", query = "SELECT t FROM Tempcard t"),
    @NamedQuery(name = "Tempcard.findByCardId", query = "SELECT t FROM Tempcard t WHERE t.tempcardPK.cardId = :cardId"),
    @NamedQuery(name = "Tempcard.findByUserId", query = "SELECT t FROM Tempcard t WHERE t.tempcardPK.userId = :userId"),
    @NamedQuery(name = "Tempcard.findByScore", query = "SELECT t FROM Tempcard t WHERE t.score = :score")})
public class Tempcard implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TempcardPK tempcardPK;
    @Size(max = 255)
    @Column(name = "score")
    private String score;

    public Tempcard() {
    }

    public Tempcard(TempcardPK tempcardPK) {
        this.tempcardPK = tempcardPK;
    }

    public Tempcard(String cardId, String userId) {
        this.tempcardPK = new TempcardPK(cardId, userId);
    }

    public TempcardPK getTempcardPK() {
        return tempcardPK;
    }

    public void setTempcardPK(TempcardPK tempcardPK) {
        this.tempcardPK = tempcardPK;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tempcardPK != null ? tempcardPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tempcard)) {
            return false;
        }
        Tempcard other = (Tempcard) object;
        if ((this.tempcardPK == null && other.tempcardPK != null) || (this.tempcardPK != null && !this.tempcardPK.equals(other.tempcardPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ca.model.Tempcard[ tempcardPK=" + tempcardPK + " ]";
    }
    
}
