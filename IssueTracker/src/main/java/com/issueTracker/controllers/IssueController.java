package com.IssueTracker.controllers;

import com.IssueTracker.constants.Constants;
import com.IssueTracker.models.bindingModels.IssueBindingModel;
import com.IssueTracker.models.bindingModels.IssueEditBindingModel;
import com.IssueTracker.models.bindingModels.LoggedUserModel;
import com.IssueTracker.models.viewModels.IssueDeleteViewModel;
import com.IssueTracker.models.viewModels.IssueEditViewModel;
import com.IssueTracker.models.viewModels.IssueViewModel;
import com.IssueTracker.services.IssueService;
import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.ModelAttribute;
import com.mvcFramework.annotations.parameters.PathVariable;
import com.mvcFramework.annotations.parameters.RequestParam;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.Set;

@Stateless
@Controller
public class IssueController {

    @Inject
    private IssueService issueService;

    @GetMapping("/issues")
    public String getIssuePage(Model model, @RequestParam("status") String status, @RequestParam("name") String name){
        Set<IssueViewModel> issueViewModels = null;
        if(status == null || status.equals("All")){
            issueViewModels = this.issueService.findAllIssues();
        } else {
            issueViewModels = this.issueService.searchIssues(status.toUpperCase(), name);
        }

        model.addAttribute("issueViewModels", issueViewModels);

        return Constants.ISSUE_PAGE;
    }

    @GetMapping("/issues/add")
    public String getAddIssuePage(){
        return Constants.ISSUES_ADD_PAGE;
    }

    @PostMapping("/issues/add")
    public String addIssue(@ModelAttribute IssueBindingModel issueBindingModel, HttpSession session){
        LoggedUserModel loggedUserModel = (LoggedUserModel) session.getAttribute("user");
        String username = loggedUserModel.getUsername();
        this.issueService.create(issueBindingModel, username);

        return Constants.REDIRECT_ISSUES;
    }

    @GetMapping("/issues/edit/{id}")
    public String getEditIssuePage(@PathVariable("id") long id, Model model){
        IssueEditViewModel issueEditViewModel = this.issueService.findById(id);
        model.addAttribute("issue", issueEditViewModel);
        return Constants.ISSUE_EDIT_PAGE;
    }

    @PostMapping("/issues/edit/{id}")
    public String editIssue(@PathVariable("id") long id, @ModelAttribute IssueEditBindingModel issueEditBindingModel){
        issueEditBindingModel.setId(id);
        issueService.update(issueEditBindingModel);
        return Constants.REDIRECT_ISSUES;
    }

    @GetMapping("/issues/delete/{id}")
    public String getDeleteIssuePage(@PathVariable("id") long id){
        this.issueService.deleteById(id);
        return Constants.REDIRECT_ISSUES;
    }

}
