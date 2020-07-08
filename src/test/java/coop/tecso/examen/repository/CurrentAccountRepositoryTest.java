package coop.tecso.examen.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import coop.tecso.examen.model.CurrencyType;
import coop.tecso.examen.model.CurrentAccount;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CurrentAccountRepositoryTest {
 
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private CurrentAccountRepository currentAccountRepository;
 
    private Long currentAccountId = null;

    private Long currencyTypeId = null;


    @Before
    public void setUp() {
    	//Persist the CurrencyType 
    	CurrencyType ct = new CurrencyType();
    	ct.setCode("ARS");
    	ct.setDescription("Peso Argentino");
    	ct.setName("Pesos");
    	ct = entityManager.persistAndFlush(ct);
    	currencyTypeId = ct.getId();
    	
    	//Persist the CurrentAccount 
    	CurrentAccount ca = new CurrentAccount();
    	ca.setAccountNumber("021-567823/1");
    	ca.setBalance(BigDecimal.valueOf(1000.123));
    	ca.setCurrencyType(ct);
    	ca = entityManager.persistAndFlush(ca);
    	currentAccountId = ca.getId();
    }
    
    @Test
    public void save() {
    	CurrencyType ct = entityManager.find(CurrencyType.class, currencyTypeId);
    	//Persist the CurrentAccount 
    	CurrentAccount ca = new CurrentAccount();
    	ca.setAccountNumber("021-567825/1");
    	ca.setBalance(BigDecimal.valueOf(1000.123));
    	ca.setCurrencyType(ct);
    	ca = entityManager.persistAndFlush(ca);
    	
    	CurrentAccount caSaved = currentAccountRepository.save(ca);
    	assertNotNull(caSaved);
	 }
 
    @Test
    public void delete() {
		currentAccountRepository.deleteById(currentAccountId);
		CurrentAccount caFounded = entityManager.find(CurrentAccount.class, currentAccountId);
	    assertNull(caFounded);
    }
    
    @Test
    public void findAll() {
		List<CurrentAccount> result = currentAccountRepository.findAll();
	    assertNotNull(result);
	    assertTrue(result.size() > 0);
    }
}
