package com.preapm.agent.plugin.interceptor;

import org.apache.http.client.methods.HttpUriRequest;

import com.preapm.agent.common.bean.MethodInfo;
import com.preapm.agent.common.context.AroundInterceptorContext;
import com.preapm.agent.common.interceptor.AroundInterceptor;
import com.preapm.sdk.zipkin.ThreadLocalTraceStore;

/**
 * org.apache.http.client.HttpClient.execute(HttpUriRequest)
 * 
 * @author Zengmin.Zhang
 *
 */
public class Httpclient4Interceptor implements AroundInterceptor {

	@Override
	public void before(MethodInfo methodInfo) {
		try {
			HttpUriRequest request = (HttpUriRequest) methodInfo.getArgs()[0];
			if(request!=null) {
				System.out.println("放入traceId到http请求头："+ThreadLocalTraceStore.get());
				request.addHeader(com.preapm.sdk.zipkin.util.TraceKeys.TRACE_ID,
						String.valueOf(ThreadLocalTraceStore.get()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void exception(MethodInfo methodInfo) {
	}

	@Override
	public void after(MethodInfo methodInfo) {

	}
	@Override
	public String name() {
		return this.getClass().getName();
	}
	
}
