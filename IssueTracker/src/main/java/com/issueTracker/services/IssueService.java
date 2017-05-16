package com.IssueTracker.services;


import com.IssueTracker.models.bindingModels.IssueBindingModel;
import com.IssueTracker.models.bindingModels.IssueEditBindingModel;
import com.IssueTracker.models.viewModels.IssueEditViewModel;
import com.IssueTracker.models.viewModels.IssueViewModel;

import java.util.Set;

public interface IssueService {

    void create(IssueBindingModel issueBindingModel, String username);

    Set<IssueViewModel> findAllIssues();

    void update(IssueEditBindingModel issueBindingModel);

    IssueEditViewModel findById(long id);

    void deleteById(long id);

    Set<IssueViewModel> searchIssues(String status, String name);

}
