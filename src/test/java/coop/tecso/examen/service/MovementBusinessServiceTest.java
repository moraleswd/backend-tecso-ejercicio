package coop.tecso.examen.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import coop.tecso.examen.dto.CurrentAccountDto;
import coop.tecso.examen.dto.MovementDto;
import coop.tecso.examen.dto.MovementTypeDto;
import coop.tecso.examen.dto.OperationTypeDto;
import coop.tecso.examen.service.impl.MovementBusinessServiceImpl;

@RunWith(SpringRunner.class)
public class MovementBusinessServiceTest {

    @TestConfiguration
    static class MovementBusinessServiceTestContextConfiguration {
  
        @Bean
        public MovementBusinessService movementBusinessService() {
            return new MovementBusinessServiceImpl();
        }
    }
    
    @Autowired
    private MovementBusinessService movementBusinessService;
    
    @MockBean
    private MovementService movementService;

    @MockBean
	private CurrentAccountBusinessService currentAccountBusinessService;
	
    @Test
    public void addMovementMovementTypeNull() {
    	Boolean hasError = false;
    	String detailMessage = null;
    	try {
    		MovementDto cam = new MovementDto();
    		cam.setMovementType(null);
    		movementBusinessService.addMovement(cam);
		} catch (Exception e) {
			hasError = true;
			detailMessage = e.getMessage();
		}
    	assertTrue(hasError);
    	assertEquals(detailMessage, "El tipo de movimiento es requerido");
    }
    
    @Test
    public void addMovementOperationTypeNull() {
    	Boolean hasError = false;
    	String detailMessage = null;
    	try {
    		MovementDto cam = new MovementDto();
    		cam.setMovementType(new MovementTypeDto());
    		movementBusinessService.addMovement(cam);
		} catch (Exception e) {
			hasError = true;
			detailMessage = e.getMessage();
		}
    	assertTrue(hasError);
    	assertEquals(detailMessage, "El tipo de operación es requerido");
    }
    
    @Test
    public void addMovementCurrentAccountNull() {
    	Boolean hasError = false;
    	String detailMessage = null;
    	try {
    		MovementTypeDto amt = new MovementTypeDto();
    		amt.setOperationType(new OperationTypeDto());
    		
    		MovementDto cam = new MovementDto();
    		cam.setMovementType(amt);
    		cam.setCurrentAccount(null);
    		movementBusinessService.addMovement(cam);
		} catch (Exception e) {
			hasError = true;
			detailMessage = e.getMessage();
		}
    	assertTrue(hasError);
    	assertEquals(detailMessage, "El movimiento no tiene una cuenta asociada");
    }
    
    @Test
    public void addMovementOK() throws Exception {
		MovementTypeDto amt = new MovementTypeDto();
		amt.setOperationType(new OperationTypeDto());
		
		MovementDto cam = new MovementDto();
		cam.setDescription("Test addMovementOK");
		cam.setMovementType(amt);
		cam.setCurrentAccount(new CurrentAccountDto());
		
    	//Se Mockea un movimiento de cuenta para el save
        Mockito.when(movementService.save(cam))
          .thenReturn(cam);
     
		MovementDto result = movementBusinessService.addMovement(cam);
		
		assertNotNull(result);
		assertEquals(result.getDescription(), "Test addMovementOK");
     }


}
