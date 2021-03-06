package tec.uom.se.function;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.measure.Quantity;
import javax.measure.function.QuantityFactory;
import javax.measure.quantity.Time;

import org.junit.Before;
import org.junit.Test;

import tec.uom.se.quantity.QuantityFactoryProvider;
import tec.uom.se.util.SI;

public class QuantityFunctionsFilterTest {

    private QuantityFactory<Time> timeFactory;
    private Quantity<Time> day;
    private Quantity<Time> hours;
    private Quantity<Time> minutes;
    private Quantity<Time> seconds;

    @Before
    public void init() {
        timeFactory = QuantityFactoryProvider.getQuantityFactory(Time.class);
        minutes = timeFactory.create(15, SI.MINUTE);
        hours = timeFactory.create(18, SI.HOUR);
        day = timeFactory.create(1, SI.DAY);
        seconds = timeFactory.create(100, SI.SECOND);
    }

    @Test
    public void filterByUnitTest() {
        List<Quantity<Time>> times = new ArrayList<>(getTimes());
        times.add(timeFactory.create(30, SI.HOUR));
        List<Quantity<Time>> list = times.stream().filter(QuantityFunctions.fiterByUnit(SI.HOUR)).collect(Collectors.toList());
        assertEquals(Integer.valueOf(2), Integer.valueOf(list.size()));
    }

    @Test
    public void shouldReturnAllWhenUnitEmpty() {
        List<Quantity<Time>> times = new ArrayList<>(getTimes());
        times.add(timeFactory.create(30, SI.HOUR));
        List<Quantity<Time>> list = times.stream().filter(QuantityFunctions.fiterByUnit()).collect(Collectors.toList());
        assertEquals(Integer.valueOf(5), Integer.valueOf(list.size()));
    }

    @Test
    public void filterByNotUnitTest() {
        List<Quantity<Time>> times = new ArrayList<>(getTimes());
        times.add(timeFactory.create(30, SI.HOUR));
        List<Quantity<Time>> list = times.stream().filter(QuantityFunctions.fiterByExcludingUnit(SI.HOUR)).collect(Collectors.toList());
        assertEquals(Integer.valueOf(3), Integer.valueOf(list.size()));
    }

    @Test
    public void shouldReturnAllWhenNotUnitEmpty() {
        List<Quantity<Time>> times = new ArrayList<>(getTimes());
        times.add(timeFactory.create(30, SI.HOUR));
        List<Quantity<Time>> list = times.stream().filter(QuantityFunctions.fiterByExcludingUnit()).collect(Collectors.toList());
        assertEquals(Integer.valueOf(5), Integer.valueOf(list.size()));
    }

    @Test
    public void filterByContainsUnitsTest() {
        List<Quantity<Time>> times = new ArrayList<>(getTimes());
        times.add(timeFactory.create(30, SI.HOUR));
        List<Quantity<Time>> list = times.stream().filter(QuantityFunctions.fiterByUnit(SI.HOUR, SI.MINUTE)).collect(Collectors.toList());
        assertEquals(Integer.valueOf(3), Integer.valueOf(list.size()));
    }

    @Test
    public void isGreaterThanTest() {
        List<Quantity<Time>> times = new ArrayList<>(getTimes());
        times.add(timeFactory.create(30, SI.HOUR));
        List<Quantity<Time>> list = times.stream().filter(QuantityFunctions.isGreaterThan(15)).collect(Collectors.toList());
        assertEquals(Integer.valueOf(3), Integer.valueOf(list.size()));

    }

    @Test
    public void isGreaterThanQuantityTest() {
        List<Quantity<Time>> times = new ArrayList<>();
        times.add(timeFactory.create(30, SI.HOUR));
        times.add(timeFactory.create(24, SI.HOUR));
        times.add(timeFactory.create(1440, SI.MINUTE));
        List<Quantity<Time>> list = times
                .stream()
                .filter(QuantityFunctions.isGreaterThan(timeFactory.create(1,
                        SI.DAY))).collect(Collectors.toList());
        assertEquals(Integer.valueOf(1), Integer.valueOf(list.size()));

    }

