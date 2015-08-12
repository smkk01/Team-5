/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author john
 */
@Entity
@Table(name = "mappingpositioncard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mappingpositioncard.findAll", query = "SELECT m FROM Mappingpositioncard m"),
    @NamedQuery(name = "Mappingpositioncard.findByPositionId", query = "SELECT m FROM Mappingpositioncard m WHERE m.mappingpositioncardPK.positionId = :positionId"),
    @NamedQuery(name = "Mappingpositioncard.findByCardId", query = "SELECT m FROM Mappingpositioncard m WHERE m.mappingpositioncardPK.cardId = :cardId")})
public class Mappingpositioncard implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MappingpositioncardPK mappingpositioncardPK;
    @JoinColumn(name = "cardId", referencedColumnName = "cardId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Card card;

    public Mappingpositioncard() {
    }

    public Mappingpositioncard(MappingpositioncardPK mappingpositioncardPK) {
        this.mappingpositioncardPK = mappingpositioncardPK;
    }

    public Mappingpositioncard(String positionId, String cardId) {
        this.mappingpositioncardPK = new MappingpositioncardPK(positionId, cardId);
    }

    public MappingpositioncardPK getMappingpositioncardPK() {
        return mappingpositioncardPK;
    }

    public void setMappingpositioncardPK(MappingpositioncardPK mappingpositioncardPK) {
        this.mappingpositioncardPK = mappingpositioncardPK;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mappingpositioncardPK != null ? mappingpositioncardPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mappingpositioncard)) {
            return false;
        }
        Mappingpositioncard other = (Mappingpositioncard) object;
        if ((this.mappingpositioncardPK == null && other.mappingpositioncardPK != null) || (this.mappingpositioncardPK != null && !this.mappingpositioncardPK.equals(other.mappingpositioncardPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ca.model.Mappingpositioncard[ mappingpositioncardPK=" + mappingpositioncardPK + " ]";
    }
    
}
