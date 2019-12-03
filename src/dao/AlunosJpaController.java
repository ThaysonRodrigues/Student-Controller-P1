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
public class AlunosJpaController implements Serializable {

    public AlunosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alunos alunos) {
        if (alunos.getFrequenciaCollection() == null) {
            alunos.setFrequenciaCollection(new ArrayList<Frequencia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Curso curso = alunos.getCurso();
            if (curso != null) {
                curso = em.getReference(curso.getClass(), curso.getId());
                alunos.setCurso(curso);
            }
            Turma turma = alunos.getTurma();
            if (turma != null) {
                turma = em.getReference(turma.getClass(), turma.getId());
                alunos.setTurma(turma);
            }
            Collection<Frequencia> attachedFrequenciaCollection = new ArrayList<Frequencia>();
            for (Frequencia frequenciaCollectionFrequenciaToAttach : alunos.getFrequenciaCollection()) {
                frequenciaCollectionFrequenciaToAttach = em.getReference(frequenciaCollectionFrequenciaToAttach.getClass(), frequenciaCollectionFrequenciaToAttach.getId());
                attachedFrequenciaCollection.add(frequenciaCollectionFrequenciaToAttach);
            }
            alunos.setFrequenciaCollection(attachedFrequenciaCollection);
            em.persist(alunos);
            if (curso != null) {
                curso.getAlunosCollection().add(alunos);
                curso = em.merge(curso);
            }
            if (turma != null) {
                turma.getAlunosCollection().add(alunos);
                turma = em.merge(turma);
            }
            for (Frequencia frequenciaCollectionFrequencia : alunos.getFrequenciaCollection()) {
                Alunos oldIdAlunoOfFrequenciaCollectionFrequencia = frequenciaCollectionFrequencia.getIdAluno();
                frequenciaCollectionFrequencia.setIdAluno(alunos);
                frequenciaCollectionFrequencia = em.merge(frequenciaCollectionFrequencia);
                if (oldIdAlunoOfFrequenciaCollectionFrequencia != null) {
                    oldIdAlunoOfFrequenciaCollectionFrequencia.getFrequenciaCollection().remove(frequenciaCollectionFrequencia);
                    oldIdAlunoOfFrequenciaCollectionFrequencia = em.merge(oldIdAlunoOfFrequenciaCollectionFrequencia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alunos alunos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alunos persistentAlunos = em.find(Alunos.class, alunos.getId());
            Curso cursoOld = persistentAlunos.getCurso();
            Curso cursoNew = alunos.getCurso();
            Turma turmaOld = persistentAlunos.getTurma();
            Turma turmaNew = alunos.getTurma();
            Collection<Frequencia> frequenciaCollectionOld = persistentAlunos.getFrequenciaCollection();
            Collection<Frequencia> frequenciaCollectionNew = alunos.getFrequenciaCollection();
            List<String> illegalOrphanMessages = null;
            for (Frequencia frequenciaCollectionOldFrequencia : frequenciaCollectionOld) {
                if (!frequenciaCollectionNew.contains(frequenciaCollectionOldFrequencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Frequencia " + frequenciaCollectionOldFrequencia + " since its idAluno field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cursoNew != null) {
                cursoNew = em.getReference(cursoNew.getClass(), cursoNew.getId());
                alunos.setCurso(cursoNew);
            }
            if (turmaNew != null) {
                turmaNew = em.getReference(turmaNew.getClass(), turmaNew.getId());
                alunos.setTurma(turmaNew);
            }
            Collection<Frequencia> attachedFrequenciaCollectionNew = new ArrayList<Frequencia>();
            for (Frequencia frequenciaCollectionNewFrequenciaToAttach : frequenciaCollectionNew) {
                frequenciaCollectionNewFrequenciaToAttach = em.getReference(frequenciaCollectionNewFrequenciaToAttach.getClass(), frequenciaCollectionNewFrequenciaToAttach.getId());
                attachedFrequenciaCollectionNew.add(frequenciaCollectionNewFrequenciaToAttach);
            }
            frequenciaCollectionNew = attachedFrequenciaCollectionNew;
            alunos.setFrequenciaCollection(frequenciaCollectionNew);
            alunos = em.merge(alunos);
            if (cursoOld != null && !cursoOld.equals(cursoNew)) {
                cursoOld.getAlunosCollection().remove(alunos);
                cursoOld = em.merge(cursoOld);
            }
            if (cursoNew != null && !cursoNew.equals(cursoOld)) {
                cursoNew.getAlunosCollection().add(alunos);
                cursoNew = em.merge(cursoNew);
            }
            if (turmaOld != null && !turmaOld.equals(turmaNew)) {
                turmaOld.getAlunosCollection().remove(alunos);
                turmaOld = em.merge(turmaOld);
            }
            if (turmaNew != null && !turmaNew.equals(turmaOld)) {
                turmaNew.getAlunosCollection().add(alunos);
                turmaNew = em.merge(turmaNew);
            }
            for (Frequencia frequenciaCollectionNewFrequencia : frequenciaCollectionNew) {
                if (!frequenciaCollectionOld.contains(frequenciaCollectionNewFrequencia)) {
                    Alunos oldIdAlunoOfFrequenciaCollectionNewFrequencia = frequenciaCollectionNewFrequencia.getIdAluno();
                    frequenciaCollectionNewFrequencia.setIdAluno(alunos);
                    frequenciaCollectionNewFrequencia = em.merge(frequenciaCollectionNewFrequencia);
                    if (oldIdAlunoOfFrequenciaCollectionNewFrequencia != null && !oldIdAlunoOfFrequenciaCollectionNewFrequencia.equals(alunos)) {
                        oldIdAlunoOfFrequenciaCollectionNewFrequencia.getFrequenciaCollection().remove(frequenciaCollectionNewFrequencia);
                        oldIdAlunoOfFrequenciaCollectionNewFrequencia = em.merge(oldIdAlunoOfFrequenciaCollectionNewFrequencia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = alunos.getId();
                if (findAlunos(id) == null) {
                    throw new NonexistentEntityException("The alunos with id " + id + " no longer exists.");
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
            Alunos alunos;
            try {
                alunos = em.getReference(Alunos.class, id);
                alunos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alunos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Frequencia> frequenciaCollectionOrphanCheck = alunos.getFrequenciaCollection();
            for (Frequencia frequenciaCollectionOrphanCheckFrequencia : frequenciaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Alunos (" + alunos + ") cannot be destroyed since the Frequencia " + frequenciaCollectionOrphanCheckFrequencia + " in its frequenciaCollection field has a non-nullable idAluno field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Curso curso = alunos.getCurso();
            if (curso != null) {
                curso.getAlunosCollection().remove(alunos);
                curso = em.merge(curso);
            }
            Turma turma = alunos.getTurma();
            if (turma != null) {
                turma.getAlunosCollection().remove(alunos);
                turma = em.merge(turma);
            }
            em.remove(alunos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alunos> findAlunosEntities() {
        return findAlunosEntities(true, -1, -1);
    }

    public List<Alunos> findAlunosEntities(int maxResults, int firstResult) {
        return findAlunosEntities(false, maxResults, firstResult);
    }

    private List<Alunos> findAlunosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alunos.class));
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

    public Alunos findAlunos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alunos.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlunosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alunos> rt = cq.from(Alunos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
