package br.com.caelum.livraria.interceptor;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.annotation.Transaction;

@Transaction
@Interceptor
public class TransactionManagerInterceptor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	@AroundInvoke
	public Object execute(InvocationContext invocationContext) throws Exception {
		entityManager.getTransaction().begin();
		Object object = invocationContext.proceed();
		entityManager.getTransaction().commit();
		return object;
	}

}
