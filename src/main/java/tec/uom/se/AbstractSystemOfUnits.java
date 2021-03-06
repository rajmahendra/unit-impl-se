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
package tec.uom.se;

import javax.measure.Dimension;
import javax.measure.Quantity;
import javax.measure.SystemOfUnits;
import javax.measure.Unit;

import java.util.*;

/**
 * <p>An abstract base class for unit systems.</p>
 *
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 0.3, $Date: 2014-09-06 $
 */
public abstract class AbstractSystemOfUnits implements SystemOfUnits {
    /**
     * Holds the units.
     */
	protected final Set<Unit<?>> units = new HashSet<Unit<?>>();

    /**
     * Holds the mapping quantity to unit.
     */
    @SuppressWarnings("rawtypes")
	protected final Map<Class<? extends Quantity>, AbstractUnit>
            quantityToUnit = new HashMap<>(); // Diamond (Java 7+)

    /**
     * The natural logarithm.
     **/
    protected static final double E = 2.71828182845904523536028747135266;

	/*
	 * (non-Javadoc)
	 * 
	 * @see SystemOfUnits#getName()
	 */
    public abstract String getName();
    
	// ///////////////////
	// Collection View //
	// ///////////////////
    @Override
    public Set<Unit<?>> getUnits() {
        return Collections.unmodifiableSet(units);
    }

    @Override
    public Set<? extends Unit<?>> getUnits(Dimension dimension) {
        final Set<Unit<?>> set = new HashSet<>(); // Diamond, Java 7+
        for (Unit<?> unit : this.getUnits()) {
            if (dimension.equals(unit.getDimension())) {
                set.add(unit);
            }
        }
        return set;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public <Q extends Quantity<Q>> AbstractUnit<Q> getUnit(Class<Q> quantityType) {
        return quantityToUnit.get(quantityType);
    }
	
	static class Helper {
		static Set<Unit<?>> getUnitsOfDimension(final Set<Unit<?>> units, 
				Dimension dimension) {
			if (dimension != null) {
				Set<Unit<?>>dimSet = new HashSet<>(); // Diamond, Java 7+
				for (Unit<?> u : units) {
					if (dimension.equals(u.getDimension())) {
						dimSet.add(u);
					}
				}
				return dimSet;
			}
			return null;
		}
	}
}
