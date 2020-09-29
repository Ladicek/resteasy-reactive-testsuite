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

package com.sun.ts.tests.jaxrs.spec.provider.standardnotnull;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

import javax.activation.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBElement;
import javax.xml.transform.Source;

import com.sun.ts.tests.jaxrs.common.impl.StringStreamingOutput;

@Path("resource")
public class Resource {

  public static final String NULL = "NULL";

  public static final String NOTNULL = "EXPECTED";

  @Path("bytearray")
  @POST
  public String bytearray(byte[] bytes) {
    return isNull(bytes);
  }

  @Path("string")
  @POST
  public String string(String string) {
    return isNull(string);
  }

  @Path("inputstream")
  @POST
  public String inputstream(InputStream inputstream) {
    return isNull(inputstream);
  }

  @Path("reader")
  @POST
  public String reader(Reader reader) {
    return isNull(reader);
  }

  @Path("file")
  @POST
  public String file(File file) {
    return isNull(file);
  }

  // QUARKUS: disabled as we don't support javax.activation.DataSource
//  @Path("datasource")
//  @POST
  public String datasource(DataSource datasource) {
    return isNull(datasource);
  }

  // QUARKUS: disable this because we don't support JAXBElement and it causes a build time error
//  @Path("jaxb")
//  @POST
//  public String jaxb(JAXBElement<String> jaxb) {
//    return isNull(jaxb);
//  }

  // QUARKUS: disabled as we don't support javax.xml.transform.Source
//  @Path("source")
//  @POST
  public String source(Source source) {
    return isNull(source);
  }

  @Path("map")
  @POST
  public String map(MultivaluedMap<String, String> map) {
    return isNull(map);
  }

  @Path("streamingoutput")
  @POST
  public StreamingOutput streamingoutput(String streamingoutput) {
    return new StringStreamingOutput(streamingoutput);
  }

  @Path("character")
  @POST
  public Character character(Character character) {
    return character;
  }

  @Path("boolean")
  @POST
  public String bigbool(Boolean bool) {
    return isNull(bool);
  }

  @Path("bigdecimal")
  @POST
  public String number(BigDecimal number) {
    return isNull(number);
  }

  @Path("integer")
  @POST
  public String number(Integer number) {
    return isNull(number);
  }

  @Path("biglong")
  @POST
  public String number(Long number) {
    return isNull(number);
  }

  @Path("bigdouble")
  @POST
  public String number(Double number) {
    return isNull(number);
  }

  @Path("atomic")
  @POST
  public String atomic(AtomicInteger ai) {
    return isNull(ai);
  }

  @Path("entity")
  @GET
  public Response entity() {
    return Response.ok().build();
  }

  @Path("null")
  @GET
  public String echo() {
    return null;
  }

  private static String isNull(Object o) {
    return (o == null) ? NULL : NOTNULL;
  }
}
