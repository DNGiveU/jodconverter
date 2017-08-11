/*
 * Copyright 2004 - 2012 Mirko Nasato and contributors
 *           2016 - 2017 Simon Braconnier and contributors
 *
 * This file is part of JODConverter - Java OpenDocument Converter.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jodconverter.filter;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.jodconverter.BaseOfficeITest;
import org.jodconverter.filter.text.PageSelectorFilter;

public class PageSelectorITest extends BaseOfficeITest {

  private static final String SOURCE_FILE = DOCUMENTS_DIR + "test_multi_page.doc";
  private static final String OUTPUT_DIR =
      TEST_OUTPUT_DIR + PageSelectorITest.class.getSimpleName() + "/";

  /** Ensures we start with a fresh output directory. */
  @BeforeClass
  public static void createOutputDir() {

    // Ensure we start with a fresh output directory
    final File outputDir = new File(OUTPUT_DIR);
    FileUtils.deleteQuietly(outputDir);
    outputDir.mkdirs();
  }

  /**  Deletes the output directory. */
  @AfterClass
  public static void deleteOutputDir() {

    // Delete the output directory
    FileUtils.deleteQuietly(new File(OUTPUT_DIR));
  }

  /**
   * Test the conversion of a document replacing text along the way.
   *
   * @throws Exception if an error occurs.
   */
  @Test
  public void doFilter_SelectPage2_ShouldSucceed() throws Exception {

    final File sourceFile = new File(SOURCE_FILE);
    final File testOutputDir = new File(OUTPUT_DIR);

    // Create the PageSelectorFilter to test.
    final PageSelectorFilter pageSelectorFilter = new PageSelectorFilter((short) 2);

    // Test the filter
    convertFileTo(
        sourceFile,
        formatRegistry.getFormatByExtension(FilenameUtils.getExtension(sourceFile.getName())),
        testOutputDir,
        null,
        formatRegistry.getFormatByExtension("html"),
        pageSelectorFilter,
        RefreshFilter.REFRESH);

    // TODO: Check if the content of the file has the expected text.
  }
}
