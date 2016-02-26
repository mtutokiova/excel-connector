package org.mule.modules.excel;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.lifecycle.Start;
import org.mule.api.annotations.param.Default;
import org.mule.modules.excel.client.ExcelClient;
import org.mule.modules.excel.config.ConnectorConfig;
import org.mule.modules.excel.exception.ExcelConnectorException;

@Connector(name="excel", friendlyName="Excel")
public class ExcelConnector {

    @Config
    ConnectorConfig config;
    
    private ExcelClient client;
    
    @Start
    public void init() {
        setClient(new ExcelClient());
    }

    /**
	 * Creates excel file from the given input json.
	 * 
	 * @param json
	 * @param fileName name of the output excel file
	 * @param filePath path to the output excel file
	 * @param appendCurrentDate true if current date should be appended to fileName
	 * @throws ExcelConnectorException when there is an issue with the input json file or an exception with the response file
	 */
    @Processor
    public void createExcel(String json, String fileName, String filePath, @Default("false") Boolean appendCurrentDate) throws ExcelConnectorException {
        getClient().createExcel(json, fileName, filePath, appendCurrentDate);
    }

    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }

	public ExcelClient getClient() {
		return client;
	}

	public void setClient(ExcelClient client) {
		this.client = client;
	}

}