package com.jsict.base.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Required;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageCaptchaAction extends Action
{
    private ImageCaptchaService captchaService;

    @Required
    public void setCaptchaService(ImageCaptchaService captchaService)
    {
        this.captchaService = captchaService;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        
        doGet(request, response);
        return null;
    }

    protected void doGet(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws ServletException,
        IOException
    {

        byte[] captchaChallengeAsJpeg = null;
        // the output stream to render the captcha image as jpeg into
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try
        {
            // get the session id that will identify the generated captcha.
            //the same id must be used to validate the response, the session id is a good candidate!
            String captchaId = httpServletRequest.getSession().getId();
            // call the ImageCaptchaService getChallenge method
            BufferedImage challenge = captchaService.getImageChallengeForID(
                captchaId, httpServletRequest.getLocale());

            // a jpeg encoder
            JPEGImageEncoder jpegEncoder = JPEGCodec
                    .createJPEGEncoder(jpegOutputStream);
            jpegEncoder.encode(challenge);

        }
        catch (IllegalArgumentException e)
        {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        catch (CaptchaServiceException e)
        {
            httpServletResponse
                    .sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

        // flush it in the response
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse
                .getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}