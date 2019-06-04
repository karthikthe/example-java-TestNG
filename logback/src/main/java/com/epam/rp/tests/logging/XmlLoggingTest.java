package com.epam.rp.tests.logging;

import com.google.common.io.BaseEncoding;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * JUST an example of file logging
 *
 * @author Andrei Varabyeu
 */
public class XmlLoggingTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(XmlLoggingTest.class);
	private static final String XML_FILE_PATH = "xml/file.xml";

	@Test
	public void logXmlBase64() throws IOException {
		/* here we are logging some binary data as BASE64 string */
		LOGGER.info("RP_MESSAGE#BASE64#{}#{}",
				BaseEncoding.base64().encode(Resources.asByteSource(Resources.getResource(XML_FILE_PATH)).read()),
				"I'm logging content via BASE64");
	}

	@Test
	public void logXmlFile() throws IOException {
		/* here we are logging some binary data as file (useful for selenium) */
		File file = File.createTempFile("rp-test", "xml");
		Resources.asByteSource(Resources.getResource(XML_FILE_PATH)).copyTo(Files.asByteSink(file));

		LOGGER.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), "I'm logging content via temp file");
	}
}
