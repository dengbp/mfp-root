package com.yr.net.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.yr.net.bean.AjaxResponse;
import com.yr.net.bean.UsersBean;
import com.yr.net.entity.Attachment;
import com.yr.net.entity.Customer;
import com.yr.net.entity.WXCustomer;
import com.yr.net.exception.UnauthorizedException;
import com.yr.net.http.HttpUtils;
import com.yr.net.model.UserInfoReq;
import com.yr.net.service.UserService;
import com.yr.net.service.impl.AttachmentService;
import com.yr.net.service.impl.WXCustomerService;
import com.yr.net.util.JWTUtil;
import com.yr.net.util.RegexUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author:     dengbp
 * @Date: 2018/5/29
 * </pre>
 * <p>
 *    用户控制器
 * </p>
 */
@Controller
@Scope("prototype")
public class UsersController {
    static Logger log = LoggerFactory.getLogger(UsersController.class);
    @Value("${validate.code.url}")
    private String validateCodeUrl;
    @Value("${validate.code.account}")
    private String account;
    @Value("${validate.code.password}")
    private String password;
    private static final String MSG_CONTENT_PREFIX = "【伊人网网络公司】您的验证码是:";
    @Resource
    UserService userService;
    @Resource
    AttachmentService attachmentService;
    @Resource
    WXCustomerService wxCustomerService;
    private static long EXPIRE = 1000 * 60 * 2;

    /**
     * 获取验证码
     * @param request request
     * @param response response
     * @param phone phone
     * @return 返回验证码
     */
    @RequestMapping(method = RequestMethod.POST,path = "/getValidateCode")
    @ResponseBody
    public AjaxResponse getValidateCode(HttpServletRequest request,HttpServletResponse response, String phone){
        response.setHeader("Access-Control-Allow-Credentials","true");
        AjaxResponse ajaxResponse = new AjaxResponse();
        if(!RegexUtils.checkMobile(phone)){
            log.info("手机号码校验失败");
            ajaxResponse.setCode(1);
            ajaxResponse.setMsg("手机号码不正确");
            return ajaxResponse;
        }
        String code = RandomStringUtils.random(4, "0123456789");
        long time = System.currentTimeMillis();
        ajaxResponse.setCode(0);
        ajaxResponse.setMsg(code.concat("_").concat(time+"").concat("_").concat(phone));
        request.getSession().setAttribute("code",code);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("account",account);
        jsonObject.put("password",password);
        jsonObject.put("msg",MSG_CONTENT_PREFIX.concat(code));
        jsonObject.put("phone",phone);
        String data = jsonObject.toJSONString();
        HttpUtils.sendByPost(validateCodeUrl,data);
        log.info("发送验证码成功");
        return ajaxResponse;
    }

