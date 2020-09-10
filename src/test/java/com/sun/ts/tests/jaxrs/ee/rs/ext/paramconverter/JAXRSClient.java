/*
 * Copyright (c) 2012, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.ts.tests.jaxrs.ee.rs.ext.paramconverter;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.sun.ts.tests.jaxrs.common.client.JaxrsCommonClient;

/*
 * @class.setup_props: webServerHost;
 *                     webServerPort;
 *                     ts_home;
 */
public class JAXRSClient extends JaxrsCommonClient {

  private static final long serialVersionUID = 863071027768369551L;

  public JAXRSClient() {
    setContextRoot("/jaxrs_ee_ext_paramconverter_web");
  }

  /**
   * Entry point for different-VM execution. It should delegate to method
   * run(String[], PrintWriter, PrintWriter), and this method should not contain
   * any test configuration.
   */
  public static void main(String[] args) {
    JAXRSClient theTests = new JAXRSClient();
    theTests.run(args);
  }

  /* Run test */

  /*
   * @testName: isParamCoverterInApplicationSingletonsUsedTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:919;
   * 
   * @test_Strategy: Providers implementing ParamConverterProvider contract must
   * be either programmatically registered in a JAX-RS runtime
   */
  public void isParamCoverterInApplicationSingletonsUsedTest() throws Fault {
    String query = "ABCDEFGH";
    setPropertyRequest(Request.GET, "dsquery?param=", query);
    setProperty(Property.SEARCH_STRING, query);
    invoke();
  }

  /*
   * @testName: isParamCoverterInApplicationClassesUsedTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:919;
   * 
   * @test_Strategy: Providers implementing ParamConverterProvider contract must
   * be either programmatically registered in a JAX-RS runtime
   */
  public void isParamCoverterInApplicationClassesUsedTest() throws Fault {
    String query = "ABCDEFGH";
    setPropertyRequest(Request.GET, "sbquery?param=", query);
    setProperty(Property.SEARCH_STRING, query);
    invoke();
  }

  /*
   * @testName: atomicIntegerPassesTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:919;
   * 
   * @test_Strategy: Providers implementing ParamConverterProvider contract must
   * be either programmatically registered in a JAX-RS runtime
   */
  public void atomicIntegerPassesTest() throws Fault {
    String query = "10";
    setPropertyRequest(Request.GET, "aiquery?param=", query);
    setProperty(Property.SEARCH_STRING, query);
    invoke();
  }

  /*
   * @testName: atomicIntegerIsLazyDeployableAndThrowsErrorTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:919;
   * 
   * @test_Strategy: Providers implementing ParamConverterProvider contract must
   * be either programmatically registered in a JAX-RS runtime
   */
  public void atomicIntegerIsLazyDeployableAndThrowsErrorTest() throws Fault {
    setPropertyRequest(Request.GET, "aiquery");
    setProperty(Property.STATUS_CODE, getStatusCode(Status.NOT_ACCEPTABLE));
    invoke();
  }

  /*
   * @testName: pathParamUsesParamConvertorTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: Parse the supplied value and create an instance of
   */
  public void pathParamUsesParamConvertorTest() throws Fault {
    String query = "ABCDEFGH";
    setPropertyRequest(Request.GET, "sbpath/", query);
    setProperty(Property.SEARCH_STRING, query);
    invoke();
  }

  /*
   * @testName: matrixParamUsesParamConvertorTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: Parse the supplied value and create an instance of
   */
  public void matrixParamUsesParamConvertorTest() throws Fault {
    String query = "ABCDEFGH";
    setPropertyRequest(Request.GET, "sbmatrix;param=", query);
    setProperty(Property.SEARCH_STRING, query);
    invoke();
  }

  /*
   * @testName: formParamUsesParamConvertorTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: Parse the supplied value and create an instance of
   */
  public void formParamUsesParamConvertorTest() throws Fault {
    String query = "ABCDEFGH";
    setProperty(Property.REQUEST_HEADERS,
        buildContentType(MediaType.APPLICATION_FORM_URLENCODED_TYPE));
    setPropertyRequest(Request.POST, "sbform/");
    setProperty(Property.CONTENT, "param=" + query);
    setProperty(Property.SEARCH_STRING, query);
    invoke();
  }

  /*
   * @testName: cookieParamUsesParamConvertorTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: Parse the supplied value and create an instance of
   */
  public void cookieParamUsesParamConvertorTest() throws Fault {
    String query = "ABCDEFGH";
    buildCookie(query);
    setPropertyRequest(Request.GET, "sbcookie");
    setProperty(Property.SEARCH_STRING, query);
    invoke();
  }

