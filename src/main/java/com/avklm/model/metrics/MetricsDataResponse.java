package com.avklm.model.metrics;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MetricsDataResponse implements Serializable {

	private Map<String, RestApiMetric> metricsData = new HashMap<>();

	public Map<String, RestApiMetric> getMetricsData() {
		return metricsData;
	}

	public void setMetricsData(Map<String, RestApiMetric> metricsData) {
		this.metricsData = metricsData;
	}
	
	
}
