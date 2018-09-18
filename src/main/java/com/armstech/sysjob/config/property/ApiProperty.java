package com.armstech.sysjob.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("sysworks")
public class ApiProperty {

	private final Security security = new Security();

	public Security gettSecurity() {
		return this.security;
	}

	public static class Security {

		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}

	}

}
