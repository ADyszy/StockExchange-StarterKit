package com.capgemini.marketplace.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.capgemini.marketplace.model.Date;
import com.capgemini.marketplace.model.Stock;
import com.capgemini.marketplace.model.StockRate;
import com.google.common.collect.Ordering;

// TODO it better.

@Service
public class DataService {
	private String source;
	private BufferedReader bufferedReader;
	private String singleLine = "";
	private List<DataObject> objects; 
	private Map<String, Stock> stockMap;
	private int maxDay;
	
	public DataService() {
		source = "src/main/csv/dane.csv";
		stockMap = new HashMap<String, Stock>();
		objects = new ArrayList<DataObject>();
		try {
			read();
			placeDataObjectsInMap();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// TODO : handle errors, validate.
	private void read() throws IOException {
		bufferedReader = new BufferedReader(new FileReader(source));
		while ((singleLine = bufferedReader.readLine()) != null) {
			String[] tmp = singleLine.split(",");
			objects.add(new DataObject(tmp[0], tmp[1], tmp[2]));
		}
	}

	private Date convertDate(DataObject dataObject) {
		return new Date(Integer.valueOf(dataObject.getDate().substring(6, 8)),
				Integer.valueOf(dataObject.getDate().substring(4, 6)),
				Integer.valueOf(dataObject.getDate().substring(0, 4)));
	}

	private void placeDataObjectsInMap() {
		for (DataObject dataObject : objects) {
			addNewStockIfNew(dataObject);
			stockMap.get(dataObject.getName()).getHistory()
					.add(new StockRate(convertDate(dataObject), Double.valueOf(dataObject.getRate())));
		} 
		sortStocksByDate(); 
		setUpDaysOfSimulation();
	}
	
	private void addNewStockIfNew(DataObject dataObject) {
		if (!stockMap.containsKey(dataObject.getName())) {
			Stock newStock = new Stock();
			newStock.setName(dataObject.getName());
			stockMap.put(dataObject.getName(), newStock);
		}
	}

	public List<Stock> getActuallStocks(int day) {
		if (day >= maxDay) return null;
		List<Stock> actuallStocks = new ArrayList<Stock>();
		stockMap.keySet().forEach(key -> actuallStocks.add(createActuallStock(stockMap.get(key), day)));
		return actuallStocks;
	}
	
	private Stock createActuallStock(Stock allStock, int day) {
		Stock actuallStock = new Stock();
		actuallStock.setName(allStock.getName());
		actuallStock.setHistory(allStock.getHistory().subList(0, day));
		return actuallStock;
	}

	public void sortStocksByDate() {
		Ordering<StockRate> ordering = Ordering.natural();
		stockMap.values().forEach(value -> Collections.sort(value.getHistory(), ordering));
	}
	
	private void setUpDaysOfSimulation() {
		stockMap.values().forEach(value -> maxDay = Math.max(value.getHistory().size(), maxDay));
	}
	
}
