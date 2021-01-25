package com.poseiden.springboot.controllers;

import com.poseiden.springboot.domain.CurvePoint;
import com.poseiden.springboot.services.ICurvePointService;
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
 * Controller in charge of any request related to CurvePoint entity
 */
@Controller
public class CurveController {

    @Autowired
    ICurvePointService curvePointService;

    /**
     * Return CurvePoint's List page content
     *
     * @param model list of CurvePoint
     * @return CurvePoint's List page
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        model.addAttribute("curvePointList",curvePointService.findAll());
        return "curvePoint/list";
    }

    /**
     * Return the add CurvePoint page
     *
     * @param curvePoint CurvePoint entity to add
     * @return add CurvePoint page
     */
    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(CurvePoint curvePoint) {
        return "curvePoint/add";
    }

    /**
     * Create a CurvePoint if valid
     *
     * @param curvePoint CurvePoint to create
     * @param result contains errors if CurvePoint is not valid
     * @param model  list of CurvePoint
     * @return list of CurvePoint page if added CurvePoint is valid, stay at CurvePoint/add page otherwise
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // DONE: check data valid and save to db, after saving return Curve list
        if (result.hasErrors()) {
            return "curvePoint/add";
        }
        curvePointService.save(curvePoint);
        model.addAttribute("curvePointList", curvePointService.findAll());
        return "redirect:/curvePoint/list";
    }

    /**
     * Return filled update CurvePoint page for a specific CurvePoint
     *
     * @param id    id of CurvePoint to update
     * @param model CurvePoint to Update
     * @return update CurvePoint page, can throw IllegalArgumentException if id of CurvePoint is invalid
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // DONE: get CurvePoint by Id and to model then show to the form
        CurvePoint curvePoint = curvePointService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        model.addAttribute("curvePoint", curvePoint);

        return "curvePoint/update";
    }

    /**
     * Update a CurvePoint if valid
     *
     * @param id      if of CurvePoint to update
     * @param curvePoint CurvePoint with modified values
     * @param result  contains errors if CurvePoint is not invalid
     * @param model   list of CurvePoint
     * @return list of CurvePoint page if updated CurvePoint is valid, stay at CurvePoint/update page otherwise
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        // DONE: check required fields, if valid call service to update Curve and return Curve list
        if (result.hasErrors()) {
            return "curvePoint/update";
        }
        curvePointService.save(curvePoint);
        model.addAttribute("curvePointList", curvePointService.findAll());
        return "redirect:/curvePoint/list";
    }

    /**
     * Delete CurvePoint by Id
     *
     * @param id    id of CurvePoint to delete
     * @param model List of CurvePoint if id is valid
     * @return CurvePoint's List page if id is valid, throw IllegalArgumentException otherwise
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // DONE: Find Curve by Id and delete the Curve, return to Curve list
        CurvePoint curvePoint = curvePointService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        curvePointService.delete(curvePoint);
        model.addAttribute("curvePointList",curvePointService.findAll());
        return "redirect:/curvePoint/list";
    }
}
