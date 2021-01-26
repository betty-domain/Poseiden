package com.poseiden.springboot.controllers;

import com.poseiden.springboot.domain.RuleName;
import com.poseiden.springboot.services.IRuleNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Controller in charge of any request related to RuleName entity
 */
@Controller
public class RuleNameController {
    @Autowired
    IRuleNameService ruleNameService;

    /**
     * Return RuleName List page content
     *
     * @param model list of RuleName
     * @return RuleName List page
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        model.addAttribute("ruleNameList",ruleNameService.findAll());
        return "ruleName/list";
    }

    /**
     * Return the add ruleName page
     *
     * @param ruleName ruleName entity to add
     * @return add ruleName page
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName) {
        return "ruleName/add";
    }

    /**
     * Create a ruleName if valid
     *
     * @param ruleName    ruleName to create
     * @param result contains errors if ruleName is not valid
     * @param model  list of ruleName
     * @return list of ruleName page if added ruleName is valid, stay at ruleName/add page otherwise
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // DONE: check data valid and save to db, after saving return RuleName list
        if (result.hasErrors()) {
            return "ruleName/add";
        }
        ruleNameService.save(ruleName);
        model.addAttribute("ruleNameList",ruleNameService.findAll());
        return "redirect:/ruleName/list";
    }

    /**
     * Return filled update ruleName page for a specific ruleName
     *
     * @param id    id of ruleName to update
     * @param model ruleName to Update
     * @return update ruleName page, can throw IllegalArgumentException if id of ruleName is invalid
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // DONE: get RuleName by Id and to model then show to the form
        RuleName ruleName = ruleNameService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        model.addAttribute("ruleName", ruleName);

        return "ruleName/update";
    }

    /**
     * Update a ruleName if valid
     *
     * @param id      if of ruleName to update
     * @param ruleName ruleName with modified values
     * @param result  contains errors if ruleName is not invalid
     * @param model   list of ruleName
     * @return list of ruleName page if updated ruleName is valid, stay at ruleName/update page otherwise
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        // DONE: check required fields, if valid call service to update RuleName and return RuleName list
        if (result.hasErrors()) {
            return "ruleName/update";
        }
        ruleNameService.save(ruleName);
        model.addAttribute("ruleNameList",ruleNameService.findAll());
        return "redirect:/ruleName/list";
    }

    /**
     * Delete ruleName by Id
     *
     * @param id    id of ruleName to delete
     * @param model List of ruleName if id is valid
     * @return ruleName's List page if id is valid, throw IllegalArgumentException otherwise
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // DONE: Find RuleName by Id and delete the RuleName, return to Rule list
        RuleName ruleName = ruleNameService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        ruleNameService.delete(ruleName);
        model.addAttribute("ruleNameList",ruleNameService.findAll());
        return "redirect:/ruleName/list";
    }
}
