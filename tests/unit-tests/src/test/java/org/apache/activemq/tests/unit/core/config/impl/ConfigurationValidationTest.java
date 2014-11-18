/*
 * Copyright 2005-2014 Red Hat, Inc.
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.apache.activemq.tests.unit.core.config.impl;

import org.junit.Test;

import org.junit.Assert;

import org.apache.activemq.core.config.impl.FileConfiguration;
import org.apache.activemq.tests.util.UnitTestCase;
import org.apache.activemq.utils.XMLUtil;
import org.w3c.dom.Element;

/**
 * A ConfigurationValidationTr
 *
 * @author jmesnil
 *
 * Created 22 janv. 2009 14:53:19
 *
 *
 */
public class ConfigurationValidationTest extends UnitTestCase
{

   // Constants -----------------------------------------------------

   // Attributes ----------------------------------------------------

   // Static --------------------------------------------------------

   // Constructors --------------------------------------------------

   // Public --------------------------------------------------------

   /**
    * test does not pass in eclipse (because it can not find activemq-configuration.xsd).
    * It runs fine on the CLI with the proper env setting.
    */
   @Test
   public void testMinimalConfiguration() throws Exception
   {
      String xml = "<configuration xmlns='urn:hornetq'>" + "</configuration>";
      Element element = XMLUtil.stringToElement(xml);
      Assert.assertNotNull(element);
      XMLUtil.validate(element, "schema/activemq-configuration.xsd");
   }

   @Test
   public void testFullConfiguration() throws Exception
   {
      FileConfiguration fc = new FileConfiguration("ConfigurationTest-full-config.xml");
      fc.start();

      Assert.assertEquals(true, fc.isPersistDeliveryCountBeforeDelivery());
   }
}
