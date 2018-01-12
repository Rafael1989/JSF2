package br.com.caelum.livraria.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.com.caelum.livraria.annotation.Log;

@Log
@Interceptor
public class TempoDeExecucaoInterceptor {
	
	@AroundInvoke
	public Object calculaTempoExecucao(InvocationContext invocationContext) throws Exception {
		long inicio = System.currentTimeMillis();
		Object object = invocationContext.proceed();
		long fim = System.currentTimeMillis();
		System.out.println(invocationContext.getMethod().getName()+":"+(fim-inicio));
		return object;
	}

}
