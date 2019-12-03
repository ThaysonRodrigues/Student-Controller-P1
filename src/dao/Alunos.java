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
@Table(name = "alunos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alunos.findAll", query = "SELECT a FROM Alunos a"),
    @NamedQuery(name = "Alunos.findById", query = "SELECT a FROM Alunos a WHERE a.id = :id"),
    @NamedQuery(name = "Alunos.findByNome", query = "SELECT a FROM Alunos a WHERE a.nome = :nome"),
    @NamedQuery(name = "Alunos.findByDataNascimento", query = "SELECT a FROM Alunos a WHERE a.dataNascimento = :dataNascimento"),
    @NamedQuery(name = "Alunos.findByRa", query = "SELECT a FROM Alunos a WHERE a.ra = :ra"),
    @NamedQuery(name = "Alunos.findByCpf", query = "SELECT a FROM Alunos a WHERE a.cpf = :cpf"),
    @NamedQuery(name = "Alunos.findByRg", query = "SELECT a FROM Alunos a WHERE a.rg = :rg"),
    @NamedQuery(name = "Alunos.findByEndereco", query = "SELECT a FROM Alunos a WHERE a.endereco = :endereco"),
    @NamedQuery(name = "Alunos.findByTel1", query = "SELECT a FROM Alunos a WHERE a.tel1 = :tel1"),
    @NamedQuery(name = "Alunos.findByTel2", query = "SELECT a FROM Alunos a WHERE a.tel2 = :tel2"),
    @NamedQuery(name = "Alunos.findByNomeResp", query = "SELECT a FROM Alunos a WHERE a.nomeResp = :nomeResp"),
    @NamedQuery(name = "Alunos.findByCelPai", query = "SELECT a FROM Alunos a WHERE a.celPai = :celPai"),
    @NamedQuery(name = "Alunos.findBySenha", query = "SELECT a FROM Alunos a WHERE a.senha = :senha"),
    @NamedQuery(name = "Alunos.findByBiometria", query = "SELECT a FROM Alunos a WHERE a.biometria = :biometria")})
public class Alunos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Basic(optional = false)
    @Column(name = "ra")
    private String ra;
    @Column(name = "cpf")
    private String cpf;
    @Basic(optional = false)
    @Column(name = "rg")
    private String rg;
    @Basic(optional = false)
    @Column(name = "endereco")
    private String endereco;
    @Basic(optional = false)
    @Column(name = "tel1")
    private String tel1;
    @Column(name = "tel2")
    private String tel2;
    @Basic(optional = false)
    @Column(name = "nome_resp")
    private String nomeResp;
    @Basic(optional = false)
    @Column(name = "cel_pai")
    private String celPai;
    @Column(name = "senha")
    private String senha;
    @Column(name = "biometria")
    private String biometria;
    @JoinColumn(name = "curso", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Curso curso;
    @JoinColumn(name = "turma", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Turma turma;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAluno")
    private Collection<Frequencia> frequenciaCollection;

    public Alunos() {
    }

    public Alunos(Integer id) {
        this.id = id;
    }

    public Alunos(Integer id, String nome, Date dataNascimento, String ra, String rg, String endereco, String tel1, String nomeResp, String celPai) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.ra = ra;
        this.rg = rg;
        this.endereco = endereco;
        this.tel1 = tel1;
        this.nomeResp = nomeResp;
        this.celPai = celPai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getNomeResp() {
        return nomeResp;
    }

    public void setNomeResp(String nomeResp) {
        this.nomeResp = nomeResp;
    }

    public String getCelPai() {
        return celPai;
    }

    public void setCelPai(String celPai) {
        this.celPai = celPai;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getBiometria() {
        return biometria;
    }

    public void setBiometria(String biometria) {
        this.biometria = biometria;
    }

    @XmlTransient

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @XmlTransient
    public Collection<Frequencia> getFrequenciaCollection() {
        return frequenciaCollection;
    }

    public void setFrequenciaCollection(Collection<Frequencia> frequenciaCollection) {
        this.frequenciaCollection = frequenciaCollection;
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
        if (!(object instanceof Alunos)) {
            return false;
        }
        Alunos other = (Alunos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Alunos[ id=" + id + " ]";
    }
    
}
