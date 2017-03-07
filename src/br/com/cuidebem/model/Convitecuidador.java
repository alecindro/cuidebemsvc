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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aleci
 */
@Entity
@Table(name = "convitecuidador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Convitecuidador.findAll", query = "SELECT c FROM Convitecuidador c")
    , @NamedQuery(name = "Convitecuidador.findByIdconvitecuidador", query = "SELECT c FROM Convitecuidador c WHERE c.idconvitecuidador = :idconvitecuidador")
    , @NamedQuery(name = "Convitecuidador.findAllByEmailcuidador", query = "SELECT c FROM Convitecuidador c WHERE c.emailcuidador = :emailcuidador")
    , @NamedQuery(name = "Convitecuidador.findByPendente", query = "SELECT c FROM Convitecuidador c WHERE c.pendente = :pendente")
    , @NamedQuery(name = "Convitecuidador.findByDtconvite", query = "SELECT c FROM Convitecuidador c WHERE c.dtconvite = :dtconvite")
    , @NamedQuery(name = "Convitecuidador.findEnableByEmailcuidador", query = "SELECT c FROM Convitecuidador c WHERE c.emailcuidador = :emailcuidador and c.pendente = true")})
public class Convitecuidador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconvitecuidador")
    private Integer idconvitecuidador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "emailcuidador")
    private String emailcuidador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pendente")
    private boolean pendente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dtconvite")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtconvite;
    @JoinColumn(name = "idpaciente", referencedColumnName = "idpaciente")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Paciente idpaciente;
    @JoinColumn(name = "emailresponsavel", referencedColumnName = "email")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users emailresponsavel;

    public Convitecuidador() {
    }

    public Convitecuidador(Integer idconvitecuidador) {
        this.idconvitecuidador = idconvitecuidador;
    }

    public Convitecuidador(Integer idconvitecuidador, String emailcuidador, boolean pendente, Date dtconvite) {
        this.idconvitecuidador = idconvitecuidador;
        this.emailcuidador = emailcuidador;
        this.pendente = pendente;
        this.dtconvite = dtconvite;
    }

    public Integer getIdconvitecuidador() {
        return idconvitecuidador;
    }

    public void setIdconvitecuidador(Integer idconvitecuidador) {
        this.idconvitecuidador = idconvitecuidador;
    }

    public String getEmailcuidador() {
        return emailcuidador;
    }

    public void setEmailcuidador(String emailcuidador) {
        this.emailcuidador = emailcuidador;
    }

    public boolean getPendente() {
        return pendente;
    }

    public void setPendente(boolean pendente) {
        this.pendente = pendente;
    }

    public Date getDtconvite() {
        return dtconvite;
    }

    public void setDtconvite(Date dtconvite) {
        this.dtconvite = dtconvite;
    }

    public Paciente getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(Paciente idpaciente) {
        this.idpaciente = idpaciente;
    }

    public Users getEmailresponsavel() {
        return emailresponsavel;
    }

    public void setEmailresponsavel(Users emailresponsavel) {
        this.emailresponsavel = emailresponsavel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconvitecuidador != null ? idconvitecuidador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Convitecuidador)) {
            return false;
        }
        Convitecuidador other = (Convitecuidador) object;
        if ((this.idconvitecuidador == null && other.idconvitecuidador != null) || (this.idconvitecuidador != null && !this.idconvitecuidador.equals(other.idconvitecuidador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebem.model.Convitecuidador[ idconvitecuidador=" + idconvitecuidador + " ]";
    }
    
}
