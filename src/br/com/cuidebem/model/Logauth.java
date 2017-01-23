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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aleci
 */
@Entity
@Table(name = "logauth")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logauth.findAll", query = "SELECT l FROM Logauth l")
    , @NamedQuery(name = "Logauth.findByIdlogauth", query = "SELECT l FROM Logauth l WHERE l.idlogauth = :idlogauth")
    , @NamedQuery(name = "Logauth.findByLogin", query = "SELECT l FROM Logauth l WHERE l.login = :login")
    , @NamedQuery(name = "Logauth.findByDatalog", query = "SELECT l FROM Logauth l WHERE l.datalog = :datalog")
    , @NamedQuery(name = "Logauth.findByTimezone", query = "SELECT l FROM Logauth l WHERE l.timezone = :timezone")
    , @NamedQuery(name = "Logauth.findBySucess", query = "SELECT l FROM Logauth l WHERE l.sucess = :sucess")})
public class Logauth implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlogauth")
    private Integer idlogauth;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datalog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datalog;
    @Size(max = 30)
    @Column(name = "timezone")
    private String timezone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sucess")
    private boolean sucess;

    public Logauth() {
    }

    public Logauth(Integer idlogauth) {
        this.idlogauth = idlogauth;
    }

    public Logauth(Integer idlogauth, String login, Date datalog, boolean sucess) {
        this.idlogauth = idlogauth;
        this.login = login;
        this.datalog = datalog;
        this.sucess = sucess;
    }

    public Integer getIdlogauth() {
        return idlogauth;
    }

    public void setIdlogauth(Integer idlogauth) {
        this.idlogauth = idlogauth;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getDatalog() {
        return datalog;
    }

    public void setDatalog(Date datalog) {
        this.datalog = datalog;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public boolean getSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlogauth != null ? idlogauth.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logauth)) {
            return false;
        }
        Logauth other = (Logauth) object;
        if ((this.idlogauth == null && other.idlogauth != null) || (this.idlogauth != null && !this.idlogauth.equals(other.idlogauth))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebem.model.Logauth[ idlogauth=" + idlogauth + " ]";
    }
    
}
