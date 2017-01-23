/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.model;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aleci
 */
@Entity
@Table(name = "patologia_paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatologiaPaciente.findAll", query = "SELECT p FROM PatologiaPaciente p")
    , @NamedQuery(name = "PatologiaPaciente.findByIdpatologiaPaciente", query = "SELECT p FROM PatologiaPaciente p WHERE p.idpatologiaPaciente = :idpatologiaPaciente")
    , @NamedQuery(name = "PatologiaPaciente.findByPatologia", query = "SELECT p FROM PatologiaPaciente p WHERE p.patologia = :patologia")})
public class PatologiaPaciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpatologia_paciente")
    private Integer idpatologiaPaciente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "patologia")
    private String patologia;
    @JoinColumn(name = "idpaciente", referencedColumnName = "idpaciente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paciente idpaciente;

    public PatologiaPaciente() {
    }

    public PatologiaPaciente(Integer idpatologiaPaciente) {
        this.idpatologiaPaciente = idpatologiaPaciente;
    }

    public PatologiaPaciente(Integer idpatologiaPaciente, String patologia) {
        this.idpatologiaPaciente = idpatologiaPaciente;
        this.patologia = patologia;
    }
    
    

    public PatologiaPaciente(String patologia, Paciente idpaciente) {
		super();
		this.patologia = patologia;
		this.idpaciente = idpaciente;
	}

	public Integer getIdpatologiaPaciente() {
        return idpatologiaPaciente;
    }

    public void setIdpatologiaPaciente(Integer idpatologiaPaciente) {
        this.idpatologiaPaciente = idpatologiaPaciente;
    }

    public String getPatologia() {
        return patologia;
    }

    public void setPatologia(String patologia) {
        this.patologia = patologia;
    }

    public Paciente getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(Paciente idpaciente) {
        this.idpaciente = idpaciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpatologiaPaciente != null ? idpatologiaPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatologiaPaciente)) {
            return false;
        }
        PatologiaPaciente other = (PatologiaPaciente) object;
        if ((this.idpatologiaPaciente == null && other.idpatologiaPaciente != null) || (this.idpatologiaPaciente != null && !this.idpatologiaPaciente.equals(other.idpatologiaPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebem.model.PatologiaPaciente[ idpatologiaPaciente=" + idpatologiaPaciente + " ]";
    }
    
}
