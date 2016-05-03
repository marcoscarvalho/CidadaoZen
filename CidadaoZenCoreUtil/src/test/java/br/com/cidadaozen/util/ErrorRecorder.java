package br.com.cidadaozen.util;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

public class ErrorRecorder implements ErrorHandler {

	private RetrofitError error;

	@Override
	public Throwable handleError(RetrofitError cause) {
		error = cause;
		return error.getCause();
	}

	public RetrofitError getError() {
		return error;
	}

}
