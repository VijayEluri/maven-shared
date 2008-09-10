package org.apache.maven.shared.filtering;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.File;
import java.util.List;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.project.MavenProject;

/**
 * @author <a href="mailto:olamy@apache.org">olamy</a>
 * @version $Id$
 */
public interface MavenFileFilter
{
    
    /**
     * Will copy a file with some filtering using defaultFilterWrappers 
     * @see #getDefaultFilterWrappers(MavenProject, List, boolean, MavenSession)
     * 
     * @param from file to copy/filter
     * @param to destination file
     * @param filtering enable or not filering
     * @param mavenProject the mavenproject
     * @param filters {@link List} of String which are path to a Property file
     * @throws MavenFilteringException 
     */
    void copyFile( File from, final File to, boolean filtering, MavenProject mavenProject, List filters,
                          boolean escapedBackslashesInFilePath, String encoding, MavenSession mavenSession )
        throws MavenFilteringException;

    /**
     * @param from
     * @param to
     * @param filtering
     * @param filterWrappers {@link List} of FileUtils.FilterWrapper
     * @throws MavenFilteringException
     */
    void copyFile( File from, final File to, boolean filtering, List filterWrappers, String encoding )
        throws MavenFilteringException;

    /**
     * 
     * Will return the default FileUtils.FilterWrappers
     * 
     * <ul>
     *   <li>interpolate with token ${} and values from sysProps, project.properties, filters and project filters.</li>
     *   <li>interpolate with token @ @ and values from sysProps, project.properties, filters and project filters.</li>
     *   <li>interpolate with token ${} and values from mavenProject interpolation.</li>
     *   <li>interpolation with token @ @ and values from mavenProject interpolation</li>
     * </ul>
     * <b>default escape characters will be \</b>
     * @deprecated use {@link #getDefaultFilterWrappers(MavenProject, List, boolean, MavenSession, MavenResourcesExecution)}
     * @param mavenProject
     * @param filters {@link List} of properties file
     * 
     * @return {@link List} of FileUtils.FilterWrapper 
     * 
     */
    List getDefaultFilterWrappers( MavenProject mavenProject, List filters, boolean escapedBackslashesInFilePath,
                                   MavenSession mavenSession )
        throws MavenFilteringException;
    
    /**
     * @since 1.0-beta-2
     * @param mavenProject
     * @param filters
     * @param escapedBackslashesInFilePath
     * @param mavenSession
     * @param mavenResourcesExecution
     * @return
     * @throws MavenFilteringException
     */
    List getDefaultFilterWrappers( MavenProject mavenProject, List filters, boolean escapedBackslashesInFilePath,
                                   MavenSession mavenSession, MavenResourcesExecution mavenResourcesExecution )
        throws MavenFilteringException;    
}
