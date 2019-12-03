package tcc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Leoruiz197
 */
public class Funcoes {

    static Connection conectar;
    static Statement comando;
    static ResultSet resultado, resultadoUnico;
    static ResultSetMetaData info;
    private String biometria;
    
    public void conectar() {
        try {
            conectar = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "");
            comando = conectar.createStatement();
            System.out.println("Conectado com sucesso");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    String dedoFormatado;

    public void MandaFormatado(byte[] bytess) {
        dedoFormatado = Arrays.toString(bytess);
        biometria = dedoFormatado.replace("[", "").replace("]", "");
        System.out.println(biometria);
    }

    public String getDedo() {
        System.out.println(biometria);
        return biometria;
    }
   
    public int Curso(String nomeCurso){
        int curso = 0;
        ResultSet resul;
        try { 
            comando = (Statement) conectar.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resul = comando.executeQuery("select count(*) as contador from tcc.curso where nome_curso = '" + nomeCurso + "';");  
            resul.next();
            curso = resul.getInt("contador");
        }catch(SQLException e){
            System.out.println(e);
            curso = 0;
        }
        return curso;
    }
    
    public int Materia(String nomeMateria, String nomeProf){
        int materia = 0;
        ResultSet resul;
        try { 
            comando = (Statement) conectar.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resul = comando.executeQuery("select count(*) as contador from tcc.materia where nome = '" + nomeMateria + "' and nome_prof = '"+ nomeProf +"';");  
            resul.next();
            materia = resul.getInt("contador");
        }catch(SQLException e){
            System.out.println(e);
            materia = 0;
        }
        return materia;
    }
    
    public int Turma(String serie, int id_curso){
        int turma = 0;
        ResultSet resul;
        System.out.println("c "+id_curso);
        try { 
            comando = (Statement) conectar.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resul = comando.executeQuery("select count(*) as contador from tcc.turma where serie = '" + serie + "' and id_curso = "+ id_curso +";");  
            resul.next();
            turma = resul.getInt("contador");
        }catch(SQLException e){
            System.out.println(e);
            turma = 0;
        }
        System.out.println("t "+turma);
        return turma;
    }
    
    public int Semana(String turma){
        int t = 0;
        int idturma = 0;
        ResultSet resul;
        try { 
            comando = (Statement) conectar.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resul = comando.executeQuery("select id as idturma from tcc.turma where serie = '" + turma + "';");
            resul.next();
            idturma = resul.getInt("idturma");
            resul = comando.executeQuery("select count(*) as contador from tcc.semana where id_turma = '" + idturma + "';");  
            resul.next();
            t = resul.getInt("contador");
        }catch(SQLException e){
            System.out.println(e);
            t = 0;
            idturma = 0;
        }
        System.out.println("t "+t);
        return t;
    }
    
    public int Aluno(String ra){
        int r = 0;
        ResultSet resul;
        
        try { 
            comando = (Statement) conectar.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resul = comando.executeQuery("select count(*) as contador from tcc.alunos where ra = " + ra + ";");  
            resul.next();
            r = resul.getInt("contador");
        }catch(SQLException e){
            System.out.println(e);
            r = 0;
        }
        System.out.println("r "+r);
        return r;
    }
    
    public byte[] VoltaBD(int id) {
        byte[] bytesRetorno = new byte[1632];
        String quebra[] = new String[1632];

        try {
            resultado = comando.executeQuery("select * from dedo.teste where id = " + id);
            while (resultado.next()) {
                String bytes = resultado.getObject(3).toString();
                quebra = bytes.split(",");

                int cont = 0;
                for (String b : quebra) {
                    bytesRetorno[cont] = Byte.parseByte(b.trim());
                    cont++;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return bytesRetorno;
    }
    
    void excluir(int id) {
        try {
            comando.executeUpdate("delete from tcc.semana where semana.id_turma = " + id);
            JOptionPane.showMessageDialog(null, "Horario excluido com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO ao Excluir!");
        }
    }

    void updateHorario(int segunda, int terca, int quarta, int quinta, int sexta, int sabado, int diasSemana, int numAulas, int id_turma, int aula) {
        try {
             comando.executeUpdate("update tcc.semana set segunda = " + segunda + ", terca = " +terca +", quarta = "+ quarta +", quinta = "+ quinta +", sexta = "+ sexta +", sabado = "+ sabado +", diasSemana = "+ diasSemana +", numAulas = "+numAulas+" where id_turma = '" + id_turma + "' AND aula = "+aula);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    void updateDiario(String nomeNovo, String nomeAntigo){
        conectar();
        try {
            comando.executeUpdate("update tcc.diario set nome_aluno = '"+nomeNovo+"' where nome_aluno = '"+nomeAntigo+"'");
        } catch (SQLException ex) {
            Logger.getLogger(Funcoes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
