/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author john
 */
@Entity
@Table(name = "gamegroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gamegroup.findAll", query = "SELECT g FROM Gamegroup g"),
    @NamedQuery(name = "Gamegroup.findByGameId", query = "SELECT g FROM Gamegroup g WHERE g.gameId = :gameId"),
    @NamedQuery(name = "Gamegroup.findByGroupId", query = "SELECT g FROM Gamegroup g WHERE g.groupId = :groupId"),
    @NamedQuery(name = "Gamegroup.findByCreateOn", query = "SELECT g FROM Gamegroup g WHERE g.createOn = :createOn"),
    @NamedQuery(name = "Gamegroup.findByTimedLimit", query = "SELECT g FROM Gamegroup g WHERE g.timedLimit = :timedLimit")})
public class Gamegroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "gameId")
    private String gameId;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "groupId")
    private String groupId;
    @Size(max = 255)
    @Column(name = "createOn")
    private String createOn;
    @Size(max = 255)
    @Column(name = "timedLimit")
    private String timedLimit;

    public Gamegroup() {
    }

    public Gamegroup(String groupId) {
        this.groupId = groupId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getTimedLimit() {
        return timedLimit;
    }

    public void setTimedLimit(String timedLimit) {
        this.timedLimit = timedLimit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupId != null ? groupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gamegroup)) {
            return false;
        }
        Gamegroup other = (Gamegroup) object;
        if ((this.groupId == null && other.groupId != null) || (this.groupId != null && !this.groupId.equals(other.groupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ca.model.Gamegroup[ groupId=" + groupId + " ]";
    }
    
}
