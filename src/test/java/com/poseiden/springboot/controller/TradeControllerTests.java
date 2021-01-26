package com.poseiden.springboot.controller;

import com.poseiden.springboot.domain.Trade;
import com.poseiden.springboot.services.ITradeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class TradeControllerTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ITradeService tradeService;

    @Test
    void get_trade_list_Ok() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/trade/list").
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(status().isOk())
                .andExpect(view().name("trade/list"));

        verify(tradeService, Mockito.times(1)).findAll();
    }

    @Test
    void get_trade_add_Form_Ok() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/trade/add").
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("trade/add"));

        verify(tradeService, Mockito.times(0)).save(any());
    }

    @Test
    void post_trade_validate_ResultHasErrors() throws Exception {
        Trade trade = new Trade(null, "type");
        trade.setBuyPrice(15.5);
        trade.setSellPrice(25.8);
        trade.setBuyQuantity(14.75);
        trade.setSellQuantity(258.0);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/trade/validate").
                contentType(MediaType.APPLICATION_JSON).
                param("account",trade.getAccount()).
                param("type", trade.getType()).
                param("buyQuantity", trade.getBuyQuantity().toString()).
                param("sellQuantity",trade.getSellQuantity().toString()).
                param("buyPrice", trade.getBuyPrice().toString()).
                param("sellPrice", trade.getSellPrice().toString());

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("trade/add")).
                andExpect(model().hasErrors());

        verify(tradeService, Mockito.times(0)).save(any());
    }

    @Test
    void post_trade_validate_isOk_RedirectToBidList() throws Exception {
        Trade trade = new Trade("account", "type");
        trade.setBuyPrice(15.5);
        trade.setSellPrice(25.8);
        trade.setBuyQuantity(14.75);
        trade.setSellQuantity(258.0);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/trade/validate").
                contentType(MediaType.APPLICATION_JSON).
                param("account",trade.getAccount()).
                param("type", trade.getType()).
                param("buyQuantity", trade.getBuyQuantity().toString()).
                param("sellQuantity",trade.getSellQuantity().toString()).
                param("buyPrice", trade.getBuyPrice().toString()).
                param("sellPrice", trade.getSellPrice().toString());

        when(tradeService.save(any())).thenReturn(trade);

        mockMvc.perform(builder).
                andExpect(redirectedUrl("/trade/list"));

        verify(tradeService, Mockito.times(1)).save(trade);
    }

    @Test
    void get_trade_updateForm_Ok() throws Exception {
        Trade trade = new Trade("account", "type");
        trade.setBuyPrice(15.5);
        trade.setSellPrice(25.8);
        trade.setBuyQuantity(14.75);
        trade.setSellQuantity(258.0);
        trade.setTradeId(5);

        when(tradeService.findById(anyInt())).thenReturn(Optional.of(trade));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/trade/update/{id}", 5).
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("trade/update"));

        verify(tradeService, Mockito.times(0)).save(any());

    }

    @Test
    void get_trade_update_ThrowIllegalArgumentException() throws Exception {

        when(tradeService.findById(anyInt())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/trade/update/{id}", 5).
                contentType(MediaType.APPLICATION_JSON);

        assertThatThrownBy(() -> mockMvc.perform(builder)).hasCause(new IllegalArgumentException("Invalid trade Id:5"));

    }

    @Test
    void post_trade_update_ResultHasErrors() throws Exception {
        Trade trade = new Trade("accout", null);
        trade.setBuyPrice(15.5);
        trade.setSellPrice(25.8);
        trade.setBuyQuantity(14.75);
        trade.setSellQuantity(258.0);
        trade.setTradeId(5);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/trade/update/{id}",5).
                contentType(MediaType.APPLICATION_JSON).
                param("account",trade.getAccount()).
                param("type", trade.getType()).
                param("buyQuantity", trade.getBuyQuantity().toString()).
                param("sellQuantity",trade.getSellQuantity().toString()).
                param("buyPrice", trade.getBuyPrice().toString()).
                param("sellPrice", trade.getSellPrice().toString());


        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("trade/update")).
                andExpect(model().hasErrors());

        verify(tradeService, Mockito.times(0)).save(any());
    }

    @Test
    void post_trade_update_isOk_RedirectToBidList() throws Exception {
        Trade trade = new Trade("accout", "type");
        trade.setBuyPrice(15.5);
        trade.setSellPrice(25.8);
        trade.setBuyQuantity(14.75);
        trade.setSellQuantity(258.0);
        trade.setTradeId(5);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/trade/update/{id}",5).
                contentType(MediaType.APPLICATION_JSON).
                param("account",trade.getAccount()).
                param("type", trade.getType()).
                param("buyQuantity", trade.getBuyQuantity().toString()).
                param("sellQuantity",trade.getSellQuantity().toString()).
                param("buyPrice", trade.getBuyPrice().toString()).
                param("tradeId",trade.getTradeId().toString()).
                param("sellPrice", trade.getSellPrice().toString());

        when(tradeService.save(any())).thenReturn(trade);

        mockMvc.perform(builder).
                andExpect(redirectedUrl("/trade/list"));

        verify(tradeService, Mockito.times(1)).save(trade);
    }

    @Test
    void get_trade_delete_Ok() throws Exception {
        Trade trade = new Trade("account", "type");
        trade.setBuyPrice(15.5);
        trade.setSellPrice(25.8);
        trade.setBuyQuantity(14.75);
        trade.setSellQuantity(258.0);
        trade.setTradeId(5);

        when(tradeService.findById(anyInt())).thenReturn(Optional.of(trade));
        when(tradeService.findById(anyInt())).thenReturn(Optional.of(trade));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/trade/delete/{id}", 5).
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(redirectedUrl("/trade/list"));

        verify(tradeService, Mockito.times(1)).delete(any());

    }

    @Test
    void get_trade_delete_ThrowIllegalArgumentException() throws Exception {

        when(tradeService.findById(anyInt())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/trade/delete/{id}", 5).
                contentType(MediaType.APPLICATION_JSON);

        assertThatThrownBy(() -> mockMvc.perform(builder)).hasCause(new IllegalArgumentException("Invalid trade Id:5"));

    }
}
