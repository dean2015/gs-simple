package org.gsimple.event;

/**
 * 事件总线接口
 * @author gaosong
 *
 */
public interface EventBus {

	/**
	 * 发布事件
	 * @param event
	 */
	void publish(Object event);

	/**
	 * 注册事件处理
	 * @param eventListener
	 */
	void register(Object eventListener);

	/**
	 * 注销事件处理
	 * @param eventListener
	 */
	void unregister(Object eventListener);

}
