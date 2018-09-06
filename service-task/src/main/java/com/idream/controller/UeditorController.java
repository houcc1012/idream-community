package com.idream.controller;

import com.idream.editor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hejiang
 * @date 2018/8/23
 */

@Controller
public class UeditorController {

    @RequestMapping("/")
    public String index(){
        return "ueditor";
    }

//    @Autowired
//    private ActionEnter actionEnter;

    @ResponseBody
    @RequestMapping("/ueditor/exec")
    public String exe(HttpServletRequest request){
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        System.out.println(rootPath);
        String str = new ActionEnter(request, rootPath).exec();
        System.out.println(str);
        return str;
    }

}
