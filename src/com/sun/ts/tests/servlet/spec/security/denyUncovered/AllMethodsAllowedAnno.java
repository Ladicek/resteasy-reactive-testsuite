/*
 * Copyright (c) 2013, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.ts.tests.servlet.spec.security.denyUncovered;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.HttpConstraint;

import com.sun.ts.lib.util.*;

/*
 * This servlet will be used to assist in testing the deny-uncovered-http-methods
 * security support (as listed in Servlet 3.1 spec, section 13.8.4).  
 * 
 * This is intended to be called from a Client, and will have specific settings
 * set on the web.xml that will allow (or disallow) certain http method calls
 * to be made based on the web.xml settings.
 *
 */

@ServletSecurity(@HttpConstraint(rolesAllowed = { "Administrator", "Manager",
    "Employee" }))
@WebServlet(name = "AllMethodsAllowedAnno", urlPatterns = {
    "/AllMethodsAllowedAnno" })
public class AllMethodsAllowedAnno extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    debug("in AllMethodsAllowedAnno.doGet()");
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    debug("in AllMethodsAllowedAnno.doPost()");
  }

  public void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    debug("in AllMethodsAllowedAnno.doDelete()");
  }

  public void doPut(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    debug("in AllMethodsAllowedAnno.doPut()");
  }

  public void debug(String str) {
    System.out.println(str);
  }

}
