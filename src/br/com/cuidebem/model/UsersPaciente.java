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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aleci
 */
@Entity
@Table(name = "users_paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersPaciente.findAll", query = "SELECT u FROM UsersPaciente u")
    , @NamedQuery(name = "UsersPaciente.findByIdusersPaciente", query = "SELECT u FROM UsersPaciente u WHERE u.idusersPaciente = :idusersPaciente")
    , @NamedQuery(name = "UsersPaciente.findByPrincipal", query = "SELECT u FROM UsersPaciente u WHERE u.principal = :principal")
   })
@NamedNativeQueries({
	@NamedNativeQuery(name="UsersPaciente.findByEmail", query="SELECT * FROM users_paciente where email = ?1", resultClass=UsersPaciente.class)
	,@NamedNativeQuery(name="UsersPaciente.findByIdPaciente", query="SELECT * FROM users_paciente where idpaciente = ?1", resultClass=UsersPaciente.class)
})
public class UsersPaciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusers_paciente")
    private Integer idusersPaciente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "principal")
    private boolean principal;
    @JoinColumn(name = "idpaciente", referencedColumnName = "idpaciente")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Paciente idpaciente;
    @JoinColumn(name = "email", referencedColumnName = "email")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Users users;
    @Basic(optional = false)
    @NotNull
    @Column(name = "enabled")
    private Boolean enabled = false;

    public UsersPaciente() {
    }

    public UsersPaciente(Integer idusersPaciente) {
        this.idusersPaciente = idusersPaciente;
    }

    public UsersPaciente(Integer idusersPaciente, boolean principal) {
        this.idusersPaciente = idusersPaciente;
        this.principal = principal;
    }

    public Integer getIdusersPaciente() {
        return idusersPaciente;
    }

    public void setIdusersPaciente(Integer idusersPaciente) {
        this.idusersPaciente = idusersPaciente;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public Paciente getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(Paciente idpaciente) {
        this.idpaciente = idpaciente;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users email) {
        this.users = email;
    }
    
    

    public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idusersPaciente != null ? idusersPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersPaciente)) {
            return false;
        }
        UsersPaciente other = (UsersPaciente) object;
        if ((this.idusersPaciente == null && other.idusersPaciente != null) || (this.idusersPaciente != null && !this.idusersPaciente.equals(other.idusersPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebem.model.UsersPaciente[ idusersPaciente=" + idusersPaciente + " ]";
    }
    
}
