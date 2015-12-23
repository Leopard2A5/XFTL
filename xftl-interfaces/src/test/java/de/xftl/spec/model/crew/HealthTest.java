package de.xftl.spec.model.crew;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class HealthTest {

	@Test
	public void healthHasAnInitialValueOf100() {
		assertThat(new Health().getValue()).isEqualTo(100);
	}

	@Test
	public void healthCannotDropBelowZero() {
		Health h = new Health();
		h.decrease(200);
		assertThat(h.getValue()).isEqualTo(0);
	}
	
	@Test
	public void healthCannotIncreaseBeyond100() {
		Health h = new Health();
		h.increase(50);
		assertThat(h.getValue()).isEqualTo(100);
	}
	
	@Test
	public void healthDecreaseWorks() {
		Health h = new Health();
		h.decrease(10);
		assertThat(h.getValue()).isEqualTo(90);
	}
	
	@Test
	public void healthIncreaseWorks() {
		Health h = new Health();
		h.decrease(10);
		h.increase(5);
		assertThat(h.getValue()).isEqualTo(95);
	}
	
	@Test
	public void equalsWorks() {
		Health a = new Health();
		Health b = new Health();
		
		assertThat(a).isEqualTo(b);
		
		b.decrease(10);
		assertThat(a).isNotEqualTo(b);
	}
}
