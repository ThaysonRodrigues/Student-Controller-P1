package dao;

import dao.Curso;
import dao.Frequencia;
import dao.Turma;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-26T14:25:07")
@StaticMetamodel(Alunos.class)
public class Alunos_ { 

    public static volatile SingularAttribute<Alunos, String> tel1;
    public static volatile SingularAttribute<Alunos, String> tel2;
    public static volatile SingularAttribute<Alunos, String> nomeResp;
    public static volatile SingularAttribute<Alunos, String> endereco;
    public static volatile SingularAttribute<Alunos, String> nome;
    public static volatile SingularAttribute<Alunos, String> ra;
    public static volatile CollectionAttribute<Alunos, Frequencia> frequenciaCollection;
    public static volatile SingularAttribute<Alunos, String> senha;
    public static volatile SingularAttribute<Alunos, String> biometria;
    public static volatile SingularAttribute<Alunos, String> rg;
    public static volatile SingularAttribute<Alunos, Curso> curso;
    public static volatile SingularAttribute<Alunos, String> cpf;
    public static volatile SingularAttribute<Alunos, Integer> id;
    public static volatile SingularAttribute<Alunos, Date> dataNascimento;
    public static volatile SingularAttribute<Alunos, Turma> turma;
    public static volatile SingularAttribute<Alunos, String> celPai;

}