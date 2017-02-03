package br.com.cuidebem.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "paciente_photo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PacientePhoto.findAll", query = "SELECT p FROM PacientePhoto p")
    , @NamedQuery(name = "PacientePhoto.findByIdpaciente", query = "SELECT p FROM PacientePhoto p WHERE p.idpaciente = :idpaciente")})
public class PacientePhoto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpaciente")
    private Integer idpaciente;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @JoinColumn(name = "idpaciente", referencedColumnName = "idpaciente", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch=FetchType.LAZY)
    private Paciente paciente;

    public PacientePhoto() {
    }

    public PacientePhoto(Integer idpaciente) {
        this.idpaciente = idpaciente;
    }

    public PacientePhoto(Integer idpaciente, byte[] photo) {
        this.idpaciente = idpaciente;
        this.photo = photo;
    }

    public Integer getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(Integer idpaciente) {
        this.idpaciente = idpaciente;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
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
        hash += (idpaciente != null ? idpaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PacientePhoto)) {
            return false;
        }
        PacientePhoto other = (PacientePhoto) object;
        if ((this.idpaciente == null && other.idpaciente != null) || (this.idpaciente != null && !this.idpaciente.equals(other.idpaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebem.model.PacientePhoto[ idpaciente=" + idpaciente + " ]";
    }
    
}
