/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Thayson&Regina
 */
@Entity
@Table(name = "turma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Turma.findAll", query = "SELECT t FROM Turma t"),
    @NamedQuery(name = "Turma.findById", query = "SELECT t FROM Turma t WHERE t.id = :id"),
    @NamedQuery(name = "Turma.findBySerie", query = "SELECT t FROM Turma t WHERE t.serie = :serie"),
    @NamedQuery(name = "Turma.findByMaxAlunos", query = "SELECT t FROM Turma t WHERE t.maxAlunos = :maxAlunos"),
    @NamedQuery(name = "Turma.findByPeriodo", query = "SELECT t FROM Turma t WHERE t.periodo = :periodo"),
    @NamedQuery(name = "Turma.findByPrimeiroDiaAula", query = "SELECT t FROM Turma t WHERE t.primeiroDiaAula = :primeiroDiaAula"),
    @NamedQuery(name = "Turma.findByUltimoDiaAula", query = "SELECT t FROM Turma t WHERE t.ultimoDiaAula = :ultimoDiaAula")})
public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "serie")
    private String serie;
    @Basic(optional = false)
    @Column(name = "max_alunos")
    private int maxAlunos;
    @Basic(optional = false)
    @Column(name = "periodo")
    private String periodo;
    @Basic(optional = false)
    @Column(name = "primeiroDiaAula")
    @Temporal(TemporalType.DATE)
    private Date primeiroDiaAula;
    @Basic(optional = false)
    @Column(name = "ultimoDiaAula")
    @Temporal(TemporalType.DATE)
    private Date ultimoDiaAula;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "turma")
    private Collection<Alunos> alunosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTurma")
    private Collection<Semana> semanaCollection;
    @JoinColumn(name = "id_curso", referencedColumnName = "id")
    @ManyToOne
    private Curso idCurso;

    public Turma() {
    }

    public Turma(Integer id) {
        this.id = id;
    }

    public Turma(Integer id, String serie, int maxAlunos, String periodo, Date primeiroDiaAula, Date ultimoDiaAula) {
        this.id = id;
        this.serie = serie;
        this.maxAlunos = maxAlunos;
        this.periodo = periodo;
        this.primeiroDiaAula = primeiroDiaAula;
        this.ultimoDiaAula = ultimoDiaAula;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getMaxAlunos() {
        return maxAlunos;
    }

    public void setMaxAlunos(int maxAlunos) {
        this.maxAlunos = maxAlunos;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Date getPrimeiroDiaAula() {
        return primeiroDiaAula;
    }

    public void setPrimeiroDiaAula(Date primeiroDiaAula) {
        this.primeiroDiaAula = primeiroDiaAula;
    }

    public Date getUltimoDiaAula() {
        return ultimoDiaAula;
    }

    public void setUltimoDiaAula(Date ultimoDiaAula) {
        this.ultimoDiaAula = ultimoDiaAula;
    }

    @XmlTransient
    public Collection<Alunos> getAlunosCollection() {
        return alunosCollection;
    }

    public void setAlunosCollection(Collection<Alunos> alunosCollection) {
        this.alunosCollection = alunosCollection;
    }

    @XmlTransient
    public Collection<Semana> getSemanaCollection() {
        return semanaCollection;
    }

    public void setSemanaCollection(Collection<Semana> semanaCollection) {
        this.semanaCollection = semanaCollection;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
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
        if (!(object instanceof Turma)) {
            return false;
        }
        Turma other = (Turma) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Turma[ id=" + id + " ]";
    }
    
}
