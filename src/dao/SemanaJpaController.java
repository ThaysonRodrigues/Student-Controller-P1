/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Thayson&Regina
 */
public class SemanaJpaController implements Serializable {

    public SemanaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Semana semana) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turma idTurma = semana.getIdTurma();
            if (idTurma != null) {
                idTurma = em.getReference(idTurma.getClass(), idTurma.getId());
                semana.setIdTurma(idTurma);
            }
            em.persist(semana);
            if (idTurma != null) {
                idTurma.getSemanaCollection().add(semana);
                idTurma = em.merge(idTurma);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Semana semana) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Semana persistentSemana = em.find(Semana.class, semana.getId());
            Turma idTurmaOld = persistentSemana.getIdTurma();
            Turma idTurmaNew = semana.getIdTurma();
            if (idTurmaNew != null) {
                idTurmaNew = em.getReference(idTurmaNew.getClass(), idTurmaNew.getId());
                semana.setIdTurma(idTurmaNew);
            }
            semana = em.merge(semana);
            if (idTurmaOld != null && !idTurmaOld.equals(idTurmaNew)) {
                idTurmaOld.getSemanaCollection().remove(semana);
                idTurmaOld = em.merge(idTurmaOld);
            }
            if (idTurmaNew != null && !idTurmaNew.equals(idTurmaOld)) {
                idTurmaNew.getSemanaCollection().add(semana);
                idTurmaNew = em.merge(idTurmaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = semana.getId();
                if (findSemana(id) == null) {
                    throw new NonexistentEntityException("The semana with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Semana semana;
            try {
                semana = em.getReference(Semana.class, id);
                semana.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The semana with id " + id + " no longer exists.", enfe);
            }
            Turma idTurma = semana.getIdTurma();
            if (idTurma != null) {
                idTurma.getSemanaCollection().remove(semana);
                idTurma = em.merge(idTurma);
            }
            em.remove(semana);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Semana> findSemanaEntities() {
        return findSemanaEntities(true, -1, -1);
    }

    public List<Semana> findSemanaEntities(int maxResults, int firstResult) {
        return findSemanaEntities(false, maxResults, firstResult);
    }

    private List<Semana> findSemanaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Semana.class));
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

    public Semana findSemana(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Semana.class, id);
        } finally {
            em.close();
        }
    }

    public int getSemanaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Semana> rt = cq.from(Semana.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
    public List<Semana> findSemanaTurma(int id_turma){
        List sem;
        EntityManager em = getEntityManager();
        Query query;
        query = em.createQuery("SELECT S FROM Semana S WHERE S.idTurma.id = :id_turma");
        query.setParameter("id_turma",id_turma);
        sem = query.getResultList();
        em.close();
        return  sem;
    }
    
    public int destroyHorario(int id_horario){
        EntityManager em = getEntityManager();
        Query query;
        query = em.createQuery("delete from semana where idTurma = :id_horario");
        query.setParameter("id_horario",id_horario);
        return query.executeUpdate();
    }
        
}
