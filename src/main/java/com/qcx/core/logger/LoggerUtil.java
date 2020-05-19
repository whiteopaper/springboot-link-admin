package com.qcx.core.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * - TODO(描述类的职责) 自定义日志类
 *
 * 
 */
public class LoggerUtil {

	/**
	 * fqcn
	 */
	private static final String fqcn = LoggerUtil.class.getName();
	/**
	 * NOT_AVAIL
	 */
	private static final String NOT_AVAIL = "?";

	/**
	 * 获取最原始被调用的堆栈信息
	 *
	 */
	private static StackTraceElement getStackTraceElement(
			final StackTraceElement[] stackTrace) {
		boolean next = false;
		for (final StackTraceElement element : stackTrace) {
			final String className = element.getClassName();
			if (next && !fqcn.equals(className)) {
				return element;
			}
			if (fqcn.equals(className)) {
				next = true;
			} else if (NOT_AVAIL.equals(className)) {
				break;
			}
		}
		return null;
	}


	public static void info(final String message) {
		StackTraceElement caller = getStackTraceElement(Thread.currentThread()
				.getStackTrace());
		if (null == caller)
			return;
		Logger log = LoggerFactory
				.getLogger(caller.getClassName() + "." + caller.getMethodName()
						+ "() Line: " + caller.getLineNumber());
		log.info(message);
	}

	public static void info(final String message, Throwable t) {
		StackTraceElement caller = getStackTraceElement(Thread.currentThread()
				.getStackTrace());
		if (null == caller)
			return;
		Logger log = LoggerFactory
				.getLogger(caller.getClassName() + "." + caller.getMethodName()
						+ "() Line: " + caller.getLineNumber());
		log.info(message, t);
	}


	public static void debug(final String message) {
		StackTraceElement caller = getStackTraceElement(Thread.currentThread()
				.getStackTrace());
		if (null == caller)
			return;
		Logger log = LoggerFactory
				.getLogger(caller.getClassName() + "." + caller.getMethodName()
						+ "() Line: " + caller.getLineNumber());
		log.debug(message);
	}


	public static void debug(final String message, Throwable t) {
		StackTraceElement caller = getStackTraceElement(Thread.currentThread()
				.getStackTrace());
		if (null == caller)
			return;
		Logger log = LoggerFactory
				.getLogger(caller.getClassName() + "." + caller.getMethodName()
						+ "() Line: " + caller.getLineNumber());
		log.debug(message, t);
	}

	public static void error(final String message) {
		StackTraceElement caller = getStackTraceElement(Thread.currentThread()
				.getStackTrace());
		if (null == caller)
			return;
		Logger log = LoggerFactory
				.getLogger(caller.getClassName() + "." + caller.getMethodName()
						+ "() Line: " + caller.getLineNumber());
		log.error(message);
	}

	public static void error(final String message, Throwable t) {
		StackTraceElement caller = getStackTraceElement(Thread.currentThread()
				.getStackTrace());
		if (null == caller)
			return;
		Logger log = LoggerFactory
				.getLogger(caller.getClassName() + "." + caller.getMethodName()
						+ "() Line: " + caller.getLineNumber());
		log.error(message, t);
	}


	public static void warn(final String message) {
		StackTraceElement caller = getStackTraceElement(Thread.currentThread()
				.getStackTrace());
		if (null == caller)
			return;
		Logger log = LoggerFactory
				.getLogger(caller.getClassName() + "." + caller.getMethodName()
						+ "() Line: " + caller.getLineNumber());
		log.warn(message);
	}
}
