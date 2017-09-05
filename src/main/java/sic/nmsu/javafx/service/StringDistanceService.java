package sic.nmsu.javafx.service;

import org.springframework.stereotype.Service;

import info.debatty.java.stringsimilarity.experimental.Sift4;
import info.debatty.java.stringsimilarity.interfaces.StringDistance;

@Service
public class StringDistanceService {
	private StringDistance metric;
	
	public StringDistanceService() {
		Sift4 sift4 =new Sift4();
		sift4.setMaxOffset(5);
		this.metric = sift4;
	}
	
	public StringDistanceService(StringDistance metric) {
		this.metric = metric;
	}
	
	public double distance(String strA, String strB) {
		return metric.distance(strA, strB);
	}
}
