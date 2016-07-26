/*
   Copyright 2010 Voice Technology Ind. e Com. Ltda.
 
   This file is part of RTSPClientLib.

    RTSPClientLib is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    RTSPClientLib is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with RTSPClientLib.  If not, see <http://www.gnu.org/licenses/>.

 */
package br.com.voicetechnology.rtspclient.test;

import java.io.IOException;

import android.util.Log;
import br.com.voicetechnology.rtspclient.MissingHeaderException;
import br.com.voicetechnology.rtspclient.concepts.Client;
import br.com.voicetechnology.rtspclient.concepts.Header;
import br.com.voicetechnology.rtspclient.concepts.Request;
import br.com.voicetechnology.rtspclient.concepts.Response;
import br.com.voicetechnology.rtspclient.concepts.Request.Method;
import mktvsmart.screen.GMScreenGlobalInfo;

/**
 * Testing the PLAY message. RTP Stream can be checked with Wireshark or a play
 * with RTP only capability.
 * 
 * @author paulo
 * 
 */
public class PLAYTest extends SETUPandTEARDOWNTest
{

	int streamId = 0;
	private final static String mBaseUrl = "rtsp://192.168.178.254:554/";


	public static void main(String[] args) throws Throwable
	{
		new PLAYTest();
	}

	protected PLAYTest() throws Exception
	{
		super();
	}

	@Override
	public void response(Client client, Request request, Response response)
	{
		try
		{
			super.response(client, request, response);

			Header[] headers = response.getHeaders();
			if(headers == null)
			{
				Log.e("PLAYTest", "response.getHeaders() == null");
				return;
			}
			for(int i=0; i<headers.length; i++)
			{
				Log.d("PLAYTest", "header ["+i+"] :  name= " + headers[i].getName() + ", value= " + headers[i].getRawValue());

			}


			if(request.getMethod() == Method.PLAY && response.getStatusCode() == 200)
			{

				//if (GMScreenGlobalInfo.playType == 2) {

//					this.streamId = Integer.parseInt(response.getHeader("com.ses.streamID").getRawValue());
//					client.play(String.valueOf(this.mBaseUrl) + "stream=" + this.streamId);
					//return;
				//}
				//else
				client.options("*", new java.net.URI(this.mBaseUrl));


				Thread.sleep(20000);
				client.teardown();
			}
		} catch(Throwable t)
		{
			generalError(client, t);
		}
	}

	@Override
	protected void sessionSet(Client client, Response response) throws IOException
	{
		boolean error = true;
		try {
			this.streamId = Integer.parseInt(response.getHeader("com.ses.streamID").getRawValue());
			error = false;
		} catch (MissingHeaderException e) {
			e.printStackTrace();
		}
		if(!error)
			client.play(String.valueOf(this.mBaseUrl) + "stream=" + this.streamId);
		else
			Log.e("PLAYTest", "streamId not parseable");
//		client.play();
	}
}
