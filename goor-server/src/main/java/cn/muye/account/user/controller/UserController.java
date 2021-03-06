package cn.muye.account.user.controller;

import cn.mrobot.bean.account.User;
import cn.mrobot.utils.StringUtil;
import cn.mrobot.utils.WhereRequest;
import cn.muye.account.user.service.UserService;
import cn.muye.base.bean.AjaxResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.net.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import cn.mrobot.bean.constant.Constant;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/6/13.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    static HttpClient httpClient = new HttpClient();

//    static Cookie[] cookies = {};

    /**
     * 新增修改用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = {"account/user"}, method = RequestMethod.POST)
    @ApiOperation(value = "新增修改用户接口", httpMethod = "POST", notes = "新增修改用户接口")
    @ResponseBody
    public AjaxResult addOrUpdateUser(@RequestBody User user) {
        User userDb = userService.getByUserName(user.getUserName());
        if (userDb!= null && userDb.getUserName().equals(user.getUserName()) && !userDb.getId().equals(user.getId())) {
            return AjaxResult.failed("用户名重复");
        }
        User userDbByDirectKey = userService.getUserByDirectKey(user.getDirectLoginKey());
        if (userDbByDirectKey!= null && userDbByDirectKey.getDirectLoginKey() != null && userDbByDirectKey.getDirectLoginKey().equals(user.getDirectLoginKey()) && !userDbByDirectKey.getId().equals(user.getId())) {
            return AjaxResult.failed("4位快捷码重复");
        }
        if (user == null) {
            return AjaxResult.failed(AjaxResult.CODE_PARAM_ERROR, "参数有误");
        }
        Long id = user.getId();
        if (id == null) {
//            PasswordEncoder encoder = new BCryptPasswordEncoder();
//            String encryPassword = encoder.encode(user.getPassword());
//            user.setPassword(encryPassword);
            userService.addUser(user);
            return AjaxResult.success(user, "新增成功");
        } else {
            User userDbById = userService.getById(id);
            if (userDbById != null) {
//                PasswordEncoder encoder = new BCryptPasswordEncoder();
//                String encryPassword = encoder.encode(user.getPassword());
                userDbById.setPassword(user.getPassword());
                userDbById.setStoreId(user.getStoreId());
                userDbById.setUserType(user.getUserType());
                userDbById.setActivated(user.getActivated());
                userDbById.setDirectLoginKey(user.getDirectLoginKey());
                userService.updateUser(userDbById);
                return AjaxResult.success(userDbById, "修改成功");
            } else {
                return AjaxResult.failed("不存在该用户");
            }
        }
    }

    /**
     * 查询用户
     *
     * @param whereRequest
     * @return
     */
    @RequestMapping(value = {"account/user"}, method = RequestMethod.GET)
    @ApiOperation(value = "查询用户接口", httpMethod = "GET", notes = "查询用户接口")
    @ResponseBody
    public AjaxResult list(WhereRequest whereRequest) {
        List<User> list = userService.list(whereRequest);
        PageInfo<User> pageList = new PageInfo<>(list);
        return AjaxResult.success(pageList, "查询成功");
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = {"account/user/{id}"}, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户接口", httpMethod = "DELETE", notes = "删除用户接口")
    @ResponseBody
    public AjaxResult delete(@PathVariable(value = "id") String id) {
        userService.fakeDeleteById(Long.valueOf(id));
        return AjaxResult.success("删除成功");
    }


    @RequestMapping(value = {"account/user/role"}, method = RequestMethod.POST)
    @ApiOperation(value = "用户角色绑定接口", httpMethod = "POST", notes = "用户角色绑定接口")
    @ResponseBody
    public AjaxResult bindRole(@RequestParam String userId, @RequestParam String roleId) {
        User user = userService.bindRole(userId, roleId);
        return AjaxResult.success(user, "绑定成功");
    }

    /**
     * 正常登录
     *
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = {"account/user/login"}, method = RequestMethod.POST)
    @ApiOperation(value = "登录接口", httpMethod = "POST", notes = "登录接口")
    @ResponseBody
    public AjaxResult login(String userName, String password) {
        User user = doLogin(userName, password);
        return AjaxResult.success(user, "登陆成功");
    }

    /**
     * 四位码快捷登录
     *
     * @param directKey
     * @return
     */
    @RequestMapping(value = {"account/user/login/pad"}, method = RequestMethod.POST)
    @ApiOperation(value = "PAD登录接口", httpMethod = "POST", notes = "PAD登录接口")
    @ResponseBody
    public AjaxResult directKeyLogin(String directKey) {
        try {
            User userDb = userService.getUserByDirectKey(Integer.valueOf(directKey));
            if (userDb != null) {
                User user = doLogin(userDb.getUserName(), userDb.getPassword());
                if (user != null) {
                    return AjaxResult.success(user, "登录成功");
                } else {
                    return AjaxResult.failed("登录失败");
                }
            } else {
                return AjaxResult.failed("用户不存在");
            }
        } catch (NumberFormatException e) {
            return AjaxResult.failed(AjaxResult.CODE_PARAM_ERROR,"参数错误");
        } finally {
        }
    }

    /**
     * 登录方法 todo 正常的有权限和菜单json和枚举的
     *
     * @param userName
     * @param password
     * @return
     */
    /*private User doLogin(String userName, String password) {
        //调auth_server的token接口
        try {
            String token = doAuthorize(userName, password);
            //判断token不等于null，说明已经登录
            if (token != null) {
                //查询用户的
                List<User> list = userService.getUser(userName, password);
                if (list != null) {
                    return list.get(0);
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/


    /**
     * 登录方法
     *
     * @param userName
     * @param password
     * @return
     */
    private User doLogin(String userName, String password) {
        //调auth_server的token接口
        User user = null;
        try {
            String accessToken = doAuthorize(userName, password);
            //判断token不等于null，说明已经登录
            if (!StringUtil.isNullOrEmpty(accessToken)) {
                //查询用户的
                List<User> list = userService.getUser(userName, password);
                if (list != null) {
                    user = list.get(0);
                    user.setAccessToken(accessToken);
                    return user;
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }

    private String doAuthorize(String username, String pwd) throws Exception {
        HttpClientParams httpParams = new HttpClientParams();
        httpParams.setSoTimeout(30000);
        httpClient.setParams(httpParams);
        httpClient.getHostConfiguration().setHost(Constant.AUTHORIZE_SERVER, Constant.AUTHORIZE_SERVER_PORT);
        httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        PostMethod login = new PostMethod(Constant.AUTH_SERVER_URL);
        login.addRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        final String auth = "Basic " + new String(Base64.encodeBase64(new StringBuilder(Constant.AUTHORIZE_USERNAME + ":" + Constant.AUTHORIZE_PASSWORD).toString().getBytes()));
        login.addRequestHeader("Authorization", auth);
        NameValuePair userName = new NameValuePair("username", username);// 邮箱
        NameValuePair password = new NameValuePair("password", pwd);// 密码
        NameValuePair grantType = new NameValuePair("grant_type", "password");// 密码

        NameValuePair[] data = {userName, password, grantType};
        login.setRequestBody(data);
        httpClient.executeMethod(login);
        int statusCode = login.getStatusCode();
        if (statusCode == 200) {
            String result = login.getResponseBodyAsString();
            JSONObject jsonObject = (JSONObject) JSON.parse(result);
            String accessToken = (String) jsonObject.get("access_token");
            return accessToken;
        } else {
            return null;
        }
    }
}
