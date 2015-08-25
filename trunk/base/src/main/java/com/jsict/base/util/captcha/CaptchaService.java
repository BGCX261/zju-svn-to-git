/**
 * CaptchaService.java 2008-12-2 下午10:40:40 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.base.util.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.Locale;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.FunkyBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.color.SingleColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class CaptchaService implements ImageCaptchaService
{
    /**
     * <p>
     * Description: [描述该类概要功能介绍]
     * </p>
     * 
     * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
     * @version $Revision$
     */

    private static ImageCaptchaService _captchaService = new DefaultManageableImageCaptchaService(
        new FastHashMapCaptchaStore(), new MyImageCaptchaEngine(), 180, 100000,
        75000);

    public Object getChallengeForID(String arg0, Locale arg1)
        throws CaptchaServiceException
    {
        return _captchaService.getChallengeForID(arg0, arg1);
    }

    public Object getChallengeForID(String arg0) throws CaptchaServiceException
    {
        return _captchaService.getChallengeForID(arg0);
    }

    public BufferedImage getImageChallengeForID(String arg0, Locale arg1)
        throws CaptchaServiceException
    {
        return _captchaService.getImageChallengeForID(arg0, arg1);
    }

    public BufferedImage getImageChallengeForID(String arg0)
        throws CaptchaServiceException
    {
        return _captchaService.getImageChallengeForID(arg0);
    }

    public String getQuestionForID(String arg0, Locale arg1)
        throws CaptchaServiceException
    {
        return _captchaService.getQuestionForID(arg0, arg1);
    }

    public String getQuestionForID(String arg0) throws CaptchaServiceException
    {
        return _captchaService.getQuestionForID(arg0);
    }

    public Boolean validateResponseForID(String arg0, Object arg1)
        throws CaptchaServiceException
    {
        return _captchaService.validateResponseForID(arg0, arg1);
    }

}

class MyImageCaptchaEngine extends ListImageCaptchaEngine
{

    protected void buildInitialFactories()
    {
        // 随机生成的字符
        WordGenerator wgen = new RandomWordGenerator(
            "abcdefghijklmnopqrstuvwxyz123456789");
        RandomRangeColorGenerator cgen = new RandomRangeColorGenerator(
            new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100});
        // 文字显示的个数
        TextPaster textPaster = new RandomTextPaster(new Integer(3),
            new Integer(3), cgen, true);
        // 图片的大小
        BackgroundGenerator backgroundGenerator = new FunkyBackgroundGenerator(
            new Integer(80), new Integer(30), new SingleColorGenerator(
                Color.white));
        // 字体格式
        Font[] fontsList = new Font[]{new Font("Arial", 0, 10),
                new Font("Tahoma", 0, 10), new Font("Verdana", 0, 10),};
        // 文字的大小
        FontGenerator fontGenerator = new RandomFontGenerator(new Integer(12),
            new Integer(16), fontsList);

        WordToImage wordToImage = new ComposedWordToImage(fontGenerator,
            backgroundGenerator, textPaster);
        this.addFactory(new GimpyFactory(wgen, wordToImage));
    }
}
