package com.door.match.paysdk;



import java.util.HashMap;
import java.util.Map;


public final class MyPayDomain {

	public static IWXPayDomain apiWeiXin() {
		IWXPayDomain domain = map.get("apiweixin");
		if(domain == null) {
			domain = new IWXPayDomain() {
				
				@Override
				public void report(String domain, long elapsedTimeMillis, Exception ex) {
				}
				
				@Override
				public DomainInfo getDomain(WXPayConfig config) {
					return new DomainInfo(WXPayConstants.DOMAIN_WEIXIN, true);
				}
			};
			map.put("apiweixin", domain);
		}
		return domain;
	}
	
	public static IWXPayDomain apiMCH() {
		IWXPayDomain domain = map.get("apiMCH");
		if(domain == null) {
			domain = new IWXPayDomain() {
				
				@Override
				public void report(String domain, long elapsedTimeMillis, Exception ex) {
				}
				
				@Override
				public DomainInfo getDomain(WXPayConfig config) {
					return new DomainInfo(WXPayConstants.DOMAIN_API, true);
				}
			};
			map.put("apiweixin", domain);
		}
		return domain;
	}
	
	private static Map<String, IWXPayDomain> map = new HashMap<String, IWXPayDomain>();
}
