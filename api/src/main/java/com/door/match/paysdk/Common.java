package com.door.match.paysdk;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @ClassName: Common
 * @Description: 工具类
 * @author JiC
 * @date 2018年7月5日
 */
@Component
public final class Common {
	
	private static final Logger logger = LoggerFactory.getLogger(Common.class);
	
	@Autowired
	private HttpServletRequest request;
	
	private static Common common;
	
	@PostConstruct
	public void init() {
		common = this;
		common.request = request;
	}
	
	private Common() {}
	
	private static Properties properties;
	
	static {
		Reader reader = null;
		try {
			properties = new Properties();
			reader = new InputStreamReader(Common.class.getClassLoader().getResourceAsStream("config.properties"), "UTF-8");
			properties.load(reader);
		} catch (UnsupportedEncodingException e) {
			logger.error("读取配置文件路径失败, 错误信息:{}", e);
		} catch (IOException e) {
			logger.error("读取配置文件失败, 错误信息:{}", e);
		} finally {
			try {
				if(reader != null)
					reader.close();
			} catch (IOException e) {
				logger.error("关闭文件流失败, 错误信息:{}", e);
			}
		}
	}
	
	/**
	 * @Description:获取配置文件值
	 * @return String 
	 * @throws
	 * @author JiC 
	 * @date 2018年7月5日
	 */
	public static String getConfig(String name) {
		if(StringUtils.isEmpty(name)) 
			return null;
		return properties.getProperty(name);
	}
	
	/**
	 * @Description: 
	 * @return
	 * @return String 
	 * @throws
	 * @author JiC 
	 * @date 2018年7月30日
	 */
    public static String getIpAddr() {   
        String ip = common.request.getHeader("x-forwarded-for");   
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = common.request.getHeader("Proxy-Client-IP");   
        }   
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = common.request.getHeader("WL-Proxy-Client-IP");   
        }   
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = common.request.getRemoteAddr();   
            if(ip.equals("127.0.0.1")){     
                //根据网卡取本机配置的IP     
                InetAddress inet=null;     
                try {     
                    inet = InetAddress.getLocalHost();     
                } catch (Exception e) {     
                    e.printStackTrace();     
                }     
                ip= inet.getHostAddress();     
            }  
        }   
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
        if(ip != null && ip.length() > 15){    
            if(ip.indexOf(",")>0){     
                ip = ip.substring(0,ip.indexOf(","));     
            }     
        }     
        return ip;   
    }  
}
