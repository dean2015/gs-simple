/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.common.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Scan classes under classpath. Jar packages are not included.
 * 
 * @author gaosong
 * 
 */
public final class ClassesScaner {

	private static final Logger logger = Logger.getLogger(ClassesScaner.class
			.getName());

	public static Map<String, WeakReference<Set<Class<?>>>> classesMap = new HashMap<String, WeakReference<Set<Class<?>>>>();

	/**
	 * Scan all classes under the classpath
	 * 
	 * @param forceReload
	 *            True: force reload/rescan the classes False: if classes are
	 *            available in the cache, then return them.
	 * @return
	 */
	public static Set<Class<?>> scanAllClasses(boolean forceReload) {
		return scanClasses("", true, forceReload);
	}

	/**
	 * Scan the classes by the package name
	 * 
	 * @param packageName
	 *            like gs.simple, . , "" , com.google.common
	 * @param recursive
	 * 
	 * @param forceReload
	 *            True: force reload/rescan the classes False: if classes are
	 *            available in the cache, then return them.
	 * @return
	 */
	public static Set<Class<?>> scanClasses(String packageName,
			boolean recursive, boolean forceReload) {
		WeakReference<Set<Class<?>>> weakReferenceClasses = classesMap
				.get(packageName);

		if (weakReferenceClasses == null
				|| CheckCollection.isEmpty(weakReferenceClasses.get())
				|| forceReload) {
			weakReferenceClasses = new WeakReference<Set<Class<?>>>(
					new LinkedHashSet<Class<?>>());
			classesMap.put(packageName, weakReferenceClasses);
		} else {
			return weakReferenceClasses.get();
		}

		Set<Class<?>> classes = weakReferenceClasses.get();
		// Validate package name
		if (!validatePackage(packageName)) {
			return classes;
		}
		String packagePathName = packageName.replace('.', '/');
		// An enum for looping
		Enumeration<URL> dirs;
		try {
			// Get all resources into an enum.
			dirs = Thread.currentThread().getContextClassLoader()
					.getResources(packagePathName);
			// Looping
			while (dirs.hasMoreElements()) {
				URL url = dirs.nextElement();
				// Get the protocol
				String protocol = url.getProtocol();
				// if the protocol is file, then processes
				if ("file".equals(protocol)) {
					// Get the absolute path of the package
					String packageAbsolutePath = URLDecoder.decode(
							url.getFile(), "UTF-8");
					// Scan the classes under the package
					scanClasses(packageName, packageAbsolutePath, recursive,
							classes);
				} else {
					// other protocols are ignored
				}
			}
		} catch (IOException e) {
			logger.severe(e.getMessage());
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}

		return classes;
	}

	/**
	 * Scan the classes under the package
	 * 
	 * @param packageName
	 * @param packageAbsolutePath
	 * @param recursive
	 * @param classes
	 *            result set
	 * 
	 */
	public static void scanClasses(String packageName,
			String packageAbsolutePath, final boolean recursive,
			Set<Class<?>> classes) {
		File dir = new File(packageAbsolutePath);
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		// List files by the filter
		File[] dirfiles = dir.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return (recursive && file.isDirectory())
						|| (file.getName().endsWith(".class"));
			}
		});
		if (null == dirfiles) {
			return;
		}
		// remove the first letter, if it is '/' or '.'
		if (packageName.indexOf("/") == 0 || packageName.indexOf(".") == 0) {
			packageName = packageName.substring(1);
		}
		// Looping checking the files
		for (File file : dirfiles) {
			// if directory, call scanClasses recursively
			if (file.isDirectory()) {
				scanClasses(packageName + "." + file.getName(),
						file.getAbsolutePath(), recursive, classes);
			} else {
				// if class, then get it and add it to the result set.
				String className = file.getName().substring(0,
						file.getName().length() - 6);
				try {
					classes.add(Thread.currentThread().getContextClassLoader()
							.loadClass(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					logger.severe(e.getMessage());
				}
			}
		}
	}

	/**
	 * 校验包名 空格为合法
	 * 
	 * @param packageName
	 * @return
	 */
	public static boolean validatePackage(String packageName) {
		Pattern pattern = Pattern
				.compile("^[a-zA-Z][\\w]*([.][a-zA-Z][\\w]*)*|^\\s*");
		Matcher matcher = pattern.matcher(packageName);
		return matcher.matches();
	}

}
