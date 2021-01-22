package com.poseiden.springboot.controllers;

import com.poseiden.springboot.domain.BidList;
import com.poseiden.springboot.repositories.BidListRepository;
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
 * Controller in charge of any request related to BidList entity
 */
@Controller
public class BidListController {

    @Autowired
    private BidListRepository bidListRepository;

    /**
     * Return bidList's List page content
     * @param model list of BidList
     * @return bidList's List page
     */
    @RequestMapping("/bidList/list")
    public String home(Model model) {
        // DONE: call service find all bids to show to the view
        model.addAttribute("bidLists", bidListRepository.findAll());
        return "bidList/list";
    }

    /**
     * Return the add bidList page
     * @param bid bidList entity to add
     * @return add bidList page
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    /**
     * Create a bidList if valid
     * @param bid bidList to create
     * @param result contains errors if bidList is not valid
     * @param model list of BidList
     * @return list of BidList page if added bidList is valid, stay at bidList/add page otherwise
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // DONE: check data valid and save to db, after saving return bid list
        if (!result.hasErrors()) {
            bidListRepository.save(bid);
            model.addAttribute("bidLists", bidListRepository.findAll());
            return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    /**
     * Return filled update BidList page for a specific bidList
     * @param id id of bidList to update
     * @param model bidList to Update
     * @return update BidList page, can throw IllegalArgumentException if id of bidList is invalid
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // DONE: get Bid by Id and to model then show to the form
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    /**
     * Update a bidList if valid
     * @param id if of bidlist to update
     * @param bidList bidList with modified values
     * @param result contains errors if bidlist is not invalid
     * @param model list of BidList
     * @return list of BidList page if updated bidList is valid, stay at bidList/update page otherwise
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                            BindingResult result, Model model) {
        // DONE: check required fields, if valid call service to update Bid and return list Bid
        if (result.hasErrors()) {
            return "bidList/update";
        }
        bidListRepository.save(bidList);
        model.addAttribute("bidLists", bidListRepository.findAll());
        return "redirect:/bidList/list";
    }

    /**
     * Delete bidList by Id
     * @param id id of bidList to delete
     * @param model List of bidList if id is valid
     * @return BidList's List page if id is valid, throw IllegalArgumentException otherwise
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // DONE: Find Bid by Id and delete the bid, return to Bid list
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        bidListRepository.delete(bidList);
        model.addAttribute("bidLists", bidListRepository.findAll());
        return "redirect:/bidList/list";
    }
}
