package dao;

import dao.Alunos;
import dao.Turma;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-26T14:25:07")
@StaticMetamodel(Curso.class)
public class Curso_ { 

    public static volatile SingularAttribute<Curso, String> nomeCurso;
    public static volatile CollectionAttribute<Curso, Turma> turmaCollection;
    public static volatile SingularAttribute<Curso, Integer> id;
    public static volatile CollectionAttribute<Curso, Alunos> alunosCollection;

}