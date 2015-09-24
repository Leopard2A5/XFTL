package de.xftl.spec.model.systems;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class EnergyTest {

	@Test
	public void plusReturnsTheCorrectEnergyLevel() {
		Energy a = Energy.valueOf(2);
		Energy b = Energy.valueOf(3);
		
		assertThat(a.plus(b).intValue()).isEqualTo(5);
	}
	
	@Test
	public void minusReturnsTheCorrectEnergyLevel() {
		Energy a = Energy.valueOf(2);
		Energy b = Energy.valueOf(3);
		
		assertThat(b.minus(a).intValue()).isEqualTo(1);
	}
	
	@Test
	public void minusCannotGoBeneathZero() {
		Energy a = Energy.valueOf(2);
		Energy b = Energy.valueOf(3);
		
		assertThat(a.minus(b).intValue()).isEqualTo(0);
	}
	
	@Test
	public void equalsWorksAsExpected() {
		Energy a = Energy.valueOf(2);
		Energy b = Energy.valueOf(3);
		Energy c = Energy.valueOf(1);
		
		assertThat(a).isNotEqualTo(b);
		assertThat(b.minus(a)).isEqualTo(c);
	}
	
	@Test
	public void valueOfReturnsTheCorrectEnergyLevel() {
		assertThat(Energy.valueOf(1).intValue()).isEqualTo(1);
		assertThat(Energy.valueOf(5).intValue()).isEqualTo(5);
	}

}
