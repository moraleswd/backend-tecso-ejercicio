package coop.tecso.examen.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import coop.tecso.examen.dto.MovementDto;
import coop.tecso.examen.repository.MovementRepository;
import coop.tecso.examen.service.impl.MovementServiceImpl;
import coop.tecso.examen.utils.Constants;

@RunWith(SpringRunner.class)
public class MovementServiceTest {

    @TestConfiguration
    static class MovementServiceTestContextConfiguration {
  
        @Bean
        public MovementService movementService() {
            return new MovementServiceImpl();
        }
    }
    
    @Autowired
    private MovementService movementService;
 
    @MockBean
    private MovementRepository movementRepository;
    
    @MockBean
    private ModelMapper modelMapper;
    
    @Before
    public void setUp() {
    	
    }
    
    @Test
    public void delete() {
    	Boolean hasError = true;
    	String detailMessage = null;
    	try {
			movementService.deleteById(1L);
		} catch (Exception e) {
			hasError = true;
			detailMessage = e.getMessage();
		}
    	assertTrue(hasError);
    	assertEquals(detailMessage, Constants.METHOD_NOT_ALLOWED_MSG);
    }
    
    @Test
    public void save() {
    	Boolean hasError = true;
    	String detailMessage = null;
    	try {
    		MovementDto cam = new MovementDto();
    		cam.setId(1L);
			movementService.save(cam);
		} catch (Exception e) {
			hasError = true;
			detailMessage = e.getMessage();
		}
    	assertTrue(hasError);
    	assertEquals(detailMessage, Constants.METHOD_NOT_ALLOWED_MSG);
    }
}