    @Test
    public void isGreaterThanOrEqualToTest() {
        List<Quantity<Time>> times = new ArrayList<>(getTimes());
        times.add(timeFactory.create(30, SI.HOUR));
        List<Quantity<Time>> list = times.stream().filter(QuantityFunctions.isGreaterThanOrEqualTo(15)).collect(Collectors.toList());
        assertEquals(Integer.valueOf(4), Integer.valueOf(list.size()));

    }

    @Test
    public void isGreaterThanOrEqualToQuantityTest() {
        List<Quantity<Time>> times = createTimesToFilter();
        Quantity<Time> filter = timeFactory.create(1, SI.DAY);
        List<Quantity<Time>> list = times.stream()
                .filter(QuantityFunctions.isGreaterThanOrEqualTo(filter))
                .collect(Collectors.toList());
        assertEquals(Integer.valueOf(3), Integer.valueOf(list.size()));

    }

    @Test
    public void isLesserThanTest() {
        List<Quantity<Time>> times = new ArrayList<>(getTimes());
        times.add(timeFactory.create(30, SI.HOUR));
        List<Quantity<Time>> list = times.stream().filter(QuantityFunctions.isLesserThan(15)).collect(Collectors.toList());
        assertEquals(Integer.valueOf(1), Integer.valueOf(list.size()));

    }

    @Test
    public void isLesserThanQuantityTest() {
        List<Quantity<Time>> times = createTimesToFilter();
        Quantity<Time> filter = timeFactory.create(1, SI.DAY);
        List<Quantity<Time>> list = times.stream()
                .filter(QuantityFunctions.isLesserThan(filter))
                .collect(Collectors.toList());
        assertEquals(Integer.valueOf(1), Integer.valueOf(list.size()));

    }


    @Test
    public void isLesserThanOrEqualToTest() {
        List<Quantity<Time>> times = new ArrayList<>(getTimes());
        times.add(timeFactory.create(30, SI.HOUR));
        List<Quantity<Time>> list = times.stream().filter(QuantityFunctions.isLesserThanOrEqualTo(15)).collect(Collectors.toList());
        assertEquals(Integer.valueOf(2), Integer.valueOf(list.size()));

    }


    @Test
    public void isLesserThanOrEqualToQuantityTest() {
        List<Quantity<Time>> times = createTimesToFilter();
        Quantity<Time> filter = timeFactory.create(1, SI.DAY);
        List<Quantity<Time>> list = times.stream()
                .filter(QuantityFunctions.isLesserThanOrEqualTo(filter))
                .collect(Collectors.toList());
        assertEquals(Integer.valueOf(3), Integer.valueOf(list.size()));

    }

    @Test
    public void isBetweenTest() {
        List<Quantity<Time>> times = new ArrayList<>(getTimes());
        times.add(timeFactory.create(30, SI.HOUR));
        List<Quantity<Time>> list = times.stream().filter(QuantityFunctions.isBetween(15, 30)).collect(Collectors.toList());
        assertEquals(Integer.valueOf(3), Integer.valueOf(list.size()));

    }

    @Test
    public void isBetweenQuantityTest() {
        List<Quantity<Time>> times = new ArrayList<>(getTimes());
        times.add(timeFactory.create(30, SI.HOUR));
        times.add(timeFactory.create(14, SI.HOUR));
        times.add(timeFactory.create(10, SI.HOUR));
        Quantity<Time> min = timeFactory.create(12, SI.HOUR);
        Quantity<Time> max = timeFactory.create(1, SI.DAY);
        List<Quantity<Time>> list = times.stream()
                .filter(QuantityFunctions.isBetween(min, max))
                .collect(Collectors.toList());
        assertEquals(Integer.valueOf(3), Integer.valueOf(list.size()));

    }


    private List<Quantity<Time>> getTimes() {
        return Arrays.asList(day, hours, minutes, seconds);
    }

    private List<Quantity<Time>> createTimesToFilter() {
        List<Quantity<Time>> times = new ArrayList<>();
        times.add(timeFactory.create(30, SI.HOUR));
        times.add(timeFactory.create(24, SI.HOUR));
        times.add(timeFactory.create(1440, SI.MINUTE));
        times.add(timeFactory.create(0.5, SI.DAY));
        return times;
    }

}
