package com.hashbinary.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class XMLConfigReader {
	private static String XML_PATH = "";

	public static void main(String[] args) {
		if (args.length < 1) {
			//XML_PATH = "config/Webservice.xml";
			XML_PATH = "config/TypicodeService.xml";
		} else {
			XML_PATH = args[0].trim();
		}
		loadFile(XML_PATH);
	}

	public static void loadFile(String path) {
		InputStream is = null;
		List<Future<ServiceResult>> list = new ArrayList<Future<ServiceResult>>();
		try {
			ClassLoader classLoader = XMLConfigReader.class.getClassLoader();
			File file = new File(classLoader.getResource(XML_PATH).getFile());
			is = new FileInputStream(file);
			Map<String, Model> dataMap = ConfigurationLoader
					.loadConfiguration(is);
			System.out.println(dataMap);

			ExecutorService executor = Executors.newFixedThreadPool(3);

			// Pass each service information.
			for (Map.Entry<String, Model> entry : dataMap.entrySet()) {
				Callable<ServiceResult> callable = new ServiceRunnerCallable(
						entry.getValue());
				Future<ServiceResult> future = executor.submit(callable);
				list.add(future);
			}

			for (Future<ServiceResult> fut : list) {
				try {
					ServiceResult res = fut.get();
				System.out.println(res);
					// TODO : Write all results to an excel file.
				} catch (InterruptedException e) {
					e.printStackTrace();
				}catch(ExecutionException e){
				    e.printStackTrace();
				}
			}
			// shut down the executor service now
			executor.shutdown();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}
}