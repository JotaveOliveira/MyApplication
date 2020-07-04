package br.com.IHelp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.IHelp.controller.PrestadorSerivicoController;
import br.com.IHelp.model.PrestaServico;
import br.com.IHelp.service.PrestaServicoService;

/**
 * Classe responsável por
 * conter os testes relacionado 
 * a classe PrestadorSerivicoController
 * 
 * @author João Vitor
 * @since 04/07/2020
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PrestadorSerivicoController.class)
public class PrestadorSerivicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PrestaServicoService prestaServicoService;

    /**
     * Método responsável por
     * testar se a requisição 
     * de uma controller está 
     * funcionando corretamente
     * 
     * @throws Exception
     */
    @Test
    public void testInserirPrestadorSucesso() throws Exception {

        PrestaServico prestaServico = new PrestaServico();
        prestaServico.setCep("03728220");
        prestaServico.setCidade("São Paulo");
        prestaServico.setCnpj("4875896784875");
        prestaServico.setDataNascimento("2000-07-27");
        prestaServico.setNome("João Vitor");
        prestaServico.setSenha("78958796548");

        when(prestaServicoService.inserirUsuario(any(PrestaServico.class))).thenReturn(prestaServico);

        mockMvc.perform(MockMvcRequestBuilders.post("/prestadoresServico")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prestaServico)))
                        .andExpect(status().isCreated())
                        .andExpect(content().json(objectMapper.writeValueAsString(prestaServico)));
    }
}
