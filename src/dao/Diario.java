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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Thayson&Regina
 */
@Entity
@Table(name = "diario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diario.findAll", query = "SELECT d FROM Diario d"),
    @NamedQuery(name = "Diario.findById", query = "SELECT d FROM Diario d WHERE d.id = :id"),
    @NamedQuery(name = "Diario.findByNomeAluno", query = "SELECT d FROM Diario d WHERE d.nomeAluno = :nomeAluno"),
    @NamedQuery(name = "Diario.findByA1", query = "SELECT d FROM Diario d WHERE d.a1 = :a1"),
    @NamedQuery(name = "Diario.findByA2", query = "SELECT d FROM Diario d WHERE d.a2 = :a2"),
    @NamedQuery(name = "Diario.findByA3", query = "SELECT d FROM Diario d WHERE d.a3 = :a3"),
    @NamedQuery(name = "Diario.findByA4", query = "SELECT d FROM Diario d WHERE d.a4 = :a4"),
    @NamedQuery(name = "Diario.findByA5", query = "SELECT d FROM Diario d WHERE d.a5 = :a5"),
    @NamedQuery(name = "Diario.findByA6", query = "SELECT d FROM Diario d WHERE d.a6 = :a6"),
    @NamedQuery(name = "Diario.findByA7", query = "SELECT d FROM Diario d WHERE d.a7 = :a7"),
    @NamedQuery(name = "Diario.findByA8", query = "SELECT d FROM Diario d WHERE d.a8 = :a8"),
    @NamedQuery(name = "Diario.findByA9", query = "SELECT d FROM Diario d WHERE d.a9 = :a9"),
    @NamedQuery(name = "Diario.findByA10", query = "SELECT d FROM Diario d WHERE d.a10 = :a10"),
    @NamedQuery(name = "Diario.findByA11", query = "SELECT d FROM Diario d WHERE d.a11 = :a11"),
    @NamedQuery(name = "Diario.findByA12", query = "SELECT d FROM Diario d WHERE d.a12 = :a12"),
    @NamedQuery(name = "Diario.findByA13", query = "SELECT d FROM Diario d WHERE d.a13 = :a13"),
    @NamedQuery(name = "Diario.findByA14", query = "SELECT d FROM Diario d WHERE d.a14 = :a14"),
    @NamedQuery(name = "Diario.findByA15", query = "SELECT d FROM Diario d WHERE d.a15 = :a15"),
    @NamedQuery(name = "Diario.findByA16", query = "SELECT d FROM Diario d WHERE d.a16 = :a16"),
    @NamedQuery(name = "Diario.findByA17", query = "SELECT d FROM Diario d WHERE d.a17 = :a17"),
    @NamedQuery(name = "Diario.findByA18", query = "SELECT d FROM Diario d WHERE d.a18 = :a18"),
    @NamedQuery(name = "Diario.findByA19", query = "SELECT d FROM Diario d WHERE d.a19 = :a19"),
    @NamedQuery(name = "Diario.findByA20", query = "SELECT d FROM Diario d WHERE d.a20 = :a20"),
    @NamedQuery(name = "Diario.findByA21", query = "SELECT d FROM Diario d WHERE d.a21 = :a21"),
    @NamedQuery(name = "Diario.findByA22", query = "SELECT d FROM Diario d WHERE d.a22 = :a22"),
    @NamedQuery(name = "Diario.findByA23", query = "SELECT d FROM Diario d WHERE d.a23 = :a23"),
    @NamedQuery(name = "Diario.findByA24", query = "SELECT d FROM Diario d WHERE d.a24 = :a24"),
    @NamedQuery(name = "Diario.findByA25", query = "SELECT d FROM Diario d WHERE d.a25 = :a25"),
    @NamedQuery(name = "Diario.findByA26", query = "SELECT d FROM Diario d WHERE d.a26 = :a26"),
    @NamedQuery(name = "Diario.findByA27", query = "SELECT d FROM Diario d WHERE d.a27 = :a27"),
    @NamedQuery(name = "Diario.findByA28", query = "SELECT d FROM Diario d WHERE d.a28 = :a28"),
    @NamedQuery(name = "Diario.findByA29", query = "SELECT d FROM Diario d WHERE d.a29 = :a29"),
    @NamedQuery(name = "Diario.findByA30", query = "SELECT d FROM Diario d WHERE d.a30 = :a30"),
    @NamedQuery(name = "Diario.findByA31", query = "SELECT d FROM Diario d WHERE d.a31 = :a31"),
    @NamedQuery(name = "Diario.findByMes", query = "SELECT d FROM Diario d WHERE d.mes = :mes"),
    @NamedQuery(name = "Diario.findByAno", query = "SELECT d FROM Diario d WHERE d.ano = :ano"),
    @NamedQuery(name = "Diario.findByIdTurma", query = "SELECT d FROM Diario d WHERE d.idTurma = :idTurma")})
