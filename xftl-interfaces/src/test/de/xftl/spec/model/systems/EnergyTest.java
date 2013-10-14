package de.xftl.spec.model.systems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class EnergyTest {

	@Test
	public void plusReturnsTheCorrectEnergyLevel() {
		Energy a = Energy.valueOf(2);
		Energy b = Energy.valueOf(3);
		
		assertEquals(Integer.valueOf(5), Integer.valueOf(a.plus(b).intValue()));
	}
	
	@Test
	public void minusReturnsTheCorrectEnergyLevel() {
		Energy a = Energy.valueOf(2);
		Energy b = Energy.valueOf(3);
		
		assertEquals(Integer.valueOf(1), Integer.valueOf(b.minus(a).intValue()));
	}
	
	@Test
	public void minusCannotGoBeneathZero() {
		Energy a = Energy.valueOf(2);
		Energy b = Energy.valueOf(3);
		
		assertEquals(Integer.valueOf(0), Integer.valueOf(a.minus(b).intValue()));
	}
	
	@Test
	public void equalsWorksAsExpected() {
		Energy a = Energy.valueOf(2);
		Energy b = Energy.valueOf(3);
		Energy c = Energy.valueOf(1);
		
		assertNotEquals(a, b);
		assertEquals(c, b.minus(a));
	}
	
	@Test
	public void valueOfReturnsTheCorrectEnergyLevel() {
		assertEquals(Integer.valueOf(1), Integer.valueOf(Energy.valueOf(1).intValue()));
		assertEquals(Integer.valueOf(5), Integer.valueOf(Energy.valueOf(5).intValue()));
	}

}
