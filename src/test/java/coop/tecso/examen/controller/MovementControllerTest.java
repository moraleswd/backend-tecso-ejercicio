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

import coop.tecso.examen.dto.MovementDto;
import coop.tecso.examen.service.MovementBusinessService;
import coop.tecso.examen.service.MovementService;

@RunWith(SpringRunner.class)
@WebMvcTest(MovementController.class)
public class MovementControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@Autowired
	private MovementController controller;
	    
    @MockBean
    private MovementService movementService;
    
    @MockBean
    private MovementBusinessService movementBusinessService;
    
    private ObjectMapper om = new ObjectMapper();
    
    @Test
    public void save() throws Exception {
    	
    	  MovementDto cam = new MovementDto();
    	  cam.setAmount(BigDecimal.valueOf(200));
    	    when(movementBusinessService.addMovement(Mockito.any(MovementDto.class))).thenReturn(cam);
    	    
    		String root = controller.getClass().getAnnotation(RequestMapping.class).value()[0];

    	    mvc.perform(MockMvcRequestBuilders.post((root +"/save"))
    	      .content(om.writeValueAsString(cam))
    	      .contentType(MediaType.APPLICATION_JSON)
    	      .accept(MediaType.APPLICATION_JSON))
    	      .andExpect(status().isOk())
    	      .andExpect(jsonPath("$.amount", is(200)));
    }
    
    @Test
    public void findMovementsByAccount() throws Exception {
    	
	  	  MovementDto cam = new MovementDto();
	  	  cam.setAmount(BigDecimal.valueOf(200));
	  	  cam.setDescription("Test OK");
    	
    	when(movementService.findByCurrentAccountOrderByMovementDateDesc(Mockito.any(Long.class))).thenReturn(Arrays.asList(cam));
    	
    	String root = controller.getClass().getAnnotation(RequestMapping.class).value()[0];
        
    	mvc.perform(get(root +"/findMovementsByAccount/{idAccount}", 1L))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].description", is("Test OK")));
    }
	
}
