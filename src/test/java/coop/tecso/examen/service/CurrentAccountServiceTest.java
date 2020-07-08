package coop.tecso.examen.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import coop.tecso.examen.model.CurrentAccount;
import coop.tecso.examen.model.Movement;
import coop.tecso.examen.repository.CurrentAccountRepository;
import coop.tecso.examen.service.impl.CurrentAccountServiceImpl;

@RunWith(SpringRunner.class)
public class CurrentAccountServiceTest {

    @TestConfiguration
    static class CurrentServiceTestContextConfiguration {
  
        @Bean
        public CurrentAccountService currentAccountService() {
            return new CurrentAccountServiceImpl();
        }
    }
    
    @Autowired
    private CurrentAccountService currentAccountService;
 
    @MockBean
    private CurrentAccountRepository currentAccountRepository;
    
    @MockBean
    private ModelMapper modelMapper;
    
    @Before
    public void setUp() {
    	//Se Mockea una cuenta con movimientos asociados
    	List<Movement> currentMovements = new ArrayList<>();
    	currentMovements.add(new Movement());
    	
        CurrentAccount result = new CurrentAccount();
        result.setMovements(currentMovements);
    	
        Optional<CurrentAccount> option = Optional.of(result);
        Mockito.when(currentAccountRepository.findById(1L))
          .thenReturn(option);
    }
    
    @Test
    public void delete() {
    	Boolean hasError = true;
    	String detailMessage = null;
    	try {
			currentAccountService.deleteById(1L);
		} catch (Exception e) {
			hasError = true;
			detailMessage = e.getMessage();
		}
    	assertTrue(hasError);
    	assertEquals(detailMessage, "No es posible eliminar la cuenta, la misma contiene movimientos.");
    }
}
