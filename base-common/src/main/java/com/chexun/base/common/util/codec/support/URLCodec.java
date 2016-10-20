package com.chexun.base.common.util.codec.support;

import com.chexun.base.common.util.codec.Decoder;
import com.chexun.base.common.util.codec.DecoderException;
import com.chexun.base.common.util.codec.Encoder;
import com.chexun.base.common.util.codec.EncoderException;


/**
 * URL编码和解码器。处理byte[]到byte[]类型。
 * 
 * @author Spencer
 * @since 18 Aug 2014
 * @see Encoder
 * @see Decoder
 */
public class URLCodec implements Encoder<byte[], byte[]>, Decoder<byte[], byte[]> {

	@Override
	public byte[] decode(byte[] source) throws DecoderException {
		try {
			return org.apache.commons.codec.net.URLCodec.decodeUrl(source);
		} catch (Exception e) {
			throw new DecoderException(e.getMessage(), e);
		}
	}

	@Override
	public byte[] encode(byte[] source) throws EncoderException {
		try {
			return org.apache.commons.codec.net.URLCodec.encodeUrl(null, source);
		} catch (Exception e) {
			throw new EncoderException(e.getMessage(), e);
		}
	}

}
