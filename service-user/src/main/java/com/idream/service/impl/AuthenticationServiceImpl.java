package com.idream.service.impl;

import com.idream.commons.lib.dto.auth.*;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.*;
import com.idream.commons.lib.model.*;
import com.idream.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author charles.wei
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private SysPermissionMapper permissionMapper;
    @Autowired
    private SysMenuMapper menuMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysUserRoleRelationMapper userRoleRelationMapper;
    @Autowired
    private SysRolePermissionRelationMapper rolePermissionRelationMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 获取当前用户的所有权限
     */
    @Override
    public List<PermissionInfo> listPermissionByUserId(Integer authUserId) {
        List<SysPermission> pers = permissionMapper.selectPermissionListByUserId(authUserId);
        Stream<PermissionInfo> stream = pers.stream().map(p -> {
            PermissionInfo info = new PermissionInfo();
            info.setCode(p.getCode());
            info.setMenuId(p.getMenuId());
            info.setMethod(p.getMethod());
            info.setName(p.getName());
            info.setType(p.getType());
            info.setUri(p.getUri());
            return info;
        });

        return stream.collect(Collectors.toList());
    }

    @Override
    public List<AuthMenuNode> listAuthMenus() {
        List<SysMenu> menus = menuMapper.selectAll();
        List<SysPermission> permissions = permissionMapper.selectAll();
        List<AuthButton> buttons = permissions.stream().map(p -> {
            AuthButton b = new AuthButton();
            b.setId(p.getId());
            b.setName(p.getName());
            b.setMenuId(Integer.parseInt(p.getMenuId()));
            return b;
        }).collect(Collectors.toList());
        List<AuthMenuNode> collect = menus.stream().map(m -> {
            AuthMenuNode node = new AuthMenuNode();
            node.setText(m.getTitle());
            node.setIcon(m.getIcon());
            node.setId(m.getId());
            node.setPid(m.getPid());
            return node;

        }).collect(Collectors.toList());

        List<AuthMenuNode> tree = buildTree(collect, 0, buttons);
        return tree;
    }

    private List<AuthMenuNode> buildTree(List<AuthMenuNode> nodes, int pid, List<AuthButton> buttons) {
        List<AuthMenuNode> temp = new ArrayList<>();
        for (AuthMenuNode n : nodes) {
            if (n.getPid() == pid) {
                List<AuthMenuNode> children = buildTree(nodes, n.getId(), buttons);
                n.setChildren(children);
                n.setButtons(buttons.stream().filter(b -> b.getMenuId() == n.getId()).collect(Collectors.toList()));
                temp.add(n);
            }
        }
        return temp;
    }

    @Override
    public List<MenuNode> listMenuByUserId(Integer authUserId) {
        List<SysMenu> menus = menuMapper.listMenuByUserId(authUserId);
        List<MenuNode> collect = menus.stream().map(m -> {
            MenuNode n = new MenuNode();
            n.setHref(m.getHref());
            n.setIcon(m.getIcon());
            n.setId(m.getId());
            n.setPid(m.getPid());
            n.setTitle(m.getTitle());
            return n;
        }).collect(Collectors.toList());
        return buildMenuTree(collect, 0);
    }

    private List<MenuNode> buildMenuTree(List<MenuNode> nodes, int pid) {
        List<MenuNode> temp = new ArrayList<>();
        for (MenuNode n : nodes) {
            if (n.getPid() == pid) {
                List<MenuNode> children = buildMenuTree(nodes, n.getId());
                n.setChildren(children);
                if (!children.isEmpty()) {
                    n.setSpread(true);
                }
                temp.add(n);
            }
        }
        return temp;
    }

    @Override
    public List<PermissionInfo> listPermissionByUserIdAndMenuId(Integer authUserId, Integer menuId) {
        return permissionMapper.selectPermissionListByUserIdAndMenuId(authUserId, menuId);
    }

    @Override
    public List<RoleInfo> listRoles(String query) {

        List<SysRole> roles = roleMapper.selectByRoleName(query);
        return roles.stream().map(r -> {
            RoleInfo info = new RoleInfo();
            info.setId(r.getId());
            info.setName(r.getName());
            info.setUpdateTime(r.getUpdateTime());
            info.setCreateTime(r.getCreateTime());
            return info;
        }).collect(Collectors.toList());
    }

    @Override
    public void saveRole(RoleVO role) {
        SysRole record = new SysRole();
        record.setName(role.getName());
        roleMapper.insertSelective(record);
        role.setId(record.getId());
        batchInsertRolePermissionRelation(role);
    }

    private List<AuthMenuNode> unfoldMenu(List<AuthMenuNode> tree) {
        List<AuthMenuNode> temp = Collections.emptyList();
        temp.addAll(tree);
        for (AuthMenuNode n : tree) {
            if (!n.getChildren().isEmpty()) {
                temp.addAll(n.getChildren());
            }
        }
        return temp;
    }

    /**
     * 批量插入角色权限中间表数据
     *
     * @param role void
     */
    private void batchInsertRolePermissionRelation(RoleVO role) {
        List<AuthMenuNode> menus = role.getAuthMenus();

        List<AuthMenuNode> unfoldMenu = unfoldMenu(menus);
        List<Integer> menuIds = unfoldMenu.stream().filter(AuthMenuNode::isSelected).map(TreeNode::getId).collect(Collectors.toList());
        List<Integer> permissionIds = unfoldMenu.stream().flatMap(m -> m.getButtons().stream()).map(AuthButton::getId).collect(Collectors.toList());
        if (!menuIds.isEmpty()) {
            rolePermissionRelationMapper.batchInsert(role.getId(), menuIds, "menu");
        }
        if (!permissionIds.isEmpty()) {
            rolePermissionRelationMapper.batchInsert(role.getId(), permissionIds, "button");
        }
    }

    @Override
    public void updateRole(RoleVO role) {
        // 修改角色
        SysRole record = new SysRole();
        record.setId(role.getId());
        record.setName(role.getName());
        Date date = new Date();
        record.setUpdateTime(date);
        roleMapper.updateByPrimaryKeySelective(record);

        rolePermissionRelationMapper.batchDeleteByRoleId(role.getId());
        batchInsertRolePermissionRelation(role);


    }

    @Override
    public RoleVO getRoleById(Integer roleId) {
        // 返回菜单与权限
        SysRole sysRole = roleMapper.selectByPrimaryKey(roleId);

        List<SysMenu> menus = menuMapper.selectAll();
        List<SysPermission> permissions = permissionMapper.selectAll();
        List<SysMenu> curtMenus = menuMapper.selectByRoleId(roleId);
        List<SysPermission> curtButtons = permissionMapper.selectByRoleId(roleId);
        List<AuthButton> buttons = Collections.emptyList();
        permissions.stream().collect(Collectors.partitioningBy(e -> curtButtons.stream().anyMatch(c -> c.getId().equals(e.getId())))).forEach((k, v) -> {
            List<AuthButton> list = v.stream().map(p -> {
                AuthButton b = new AuthButton();
                b.setId(p.getId());
                b.setName(p.getName());
                b.setMenuId(Integer.parseInt(p.getMenuId()));
                return b;
            }).collect(Collectors.toList());
            buttons.addAll(list);
        });

        List<AuthMenuNode> nodes = Collections.emptyList();
        menus.stream().collect(Collectors.partitioningBy(e -> curtMenus.stream().anyMatch(c -> c.getId().equals(e.getId())))).forEach((k, v) -> {
            List<AuthMenuNode> list = v.stream().map(m -> {
                AuthMenuNode node = new AuthMenuNode();
                node.setText(m.getTitle());
                node.setIcon(m.getIcon());
                node.setId(m.getId());
                node.setPid(m.getPid());
                node.setSelected(k);
                return node;
            }).collect(Collectors.toList());
            nodes.addAll(list);
        });
        List<AuthMenuNode> tree = buildTree(nodes, 0, buttons);
        RoleVO role = new RoleVO();
        role.setAuthMenus(tree);
        role.setId(sysRole.getId());
        role.setName(sysRole.getName());
        return role;
    }

    @Override
    public void saveUserRoleRelation(Integer userId, Integer roleId) {
        // 
        int i = userRoleRelationMapper.selectExistByUserIdAndRoleId(userId);
        if (i > 1) {
            throw new BusinessException("用户已存在!");
        }
        SysUserRoleRelation record = new SysUserRoleRelation();
        record.setUserId(userId);
        record.setRoleId(roleId);
        Date date = new Date();
        record.setCreateTime(date);
        record.setUpdateTime(date);
        userRoleRelationMapper.insertSelective(record);
    }

    @Override
    public void updateUserRoleRelation(Integer userId, Integer roleId) {
        // 
        userRoleRelationMapper.updateRoleIdByUserId(userId, roleId);

    }

    @Override
    public void deleteUserRoleRelation(Integer userId, Integer roleId) {
        // 
        userRoleRelationMapper.deleteByUserIdAndRoleId(userId, roleId);

    }

    @Override
    public UserRoleVO listUserRoleByUserId(Integer userId) {

        SysUserRoleRelation record = userRoleRelationMapper.selectByUserId(userId);
        UserInfo user = userInfoMapper.selectByPrimaryKey(userId);

        List<SysRole> all = roleMapper.selectAll();
        List<RoleInfo> roles = all.stream().map(r -> {
            RoleInfo info = new RoleInfo();
            info.setId(r.getId());
            info.setName(r.getName());
            return info;
        }).collect(Collectors.toList());
        UserRoleVO vo = new UserRoleVO();
        vo.setUserId(userId);
        vo.setUserName(user.getNickName());
        vo.setRoleId(record.getRoleId());
        vo.setRoles(roles);
        return vo;
    }

    @Override
    public List<PermissionInfo> listBasisPermission() {
        List<SysPermission> pers = permissionMapper.selectBasis();
        Stream<PermissionInfo> stream = pers.stream().map(p -> {
            PermissionInfo info = new PermissionInfo();
            info.setCode(p.getCode());
            info.setMenuId(p.getMenuId());
            info.setMethod(p.getMethod());
            info.setName(p.getName());
            info.setType(p.getType());
            info.setUri(p.getUri());
            return info;
        });

        return stream.collect(Collectors.toList());
    }

    @Override
    public List<SimpleAuthMenu> listSimpleAuthMenus() {


        List<SysMenu> sysMenus = menuMapper.selectAll();
        List<SysPermission> sysPermissions = permissionMapper.selectAll();
        return convertSimpleAuthMenus(sysPermissions, sysMenus);
    }

    @Override
    public void createSimpleRole(SimpleRoleInfo role) {
        SysRole a = roleMapper.selectByExactRoleName(role.getRoleName());
        if (a != null) {
            throw new BusinessException("9999", "角色名不能重复");
        }
        SysRole record = new SysRole();
        record.setName(role.getRoleName());
        Date date = new Date();
        record.setCreateTime(date);
        record.setUpdateTime(date);

        roleMapper.insertSelective(record);

        Integer roleId = record.getId();
        role.setRoleId(roleId);
        saveSimplePermission(role);

    }

    private void saveSimplePermission(SimpleRoleInfo role) {
        List<SimplePermissionInfo> permissions = role.getPermissions();

        List<Integer> ids = new ArrayList<>();
        permissions.forEach(p -> {
            ids.add(p.getPmId());
            ids.add(p.getMenuId());
        });
        List<Integer> ps = permissions.stream().filter(p -> p.getId() != 0).map(SimplePermissionInfo::getId).collect(Collectors.toList());

        Set<Integer> set = new HashSet<>(ids);
        List<Integer> list = new ArrayList<>(set);
        if (!list.isEmpty()) {
            rolePermissionRelationMapper.batchInsert(role.getRoleId(), list, "menu");
        }
        if (!ps.isEmpty()) {
            rolePermissionRelationMapper.batchInsert(role.getRoleId(), ps, "button");
        }
    }

    @Override
    public SimpleRoleInfo getSimpleRoleByRoleId(Integer roleId) {
        SysRole sysRole = roleMapper.selectByPrimaryKey(roleId);
        SimpleRoleInfo role = new SimpleRoleInfo();
        role.setRoleId(sysRole.getId());
        role.setRoleName(sysRole.getName());

        List<SysMenu> sysMenus = menuMapper.selectByRoleId(roleId);
        List<SysPermission> permissions = permissionMapper.selectByRoleId(roleId);
        List<SimpleAuthMenu> collect = convertSimpleAuthMenus(permissions, sysMenus);
        role.setAuthMenus(collect);
        return role;
    }

    @Override
    public void updateSimpleRole(SimpleRoleInfo role) {
        SysRole a = roleMapper.selectByExactRoleName(role.getRoleName());
        if (a != null) {
            if (!a.getId().equals(role.getRoleId())) {
                throw new BusinessException("9999", "角色名不能重复");
            }
        }
        SysRole sysRole = roleMapper.selectByPrimaryKey(role.getRoleId());
        sysRole.setName(role.getRoleName());
        sysRole.setUpdateTime(new Date());
        roleMapper.updateByPrimaryKeySelective(sysRole);
        rolePermissionRelationMapper.batchDeleteByRoleId(role.getRoleId());
        saveSimplePermission(role);
    }

    @Override
    public List<SimpleAuthMenu> listSimpleAuthMenusByUserId(Integer userId) {
        if (userId == 1) {
            return listSimpleAuthMenus();
        }
        List<SysPermission> sysPermissions = permissionMapper.selectPermissionListByUserId(userId);
        List<SysMenu> sysMenus = menuMapper.listMenuByUserId(userId);

        return convertSimpleAuthMenus(sysPermissions, sysMenus);

    }

    private List<SimpleAuthMenu> convertSimpleAuthMenus(List<SysPermission> sysPermissions, List<SysMenu> sysMenus) {
        return sysMenus.stream().filter(m -> m.getPid() == 0).map(m -> {
            SimpleAuthMenu menu = new SimpleAuthMenu();
            menu.setCode(m.getCode());
            menu.setId(m.getId());
            menu.setName(m.getTitle());
            menu.setChildren(sysMenus.stream().filter(s -> s.getPid().equals(m.getId())).map(c -> {
                SimpleAuthMenu menu1 = new SimpleAuthMenu();
                menu1.setCode(c.getCode());
                menu1.setId(c.getId());
                menu1.setName(c.getTitle());
                //二级页面下的所有权限
                List<SimplePermissionInfo> ps = sysPermissions.stream().filter(s -> Integer.parseInt(s.getMenuId()) == c.getId()).map(i -> {
                    SimplePermissionInfo button = new SimplePermissionInfo();
                    button.setPmId(m.getId());
                    button.setMenuId(c.getId());
                    button.setCode(i.getCode());
                    button.setId(i.getId());
                    button.setName(i.getName());
                    return button;
                }).collect(Collectors.toList());

                SimplePermissionInfo permission = new SimplePermissionInfo();
                permission.setPmId(m.getId());
                permission.setMenuId(c.getId());
                permission.setId(0);
                permission.setCode("menu" + m.getId() + "second" + c.getId());
                permission.setName("查看页面");
                ps.add(permission);
                menu1.setChildren(Collections.unmodifiableList(ps));
                return menu1;
            }).collect(Collectors.toList()));
            return menu;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteUserRole(Integer roleId) {
        int count = userRoleRelationMapper.countByRoleId(roleId);
        if (count > 0) {
            throw new BusinessException("角色还有关联账户,不能删除");
        }
        roleMapper.deleteByPrimaryKey(roleId);
    }
}