public class Diario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nome_aluno")
    private String nomeAluno;
    @Column(name = "A1")
    private Character a1;
    @Column(name = "A2")
    private Character a2;
    @Column(name = "A3")
    private Character a3;
    @Column(name = "A4")
    private Character a4;
    @Column(name = "A5")
    private Character a5;
    @Column(name = "A6")
    private Character a6;
    @Column(name = "A7")
    private Character a7;
    @Column(name = "A8")
    private Character a8;
    @Column(name = "A9")
    private Character a9;
    @Column(name = "A10")
    private Character a10;
    @Column(name = "A11")
    private Character a11;
    @Column(name = "A12")
    private Character a12;
    @Column(name = "A13")
    private Character a13;
    @Column(name = "A14")
    private Character a14;
    @Column(name = "A15")
    private Character a15;
    @Column(name = "A16")
    private Character a16;
    @Column(name = "A17")
    private Character a17;
    @Column(name = "A18")
    private Character a18;
    @Column(name = "A19")
    private Character a19;
    @Column(name = "A20")
    private Character a20;
    @Column(name = "A21")
    private Character a21;
    @Column(name = "A22")
    private Character a22;
    @Column(name = "A23")
    private Character a23;
    @Column(name = "A24")
    private Character a24;
    @Column(name = "A25")
    private Character a25;
    @Column(name = "A26")
    private Character a26;
    @Column(name = "A27")
    private Character a27;
    @Column(name = "A28")
    private Character a28;
    @Column(name = "A29")
    private Character a29;
    @Column(name = "A30")
    private Character a30;
    @Column(name = "A31")
    private Character a31;
    @Column(name = "mes")
    private Integer mes;
    @Column(name = "ano")
    private Integer ano;
    @Column(name = "id_turma")
    private Integer idTurma;

    public Diario() {
    }

    public Diario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public Character getA1() {
        return a1;
    }

    public void setA1(Character a1) {
        this.a1 = a1;
    }

    public Character getA2() {
        return a2;
    }

    public void setA2(Character a2) {
        this.a2 = a2;
    }

    public Character getA3() {
        return a3;
    }

    public void setA3(Character a3) {
        this.a3 = a3;
    }

    public Character getA4() {
        return a4;
    }

    public void setA4(Character a4) {
        this.a4 = a4;
    }

    public Character getA5() {
        return a5;
    }

    public void setA5(Character a5) {
        this.a5 = a5;
    }

    public Character getA6() {
        return a6;
    }

    public void setA6(Character a6) {
        this.a6 = a6;
    }

    public Character getA7() {
        return a7;
    }

    public void setA7(Character a7) {
        this.a7 = a7;
    }

    public Character getA8() {
        return a8;
    }

    public void setA8(Character a8) {
        this.a8 = a8;
    }

    public Character getA9() {
        return a9;
    }

    public void setA9(Character a9) {
        this.a9 = a9;
    }

    public Character getA10() {
        return a10;
    }

    public void setA10(Character a10) {
        this.a10 = a10;
    }

    public Character getA11() {
        return a11;
    }

    public void setA11(Character a11) {
        this.a11 = a11;
    }

    public Character getA12() {
        return a12;
    }

    public void setA12(Character a12) {
        this.a12 = a12;
    }

    public Character getA13() {
        return a13;
    }

    public void setA13(Character a13) {
        this.a13 = a13;
    }

    public Character getA14() {
        return a14;
    }

    public void setA14(Character a14) {
        this.a14 = a14;
    }

    public Character getA15() {
        return a15;
    }

    public void setA15(Character a15) {
        this.a15 = a15;
    }

    public Character getA16() {
        return a16;
    }

    public void setA16(Character a16) {
        this.a16 = a16;
    }

    public Character getA17() {
        return a17;
    }

    public void setA17(Character a17) {
        this.a17 = a17;
    }

    public Character getA18() {
        return a18;
    }

    public void setA18(Character a18) {
        this.a18 = a18;
    }

    public Character getA19() {
        return a19;
    }

    public void setA19(Character a19) {
        this.a19 = a19;
    }

    public Character getA20() {
        return a20;
    }

    public void setA20(Character a20) {
        this.a20 = a20;
    }

    public Character getA21() {
        return a21;
    }

    public void setA21(Character a21) {
        this.a21 = a21;
    }

    public Character getA22() {
        return a22;
    }

    public void setA22(Character a22) {
        this.a22 = a22;
    }

    public Character getA23() {
        return a23;
    }

    public void setA23(Character a23) {
        this.a23 = a23;
    }

    public Character getA24() {
        return a24;
    }

    public void setA24(Character a24) {
        this.a24 = a24;
    }

    public Character getA25() {
        return a25;
    }

    public void setA25(Character a25) {
        this.a25 = a25;
    }

    public Character getA26() {
        return a26;
    }

    public void setA26(Character a26) {
        this.a26 = a26;
    }

    public Character getA27() {
        return a27;
    }

    public void setA27(Character a27) {
        this.a27 = a27;
    }

    public Character getA28() {
        return a28;
    }

    public void setA28(Character a28) {
        this.a28 = a28;
    }

    public Character getA29() {
        return a29;
    }

    public void setA29(Character a29) {
        this.a29 = a29;
    }

    public Character getA30() {
        return a30;
    }

    public void setA30(Character a30) {
        this.a30 = a30;
    }

    public Character getA31() {
        return a31;
    }

    public void setA31(Character a31) {
        this.a31 = a31;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Integer idTurma) {
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
        if (!(object instanceof Diario)) {
            return false;
        }
        Diario other = (Diario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Diario[ id=" + id + " ]";
    }
    
}
