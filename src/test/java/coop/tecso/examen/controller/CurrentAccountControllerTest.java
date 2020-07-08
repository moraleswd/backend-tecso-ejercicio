package coop.tecso.examen.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import coop.tecso.examen.dto.CurrentAccountDto;
import coop.tecso.examen.service.CurrentAccountService;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrentAccountController.class)
public class CurrentAccountControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@Autowired
	private CurrentAccountController controller;
	    
    @MockBean
    private CurrentAccountService currentAccountService;
    
    private ObjectMapper om = new ObjectMapper();
    
    @Test
    public void save() throws Exception {
    	
    	  CurrentAccountDto ca = new CurrentAccountDto();
    	  ca.setAccountNumber("021-567823/1");
    	    when(currentAccountService.save(Mockito.any(CurrentAccountDto.class))).thenReturn(ca);
    	    
    		String root = controller.getClass().getAnnotation(RequestMapping.class).value()[0];

    	    mvc.perform(MockMvcRequestBuilders.post((root +"/save"))
    	      .content(om.writeValueAsString(ca))
    	      .contentType(MediaType.APPLICATION_JSON)
    	      .accept(MediaType.APPLICATION_JSON))
    	      .andExpect(status().isOk())
    	      .andExpect(jsonPath("$.accountNumber", is("021-567823/1")));
    }
    
    @Test
    public void delete() throws Exception {
		String root = controller.getClass().getAnnotation(RequestMapping.class).value()[0];
		Mockito.doNothing().when(currentAccountService).deleteById(Mockito.any(Long.class));
	    
	    mvc.perform(MockMvcRequestBuilders.delete(root +"/delete/{id}", 1L)
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isNoContent());
    }
    
    @Test
    public void findAll() throws Exception {
    	
		CurrentAccountDto ca = new CurrentAccountDto();
		ca.setId(1L);
		ca.setAccountNumber("021-567823/1");
		ca.setBalance(BigDecimal.valueOf(200));
    	
    	when(currentAccountService.findAll()).thenReturn(Arrays.asList(ca));
    	
    	String root = controller.getClass().getAnnotation(RequestMapping.class).value()[0];
        
    	mvc.perform(get(root +"/findAll"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].id", is(1)))
			.andExpect(jsonPath("$[0].accountNumber", is("021-567823/1")))
			.andExpect(jsonPath("$[0].balance", is(200)));
    }
	
}
