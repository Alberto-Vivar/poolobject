/**
 * 
 */
package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;

import java.io.NotActiveException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ubu.gii.dass.c01.Client;
import ubu.gii.dass.c01.DuplicatedInstanceException;
import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;

/**
 * @author alumno
 *
 */
public class ReusablePoolTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		assertNotNull(ReusablePool.getInstance());
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 */
	@Test
	public void testAcquireReusable() {
		try{
			while(true){
				ReusablePool.getInstance().acquireReusable();
			}
		}catch(NotFreeInstanceException e){
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 */
	@Test
	public void testReleaseReusable() {
		Reusable reusable = new Reusable();
		ReusablePool rp = ReusablePool.getInstance();
		try{
			while(true){
				rp.acquireReusable();
			}
		}catch(NotFreeInstanceException e){
			try{
				rp.releaseReusable(reusable);
				assertEquals(rp.acquireReusable(),reusable);
			}catch(DuplicatedInstanceException e2){
				fail("La piscina debería estar vacía");
			} catch (NotFreeInstanceException e1) {
				fail("No puedo recoger un objeto que acabo de meter");
			}
		}
	}
	
	@Test
	public void testCliente(){
		Client cliente=new Client();
		assertTrue(cliente.getClass().equals(Client.class));
		try{
			Client.main(null);
		}catch(NotFreeInstanceException e){
			fail("El main no se lanza adecuadamente");
		} catch (DuplicatedInstanceException e) {
			fail(e.getMessage());
		}
	}

}
