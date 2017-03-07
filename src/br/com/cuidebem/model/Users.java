/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aleci
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u where u.enabled = true")
    , @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email and u.enabled = true")
    , @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password and u.enabled = true")
    , @NamedQuery(name = "Users.findByTypeuser", query = "SELECT u FROM Users u WHERE u.typeuser = :typeuser and u.enabled = true")
    , @NamedQuery(name = "Users.findByNome", query = "SELECT u FROM Users u WHERE u.nome = :nome and u.enabled = true")
    , @NamedQuery(name = "Users.findByBlocked", query = "SELECT u FROM Users u WHERE u.blocked = :blocked and u.enabled = true")
    , @NamedQuery(name = "Users.findByDatacadastro", query = "SELECT u FROM Users u WHERE u.datacadastro = :datacadastro and u.enabled = true")
    , @NamedQuery(name = "Users.findAllCuidadores", query = "SELECT u FROM Users u WHERE u.typeuser = 2 and u.enabled = true")
    , @NamedQuery(name = "Users.findCuidadorByEmail", query = "SELECT u FROM Users u WHERE u.typeuser = 2 and u.email = :email")
    , @NamedQuery(name = "Users.findCuidadorByNome", query = "SELECT u FROM Users u WHERE u.typeuser = 2 and u.nome = :nome")
    ,@NamedQuery(name = "Users.likeCuidadorByEmail", query = "SELECT u FROM Users u WHERE u.typeuser = 2 and u.email like :email")
    , @NamedQuery(name = "Users.likeCuidadorByNome", query = "SELECT u FROM Users u WHERE u.typeuser = 2 and u.nome like :nome")})
@NamedNativeQueries({
	@NamedNativeQuery(name = "Users.findCuidadorByPaciente", query = "SELECT u.* FROM users u inner join users_paciente up on u.email = up.email where u.typeuser = 2 and up.idpaciente = ?1", resultClass=Users.class)
	,@NamedNativeQuery(name = "Users.findCuidadorByListPaciente", query = "SELECT u.* FROM users u inner join users_paciente up on u.email = up.email where u.typeuser = 2 and up.idpaciente in (?2)", resultClass=Users.class)
})
public class Users implements Serializable {

    @Basic(optional = false)
    @Column(name = "alterLogin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date alterLogin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users", fetch = FetchType.LAZY)
    private List<UsersPaciente> usersPacienteList;

    private static final long serialVersionUID = 1L;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "typeuser")
    private int typeuser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nome")
    private String nome;
    @Column(name = "blocked")
    private boolean blocked;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datacadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datacadastro;
    @Basic(optional = false)
    @Column(name = "activation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activation;
    @NotNull
    @Column(name = "enabled")
    private Boolean enabled = true;
    @Size(max = 255)
    @Column(name = "telefoneWhats")
    private String telefoneWhats;
    @Size(max = 255)
    @Column(name = "telefone")
    private String telefone;

    public Users() {
    }

    public Users(String email) {
        this.email = email;
    }

    public Users(String email, String password, int typeuser, String nome, Date datacadastro) {
        this.email = email;
        this.password = password;
        this.typeuser = typeuser;
        this.nome = nome;
        this.datacadastro = datacadastro;
    }
    
    public Users(String email, String password, int typeuser, String nome){
    	   this.email = email;
           this.password = password;
           this.typeuser = typeuser;
           this.nome = nome;
    }

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTypeuser() {
        return typeuser;
    }

    public void setTypeuser(int typeuser) {
        this.typeuser = typeuser;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public Date getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(Date datacadastro) {
        this.datacadastro = datacadastro;
    }
    
    

    public String getTelefoneWhats() {
		return telefoneWhats;
	}

	public void setTelefoneWhats(String telefoneWhats) {
		this.telefoneWhats = telefoneWhats;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebem.control.Users[ email=" + email + " ]";
    }

    public Date getAlterLogin() {
        return alterLogin;
    }

    public void setAlterLogin(Date alterLogin) {
        this.alterLogin = alterLogin;
    }
    
    

    public Date getActivation() {
		return activation;
	}

	public void setActivation(Date activation) {
		this.activation = activation;
	}

	@XmlTransient
    public List<UsersPaciente> getUsersPacienteList() {
        return usersPacienteList;
    }

    public void setUsersPacienteList(List<UsersPaciente> usersPacienteList) {
        this.usersPacienteList = usersPacienteList;
    }
    
}
