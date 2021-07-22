package com.uga.forwords.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONFIG_MASTER")
public class Config {

	@Id
	@Column(name = "CONFIG_KEY")
	private String configKey;
	
	@Column(name = "CONFIG_VALUE")
	private String configValue;

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public Config(String configKey, String configValue) {
		this.configKey = configKey;
		this.configValue = configValue;
	}
	
	public Config() {
	
	}

	@Override
	public String toString() {
		return "Config [configKey=" + configKey + ", configValue=" + configValue + "]";
	}
	
	
	
}
