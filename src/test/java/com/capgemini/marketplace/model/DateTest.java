package com.capgemini.marketplace.model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Ordering;

public class DateTest {

	@Test
	public void testShouldGrowingSortDatesCorrectly() {
		List<Date> dateList = Arrays.asList(new Date(3, 5, 2014), new Date(2,5,2013), new Date(1, 6, 2013));
		Ordering<Date> ordering = Ordering.natural();
		Collections.sort(dateList, ordering);
		assertEquals(dateList.get(0).getDay(), 2);
		assertEquals(dateList.get(1).getDay(), 1);
	}

	@Test
	public void testShouldDescendingSortDatesCorrectly() {
		List<Date> dateList = Arrays.asList(new Date(3, 5, 2014), new Date(2,5,2013), new Date(1, 6, 2013));
		Ordering<Date> ordering = Ordering.natural().reverse();
		Collections.sort(dateList, ordering);
		assertEquals(dateList.get(0).getDay(), 3);
		assertEquals(dateList.get(1).getDay(), 1);
	}

}
