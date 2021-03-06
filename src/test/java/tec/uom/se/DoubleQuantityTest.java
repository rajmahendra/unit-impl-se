package tec.uom.se;

import java.math.BigDecimal;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Time;

import org.junit.Assert;
import org.junit.Test;

import tec.uom.se.quantity.Quantities;
import tec.uom.se.util.SI;

public class DoubleQuantityTest {
    @Test
    public void divideTest() {
        Quantity<Length> metre = Quantities.getQuantity(10D, SI.METRE);
        Quantity<Length> result = metre.divide(10D);
        Assert.assertTrue(result.getValue().intValue() == 1);
        Assert.assertEquals(result.getUnit(), SI.METRE);

        Quantity<Time> day = Quantities.getQuantity(10D, SI.DAY);
        Quantity<Time> dayResult = day.divide(BigDecimal.valueOf(2.5D));
        Assert.assertTrue(dayResult.getValue().intValue() == 4);
        Assert.assertEquals(dayResult.getUnit(), SI.DAY);
    }

    @Test
    public void addTest() {
        Quantity<Length> m = Quantities.getQuantity(10D, SI.METRE);
        Quantity<Length> m2 = Quantities.getQuantity(BigDecimal.valueOf(12.5), SI.METRE);
        Quantity<Length> m3 = Quantities.getQuantity(2.5, SI.METRE);
        Quantity<Length> m4 = Quantities.getQuantity(5L, SI.METRE);
        Quantity<Length> result = m.add(m2).add(m3).add(m4);
        Assert.assertTrue(result.getValue().doubleValue() == 30.0);
        Assert.assertEquals(result.getUnit(), SI.METRE);
    }

    @Test
    public void addQuantityTest() {
        Quantity<Time> day = Quantities.getQuantity(1, SI.DAY);
        Quantity<Time> hours = Quantities.getQuantity(12D, SI.HOUR);
        Quantity<Time> result = day.add(hours);
        Assert.assertTrue(result.getValue().doubleValue() == 1.5);
        Assert.assertEquals(result.getUnit(), SI.DAY);
    }

    @Test
    public void subtractTest() {
        Quantity<Length> m = Quantities.getQuantity(10D, SI.METRE);
        Quantity<Length> m2 = Quantities.getQuantity(12.5, SI.METRE);
        Quantity<Length> result = m.subtract(m2);
        Assert.assertTrue(result.getValue().doubleValue() == -2.5);
        Assert.assertEquals(result.getUnit(), SI.METRE);
    }

    @Test
    public void subtractQuantityTest() {
        Quantity<Time> day = Quantities.getQuantity(1, SI.DAY);
        Quantity<Time> hours = Quantities.getQuantity(12F, SI.HOUR);
        Quantity<Time> result = day.subtract(hours);
        Assert.assertTrue(result.getValue().doubleValue() == 0.5);
        Assert.assertEquals(result.getUnit(), SI.DAY);
    }

    @Test
    public void multiplyTest() {
        Quantity<Length> metre = Quantities.getQuantity(10D, SI.METRE);
        Quantity<Length> result = metre.multiply(10D);
        Assert.assertTrue(result.getValue().intValue() == 100);
        Assert.assertEquals(result.getUnit(), SI.METRE);
        @SuppressWarnings("unchecked")
        Quantity<Length> result2 = (Quantity<Length>) metre
                .multiply(Quantities.getQuantity(10D, SI.HOUR));
        Assert.assertTrue(result2.getValue().intValue() == 100);


    }

    @Test
    public void toTest() {
        Quantity<Time> day = Quantities.getQuantity(1D, SI.DAY);
        Quantity<Time> hour = day.to(SI.HOUR);
        Assert.assertEquals(hour.getValue().intValue(), 24);
        Assert.assertEquals(hour.getUnit(), SI.HOUR);

        Quantity<Time> dayResult = hour.to(SI.DAY);
        Assert.assertEquals(dayResult.getValue().intValue(), day.getValue().intValue());
        Assert.assertEquals(dayResult.getValue().intValue(), day.getValue().intValue());
    }

    @Test
    public void inverseTest() {
        Quantity<Length> metre = Quantities.getQuantity(BigDecimal.TEN, SI.METRE).inverse();
        Assert.assertTrue(metre.getValue().intValue() == 10);
    }

}
