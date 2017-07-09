package com.flavio.util.jsf;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JsfExceptionHandler extends ExceptionHandlerWrapper {
	
	private static Log log = LogFactory.getLog(JsfExceptionHandler.class);

	private ExceptionHandler wrapped;

	public JsfExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	@Override
	public void handle() throws FacesException {
		Iterator<ExceptionQueuedEvent> events = getHandledExceptionQueuedEvents().iterator();

		while (events.hasNext()) {
			ExceptionQueuedEvent exceptionQueuedEvent = (ExceptionQueuedEvent) events.next();
			ExceptionQueuedEventContext exceptionQueuedEventContext = exceptionQueuedEvent.getContext();

			boolean handler = false;

			try {
				Throwable exception = exceptionQueuedEventContext.getException();
				if (exception instanceof ViewExpiredException) {
					handler = true;
					redirect("/");
				}else {
					log.error("erro de sistemas: " + exception.getMessage(),exception);
				}
			} finally {
				if (handler) {
					events.remove();
				}
			}
		}
		getWrapped().handle();
	}

	private void redirect(String page) {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			String contextPath = externalContext.getRequestContextPath();

			externalContext.redirect(contextPath + page);
			facesContext.responseComplete();
		} catch (IOException e) {
			throw new FacesException(e);
		}

	}
}
