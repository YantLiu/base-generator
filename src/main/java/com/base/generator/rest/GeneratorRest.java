package com.base.generator.rest;

import com.alibaba.fastjson.JSON;
import com.base.generator.dto.TableResultResponse;
import com.base.generator.service.GeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by ace on 2017/8/25.
 */
@RestController
@RequestMapping("/base/generator")
public class GeneratorRest {

    @Autowired
    private GeneratorService generatorService;

    /**
     * 列表
     */
    @GetMapping("/page")
    public TableResultResponse<Map<String, Object>> list(@RequestParam Map<String, Object> params) {
        List<Map<String, Object>> result = generatorService.queryList(params);
        int total = generatorService.queryTotal(params);
        return new TableResultResponse<>(total, result);
    }

    /**
     * 生成代码
     */
    @GetMapping("/code")
    public void code(String tables, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] tableNames = new String[]{};
        tableNames = JSON.parseArray(tables).toArray(tableNames);

        byte[] data = generatorService.generatorCode(tableNames);

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"generate-code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }
}
