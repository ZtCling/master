package com.qcby.db.service.impl;

import com.qcby.db.common.constant.ResultJson;
import com.qcby.db.common.contest.QcbyContext;
import com.qcby.db.entity.SysLoginLog;
import com.qcby.db.entity.SysMenu;
import com.qcby.db.entity.SysUser;
import com.qcby.db.mapper.LoginMapper;
import com.qcby.db.service.LoginLogService;
import com.qcby.db.service.LoginService;
import com.qcby.db.util.JwtUtil;
import com.qcby.db.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/6 16:30
 */
@Service("login")
@Transactional // 事务
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private LoginLogService loginLogService;




    /**
     * @return [user]
     * @Author liu-miss
     * @Description //TODO 根据要登录的用户名和密码去查询数据库
     * @Date l 2021/9/6
     * @Param com.qcby.db.common.web.RestultJson
     **/
    @Override
    public com.qcby.db.common.web.ResultJson login(SysUser user, HttpServletRequest request) {

        if (StringUtil.isEmpty(user.getUserName()) || StringUtil.isEmpty(user.getPassword())) {
            return com.qcby.db.common.web.ResultJson.error("用户名或账号为空", null);
        }


        SysUser userDb = loginMapper.login(user);
        String token = null;
        if (userDb == null) {
            return com.qcby.db.common.web.ResultJson.error("用户不存在", null);
        } else {
            //利用JwtUtil得到一个token
            token = JwtUtil.createToken(userDb.getId(), userDb.getUserName());
            userDb.setToken(token);
            //将用户信息添加到全局上下文中
            QcbyContext.add(userDb);

            long userId = userDb.getId();
            List<Long> list = loginMapper.listRole(userId);

            for (int i = 0; i < list.size(); i++){
                userDb.setRoleId(list);
            }

            List<String> authList = loginMapper.listAuth(userId);
            userDb.setAuthList(authList);

            String userName = userDb.getRealName();
            String ip = request.getHeader("x-forwarded-for");

            if (StringUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("x-real-ip");
            }

            if (StringUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            log.info(ip);

            SysLoginLog sysLoginLog = new SysLoginLog();
            sysLoginLog.setUserName(userName);
            sysLoginLog.setLoginTime(LocalDateTime.now());
            sysLoginLog.setLoginIp(ip);
            boolean c = loginLogService.save(sysLoginLog);
            log.info("登录日志是否添加成功:{}", String.valueOf(c));




            return com.qcby.db.common.web.ResultJson.ok("登录成功", userDb);
        }
    }


    /**
     * @Author liu-miss
     * @Description // 修改用户密码
     * @Date l 2021/9/8
     * @return [passwordOld, password, passwordAgain]
     **/
    @Override
    public int updatePassword(String passwordOld, String password, String passwordAgain) {
        return loginMapper.updatePassword(passwordOld, password, passwordAgain);
    }



    /**
     * @Author liu-miss
     * @Description //TODO  修改用户个人信息
     * @Date l 2021/9/8
     * @return [sex, email, realName, photo, userId]
     **/
    @Override
    public int updatePersonMessage(String sex, String email, String realName, String photo, String userId) {
        return loginMapper.updatePersonMessage(sex, email, realName, photo, userId);
    }

    @Override
    public List<Long> listRole(Long userId) {
        return loginMapper.listRole(userId);
    }



    @Override
    public com.qcby.db.common.web.ResultJson forgetPassword(HttpServletRequest request, String verCode,
                                                            String password, Long userId) {
        String token = request.getHeader("token");
        SysUser userDb = QcbyContext.getUser(token);
        String realVerCode = userDb.getVerCode();
        log.info(verCode);
        log.info(realVerCode);
        if (verCode.equals(realVerCode.trim())){
            return com.qcby.db.common.web.ResultJson.ok(loginMapper.forgetPassword(password, userId));
        }else{
            return com.qcby.db.common.web.ResultJson.error(null);
        }

    }



    /**
     * 此方法是返回 菜单树的方法，controller 层调用，返回结果
     * @param request
     * @return
     */
    @Override
    public com.qcby.db.common.web.ResultJson menuTree(HttpServletRequest request) {

        String token = request.getHeader("token");

        SysUser user = QcbyContext.getUser(token);

        List<SysMenu> menus = loginMapper.getMenus(user.getId());

        List<SysMenu> result = getTreeMenu(menus);
        return com.qcby.db.common.web.ResultJson.ok("查询成功", result);
    }



    /**
     * 此方法，递归生成树形结构
     * @param menus
     * @return
     */
    private List<SysMenu> getTreeMenu(List<SysMenu> menus) {

        List<SysMenu> result = new ArrayList<>();
        for (SysMenu menu : menus) {
            if (menu.getMenuParentId() == ResultJson.MAN) {
                result.add(menu);
            }
        }

        for (SysMenu parent : result) {
            menuChild(parent, menus);
        }
        return result;

    }

    private void menuChild(SysMenu parent, List<SysMenu> menus) {
//        遍历菜单集合
        for (SysMenu menu : menus) {
//            如果当前集合属于子集
            if (menu.getMenuParentId() == parent.getId()) {
//                让根节点添加子集，让该自己称为模拟根节点，添加子集
                parent.getChildren().add(menu);
//                递归，添加子集
                menuChild(menu, menus);
            }
        }

    }




}



