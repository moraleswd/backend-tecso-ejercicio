package coop.tecso.examen.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import coop.tecso.examen.model.MovementType;
import coop.tecso.examen.model.CurrencyType;
import coop.tecso.examen.model.CurrentAccount;
import coop.tecso.examen.model.Movement;
import coop.tecso.examen.model.OperationType;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovementRepositoryTest {
 
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private MovementRepository movementRepository;
 
    private Long currentAccountId = null;

	private Long movementTypeId = null;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
    @Before
    public void setUp() throws ParseException {
    	//Persist the CurrencyType 
    	CurrencyType ct = new CurrencyType();
    	ct.setCode("ARS");
    	ct.setDescription("Peso Argentino");
    	ct.setName("Pesos");
    	ct = entityManager.persistAndFlush(ct);
    	
    	//Persist the CurrentAccount 
    	CurrentAccount ca = new CurrentAccount();
    	ca.setAccountNumber("021-567823/1");
    	ca.setBalance(BigDecimal.valueOf(1000.123));
    	ca.setCurrencyType(ct);
    	ca = entityManager.persistAndFlush(ca);
    	currentAccountId = ca.getId();
    	
    	//Persist the OperationType
    	OperationType ot = new OperationType();
    	ot.setCode("DEB");
    	ot.setDescription("Débito en cuenta");
    	ot.setName("Débito");
    	ot = entityManager.persistAndFlush(ot);
    	
    	//Persist the MovementType
    	MovementType amt = new MovementType();
    	amt.setCode("DEB");
    	amt.setDescription("Débito en cuenta");
    	amt.setName("Débito");
    	amt.setOperationType(ot);
    	amt = entityManager.persistAndFlush(amt);
    	movementTypeId  = amt.getId();
    	
    	//Persist the Movement
    	Movement cat = new Movement();
    	cat.setMovementType(amt);
    	cat.setAmount(BigDecimal.valueOf(99));
    	cat.setCurrentAccount(ca);
    	cat.setDescription("Test 0");
    	cat.setMovementDate(sdf.parse("07/07/2020 19:15"));
    	cat = entityManager.persistAndFlush(cat);
    	
    	//Persist the Movement
    	Movement cat2 = new Movement();
    	cat2.setMovementType(amt);
    	cat2.setAmount(BigDecimal.valueOf(99));
    	cat2.setCurrentAccount(ca);
    	cat2.setDescription("Test 1");
    	cat2.setMovementDate(sdf.parse("06/07/2020 09:50"));
    	cat2 = entityManager.persistAndFlush(cat2);
    	
    }
    
    @Test
    public void save() {
    	CurrentAccount ca = entityManager.find(CurrentAccount.class, currentAccountId);
    	MovementType amt = entityManager.find(MovementType.class, movementTypeId);

    	//Persist the Movement
    	Movement cat = new Movement();
    	cat.setMovementType(amt);
    	cat.setAmount(BigDecimal.valueOf(99));
    	cat.setCurrentAccount(ca);
    	cat.setDescription("Test 2");
    	cat.setMovementDate(new Date());
    	
    	Movement catSaved = movementRepository.save(cat);
    	assertNotNull(catSaved);
	 }
 
    @Test
    public void findByCurrentAccountOrderByMovementDateDesc() throws ParseException {
    	List<Movement> catFounded = movementRepository.findByCurrentAccountIdOrderByMovementDateDesc(currentAccountId);
        assertNotNull(catFounded);
        assertTrue(catFounded.size() == 2);
        assertTrue(catFounded.get(0).getMovementDate().equals(sdf.parse("07/07/2020 19:15")));
        assertTrue(catFounded.get(1).getMovementDate().equals(sdf.parse("06/07/2020 09:50")));
     }
}
