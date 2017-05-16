package com.IssueTracker.serviceImpl;

import com.IssueTracker.entities.Issue;
import com.IssueTracker.entities.User;
import com.IssueTracker.entities.enums.Priority;
import com.IssueTracker.entities.enums.Status;
import com.IssueTracker.models.bindingModels.IssueBindingModel;
import com.IssueTracker.models.bindingModels.IssueEditBindingModel;
import com.IssueTracker.models.viewModels.IssueEditViewModel;
import com.IssueTracker.models.viewModels.IssueViewModel;
import com.IssueTracker.repositories.IssueRepository;
import com.IssueTracker.repositories.UserRepository;
import com.IssueTracker.services.IssueService;
import com.IssueTracker.utils.modelParser.ModelParser;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
@Local(IssueService.class)
public class IssueServiceImpl implements IssueService {

    @Inject
    private IssueRepository issueRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private ModelParser modelParser;

    @Override
    public void create(IssueBindingModel issueBindingModel, String username) {
        Issue issue = this.modelParser.convert(issueBindingModel, Issue.class);
        issue.setPriority(Priority.valueOf(issueBindingModel.getPriority().toUpperCase()));
        issue.setStatus(Status.valueOf(issueBindingModel.getStatus().toUpperCase()));
        issue.setCreationDate(new Date());
        User user = this.userRepository.findByUsername(username);
        issue.setAuthor(user);
        this.issueRepository.create(issue);
    }

    @Override
    public Set<IssueViewModel> findAllIssues() {
        Set<Issue> issues = this.issueRepository.findAllIssues();
        Set<IssueViewModel> issueViewModels = new HashSet<>();
        for (Issue issue : issues) {
            IssueViewModel issueViewModel = this.modelParser.convert(issue, IssueViewModel.class);
            issueViewModel.setPriority(issue.getPriority().toString());
            issueViewModel.setStatus(issue.getStatus().toString());
            issueViewModels.add(issueViewModel);
        }
        return issueViewModels;
    }

    @Override
    public void update(IssueEditBindingModel issueBindingModel) {
        Issue issue = this.modelParser.convert(issueBindingModel, Issue.class);
        issue.setStatus(Status.valueOf(issueBindingModel.getStatus().toUpperCase()));
        issue.setPriority(Priority.valueOf(issueBindingModel.getPriority().toUpperCase()));
        this.issueRepository.update(issue);
    }

    @Override
    public IssueEditViewModel findById(long id) {
        Issue issue = this.issueRepository.findById(id);
        IssueEditViewModel issueEditViewModel = this.modelParser.convert(issue, IssueEditViewModel.class);
        issueEditViewModel.setStatus(issue.getStatus().toString());
        issueEditViewModel.setPriority(issue.getPriority().toString());
        return issueEditViewModel;
    }

    @Override
    public void deleteById(long id) {
        this.issueRepository.deleteById(id);
    }

    @Override
    public Set<IssueViewModel> searchIssues(String status, String name) {
        Set<Issue> issues = this.issueRepository.findAllByStatusAndName(status, name);
        Set<IssueViewModel> issueViewModels = new HashSet<>();
        for (Issue issue : issues) {
            IssueViewModel issueViewModel = this.modelParser.convert(issue, IssueViewModel.class);
            issueViewModels.add(issueViewModel);
        }
        return issueViewModels;
    }
}
