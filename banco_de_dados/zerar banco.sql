delete from semana where id > 0;
alter table semana auto_increment = 1;

delete from frequencia where id > 0;
alter table frequencia auto_increment = 1;

delete from alunos where id > 0;
alter table alunos auto_increment = 1;

delete from turma where id > 0;
alter table turma auto_increment = 1;

delete from curso where id > 0;
alter table curso auto_increment = 1;

delete from materia where id > 0;
alter table materia auto_increment = 1;

delete from diario where id > 0;
alter table diario auto_increment = 1;