  /*
   * @testName: headerParamUsesParamConvertorTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: Parse the supplied value and create an instance of
   */
  public void headerParamUsesParamConvertorTest() throws Fault {
    String query = "ABCDEFGH";
    setProperty(Property.REQUEST_HEADERS, "param:" + query);
    setPropertyRequest(Request.GET, "sbheader");
    setProperty(Property.SEARCH_STRING, query);
    invoke();
  }

  /*
   * @testName: defaultValueInQueryParamTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: selected ParamConverter instance MUST be used eagerly by a
   * JAX-RS runtime to convert any default value in the resource or provider
   * model, that is during the application deployment, before any value default
   * or otherwise is actually required
   */
  public void defaultValueInQueryParamTest() throws Fault {
    setPropertyRequest(Request.GET, "sbquery");
    setProperty(Property.SEARCH_STRING, Resource.DEFAULT);
    invoke();
  }

  /*
   * @testName: defaultValueInMatrixParamTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: selected ParamConverter instance MUST be used eagerly by a
   * JAX-RS runtime to convert any default value in the resource or provider
   * model, that is during the application deployment, before any value default
   * or otherwise is actually required
   */
  public void defaultValueInMatrixParamTest() throws Fault {
    setPropertyRequest(Request.GET, "sbmatrix;");
    setProperty(Property.SEARCH_STRING, Resource.DEFAULT);
    invoke();
  }

  /*
   * @testName: defaultValueInPathParamTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: selected ParamConverter instance MUST be used eagerly by a
   * JAX-RS runtime to convert any default value in the resource or provider
   * model, that is during the application deployment, before any value default
   * or otherwise is actually required
   */
  public void defaultValueInPathParamTest() throws Fault {
    setPropertyRequest(Request.GET, "sbpath/default");
    setProperty(Property.SEARCH_STRING, Resource.DEFAULT);
    invoke();
  }

  /*
   * @testName: defaultValueInFormParamTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: selected ParamConverter instance MUST be used eagerly by a
   * JAX-RS runtime to convert any default value in the resource or provider
   * model, that is during the application deployment, before any value default
   * or otherwise is actually required
   */
  public void defaultValueInFormParamTest() throws Fault {
    setProperty(Property.REQUEST_HEADERS,
        buildContentType(MediaType.APPLICATION_FORM_URLENCODED_TYPE));
    setPropertyRequest(Request.POST, "sbform/");
    setProperty(Property.SEARCH_STRING, Resource.DEFAULT);
    invoke();
  }

  /*
   * @testName: defaultValueInCookieParamTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: selected ParamConverter instance MUST be used eagerly by a
   * JAX-RS runtime to convert any default value in the resource or provider
   * model, that is during the application deployment, before any value default
   * or otherwise is actually required
   */
  public void defaultValueInCookieParamTest() throws Fault {
    setPropertyRequest(Request.GET, "sbcookie");
    setProperty(Property.SEARCH_STRING, Resource.DEFAULT);
    invoke();
  }

  /*
   * @testName: defaultValueInHeaderParamTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: selected ParamConverter instance MUST be used eagerly by a
   * JAX-RS runtime to convert any default value in the resource or provider
   * model, that is during the application deployment, before any value default
   * or otherwise is actually required
   */
  public void defaultValueInHeaderParamTest() throws Fault {
    setPropertyRequest(Request.GET, "sbheader");
    setProperty(Property.SEARCH_STRING, Resource.DEFAULT);
    invoke();
  }

  /*
   * @testName: queryParamInLocatorTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: Parse the supplied value and create an instance of
   */
  public void queryParamInLocatorTest() throws Fault {
    String query = "ABCDEFGH";
    setPropertyRequestInLocator(Request.GET, "sbquery/sbquery?param=", query);
    setProperty(Property.SEARCH_STRING, query);
    invoke();
  }

  /*
   * @testName: defaultValueQueryParamInLocatorTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: selected ParamConverter instance MUST be used eagerly by a
   * JAX-RS runtime to convert any default value
   */
  public void defaultValueQueryParamInLocatorTest() throws Fault {
    String query = "ABCDEFGH";
    setPropertyRequestInLocator(Request.GET, "sbquery/sbquery?", query);
    setProperty(Property.SEARCH_STRING, Resource.DEFAULT);
    invoke();
  }

  /*
   * @testName: pathParamInLocatorTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: Parse the supplied value and create an instance of
   */
  public void pathParamInLocatorTest() throws Fault {
    String query = "ABCDEFGH";
    setPropertyRequestInLocator(Request.GET, "sbpath/sbpath/", query);
    setProperty(Property.SEARCH_STRING, query);
    invoke();
  }

  /*
   * @testName: defaultValuePathParamInLocatorTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: selected ParamConverter instance MUST be used eagerly by a
   * JAX-RS runtime to convert any default value
   */
  public void defaultValuePathParamInLocatorTest() throws Fault {
    setPropertyRequestInLocator(Request.GET, "sbpath/sbpath/default");
    setProperty(Property.SEARCH_STRING, Resource.DEFAULT);
    invoke();
  }

