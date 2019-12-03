package dao;

import dao.Alunos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-26T14:25:07")
@StaticMetamodel(Frequencia.class)
public class Frequencia_ { 

    public static volatile SingularAttribute<Frequencia, Character> presenca;
    public static volatile SingularAttribute<Frequencia, Date> data;
    public static volatile SingularAttribute<Frequencia, Integer> aula;
    public static volatile SingularAttribute<Frequencia, Integer> idMateria;
    public static volatile SingularAttribute<Frequencia, Character> falta;
    public static volatile SingularAttribute<Frequencia, Alunos> idAluno;
    public static volatile SingularAttribute<Frequencia, Integer> id;

}