    /**
     * 用户登录
     * @param userInfoReq 用户信息
     * @return 登录结果信息
     */
    @PostMapping("/signIn")
    @ResponseBody
    public AjaxResponse login(HttpServletRequest request,@RequestBody UserInfoReq userInfoReq) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        //暂时注释
   /*     if(!this.validate(request,ajaxResponse,userInfoReq)){
            ajaxResponse.setCode(1);
            return ajaxResponse;
        }*/
        UsersBean usersBean = userService.findByPhone(userInfoReq.getPhone());
        if (usersBean != null) {
            return new AjaxResponse(200, "Login success", JWTUtil.sign(userInfoReq.getPhone(), usersBean.getPassword()));
        } else {
            ajaxResponse.setCode(1);
            ajaxResponse.setMsg("用户不存在");
            return ajaxResponse;
        }
    }

    /**
     * 绑定
     * @param request
     * @param phone 手机
     * @param code 验证码
     * @param codeTime 验证码用效时间
     * @param role 角色。0：相亲；1：红娘
     * @return 登录结果
     */
    @PostMapping("/login")
    @ResponseBody
    public AjaxResponse login(HttpServletRequest request,String phone,String code,String codeTime,Integer role,String openId){
        log.info("请求参数:phone[{}],code[{}],codeTime[{}],role[{}]",phone,code,codeTime,role);
        AjaxResponse ajaxResponse = new AjaxResponse();
        UserInfoReq userInfoReq = new UserInfoReq(phone,code,codeTime);
        if(!this.validate(request,ajaxResponse,userInfoReq)){
            ajaxResponse.setCode(1);
            return ajaxResponse;
        }
        ajaxResponse.setCode(0);
        Customer customer = new Customer();
        customer.setPhone(phone);
        customer.setRole(role);
        customer.setOpenId(openId);
        customer = userService.saveOrUpdate(customer);
        ajaxResponse.setResult(customer);
        ajaxResponse.setMsg("绑定成功");
        request.getSession().setAttribute("user",customer);
        return ajaxResponse;
    }

    /**
     * 用户信息校验
     * @param request request
     * @param ajaxResponse ajaxResponse
     * @param userInfoReq userInfoReq
     * @return ajaxResponse
     */
    private boolean validate(HttpServletRequest request,AjaxResponse ajaxResponse,UserInfoReq userInfoReq){
        String[] preCode = userInfoReq.getCodeTime().split("_");
        boolean validate = true;
        if (preCode.length != 3){
            ajaxResponse.setMsg("先获取手机验证码");
            validate = false;
            return validate;
        }
        if(!RegexUtils.checkMobile(userInfoReq.getPhone())){
            ajaxResponse.setMsg("手机号码格式不正确");
            validate = false;
            return validate;
        }
        /**
         * 取验证码的手机号与登录手机号不相同
         */
        if (!StringUtils.equals(userInfoReq.getPhone(),preCode[2])){
            ajaxResponse.setMsg("手机号码校验不通过");
            validate = false;
            return validate;
        }
        String id = (String) request.getSession().getAttribute("code");
        ajaxResponse.setMsg("验证码验证失败,请重新获取验证码");
        long current = System.currentTimeMillis();
        if(!id.equals(userInfoReq.getMessageCode()) || (current - new Long(preCode[1]).longValue())>EXPIRE){
            ajaxResponse.setMsg("验证码验证失败,请重新获取验证码");
            validate = false;
            return validate;
        }
        return validate;
    }

    /**
     * 保存微信用户信息
     * @param wxCustomer
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,path = "/wx/save")
    @ResponseBody
    public AjaxResponse saveWXcustomer(WXCustomer wxCustomer){
        wxCustomerService.save(wxCustomer);
        return new AjaxResponse(0,"保存用户信息成功",null);
    }

    /**
     * 根据openid取用户信息
     * @param openId 微信用户唯一标识
     * @return  AjaxResponse
     */
    @RequestMapping(method = RequestMethod.GET,path = "/wx/find/openid")
    @ResponseBody
    public AjaxResponse getCustomerByOpenId(String openId){
        UsersBean usersBean = userService.findByOpenId(openId);
        return new AjaxResponse(0,"获取用户信息成功",usersBean);
    }

    /**
     * 根据openid检查资料是否完善
     * @param openId 微信用户唯一标识
     * @return  AjaxResponse
     */
    @RequestMapping(method = RequestMethod.GET,path = "/checkInfo/openid")
    @ResponseBody
    public AjaxResponse checkInfo(String openId){
        boolean b = userService.checkInfo(openId);
        return new AjaxResponse(0,"获取用户信息成功",b);
    }

    /**
     * 根据用户手机取用户信息
     * @param phone phone
     * @return AjaxResponse
     */
    @RequestMapping(method = RequestMethod.GET,path = "/find/phone")
    @ResponseBody
    public AjaxResponse getCustomerByPhone(String phone){
        UsersBean usersBean = userService.findByPhone(phone);
        return new AjaxResponse(0,"获取用户信息成功",usersBean);
    }

    /**
     * 把openid插入到用户信息表
     * @param id
     * @param openId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,path = "/wx/add/openid")
    @ResponseBody
    public AjaxResponse insertOpenId(Long id,String openId){
        userService.updateOpenIdById(id,openId);
        return new AjaxResponse(0,"更新成功",null);
    }


    /**
     * 根据性别查询用户
     * @param request request
     * @param response response
     * @param sex sex
     * @param pageSize pageSize
     * @param pageNo pageNo
     * @param model model
     * @return 用户数据
     */
    @RequestMapping(method = RequestMethod.GET,path = "/getUserByQuery")
    @ResponseBody
    public AjaxResponse getUserByQuery(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="sex", required=true) Integer sex,@RequestParam(value="pageSize", required=true) Integer pageSize,@RequestParam(value="pageNo", required=true) Integer pageNo,Model model){
        Map<String,Object> criteria = new HashMap<String,Object>();
        criteria.put("sex",sex);
        Page<Customer> page =  userService.findUserCriteria(pageNo,pageSize, criteria);
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(0);
        ajaxResponse.setResult(page);
        return ajaxResponse;
    }

    /**
     * 根据id获取用户资料
     * @param request request
     * @param response response
     * @param id id
     * @return 用户资料
     */
    @RequestMapping(method = RequestMethod.GET,path = "/users/info")
    @ResponseBody
    public AjaxResponse getUserInfo(HttpServletRequest request, HttpServletResponse response,Long id) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        log.info("call getUserInfo method...");
        ajaxResponse.setCode(1);
        ajaxResponse.setMsg("id is null");
        if(id != null){
            ajaxResponse.setCode(0);
            ajaxResponse.setMsg("成功");
            UsersBean usersBean = userService.findById(id);
            ajaxResponse.setResult(usersBean);
        }
        return ajaxResponse;
    }

    /**
     * 用户资料更新
     * @param usersBean
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,path = "/users/update")
    @ResponseBody
    public AjaxResponse updateUser(HttpServletRequest request,UsersBean usersBean){
        return userService.updateUserById(usersBean);
    }

    /**
     * 用户头像
     * @param id id
     * @return 返回用户头像
     */
    @RequestMapping(method = RequestMethod.GET,path = "/users/head")
    @ResponseBody
    public AjaxResponse getUserHeadPic(Long id){
        if(id==null){
            AjaxResponse ajaxResponse = new AjaxResponse(1,"id不能为空");
            return ajaxResponse;
        }
        AjaxResponse ajaxResponse = new AjaxResponse(0,"获取用户头像成功");
        ajaxResponse.setResult(userService.findHeadPortraitById(id));
        return ajaxResponse;
    }

    /**
     * 用户背景图
     * @param id id
     * @return 返回用户头像
     */
    @RequestMapping(method = RequestMethod.GET,path = "/users/background")
    @ResponseBody
    public AjaxResponse getBackground(Long id){
        if(id==null){
            AjaxResponse ajaxResponse = new AjaxResponse(1,"id不能为空");
            return ajaxResponse;
        }
        AjaxResponse ajaxResponse = new AjaxResponse(0,"获取用户背景图成功");
        ajaxResponse.setResult(userService.findBackdropById(id));
        return ajaxResponse;
    }

    /**
     * 用户相册
     * @param id id
     * @param fileType 文件类型1:图片；2:视频
     * @return 返回相册
     */
    @RequestMapping(method = RequestMethod.GET,path = "/users/album")
    @ResponseBody
    public AjaxResponse getAlbum(Long id,Integer fileType){
        if(id==null || fileType==null){
            AjaxResponse ajaxResponse = new AjaxResponse(1,"id或fileType不能为空");
            return ajaxResponse;
        }
        AjaxResponse ajaxResponse = new AjaxResponse(0,"获取用户相册成功");
        ajaxResponse.setResult(attachmentService.findByCondition(id,fileType));
        return ajaxResponse;
    }

    /**
     * 用户视频
     * @param id id
     * @param fileType 文件类型1:图片；2:视频
     * @return 返回视频
     */
    @RequestMapping(method = RequestMethod.GET,path = "/users/video")
    @ResponseBody
    public AjaxResponse getVideo(Long id,Integer fileType){
        if(id==null || fileType==null){
            AjaxResponse ajaxResponse = new AjaxResponse(1,"id或fileType不能为空");
            return ajaxResponse;
        }
        AjaxResponse ajaxResponse = new AjaxResponse(0,"获取用户视频成功");
        ajaxResponse.setResult(attachmentService.findByCondition(id,fileType));
        return ajaxResponse;
    }

    /**
     * 用户删除图片和视频
     * @param idStr 文件idStr
     * @return 删除结果
     */
    @RequestMapping(method = RequestMethod.GET,path = "/users/file/delete")
    @ResponseBody
    public AjaxResponse deleteFile(HttpServletRequest request,String idStr){
        if(StringUtils.isBlank(idStr)){
            AjaxResponse ajaxResponse = new AjaxResponse(1,"id不能为空");
            return ajaxResponse;
        }
        String[] ids = idStr.split(",");
        List<Attachment> list = new ArrayList<>();
        for (String id : ids){
            Attachment attachment = new Attachment();
            attachment.setId(new Long(id));
            list.add(attachment);
        }
        attachmentService.deleteBatch(list);
        AjaxResponse ajaxResponse = new AjaxResponse(0,"删除文件成功");
        return ajaxResponse;
    }

    /**
     * 测试接口
     * @param userInfoReq userInfoReq
     * @return 成功信息
     */
    @PostMapping("/users/test/edit")
    @ResponseBody
    public AjaxResponse edit(@RequestBody UserInfoReq userInfoReq){
        log.info(JSONObject.toJSONString(userInfoReq));
        return new AjaxResponse(0,"成功");
    }
}
