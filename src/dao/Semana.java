/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Thayson&Regina
 */
@Entity
@Table(name = "semana")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Semana.findAll", query = "SELECT s FROM Semana s"),
    @NamedQuery(name = "Semana.findById", query = "SELECT s FROM Semana s WHERE s.id = :id"),
    @NamedQuery(name = "Semana.findByAula", query = "SELECT s FROM Semana s WHERE s.aula = :aula"),
    @NamedQuery(name = "Semana.findBySegunda", query = "SELECT s FROM Semana s WHERE s.segunda = :segunda"),
    @NamedQuery(name = "Semana.findByTerca", query = "SELECT s FROM Semana s WHERE s.terca = :terca"),
    @NamedQuery(name = "Semana.findByQuarta", query = "SELECT s FROM Semana s WHERE s.quarta = :quarta"),
    @NamedQuery(name = "Semana.findByQuinta", query = "SELECT s FROM Semana s WHERE s.quinta = :quinta"),
    @NamedQuery(name = "Semana.findBySexta", query = "SELECT s FROM Semana s WHERE s.sexta = :sexta"),
    @NamedQuery(name = "Semana.findBySabado", query = "SELECT s FROM Semana s WHERE s.sabado = :sabado"),
    @NamedQuery(name = "Semana.findByDiasSemana", query = "SELECT s FROM Semana s WHERE s.diasSemana = :diasSemana"),
    @NamedQuery(name = "Semana.findByNumAulas", query = "SELECT s FROM Semana s WHERE s.numAulas = :numAulas")})
public class Semana implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "aula")
    private int aula;
    @Basic(optional = false)
    @Column(name = "segunda")
    private int segunda;
    @Basic(optional = false)
    @Column(name = "terca")
    private int terca;
    @Basic(optional = false)
    @Column(name = "quarta")
    private int quarta;
    @Basic(optional = false)
    @Column(name = "quinta")
    private int quinta;
    @Basic(optional = false)
    @Column(name = "sexta")
    private int sexta;
    @Column(name = "sabado")
    private Integer sabado;
    @Basic(optional = false)
    @Column(name = "diasSemana")
    private int diasSemana;
    @Basic(optional = false)
    @Column(name = "numAulas")
    private int numAulas;
    @JoinColumn(name = "id_turma", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Turma idTurma;

    public Semana() {
    }

    public Semana(Integer id) {
        this.id = id;
    }

    public Semana(Integer id, int aula, int segunda, int terca, int quarta, int quinta, int sexta, int diasSemana, int numAulas) {
        this.id = id;
        this.aula = aula;
        this.segunda = segunda;
        this.terca = terca;
        this.quarta = quarta;
        this.quinta = quinta;
        this.sexta = sexta;
        this.diasSemana = diasSemana;
        this.numAulas = numAulas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAula() {
        return aula;
    }

    public void setAula(int aula) {
        this.aula = aula;
    }

    public int getSegunda() {
        return segunda;
    }

    public void setSegunda(int segunda) {
        this.segunda = segunda;
    }

    public int getTerca() {
        return terca;
    }

    public void setTerca(int terca) {
        this.terca = terca;
    }

    public int getQuarta() {
        return quarta;
    }

    public void setQuarta(int quarta) {
        this.quarta = quarta;
    }

    public int getQuinta() {
        return quinta;
    }

    public void setQuinta(int quinta) {
        this.quinta = quinta;
    }

    public int getSexta() {
        return sexta;
    }

    public void setSexta(int sexta) {
        this.sexta = sexta;
    }

    public Integer getSabado() {
        return sabado;
    }

    public void setSabado(Integer sabado) {
        this.sabado = sabado;
    }

    public int getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(int diasSemana) {
        this.diasSemana = diasSemana;
    }

    public int getNumAulas() {
        return numAulas;
    }

    public void setNumAulas(int numAulas) {
        this.numAulas = numAulas;
    }

    public Turma getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Turma idTurma) {
        this.idTurma = idTurma;
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
        if (!(object instanceof Semana)) {
            return false;
        }
        Semana other = (Semana) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Semana[ id=" + id + " ]";
    }
    
}
