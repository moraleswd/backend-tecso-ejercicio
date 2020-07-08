package coop.tecso.examen.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import coop.tecso.examen.dto.CurrencyTypeDto;
import coop.tecso.examen.dto.CurrentAccountDto;
import coop.tecso.examen.service.impl.CurrentAccountBusinessServiceImpl;
import coop.tecso.examen.utils.Constants;

@RunWith(SpringRunner.class)
public class CurrentAccountBusinessServiceTest {

    @TestConfiguration
    static class CurrentAccountBusinessServiceTestContextConfiguration {
  
        @Bean
        public CurrentAccountBusinessService currentAccountBusinessService() {
            return new CurrentAccountBusinessServiceImpl();
        }
    }
    
    @Autowired
    private CurrentAccountBusinessService currentAccountBusinessService;
    
    @MockBean
    private CurrentAccountService currentAccountService;

    
    @Before
    public void setUp() throws Exception {
    	//Se Mockea una cuenta con movimientos asociados
        CurrentAccountDto result = new CurrentAccountDto();
        Mockito.when(currentAccountService.findById(1L))
          .thenReturn(result);
    }
    
    @Test
    public void updateBalanceCurrentAccountNull() {
    	Boolean hasError = true;
    	String detailMessage = null;
    	try {
        	//Se Mockea una cuenta que no se encuentre (Retorno null)
            Mockito.when(currentAccountService.findById(1L))
              .thenReturn(null);
            currentAccountBusinessService.updateBalance(1L, BigDecimal.valueOf(200), Constants.OPERATION_DEBIT_CODE);
		} catch (Exception e) {
			hasError = true;
			detailMessage = e.getMessage();
		}
    	assertTrue(hasError);
    	assertEquals(detailMessage, "Cuenta inexistente");
    }
    
    @Test
    public void updateBalanceOperationCreditOK() throws Exception {
		CurrencyTypeDto ct = new CurrencyTypeDto();
		ct.setId(1L);
    	ct.setCode("ARS");
    	ct.setDescription("Peso Argentino");
    	ct.setName("Pesos");
    	
		CurrentAccountDto ca = new CurrentAccountDto();
		ca.setId(1L);
		ca.setAccountNumber("021-567823/1");
		ca.setBalance(BigDecimal.valueOf(200));
		ca.setCurrencyType(ct);
    	//Se Mockea una cuenta para el find
        Mockito.when(currentAccountService.findById(1L))
          .thenReturn(ca);
        
        //Se Mockea una cuenta para el save
        Mockito.when(currentAccountService.save(ca))
        .thenReturn(ca);
        
        CurrentAccountDto result = currentAccountBusinessService.updateBalance(1L, BigDecimal.valueOf(200), Constants.OPERATION_CREDIT_CODE);

    	assertNotNull(result);
    }
    
    @Test
    public void updateBalanceOperationDebitOK() throws Exception {
		CurrencyTypeDto ct = new CurrencyTypeDto();
		ct.setId(1L);
    	ct.setCode("ARS");
    	ct.setDescription("Peso Argentino");
    	ct.setName("Pesos");
    	
		CurrentAccountDto ca = new CurrentAccountDto();
		ca.setId(1L);
		ca.setAccountNumber("021-567823/1");
		ca.setBalance(BigDecimal.valueOf(200));
		ca.setCurrencyType(ct);
    	//Se Mockea una cuenta para el find
        Mockito.when(currentAccountService.findById(1L))
          .thenReturn(ca);
        
        //Se Mockea una cuenta para el save
        Mockito.when(currentAccountService.save(ca))
        .thenReturn(ca);
        
        CurrentAccountDto result = currentAccountBusinessService.updateBalance(1L, BigDecimal.valueOf(200), Constants.OPERATION_DEBIT_CODE);

    	assertNotNull(result);
    }
    
    @Test
    public void updateBalanceOperationDebitOverdraftArs() throws Exception {
    	Boolean hasError = true;
    	String detailMessage = null;

		CurrencyTypeDto ct = new CurrencyTypeDto();
		ct.setId(1L);
    	ct.setCode("ARS");
    	ct.setDescription("Peso Argentino");
    	ct.setName("Pesos");
    	
		CurrentAccountDto ca = new CurrentAccountDto();
		ca.setId(1L);
		ca.setAccountNumber("021-567823/1");
		ca.setBalance(BigDecimal.valueOf(200));
		ca.setCurrencyType(ct);
    	//Se Mockea una cuenta para el find
        Mockito.when(currentAccountService.findById(1L))
          .thenReturn(ca);
        
        //Se Mockea una cuenta para el save
        Mockito.when(currentAccountService.save(ca))
        .thenReturn(ca);
        try {
        	currentAccountBusinessService.updateBalance(1L, BigDecimal.valueOf(1201), Constants.OPERATION_DEBIT_CODE);
		} catch (Exception e) {
			hasError = true;
			detailMessage = e.getMessage();
		}
        assertTrue(hasError);
    	assertEquals(detailMessage, "La cuenta excedió el limite por descubierto");

    }
    
    @Test
    public void updateBalanceOperationDebitOverdraftUsd() throws Exception {
    	Boolean hasError = true;
    	String detailMessage = null;

		CurrencyTypeDto ct = new CurrencyTypeDto();
		ct.setId(2L);
    	ct.setCode("USD");
    	ct.setDescription("United States dollar");
    	ct.setName("Dolar");
    	
		CurrentAccountDto ca = new CurrentAccountDto();
		ca.setId(1L);
		ca.setAccountNumber("021-567823/1");
		ca.setBalance(BigDecimal.valueOf(200));
		ca.setCurrencyType(ct);
    	//Se Mockea una cuenta para el find
        Mockito.when(currentAccountService.findById(1L))
          .thenReturn(ca);
        
        //Se Mockea una cuenta para el save
        Mockito.when(currentAccountService.save(ca))
        .thenReturn(ca);
        try {
        	currentAccountBusinessService.updateBalance(1L, BigDecimal.valueOf(501), Constants.OPERATION_DEBIT_CODE);
		} catch (Exception e) {
			hasError = true;
			detailMessage = e.getMessage();
		}
        assertTrue(hasError);
    	assertEquals(detailMessage, "La cuenta excedió el limite por descubierto");
    }
    
    @Test
    public void updateBalanceOperationDebitOverdraftEur() throws Exception {
    	Boolean hasError = true;
    	String detailMessage = null;

		CurrencyTypeDto ct = new CurrencyTypeDto();
		ct.setId(3L);
    	ct.setCode("EUR");
    	ct.setDescription("European euro");
    	ct.setName("Euro");
    	
		CurrentAccountDto ca = new CurrentAccountDto();
		ca.setId(1L);
		ca.setAccountNumber("021-567823/1");
		ca.setBalance(BigDecimal.valueOf(200));
		ca.setCurrencyType(ct);
    	//Se Mockea una cuenta para el find
        Mockito.when(currentAccountService.findById(1L))
          .thenReturn(ca);
        
        //Se Mockea una cuenta para el save
        Mockito.when(currentAccountService.save(ca))
        .thenReturn(ca);
        try {
        	currentAccountBusinessService.updateBalance(1L, BigDecimal.valueOf(351), Constants.OPERATION_DEBIT_CODE);
		} catch (Exception e) {
			hasError = true;
			detailMessage = e.getMessage();
		}
        assertTrue(hasError);
    	assertEquals(detailMessage, "La cuenta excedió el limite por descubierto");
    }
}
