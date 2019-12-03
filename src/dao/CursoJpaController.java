/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Thayson&Regina
 */
public class CursoJpaController implements Serializable {

    public CursoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Curso curso) {
        if (curso.getAlunosCollection() == null) {
            curso.setAlunosCollection(new ArrayList<Alunos>());
        }
        if (curso.getTurmaCollection() == null) {
            curso.setTurmaCollection(new ArrayList<Turma>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Alunos> attachedAlunosCollection = new ArrayList<Alunos>();
            for (Alunos alunosCollectionAlunosToAttach : curso.getAlunosCollection()) {
                alunosCollectionAlunosToAttach = em.getReference(alunosCollectionAlunosToAttach.getClass(), alunosCollectionAlunosToAttach.getId());
                attachedAlunosCollection.add(alunosCollectionAlunosToAttach);
            }
            curso.setAlunosCollection(attachedAlunosCollection);
            Collection<Turma> attachedTurmaCollection = new ArrayList<Turma>();
            for (Turma turmaCollectionTurmaToAttach : curso.getTurmaCollection()) {
                turmaCollectionTurmaToAttach = em.getReference(turmaCollectionTurmaToAttach.getClass(), turmaCollectionTurmaToAttach.getId());
                attachedTurmaCollection.add(turmaCollectionTurmaToAttach);
            }
            curso.setTurmaCollection(attachedTurmaCollection);
            em.persist(curso);
            for (Alunos alunosCollectionAlunos : curso.getAlunosCollection()) {
                Curso oldCursoOfAlunosCollectionAlunos = alunosCollectionAlunos.getCurso();
                alunosCollectionAlunos.setCurso(curso);
                alunosCollectionAlunos = em.merge(alunosCollectionAlunos);
                if (oldCursoOfAlunosCollectionAlunos != null) {
                    oldCursoOfAlunosCollectionAlunos.getAlunosCollection().remove(alunosCollectionAlunos);
                    oldCursoOfAlunosCollectionAlunos = em.merge(oldCursoOfAlunosCollectionAlunos);
                }
            }
            for (Turma turmaCollectionTurma : curso.getTurmaCollection()) {
                Curso oldIdCursoOfTurmaCollectionTurma = turmaCollectionTurma.getIdCurso();
                turmaCollectionTurma.setIdCurso(curso);
                turmaCollectionTurma = em.merge(turmaCollectionTurma);
                if (oldIdCursoOfTurmaCollectionTurma != null) {
                    oldIdCursoOfTurmaCollectionTurma.getTurmaCollection().remove(turmaCollectionTurma);
                    oldIdCursoOfTurmaCollectionTurma = em.merge(oldIdCursoOfTurmaCollectionTurma);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Curso curso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Curso persistentCurso = em.find(Curso.class, curso.getId());
            Collection<Alunos> alunosCollectionOld = persistentCurso.getAlunosCollection();
            Collection<Alunos> alunosCollectionNew = curso.getAlunosCollection();
            Collection<Turma> turmaCollectionOld = persistentCurso.getTurmaCollection();
            Collection<Turma> turmaCollectionNew = curso.getTurmaCollection();
            List<String> illegalOrphanMessages = null;
            for (Alunos alunosCollectionOldAlunos : alunosCollectionOld) {
                if (!alunosCollectionNew.contains(alunosCollectionOldAlunos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Alunos " + alunosCollectionOldAlunos + " since its curso field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Alunos> attachedAlunosCollectionNew = new ArrayList<Alunos>();
            for (Alunos alunosCollectionNewAlunosToAttach : alunosCollectionNew) {
                alunosCollectionNewAlunosToAttach = em.getReference(alunosCollectionNewAlunosToAttach.getClass(), alunosCollectionNewAlunosToAttach.getId());
                attachedAlunosCollectionNew.add(alunosCollectionNewAlunosToAttach);
            }
            alunosCollectionNew = attachedAlunosCollectionNew;
            curso.setAlunosCollection(alunosCollectionNew);
            Collection<Turma> attachedTurmaCollectionNew = new ArrayList<Turma>();
            for (Turma turmaCollectionNewTurmaToAttach : turmaCollectionNew) {
                turmaCollectionNewTurmaToAttach = em.getReference(turmaCollectionNewTurmaToAttach.getClass(), turmaCollectionNewTurmaToAttach.getId());
                attachedTurmaCollectionNew.add(turmaCollectionNewTurmaToAttach);
            }
            turmaCollectionNew = attachedTurmaCollectionNew;
            curso.setTurmaCollection(turmaCollectionNew);
            curso = em.merge(curso);
            for (Alunos alunosCollectionNewAlunos : alunosCollectionNew) {
                if (!alunosCollectionOld.contains(alunosCollectionNewAlunos)) {
                    Curso oldCursoOfAlunosCollectionNewAlunos = alunosCollectionNewAlunos.getCurso();
                    alunosCollectionNewAlunos.setCurso(curso);
                    alunosCollectionNewAlunos = em.merge(alunosCollectionNewAlunos);
                    if (oldCursoOfAlunosCollectionNewAlunos != null && !oldCursoOfAlunosCollectionNewAlunos.equals(curso)) {
                        oldCursoOfAlunosCollectionNewAlunos.getAlunosCollection().remove(alunosCollectionNewAlunos);
                        oldCursoOfAlunosCollectionNewAlunos = em.merge(oldCursoOfAlunosCollectionNewAlunos);
                    }
                }
            }
            for (Turma turmaCollectionOldTurma : turmaCollectionOld) {
                if (!turmaCollectionNew.contains(turmaCollectionOldTurma)) {
                    turmaCollectionOldTurma.setIdCurso(null);
                    turmaCollectionOldTurma = em.merge(turmaCollectionOldTurma);
                }
            }
            for (Turma turmaCollectionNewTurma : turmaCollectionNew) {
                if (!turmaCollectionOld.contains(turmaCollectionNewTurma)) {
                    Curso oldIdCursoOfTurmaCollectionNewTurma = turmaCollectionNewTurma.getIdCurso();
                    turmaCollectionNewTurma.setIdCurso(curso);
                    turmaCollectionNewTurma = em.merge(turmaCollectionNewTurma);
                    if (oldIdCursoOfTurmaCollectionNewTurma != null && !oldIdCursoOfTurmaCollectionNewTurma.equals(curso)) {
                        oldIdCursoOfTurmaCollectionNewTurma.getTurmaCollection().remove(turmaCollectionNewTurma);
                        oldIdCursoOfTurmaCollectionNewTurma = em.merge(oldIdCursoOfTurmaCollectionNewTurma);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = curso.getId();
                if (findCurso(id) == null) {
                    throw new NonexistentEntityException("The curso with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Curso curso;
            try {
                curso = em.getReference(Curso.class, id);
                curso.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The curso with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Alunos> alunosCollectionOrphanCheck = curso.getAlunosCollection();
            for (Alunos alunosCollectionOrphanCheckAlunos : alunosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Curso (" + curso + ") cannot be destroyed since the Alunos " + alunosCollectionOrphanCheckAlunos + " in its alunosCollection field has a non-nullable curso field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Turma> turmaCollection = curso.getTurmaCollection();
            for (Turma turmaCollectionTurma : turmaCollection) {
                turmaCollectionTurma.setIdCurso(null);
                turmaCollectionTurma = em.merge(turmaCollectionTurma);
            }
            em.remove(curso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Curso> findCursoEntities() {
        return findCursoEntities(true, -1, -1);
    }

    public List<Curso> findCursoEntities(int maxResults, int firstResult) {
        return findCursoEntities(false, maxResults, firstResult);
    }

    private List<Curso> findCursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Curso.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Curso findCurso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Curso.class, id);
        } finally {
            em.close();
        }
    }

    public int getCursoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Curso> rt = cq.from(Curso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
