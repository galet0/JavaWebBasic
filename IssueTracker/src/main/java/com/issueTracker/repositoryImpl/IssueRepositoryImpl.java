package com.IssueTracker.repositoryImpl;

import com.IssueTracker.entities.Issue;
import com.IssueTracker.entities.enums.Status;
import com.IssueTracker.repositories.IssueRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
@Local(IssueRepository.class)
public class IssueRepositoryImpl implements IssueRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Issue issue) {
        this.entityManager.persist(issue);
    }

    @Override
    public Set<Issue> findAllIssues() {
        Query query = this.entityManager.createQuery("SELECT i FROM Issue AS i ");
        return new HashSet<>(query.getResultList());
    }

    @Override
    public void update(Issue issue) {
        this.entityManager.merge(issue);
    }

    @Override
    public Issue findById(long id) {
        Query query = this.entityManager.createQuery("SELECT i FROM Issue AS i WHERE i.id = :id");
        query.setParameter("id", id);
        List<Issue> issues = query.getResultList();
        return issues.stream().findFirst().orElse(null);
    }

    @Override
    public void deleteById(long id) {
        Query query = this.entityManager.createQuery("DELETE FROM Issue AS i WHERE i.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Set<Issue> findAllByStatusAndName(String status, String name) {
        Query query = this.entityManager.createQuery("SELECT i FROM Issue AS i " +
                "WHERE i.status = :status " +
                "AND i.name LIKE :name");
        query.setParameter("status", Status.valueOf(status));
        query.setParameter("name", "%" + name + "%");
        return new HashSet<>(query.getResultList());
    }
}
