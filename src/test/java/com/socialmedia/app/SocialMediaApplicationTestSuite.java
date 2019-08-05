package com.socialmedia.app;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SocialMediaApplicationTestSuite {

	public static Test suite() {

		TestSuite suite = new TestSuite("Social Media Application test suite");

		suite.addTestSuite(SocialMediaApplicationTest.class);

		return suite;
	}
}
