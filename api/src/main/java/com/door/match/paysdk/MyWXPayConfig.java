package com.door.match.paysdk;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public final class MyWXPayConfig {

	public static WXPayConfig apiWeiXin() {
		WXPayConfig config = map.get("apiweixin");
		if(config == null) {
			config = new Config(MyPayDomain.apiWeiXin());
			map.put("apiweixin", config);
		}
		return config;
	}
	
	public static WXPayConfig apiMCH() {
		WXPayConfig config = map.get("apiMCH");
		if(config == null) {
			config = new Config(MyPayDomain.apiMCH());
			map.put("apiweixin", config);
		}
		return config;
	}
	
	private static class Config extends WXPayConfig {

		public Config(IWXPayDomain domain) {
			this.domain = domain;
		}
		
		@Override
		String getAppID() {
			return appid;
		}

		@Override
		String getMchID() {
			return wxmchid;
		}

		@Override
		String getKey() {
			return wxkey;
		}

		@Override
		InputStream getCertStream() {
			InputStream is = null;
			try {
				is = new FileInputStream("E:\\apiclient_cert.p12");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return is;
		}

		@Override
		IWXPayDomain getWXPayDomain() {
			return domain;
		}
		
		private IWXPayDomain domain = null;

		@Override
		String getSecret() {
			return wxsecret;
		}
	}
	
	private static Map<String, WXPayConfig> map = new HashMap<String, WXPayConfig>();
	private static final String appid = Common.getConfig("wxappid");
	private static final String wxmchid = Common.getConfig("wxmchid");
	private static final String wxkey = Common.getConfig("wxkey");
	private static final String wxsecret = Common.getConfig("wxsecret");
}
