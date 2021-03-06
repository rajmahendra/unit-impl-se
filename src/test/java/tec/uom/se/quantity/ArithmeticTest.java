/**
 *  Unit-API - Units of Measurement API for Java
 *  Copyright 2013-2014, Jean-Marie Dautelle, Werner Keil, V2COM and individual
 *  contributors by the @author tag.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package tec.uom.se.quantity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static tec.uom.se.util.SI.METRE;

import javax.measure.Quantity;
import javax.measure.function.QuantityFactory;
import javax.measure.quantity.Length;

import org.junit.Before;
import org.junit.Test;

public class ArithmeticTest {

	private Quantity<Length> sut;

	private QuantityFactory<Length> lenghtFactory;

	@Before
	public void init() {
	    lenghtFactory =  QuantityFactoryProvider.getQuantityFactory(Length.class);
		sut = lenghtFactory.create(10, METRE);
	}

	@Test
	public void testAdd() {
		Quantity<Length> len = lenghtFactory.create(5, METRE);
		Quantity<Length> result = sut.add(len);
		assertNotNull(result);
		assertEquals(METRE, result.getUnit());
		assertEquals(Double.valueOf(15), result.getValue());
	}

	@Test
	public void testValue() {
		assertEquals(Double.valueOf(10), sut.getValue());
	}

	@Test
	public void testToString() {
		assertEquals("10.0 m", sut.toString());
	}

}
