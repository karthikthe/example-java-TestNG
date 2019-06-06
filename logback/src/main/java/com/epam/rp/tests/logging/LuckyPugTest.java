package com.epam.rp.tests.logging;

import com.epam.reportportal.annotations.Step;
import com.epam.reportportal.annotations.StepTemplateConfig;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.epam.rp.tests.MagicRandomizer;
import com.google.common.collect.Lists;
import com.google.common.io.BaseEncoding;
import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

/**
 * Logs image
 *
 * @author Andrei Varabyeu
 */
@Listeners(ReportPortalTestNGListener.class)
public class LuckyPugTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(LuckyPugTest.class);

	@Test
	public void logImageBase64() throws IOException {

		for (int i = 0; i < 3; i++) {

			everyone(Lists.newArrayList("SOLUTION", "ARCHITECT"),
					"VANYA",
					Lists.<String[]>newArrayList(new String[] { "FIRST", "SECOND" })
			);
		}

		/* Generate 10 logs with pugs. Pug may be lucky or unlucky based on randomizer */
		for (int i = 0; i < 1; i++) {
			/* 50 percents. So we should have approximately same count of lucky and unlucky pugs */
			boolean happy = MagicRandomizer.checkYourLucky(30);
			String image = getImageResource(happy);

			LOGGER.info("RP_MESSAGE#BASE64#{}#{}",
					BaseEncoding.base64().encode(Resources.asByteSource(Resources.getResource(image)).read()),
					"Pug is " + (happy ? "HAPPY" : "NOT HAPPY")
			);
		}

		for (int i = 0; i < 3; i++) {
			nested("STEP");
		}

	}

	@Step(value = "Hi {o_O} my name is {name} and i'm a {rank}. List of arrays - {joska} {joska.hash}", templateConfig = @StepTemplateConfig(arrayElementDelimiter = " =) ", methodNameTemplate = "o_O"))
	public void everyone(List<String> rank, String name, List<String[]> joska) {
		LOGGER.error("MESSAGE");

		for (int i = 0; i < 3; i++) {
			nested("STEP");
		}
	}

	@Step(value = "IT IS {method} {name}")
	public void nested(String name) {
		LOGGER.debug("HALO FRIEND");
		LOGGER.debug("HALO FRIEND");
		LOGGER.debug("HALO FRIEND");

	}

	private String getImageResource(boolean lucky) {
		return "pug/" + (lucky ? "lucky.jpg" : "unlucky.jpg");
	}
}
