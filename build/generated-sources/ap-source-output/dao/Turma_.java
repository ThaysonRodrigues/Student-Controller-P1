package dao;

import dao.Alunos;
import dao.Curso;
import dao.Semana;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-26T14:25:07")
@StaticMetamodel(Turma.class)
public class Turma_ { 

    public static volatile SingularAttribute<Turma, Date> ultimoDiaAula;
    public static volatile SingularAttribute<Turma, String> periodo;
    public static volatile CollectionAttribute<Turma, Semana> semanaCollection;
    public static volatile SingularAttribute<Turma, String> serie;
    public static volatile SingularAttribute<Turma, Integer> maxAlunos;
    public static volatile SingularAttribute<Turma, Curso> idCurso;
    public static volatile SingularAttribute<Turma, Integer> id;
    public static volatile CollectionAttribute<Turma, Alunos> alunosCollection;
    public static volatile SingularAttribute<Turma, Date> primeiroDiaAula;

}