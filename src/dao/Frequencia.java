/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Thayson&Regina
 */
@Entity
@Table(name = "frequencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Frequencia.findAll", query = "SELECT f FROM Frequencia f"),
    @NamedQuery(name = "Frequencia.findById", query = "SELECT f FROM Frequencia f WHERE f.id = :id"),
    @NamedQuery(name = "Frequencia.findByData", query = "SELECT f FROM Frequencia f WHERE f.data = :data"),
    @NamedQuery(name = "Frequencia.findByIdMateria", query = "SELECT f FROM Frequencia f WHERE f.idMateria = :idMateria"),
    @NamedQuery(name = "Frequencia.findByAula", query = "SELECT f FROM Frequencia f WHERE f.aula = :aula"),
    @NamedQuery(name = "Frequencia.findByFalta", query = "SELECT f FROM Frequencia f WHERE f.falta = :falta"),
    @NamedQuery(name = "Frequencia.findByPresenca", query = "SELECT f FROM Frequencia f WHERE f.presenca = :presenca")})
public class Frequencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @Column(name = "id_materia")
    private int idMateria;
    @Basic(optional = false)
    @Column(name = "aula")
    private int aula;
    @Basic(optional = false)
    @Column(name = "falta")
    private Character falta;
    @Basic(optional = false)
    @Column(name = "presenca")
    private Character presenca;
    @JoinColumn(name = "id_aluno", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Alunos idAluno;

    public Frequencia() {
    }

    public Frequencia(Integer id) {
        this.id = id;
    }

    public Frequencia(Integer id, Date data, int idMateria, int aula, Character falta, Character presenca) {
        this.id = id;
        this.data = data;
        this.idMateria = idMateria;
        this.aula = aula;
        this.falta = falta;
        this.presenca = presenca;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public int getAula() {
        return aula;
    }

    public void setAula(int aula) {
        this.aula = aula;
    }

    public Character getFalta() {
        return falta;
    }

    public void setFalta(Character falta) {
        this.falta = falta;
    }

    public Character getPresenca() {
        return presenca;
    }

    public void setPresenca(Character presenca) {
        this.presenca = presenca;
    }

    public Alunos getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Alunos idAluno) {
        this.idAluno = idAluno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Frequencia)) {
            return false;
        }
        Frequencia other = (Frequencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Frequencia[ id=" + id + " ]";
    }
    
}
