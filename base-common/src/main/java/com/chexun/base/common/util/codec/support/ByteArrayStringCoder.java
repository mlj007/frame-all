package com.chexun.base.common.util.codec.support;

import com.chexun.base.common.util.codec.DecoderException;
import com.chexun.base.common.util.codec.EncoderException;

/**
 * 处理{@link String}到{@link byte[]}类型的编码和解码器。
 * 
 * @author Spencer
 * @since 18 Aug 2014
 * @see AbstractCoder
 */
public class ByteArrayStringCoder extends AbstractCoder<byte[], String, byte[], char[]> {

	@Override
	public String encode(byte[] source) throws EncoderException {
		return new String(innerEncode(source));
	}

	@Override
	public byte[] decode(String source) throws DecoderException {
		return innerDecode(source.toCharArray());
	}

}
