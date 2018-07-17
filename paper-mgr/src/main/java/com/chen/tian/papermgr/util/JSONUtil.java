package com.chen.tian.papermgr.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Arrays;

public class JSONUtil {
	public static String toJSONString(Object obj,String[] includesProperties, String[] excludesProperties){
		FastjsonFilter filter = new FastjsonFilter();// excludes优先于includes
		if (excludesProperties != null && excludesProperties.length > 0) {
			filter.getExcludes().addAll(Arrays.<String> asList(excludesProperties));
		}
		if (includesProperties != null && includesProperties.length > 0) {
			filter.getIncludes().addAll(Arrays.<String> asList(includesProperties));
		}
		
		return JSON.toJSONString(obj, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
	}
	
	public static String toJSONStringFromExcludes(Object obj,String[] excludesProperties){
		return toJSONString(obj, null, excludesProperties);
	}
	
	public static String toJSONStringFromIncludes(Object obj,String[] includesProperties){
		return toJSONString(obj, includesProperties, null);
	}
}

