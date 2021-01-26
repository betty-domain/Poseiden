package com.poseiden.springboot.controllers;

import com.poseiden.springboot.domain.Trade;
import com.poseiden.springboot.services.ITradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class TradeController {
    @Autowired
    ITradeService tradeService;

    /**
     * Return Trade List page content
     *
     * @param model list of Trade
     * @return Trade List page
     */
    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        model.addAttribute("tradeList",tradeService.findAll());
        return "trade/list";
    }

    /**
     * Return the add trade page
     *
     * @param trade trade entity to add
     * @return add trade page
     */
    @GetMapping("/trade/add")
    public String addUser(Trade trade) {
        return "trade/add";
    }

    /**
     * Create a trade if valid
     *
     * @param trade    trade to create
     * @param result contains errors if trade is not valid
     * @param model  list of trade
     * @return list of trade page if added trade is valid, stay at trade/add page otherwise
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        // DONE: check data valid and save to db, after saving return Trade list
        if (result.hasErrors()) {
            return "trade/add";
        }
        tradeService.save(trade);
        model.addAttribute("tradeList",tradeService.findAll());
        return "redirect:/trade/list";
    }

    /**
     * Return filled update trade page for a specific trade
     *
     * @param id    id of trade to update
     * @param model trade to Update
     * @return update trade page, can throw IllegalArgumentException if id of trade is invalid
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // DONE: get Trade by Id and to model then show to the form
        Trade trade = tradeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        model.addAttribute("trade", trade);

        return "trade/update";
    }

    /**
     * Update a trade if valid
     *
     * @param id      if of trade to update
     * @param trade trade with modified values
     * @param result  contains errors if trade is not invalid
     * @param model   list of trade
     * @return list of trade page if updated trade is valid, stay at trade/update page otherwise
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        // DONE: check required fields, if valid call service to update Trade and return Trade list
        if (result.hasErrors()) {
            return "trade/update";
        }
        tradeService.save(trade);
        model.addAttribute("tradeList",tradeService.findAll());
        return "redirect:/trade/list";
    }

    /**
     * Delete trade by Id
     *
     * @param id    id of trade to delete
     * @param model List of trade if id is valid
     * @return trade's List page if id is valid, throw IllegalArgumentException otherwise
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        // DONE: Find Trade by Id and delete the Trade, return to Trade list
        Trade trade = tradeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        tradeService.delete(trade);
        model.addAttribute("tradeList",tradeService.findAll());
        return "redirect:/trade/list";
    }
}
