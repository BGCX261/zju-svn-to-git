package dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jsict.platform.constants.CodeKey;
import com.jsict.platform.dao.IPermissionDao;
import com.jsict.platform.entity.Permission;
import com.jsict.platform.util.BasePlfTest;

public class PermissionDaoTest extends BasePlfTest
{
    @Autowired
    private IPermissionDao permissionDao;

    private Long userId;

    private Long roleId;

    private Long parentId;

    private List<Long> subMenuIds;

    private int subCount;

    private int sequence;

    private String platform;

    @Override
    protected void onSetUpInTransaction()
    {
        super.onSetUpInTransaction();

        userId = 1l;
        roleId = 1l;
        parentId = 1l;
        subCount = 2;
        sequence = 1;
        platform = CodeKey.PLF_PERMISSION_PLATFORM_PLF;
        subMenuIds = new ArrayList<Long>();
        Long permissionId = parentId + 1;

        String sql;

        sql = "insert into user_account (id,login_id,name,status,company_id,password) values ("
                + userId
                + ",'"
                + userId
                + "','"
                + userId
                + "','"
                + CodeKey.USER_STATUS_AVAILABLE + "',1,'password')";
        getJdbcTemplate().execute(sql);

        sql = "insert into role (id,name) values (" + roleId + ",'1')";
        getJdbcTemplate().execute(sql);

        sql = "insert into user_role (id,user_id,role_id) values (1," + userId
                + "," + roleId + ")";
        getJdbcTemplate().execute(sql);

        Long rolePermissionId = 1l;
        //parent permission
        sql = "insert into permission (id,code,type,name,sequence,platform) values ("
                + parentId
                + ",'"
                + parentId
                + "','"
                + CodeKey.PERMISSION_TYPE_MENU
                + "','"
                + parentId
                + "',"
                + sequence++ + ",'" + platform + "')";
        getJdbcTemplate().execute(sql);
        sql = "insert into role_permission (id,role_id,permission_id) values ("
                + rolePermissionId++ + "," + roleId + "," + parentId + ")";
        getJdbcTemplate().execute(sql);

        //sub permissions
        for (int i = 0; i < subCount; i++, permissionId++)
        {
            sql = "insert into permission (id,code,type,name,parent_id,sequence,platform) values ("
                    + permissionId
                    + ",'"
                    + permissionId
                    + "','"
                    + CodeKey.PERMISSION_TYPE_MENU
                    + "','"
                    + permissionId
                    + "'," + parentId + "," + sequence++ + "," + platform + ")";
            getJdbcTemplate().execute(sql);

            sql = "insert into role_permission (id,role_id,permission_id) values ("
                    + rolePermissionId++
                    + ","
                    + roleId
                    + ","
                    + permissionId
                    + ")";
            getJdbcTemplate().execute(sql);

            subMenuIds.add(permissionId);

        }

        //permission for other parent
        sql = "insert into permission (id,code,type,name,parent_id,sequence,platform) values ("
                + permissionId
                + ",'"
                + permissionId
                + "','"
                + CodeKey.PERMISSION_TYPE_MENU
                + "','"
                + permissionId
                + "',"
                + 99l + "," + sequence++ + ",'" + platform + "')";
        getJdbcTemplate().execute(sql);
        sql = "insert into role_permission (id,role_id,permission_id) values ("
                + rolePermissionId++ + "," + roleId + "," + permissionId + ")";
        getJdbcTemplate().execute(sql);
        permissionId++;

        //item type permission
        sql = "insert into permission (id,code,type,name,parent_id,sequence,platform) values ("
                + permissionId
                + ",'"
                + permissionId
                + "','"
                + CodeKey.PERMISSION_TYPE_ITEM
                + "',"
                + permissionId
                + ","
                + 99l
                + ","
                + sequence + ",'" + platform + "')";
        getJdbcTemplate().execute(sql);
        sql = "insert into role_permission (id,role_id,permission_id) values ("
                + rolePermissionId++ + "," + roleId + "," + permissionId + ")";
        getJdbcTemplate().execute(sql);
        permissionId++;
    }

    public void testGetRootPermissionByUser()
    {
        List<Permission> permissionList = permissionDao.getPermissionsByUser(
            null, userId, platform, true);

        assert permissionList != null;
        assert permissionList.size() == 1 : permissionList.size();

        Permission permission = (Permission) getFirst(permissionList);
        assert permission.getId().equals(parentId) : permission.getId();

    }

    public void testGetSubPermissionByUser()
    {
        List<Permission> permissionList = permissionDao.getPermissionsByUser(
            parentId, userId, platform, false);

        assert permissionList != null;
        assert permissionList.size() == subCount : permissionList.size();

        for (int i = 0; i < permissionList.size(); i++)
        {
            Permission permission = permissionList.get(i);
            assert permission.getId().equals(subMenuIds.get(i)) : "i=" + i
                    + "|" + permission.getId() + " vs " + subMenuIds.get(i);
        }
    }
}
