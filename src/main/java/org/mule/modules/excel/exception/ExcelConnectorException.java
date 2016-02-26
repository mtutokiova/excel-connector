package org.mule.modules.excel.exception;

public class ExcelConnectorException extends Exception{

	private static final long serialVersionUID = -4422030345467325340L;
	
	public ExcelConnectorException() {
	}

	public ExcelConnectorException(String message) {
		super(message);
	}

	public ExcelConnectorException(Throwable cause) {
		super(cause);
	}

	public ExcelConnectorException(String message, Throwable cause) {
		super(message, cause);
	}

}
