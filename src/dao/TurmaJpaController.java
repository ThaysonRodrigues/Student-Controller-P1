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
public class TurmaJpaController implements Serializable {

    public TurmaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Turma turma) {
        if (turma.getAlunosCollection() == null) {
            turma.setAlunosCollection(new ArrayList<Alunos>());
        }
        if (turma.getSemanaCollection() == null) {
            turma.setSemanaCollection(new ArrayList<Semana>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Curso idCurso = turma.getIdCurso();
            if (idCurso != null) {
                idCurso = em.getReference(idCurso.getClass(), idCurso.getId());
                turma.setIdCurso(idCurso);
            }
            Collection<Alunos> attachedAlunosCollection = new ArrayList<Alunos>();
            for (Alunos alunosCollectionAlunosToAttach : turma.getAlunosCollection()) {
                alunosCollectionAlunosToAttach = em.getReference(alunosCollectionAlunosToAttach.getClass(), alunosCollectionAlunosToAttach.getId());
                attachedAlunosCollection.add(alunosCollectionAlunosToAttach);
            }
            turma.setAlunosCollection(attachedAlunosCollection);
            Collection<Semana> attachedSemanaCollection = new ArrayList<Semana>();
            for (Semana semanaCollectionSemanaToAttach : turma.getSemanaCollection()) {
                semanaCollectionSemanaToAttach = em.getReference(semanaCollectionSemanaToAttach.getClass(), semanaCollectionSemanaToAttach.getId());
                attachedSemanaCollection.add(semanaCollectionSemanaToAttach);
            }
            turma.setSemanaCollection(attachedSemanaCollection);
            em.persist(turma);
            if (idCurso != null) {
                idCurso.getTurmaCollection().add(turma);
                idCurso = em.merge(idCurso);
            }
            for (Alunos alunosCollectionAlunos : turma.getAlunosCollection()) {
                Turma oldTurmaOfAlunosCollectionAlunos = alunosCollectionAlunos.getTurma();
                alunosCollectionAlunos.setTurma(turma);
                alunosCollectionAlunos = em.merge(alunosCollectionAlunos);
                if (oldTurmaOfAlunosCollectionAlunos != null) {
                    oldTurmaOfAlunosCollectionAlunos.getAlunosCollection().remove(alunosCollectionAlunos);
                    oldTurmaOfAlunosCollectionAlunos = em.merge(oldTurmaOfAlunosCollectionAlunos);
                }
            }
            for (Semana semanaCollectionSemana : turma.getSemanaCollection()) {
                Turma oldIdTurmaOfSemanaCollectionSemana = semanaCollectionSemana.getIdTurma();
                semanaCollectionSemana.setIdTurma(turma);
                semanaCollectionSemana = em.merge(semanaCollectionSemana);
                if (oldIdTurmaOfSemanaCollectionSemana != null) {
                    oldIdTurmaOfSemanaCollectionSemana.getSemanaCollection().remove(semanaCollectionSemana);
                    oldIdTurmaOfSemanaCollectionSemana = em.merge(oldIdTurmaOfSemanaCollectionSemana);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Turma turma) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turma persistentTurma = em.find(Turma.class, turma.getId());
            Curso idCursoOld = persistentTurma.getIdCurso();
            Curso idCursoNew = turma.getIdCurso();
            Collection<Alunos> alunosCollectionOld = persistentTurma.getAlunosCollection();
            Collection<Alunos> alunosCollectionNew = turma.getAlunosCollection();
            Collection<Semana> semanaCollectionOld = persistentTurma.getSemanaCollection();
            Collection<Semana> semanaCollectionNew = turma.getSemanaCollection();
            List<String> illegalOrphanMessages = null;
            for (Alunos alunosCollectionOldAlunos : alunosCollectionOld) {
                if (!alunosCollectionNew.contains(alunosCollectionOldAlunos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Alunos " + alunosCollectionOldAlunos + " since its turma field is not nullable.");
                }
            }
            for (Semana semanaCollectionOldSemana : semanaCollectionOld) {
                if (!semanaCollectionNew.contains(semanaCollectionOldSemana)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Semana " + semanaCollectionOldSemana + " since its idTurma field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCursoNew != null) {
                idCursoNew = em.getReference(idCursoNew.getClass(), idCursoNew.getId());
                turma.setIdCurso(idCursoNew);
            }
            Collection<Alunos> attachedAlunosCollectionNew = new ArrayList<Alunos>();
            for (Alunos alunosCollectionNewAlunosToAttach : alunosCollectionNew) {
                alunosCollectionNewAlunosToAttach = em.getReference(alunosCollectionNewAlunosToAttach.getClass(), alunosCollectionNewAlunosToAttach.getId());
                attachedAlunosCollectionNew.add(alunosCollectionNewAlunosToAttach);
            }
            alunosCollectionNew = attachedAlunosCollectionNew;
            turma.setAlunosCollection(alunosCollectionNew);
            Collection<Semana> attachedSemanaCollectionNew = new ArrayList<Semana>();
            for (Semana semanaCollectionNewSemanaToAttach : semanaCollectionNew) {
                semanaCollectionNewSemanaToAttach = em.getReference(semanaCollectionNewSemanaToAttach.getClass(), semanaCollectionNewSemanaToAttach.getId());
                attachedSemanaCollectionNew.add(semanaCollectionNewSemanaToAttach);
            }
            semanaCollectionNew = attachedSemanaCollectionNew;
            turma.setSemanaCollection(semanaCollectionNew);
            turma = em.merge(turma);
            if (idCursoOld != null && !idCursoOld.equals(idCursoNew)) {
                idCursoOld.getTurmaCollection().remove(turma);
                idCursoOld = em.merge(idCursoOld);
            }
            if (idCursoNew != null && !idCursoNew.equals(idCursoOld)) {
                idCursoNew.getTurmaCollection().add(turma);
                idCursoNew = em.merge(idCursoNew);
            }
            for (Alunos alunosCollectionNewAlunos : alunosCollectionNew) {
                if (!alunosCollectionOld.contains(alunosCollectionNewAlunos)) {
                    Turma oldTurmaOfAlunosCollectionNewAlunos = alunosCollectionNewAlunos.getTurma();
                    alunosCollectionNewAlunos.setTurma(turma);
                    alunosCollectionNewAlunos = em.merge(alunosCollectionNewAlunos);
                    if (oldTurmaOfAlunosCollectionNewAlunos != null && !oldTurmaOfAlunosCollectionNewAlunos.equals(turma)) {
                        oldTurmaOfAlunosCollectionNewAlunos.getAlunosCollection().remove(alunosCollectionNewAlunos);
                        oldTurmaOfAlunosCollectionNewAlunos = em.merge(oldTurmaOfAlunosCollectionNewAlunos);
                    }
                }
            }
            for (Semana semanaCollectionNewSemana : semanaCollectionNew) {
                if (!semanaCollectionOld.contains(semanaCollectionNewSemana)) {
                    Turma oldIdTurmaOfSemanaCollectionNewSemana = semanaCollectionNewSemana.getIdTurma();
                    semanaCollectionNewSemana.setIdTurma(turma);
                    semanaCollectionNewSemana = em.merge(semanaCollectionNewSemana);
                    if (oldIdTurmaOfSemanaCollectionNewSemana != null && !oldIdTurmaOfSemanaCollectionNewSemana.equals(turma)) {
                        oldIdTurmaOfSemanaCollectionNewSemana.getSemanaCollection().remove(semanaCollectionNewSemana);
                        oldIdTurmaOfSemanaCollectionNewSemana = em.merge(oldIdTurmaOfSemanaCollectionNewSemana);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = turma.getId();
                if (findTurma(id) == null) {
                    throw new NonexistentEntityException("The turma with id " + id + " no longer exists.");
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
            Turma turma;
            try {
                turma = em.getReference(Turma.class, id);
                turma.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The turma with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Alunos> alunosCollectionOrphanCheck = turma.getAlunosCollection();
            for (Alunos alunosCollectionOrphanCheckAlunos : alunosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Turma (" + turma + ") cannot be destroyed since the Alunos " + alunosCollectionOrphanCheckAlunos + " in its alunosCollection field has a non-nullable turma field.");
            }
            Collection<Semana> semanaCollectionOrphanCheck = turma.getSemanaCollection();
            for (Semana semanaCollectionOrphanCheckSemana : semanaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Turma (" + turma + ") cannot be destroyed since the Semana " + semanaCollectionOrphanCheckSemana + " in its semanaCollection field has a non-nullable idTurma field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Curso idCurso = turma.getIdCurso();
            if (idCurso != null) {
                idCurso.getTurmaCollection().remove(turma);
                idCurso = em.merge(idCurso);
            }
            em.remove(turma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Turma> findTurmaEntities() {
        return findTurmaEntities(true, -1, -1);
    }

    public List<Turma> findTurmaEntities(int maxResults, int firstResult) {
        return findTurmaEntities(false, maxResults, firstResult);
    }

    private List<Turma> findTurmaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Turma.class));
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

    public Turma findTurma(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Turma.class, id);
        } finally {
            em.close();
        }
    }

    public int getTurmaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Turma> rt = cq.from(Turma.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Turma> findCursoTurma(int idCurso){
        List rs = null;
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Turma.findCursoTurma");
        query.setParameter("idCurso", idCurso);
        rs = query.getResultList();
        return rs;
    }
    
}