  /*
   * @testName: matrixParamInLocatorTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: Parse the supplied value and create an instance of
   */
  public void matrixParamInLocatorTest() throws Fault {
    String query = "ABCDEFGH";
    setPropertyRequestInLocator(Request.GET, "sbmatrix/sbmatrix;param=", query);
    setProperty(Property.SEARCH_STRING, query);
    invoke();
  }

  /*
   * @testName: defaultValueMatrixParamInLocatorTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: selected ParamConverter instance MUST be used eagerly by a
   * JAX-RS runtime to convert any default value
   */
  public void defaultValueMatrixParamInLocatorTest() throws Fault {
    setPropertyRequestInLocator(Request.GET, "sbmatrix/sbmatrix");
    setProperty(Property.SEARCH_STRING, Resource.DEFAULT);
    invoke();
  }

  /*
   * @testName: formParamInLocatorTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: Parse the supplied value and create an instance of
   */
  public void formParamInLocatorTest() throws Fault {
    String query = "ABCDEFGH";
    setProperty(Property.REQUEST_HEADERS,
        buildContentType(MediaType.APPLICATION_FORM_URLENCODED_TYPE));
    setPropertyRequestInLocator(Request.POST, "sbform/sbform");
    setProperty(Property.CONTENT, "param=" + query);
    setProperty(Property.SEARCH_STRING, query);
    invoke();
  }

  /*
   * @testName: defaultValueFormParamInLocatorTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: selected ParamConverter instance MUST be used eagerly by a
   * JAX-RS runtime to convert any default value
   */
  public void defaultValueFormParamInLocatorTest() throws Fault {
    setProperty(Property.REQUEST_HEADERS,
        buildContentType(MediaType.APPLICATION_FORM_URLENCODED_TYPE));
    setPropertyRequestInLocator(Request.POST, "sbform/sbform");
    setProperty(Property.SEARCH_STRING, Resource.DEFAULT);
    invoke();
  }

  /*
   * @testName: cookieParamInLocatorTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: Parse the supplied value and create an instance of
   */
  public void cookieParamInLocatorTest() throws Fault {
    String query = "ABCDEFGH";
    buildCookie(query);
    setPropertyRequestInLocator(Request.GET, "sbcookie/sbcookie");
    setProperty(Property.SEARCH_STRING, query);
    invoke();
  }

  /*
   * @testName: defaultValueCookieParamInLocatorTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: selected ParamConverter instance MUST be used eagerly by a
   * JAX-RS runtime to convert any default value
   */
  public void defaultValueCookieParamInLocatorTest() throws Fault {
    setPropertyRequestInLocator(Request.GET, "sbcookie/sbcookie");
    setProperty(Property.SEARCH_STRING, Resource.DEFAULT);
    invoke();
  }

  /*
   * @testName: headerParamInLocatorTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: Parse the supplied value and create an instance of
   */
  public void headerParamInLocatorTest() throws Fault {
    String query = "ABCDEFGH";
    setProperty(Property.REQUEST_HEADERS, "param:" + query);
    setPropertyRequestInLocator(Request.GET, "sbheader/sbheader");
    setProperty(Property.SEARCH_STRING, query);
    invoke();
  }

  /*
   * @testName: defaultValueHeaderParamInLocatorTest
   * 
   * @assertion_ids: JAXRS:JAVADOC:915;
   * 
   * @test_Strategy: selected ParamConverter instance MUST be used eagerly by a
   * JAX-RS runtime to convert any default value
   */
  public void defaultValueHeaderParamInLocatorTest() throws Fault {
    setPropertyRequestInLocator(Request.GET, "sbheader/sbheader");
    setProperty(Property.SEARCH_STRING, Resource.DEFAULT);
    invoke();
  }

  // ////////////////////////////////////////////////////////////////////
  private//
  void setPropertyRequestInResource(Request request, String... resource) {
    StringBuilder sb = new StringBuilder("resource/");
    for (String r : resource)
      sb.append(r);
    setProperty(Property.REQUEST, buildRequest(request, sb.toString()));
  }

  private void setPropertyRequest(Request request, String... resource) {
    setPropertyRequestInResource(request, resource);
  }

  private//
  void setPropertyRequestInLocator(Request request, String... resource) {
    StringBuilder sb = new StringBuilder("locator/");
    for (String r : resource)
      sb.append(r);
    setProperty(Property.REQUEST, buildRequest(request, sb.toString()));
  }

  private void buildCookie(String cookieValue) {
    StringBuilder sb = new StringBuilder();
    sb.append("Cookie: param=").append(cookieValue);
    setProperty(Property.REQUEST_HEADERS, sb.toString());
  }

}
