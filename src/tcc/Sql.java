package tcc;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Sql {

    static Connection conecta;
    static Statement comando;
    static ResultSet pegarCurso, mostrarCurso;
    static ResultSetMetaData info;
    boolean conf;
    
    
    void conectar() {
        try {
            conecta = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "");
            comando = conecta.createStatement();
            System.out.println("Conectado com sucesso");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean inserirCurso(String nome) {
        try {
            comando.executeUpdate("insert into tcc.curso (nome_curso) values ('" + nome + "')");
            JOptionPane.showMessageDialog(null, "Curso cadastrado com sucesso !");
            conf = true;
        } catch (SQLException | HeadlessException e) {
            System.out.println(e);
        }
        return conf;
    }

    public boolean inserirMateria(String nome, String nome_prof) {
        try {
            comando.executeUpdate("insert into tcc.materia (nome,nome_prof) values ('" + nome + "','" + nome_prof + "')");
            JOptionPane.showMessageDialog(null, "Matéria cadastrada com sucesso !");
            conf = true;
        } catch (SQLException | HeadlessException e) {
            System.out.println(e);
        }
        return conf;
    }

    public boolean inserirTurma(String serie, Object id_curso, String max_alunos, String periodo, String primeiroDia, String ultimoDia) {
        try {
            comando.executeUpdate("insert into tcc.turma (serie,id_curso,max_alunos,periodo,primeiroDiaAula,ultimoDiaAula) values ('" + serie + "'," + id_curso + "," + max_alunos + ",'" + periodo + "','" + primeiroDia + "','" + ultimoDia + "')");
            JOptionPane.showMessageDialog(null, "Turma cadastrada com sucesso !");
            conf = true;
        } catch (SQLException | HeadlessException e) {
            System.out.println(e);
        }
        return conf;
    }

    public boolean inserirAlno(String nome, String dataNasc, String ra, String cpf, String rg, String endereco, String tel1, String tel2, String nome_resp, String cel_pai, Object turma, String curso) {
        try {
            comando.executeUpdate("insert into tcc.alunos (nome,data_nascimento,ra,cpf,rg,endereco,tel1,tel2,nome_resp,cel_pai,turma,curso) values ('" + nome + "','" + dataNasc + "','" + ra + "','" + cpf + "','" + rg + "','" + endereco + "'," + tel1 + "," + tel2 + ",'" + nome_resp + "'," + cel_pai + "," + turma + "," + curso + ")");
            JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso !");
            conf = true;
        } catch (SQLException | HeadlessException e) {
            System.out.println(e);
        }
        return conf;
    }

    public String[] pegarCurso() {
        int i = 0;
        String nome_curso[];
        try {
            pegarCurso = comando.executeQuery("select (nome_curso) from tcc.curso");

            pegarCurso.last();
            int linhas = pegarCurso.getRow();
            pegarCurso.beforeFirst();

            nome_curso = new String[linhas];

            while (pegarCurso.next()) {
                nome_curso[i] = pegarCurso.getObject("nome_curso").toString();
                i++;
            }
        } catch (Exception e) {
            nome_curso = new String[1];
            System.out.println(e);
        }

        return nome_curso;
    }

    public String[] pegarTurma() {
        int i = 0;
        String nome_Turma[];
        try {
            pegarCurso = comando.executeQuery("select * from tcc.turma");

            pegarCurso.last();
            int linhas = pegarCurso.getRow();
            pegarCurso.beforeFirst();

            nome_Turma = new String[linhas];

            while (pegarCurso.next()) {
                nome_Turma[i] = pegarCurso.getObject("serie").toString();
                i++;
            }
        } catch (Exception e) {
            nome_Turma = new String[1];
            System.out.println(e);
        }

        return nome_Turma;
    }

    ResultSet getCurso(int id) {
        try {
            pegarCurso = comando.executeQuery("select * from tcc.turma where id=" + id);
            pegarCurso.next();
        } catch (Exception e) {
            System.out.println(e);
        }
        return pegarCurso;
    }

    ResultSet MostrarCurso(String id) {
        try {
            mostrarCurso = comando.executeQuery("select * from tcc.curso where id=" + id);
            mostrarCurso.next();
        } catch (Exception e) {
            System.out.println(e);
        }
        return mostrarCurso;
    }
}
