package com.chexun.base.common.util.codec;

/**
 * 解码器。
 * 
 * @author Spencer
 * @since 18 Aug 2014
 *
 * @param <S> 原始源。
 * @param <T> 目标源。
 */
public interface Decoder<S, T> {
	
	/**
	 * 解码。
	 * 
	 * @param source 原始源。
	 * @return 目标源。
	 * @throws DecoderException 解码异常，在解码过程中出现的任何异常都会使用{@link DecoderException}封装并抛出。
	 */
	T decode(S source) throws DecoderException;
}
