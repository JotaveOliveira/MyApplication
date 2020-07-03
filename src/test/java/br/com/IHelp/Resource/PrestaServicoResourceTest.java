package br.com.IHelp.Resource;

import br.com.IHelp.Entities.PrestaServico;
import br.com.IHelp.Service.PrestaServicoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PrestaServicoResource.class)
public class PrestaServicoResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PrestaServicoService prestaServicoService;

    @Test
    public void inserirPrestador() throws Exception {

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
