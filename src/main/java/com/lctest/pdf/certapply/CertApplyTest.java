package com.lctest.pdf.certapply;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lichao on 2017/12/9.
 */
public class CertApplyTest {
    private static final String CONTRACT = "resource/contract/";//合同文件存储路径
    private static final String TEMPLATE = "resource/templates/";//模板存储路径

    private static final String PDFNAME = "certApply";//pdf文件名
    private static final String HTMLNAME = "certApply";//html文件名

    public static void contractHandler(String templateName,
                                       Map<String, Object> paramMap) throws Exception{
        // 获取本地模板存储路径、合同文件存储路径
        String templatePath = TEMPLATE;
        String contractPath = CONTRACT;
        // 组装html和pdf合同的全路径URL
        String localHtmlUrl = contractPath + HTMLNAME + ".html";
        String localPdfPath = contractPath + "/";
        // 判断本地路径是否存在如果不存在则创建
        File localFile = new File(localPdfPath);
        if (!localFile.exists()) {
            localFile.mkdirs();
        }
        String localPdfUrl = localFile + "/" + PDFNAME + ".pdf";
        templateName=templateName+".ftl";
        htmHandler(templatePath, templateName, localHtmlUrl, paramMap);// 生成html合同
        pdfHandler(localHtmlUrl, localPdfUrl);// 根据html合同生成pdf合同
        deleteFile(localHtmlUrl);// 删除html格式合同

        System.out.println("PDF生成成功");
    }

    /**
     * 生成html格式合同
     */
    private static void htmHandler(String templatePath, String templateName,
                                   String htmUrl, Map<String, Object> paramMap) throws Exception {
        Configuration cfg = new Configuration();
        cfg.setDefaultEncoding("UTF-8");
        cfg.setDirectoryForTemplateLoading(new File(templatePath));

        Template template = cfg.getTemplate(templateName);

        File outHtmFile = new File(htmUrl);

        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outHtmFile)));
        template.process(paramMap, out);

        out.close();
    }

    /**
     * 生成pdf格式合同
     */
    private static void pdfHandler(String htmUrl, String pdfUrl)
            throws DocumentException, IOException {
        File htmFile = new File(htmUrl);
        File pdfFile = new File(pdfUrl);

        String url = htmFile.toURI().toURL().toString();

        OutputStream os = new FileOutputStream(pdfFile);

        org.xhtmlrenderer.pdf.ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);

        org.xhtmlrenderer.pdf.ITextFontResolver fontResolver = renderer
                .getFontResolver();
        // 解决中文支持问题
        fontResolver.addFont(TEMPLATE+"simsun.ttc",
                BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        renderer.layout();
        renderer.createPDF(os);
        os.close();
    }

    /**
     * 删除文件
     */
    private static void deleteFile(String fileUrl) {
        File file = new File(fileUrl);
        file.delete();
    }

    public static void main(String[] args) throws Exception {
        String templateName = "301";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code","RZRW201711010001");
        paramMap.put("bidProjectCode","1234565432");
        paramMap.put("supplierName","大连中晟联合国际物流有限公司");
        paramMap.put("businessLicenseNo","91210202MA0QCH5H3C");
        paramMap.put("categoryName","大连中晟联合国际物流有限公司");
        paramMap.put("jyms","代理型");
        paramMap.put("zzsh","资质审核");
        paramMap.put("xcrz","现场认证");
        paramMap.put("cpsy","产品试验");
        paramMap.put("adviceGradeName","优秀");
        paramMap.put("xcrzresult","合格");
        paramMap.put("cpsyresult","合格");
        paramMap.put("result","优秀");
        paramMap.put("bgsj","2017-12-09");
        paramMap.put("certificateOrgName","鞍钢招标有限公司");
        paramMap.put("creatorName","徐蕾");
        List<QualifyCheck> qualifyChecklist = new ArrayList<QualifyCheck>();
        QualifyCheck qualifyCheck = null;
        for(int i =0;i< 100;i++){
            qualifyCheck = new QualifyCheck();
            qualifyCheck.setZzxm((i+1)+"zzxm");
            qualifyCheck.setFjbz((i+1)+"");
            qualifyCheck.setZzqk((i+1)+"");
            qualifyCheck.setShjg((i+1)+"");
            qualifyCheck.setBz("备至");
            qualifyChecklist.add(qualifyCheck);
        }
        paramMap.put("qualifyChecklist",qualifyChecklist);

        List<ApplyField> applyFieldList = new ArrayList<>();
        ApplyField applyField = null;
        for(int i=0;i< 30;i++){
            applyField = new ApplyField();
            applyField.setSsfl("aaaa");
            applyField.setRbmc("bbbb");
            applyField.setZbms("cccc");
            applyField.setPfyj("dddd");
            applyField.setZbqz("eeee");
            applyField.setRzh("ffff");
            applyFieldList.add(applyField);
        }
        paramMap.put("applyFieldList",applyFieldList);
        contractHandler(templateName, paramMap);
    }
}
