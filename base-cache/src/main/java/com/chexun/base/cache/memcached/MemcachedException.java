/**
 * 
 */
package com.chexun.base.cache.memcached;

/**
 * Memcached 内部错误定义
 * @author spencer
 *
 */
@SuppressWarnings("serial")
public class MemcachedException extends RuntimeException
{
	public MemcachedException() 
	{
		super();
	}

	public MemcachedException(String message) 
	{
		super(message);
	}


	public MemcachedException(String message, Throwable cause) 
	{
	   super(message, cause);
	}

	public MemcachedException(Throwable cause) 
	{
	   super(cause);
	}
}
