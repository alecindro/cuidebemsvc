/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aleci
 */
@Entity
@Table(name = "paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p where p.enabled = true")
    , @NamedQuery(name = "Paciente.findByIdpaciente", query = "SELECT p FROM Paciente p WHERE p.idpaciente = :idpaciente AND p.enabled = true")
    , @NamedQuery(name = "Paciente.findByNome", query = "SELECT p FROM Paciente p WHERE p.nome = :nome AND p.enabled = true")
    , @NamedQuery(name = "Paciente.findBySobrenome", query = "SELECT p FROM Paciente p WHERE p.sobrenome = :sobrenome AND p.enabled = true")
    , @NamedQuery(name = "Paciente.findByGenero", query = "SELECT p FROM Paciente p WHERE p.genero = :genero and p.enabled = true")
    , @NamedQuery(name = "Paciente.findByDtnascimento", query = "SELECT p FROM Paciente p WHERE p.dtnascimento = :dtnascimento AND p.enabled = true")
    , @NamedQuery(name = "Paciente.findByPlanosaude", query = "SELECT p FROM Paciente p WHERE p.planosaude = :planosaude AND p.enabled = true")})
@NamedNativeQueries({
	@NamedNativeQuery(name="Paciente.findByUser", query="select p.* from paciente p inner join users_paciente up on p.idpaciente= up.idpaciente where up.email = ?1 AND p.enabled = 1", resultClass=Paciente.class)
})
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpaciente")
    private Integer idpaciente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nome")
    private String nome;
    @Size(max = 255)
    @Column(name = "sobrenome")
    private String sobrenome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "genero")
    private int genero;
    @Column(name = "dtnascimento")
    @Temporal(TemporalType.DATE)
    private Date dtnascimento;
    @Size(max = 255)
    @Column(name = "planosaude")
    private String planosaude;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpaciente", fetch = FetchType.LAZY)
    private List<PatologiaPaciente> patologiaPacienteList;
    @NotNull
    @Column(name = "enabled")
    private Boolean enabled = true;

    @Transient
    private String dtnascimentoStr;
  

    public Paciente() {
    }

    public Paciente(Integer idpaciente) {
        this.idpaciente = idpaciente;
    }

    public Paciente(Integer idpaciente, String nome, int genero) {
        this.idpaciente = idpaciente;
        this.nome = nome;
        this.genero = genero;
    }
    
    

    public Paciente(String nome, String sobrenome, int genero, Date dtnascimento, String planosaude) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.genero = genero;
		this.dtnascimento = dtnascimento;
		this.planosaude = planosaude;
		
	}
    
    

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(Integer idpaciente) {
        this.idpaciente = idpaciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public Date getDtnascimento() {
        return dtnascimento;
    }

    public void setDtnascimento(Date dtnascimento) {
        this.dtnascimento = dtnascimento;
    }

    public String getPlanosaude() {
        return planosaude;
    }

    public void setPlanosaude(String planosaude) {
        this.planosaude = planosaude;
    }
    
    

    public String getDtnascimentoStr() {
    	if(dtnascimento != null){
    		try {
				dtnascimentoStr = convert(dtnascimento, "yyyy-MM-dd");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		return dtnascimentoStr;
	}

	public void setDtnascimentoStr(String dtnascimentoStr) {
		this.dtnascimentoStr = dtnascimentoStr;
		try {
			this.dtnascimento = convert(dtnascimentoStr, "yyyy-MM-dd");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@XmlTransient
    public List<PatologiaPaciente> getPatologiaPacienteList() {
        return patologiaPacienteList;
    }

    public void setPatologiaPacienteList(List<PatologiaPaciente> patologiaPacienteList) {
        this.patologiaPacienteList = patologiaPacienteList;
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
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.idpaciente == null && other.idpaciente != null) || (this.idpaciente != null && !this.idpaciente.equals(other.idpaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebem.model.Paciente[ idpaciente=" + idpaciente + " ]";
    }
    
	private static Date convert(String dateinput, String pattern) throws ParseException{
		SimpleDateFormat formatDate = new SimpleDateFormat(pattern);
		return formatDate.parse(dateinput);
	}
	
	private static String convert(Date dateinput, String pattern) throws ParseException{
		SimpleDateFormat formatDate = new SimpleDateFormat(pattern);
		return formatDate.format(dateinput);
	}
	

    
}
