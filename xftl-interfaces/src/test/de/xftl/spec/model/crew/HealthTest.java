package de.xftl.spec.model.crew;

import static org.junit.Assert.*;

import org.junit.Test;

public class HealthTest {

	@Test
	public void healthHasAnInitialValueOf100() {
		assertEquals(100, new Health().getValue());
	}

	@Test
	public void healthCannotDropBelowZero() {
		Health h = new Health();
		h.decrease(200);
		assertEquals(0, h.getValue());
	}
	
	@Test
	public void healthCannotIncreaseBeyond100() {
		Health h = new Health();
		h.increase(50);
		assertEquals(100, h.getValue());
	}
	
	@Test
	public void healthDecreaseWorks() {
		Health h = new Health();
		
		h.decrease(10);
		
		assertEquals(90, h.getValue());
	}
	
	@Test
	public void healthIncreaseWorks() {
		Health h = new Health();
		
		h.decrease(10);
		h.increase(5);
		
		assertEquals(95, h.getValue());
	}
	
	@Test
	public void equalsWorks() {
		Health a = new Health();
		Health b = new Health();
		
		assertEquals(a, b);
		
		b.decrease(10);
		assertNotEquals(a, b);
	}
}
