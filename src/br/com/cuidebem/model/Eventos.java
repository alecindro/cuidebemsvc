/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.model;

import java.io.Serializable;
import java.util.Calendar;
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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aleci
 */
@Entity
@Table(name = "eventos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eventos.findAll", query = "SELECT e FROM Eventos e")
    , @NamedQuery(name = "Eventos.findByIdeventos", query = "SELECT e FROM Eventos e WHERE e.ideventos = :ideventos")
    , @NamedQuery(name = "Eventos.findByDescevento", query = "SELECT e FROM Eventos e WHERE e.descevento = :descevento")
    , @NamedQuery(name = "Eventos.findByDataevento", query = "SELECT e FROM Eventos e WHERE e.dataevento = :dataevento")})
@NamedNativeQueries({
	@NamedNativeQuery(name="Eventos.findByPaciente", query="select * from eventos where idpaciente=?1 and dataevento between ?2 and ?3", resultClass=Eventos.class),
	@NamedNativeQuery(name="Eventos.findEnableByPaciente", query="select * from eventos where enabled = 1 and idpaciente=?1 and dataevento between ?2 and ?3", resultClass=Eventos.class)
})
public class Eventos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ideventos")
    private Integer ideventos;
    @Basic(optional = false)
    @Size(max = 255)
    @Column(name = "descevento")
    private String descevento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataevento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataevento;
    @JoinColumn(name = "idcuidador", referencedColumnName = "email")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users idcuidador;
    @JoinColumn(name = "idpaciente", referencedColumnName = "idpaciente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paciente idpaciente;
    @NotNull
    @Column(name = "enabled")
    private Boolean enabled = true;
    @Basic(optional = false)
    @Size(max = 255)
    @Column(name = "obsevento")
    private String obsevento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "grupoevento")
    private String grupoevento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "subgrupoevento")
    private String subgrupoevento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "respeventos")
    private String respeventos;
    @Transient
    private String hour;
    @Transient
    private String deletedeColor;

    public Eventos() {
    }

    public Eventos(Integer ideventos) {
        this.ideventos = ideventos;
    }

    public Eventos(Integer ideventos, String descevento, Date dataevento) {
        this.ideventos = ideventos;
        this.descevento = descevento;
        this.dataevento = dataevento;
    }

    public Integer getIdeventos() {
        return ideventos;
    }

    public void setIdeventos(Integer ideventos) {
        this.ideventos = ideventos;
    }

    public String getDescevento() {
        return descevento;
    }

    public void setDescevento(String descevento) {
        this.descevento = descevento;
    }

    public Date getDataevento() {
        return dataevento;
    }

    public void setDataevento(Date dataevento) {
        this.dataevento = dataevento;
    }

    public Users getIdcuidador() {
        return idcuidador;
    }

    public void setIdcuidador(Users idcuidador) {
        this.idcuidador = idcuidador;
    }

    public Paciente getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(Paciente idpaciente) {
        this.idpaciente = idpaciente;
    }
    
    

    public String getHour() {
    	if(dataevento != null){
    		try {
				hour = convertHour(dataevento);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
		try {
			dataevento = convertHour(hour);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public String getObsevento() {
		return obsevento;
	}

	public void setObsevento(String obsevento) {
		this.obsevento = obsevento;
	}

	public String getGrupoevento() {
		return grupoevento;
	}

	public void setGrupoevento(String grupoevento) {
		this.grupoevento = grupoevento;
	}

	public String getSubgrupoevento() {
		return subgrupoevento;
	}

	public void setSubgrupoevento(String subgrupoevento) {
		this.subgrupoevento = subgrupoevento;
	}

	public String getRespeventos() {
		return respeventos;
	}

	public void setRespeventos(String respeventos) {
		this.respeventos = respeventos;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (ideventos != null ? ideventos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eventos)) {
            return false;
        }
        Eventos other = (Eventos) object;
        if ((this.ideventos == null && other.ideventos != null) || (this.ideventos != null && !this.ideventos.equals(other.ideventos))) {
            return false;
        }
        return true;
    }
    
    public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	

	public String getDeletedeColor() {
		if(!enabled){
			deletedeColor = "background-color:red";
		}
		return deletedeColor;
	}

	public void setDeletedeColor(String deletedeColor) {
		this.deletedeColor = deletedeColor;
	}

	@Override
    public String toString() {
        return "br.com.cuidebem.model.Eventos[ ideventos=" + ideventos + " ]";
    }
    
    public static Date convertHour(String hourinput) throws Exception {
		Calendar calendar = Calendar.getInstance();
		String[] values = hourinput.split(":");
		calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(values[0]));
		calendar.set(Calendar.MINUTE,Integer.valueOf(values[1]));
		return calendar.getTime();
	}
    
    
    public static String convertHour(Date date) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		String _hour = String.valueOf(hour);
		if(hour<10){
			_hour = 0+_hour;
		}
		String _minute = String.valueOf(minute);
		if(minute<10){
			_minute = 0+_minute;
		}
		return _hour+":"+_minute;
	}
    
}
