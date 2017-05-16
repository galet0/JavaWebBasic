package com.IssueTracker.repositories;


import com.IssueTracker.entities.Issue;

import java.util.Set;

public interface IssueRepository {

    void create(Issue issue);

    Set<Issue> findAllIssues();

    void update(Issue issue);

    Issue findById(long id);

    void deleteById(long id);

    Set<Issue> findAllByStatusAndName(String status, String name);
}
