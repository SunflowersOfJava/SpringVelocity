package com.boonya.base.utils.handlerInterceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.boonya.base.utils.ehcache.EhcacheUtil;
import com.boonya.base.utils.finals.Const;

/**
 * 系统菜单权限过滤器类
 * 
 * @packge com.wlyd.fmcgwms.util.handlerInterceptor.RightsHandlerInterceptor
 * @date 2016年4月28日 下午4:53:07
 * @author pengjunlin
 * @comment
 * @update 添加注释，代码重构
 */
public class RightsHandlerInterceptor extends HandlerInterceptorAdapter {

	/*@Autowired
	private EsMenuService menuService;

	*//**
	 * URL请求过滤预处理
	 *//*
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		ModelAndView mv = new ModelAndView();
		if (path.matches((String) EhcacheUtil.get(Const.NO_INTERCEPTOR_PATH)))
			return true;
		EsUser user = (EsUser) OperationSession.getSession(request,
				Const.SESSION_BACK_USER);
		if (user == null) {
			mv.setViewName("redirect:/admin/login.html");
			throw new ModelAndViewDefiningException(mv);
		} else {
			// 处理菜单权限
			return handleMenuRights(request, response, mv, user, handler, path);
		}
	}

	*//**
	 * 匹配菜单ID是否存在
	 * 
	 * @MethodName: matchMenuId
	 * @Description:
	 * @param path
	 * @return
	 * @throws
	 *//*
	public Integer matchMenuId(String path) {
		Integer menuId = null;
		List<EsMenu> subList = menuService.findAll(null);
		path = path.substring(path.lastIndexOf("/") + 1);
		for (EsMenu m : subList) {
			String menuUrl = m.getEsMenuUrl();
			if (Tools.notEmpty(menuUrl)) {
				// if(path.contains(menuUrl)){
				if (path.equals(menuUrl)) {
					menuId = m.getId();
					break;
				} else {
					String[] arr = menuUrl.split("\\.");
					String regex = "";
					if (arr.length == 2) {
						regex = "/?" + arr[0] + "(/.*)?." + arr[1];
					} else {
						regex = "/?" + menuUrl + "(/.*)?.html";
					}
					if (path.matches(regex)) {
						menuId = m.getId();
						break;
					}
				}
			}
		}
		return menuId;
	}

	*//**
	 * 处理菜单权限
	 * 
	 * @MethodName: handleMenuRights
	 * @Description:
	 * @param request
	 * @param response
	 * @param mv
	 * @param user
	 * @param handler
	 * @param path
	 * @return
	 * @throws Exception
	 * @throws
	 *//*
	public boolean handleMenuRights(HttpServletRequest request,
			HttpServletResponse response, ModelAndView mv, EsUser user,
			Object handler, String path) throws Exception {
		// 匹配菜单ID是否存在
		Integer menuId = matchMenuId(path);
		// 匹配菜单对应的权限是否符合
		if (menuId != null) {
			String userRights = ((EsUser) OperationSession.getSession(request,
					Const.SESSION_BACK_USER)).getEsUsertRights();
			String roleRights = ((EsUser) OperationSession.getSession(request,
					Const.SESSION_BACK_USER)).getEsRole().getEsRoleRights();
			if (RightsHelper.testRights(userRights, menuId)
					|| RightsHelper.testRights(roleRights, menuId)) {
				return true;
			} else {
				System.out.println("用户：" + user.getEsLoginName() + "试图访问"
						+ path + "被阻止！");
				mv.setViewName("no_rights");
				throw new ModelAndViewDefiningException(mv);
			}
		}
		return super.preHandle(request, response, handler);
	}*/
	
	
}
