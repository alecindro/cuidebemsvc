/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aleci
 */
@Entity
@Table(name = "checkin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Checkin.findAll", query = "SELECT c FROM Checkin c")
    , @NamedQuery(name = "Checkin.findByidcheckin", query = "SELECT c FROM Checkin c WHERE c.idcheckin = :idcheckin")
    , @NamedQuery(name = "Checkin.findByDatecheckin", query = "SELECT c FROM Checkin c WHERE c.datecheckin = :datecheckin")
    , @NamedQuery(name = "Checkin.findByDatecheckout", query = "SELECT c FROM Checkin c WHERE c.datecheckout = :datecheckout")
    , @NamedQuery(name = "Checkin.findCheckinOpenByUser", query = "SELECT c FROM Checkin c WHERE c.user.email = :email and c.datecheckout is null")
    })

public class Checkin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcheckin")
    private Integer idcheckin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datecheckin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecheckin;
    @Column(name = "datecheckout")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecheckout;
    @JoinColumn(name = "user", referencedColumnName = "email")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users user;
    @JoinColumn(name = "idpaciente", referencedColumnName = "idpaciente")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Paciente paciente;

    public Checkin() {
    }

    public Checkin(Integer idcheckin) {
        this.idcheckin = idcheckin;
    }

    public Checkin(Integer idcheckin, Date datecheckin) {
        this.idcheckin = idcheckin;
        this.datecheckin = datecheckin;
    }

    public Integer getidcheckin() {
        return idcheckin;
    }

    public void setidcheckin(Integer idcheckin) {
        this.idcheckin = idcheckin;
    }

    public Date getDatecheckin() {
        return datecheckin;
    }

    public void setDatecheckin(Date datecheckin) {
        this.datecheckin = datecheckin;
    }

    public Date getDatecheckout() {
        return datecheckout;
    }

    public void setDatecheckout(Date datecheckout) {
        this.datecheckout = datecheckout;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcheckin != null ? idcheckin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Checkin)) {
            return false;
        }
        Checkin other = (Checkin) object;
        if ((this.idcheckin == null && other.idcheckin != null) || (this.idcheckin != null && !this.idcheckin.equals(other.idcheckin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebem.model.Chekin[ idcheckin=" + idcheckin + " ]";
    }
    
}
