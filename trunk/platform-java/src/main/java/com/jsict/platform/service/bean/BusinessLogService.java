/**
 * BusinessLogService.java 2008-11-10 下午03:40:27 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.service.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.jsict.base.BaseService;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.ConvertUtil;
import com.jsict.base.util.ListUtil;
import com.jsict.platform.domain.BusinessLogDetailDomain;
import com.jsict.platform.domain.BusinessLogDomain;
import com.jsict.platform.domain.UserAccountDomain;
import com.jsict.platform.entity.BusinessLog;
import com.jsict.platform.entity.BusinessLogDetail;
import com.jsict.platform.entity.UserAccount;
import com.jsict.platform.repository.IBusinessLogRepository;
import com.jsict.platform.service.IBusinessLogService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
@Transactional
public class BusinessLogService extends BaseService implements
        IBusinessLogService
{
    private IBusinessLogRepository businessLogRepository;

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param businessLogRepository
     *            The businessLogRepository to set.
     */
    @Required
    public void setBusinessLogRepository(
            IBusinessLogRepository businessLogRepository)
    {
        this.businessLogRepository = businessLogRepository;
    }

    public List<BusinessLogDomain> getBusinessLogList(
            EntityFilter entityFilter, Integer pageNo, Integer pageSize,
            boolean withDetail) throws ApplicationException, SystemException
    {
        entityFilter.addOrder("log.createdDate", "desc");
        List<Object[]> businessLogList = businessLogRepository
                .getBusinessLogListWithUser(entityFilter, pageNo, pageSize);

        List<BusinessLogDomain> list = new PagedList<BusinessLogDomain>();
        for (Object[] objs : businessLogList)
        {
            BusinessLog businessLog = (BusinessLog) objs[0];
            UserAccount userAccount = (UserAccount) objs[1];
            BusinessLogDomain businessLogDomain = new BusinessLogDomain();
            ConvertUtil.entity2domain(businessLog, businessLogDomain);

            //convert useraccount
            UserAccountDomain userAccountDomain = new UserAccountDomain();
            ConvertUtil.entity2domain(userAccount, userAccountDomain);
            businessLogDomain.setUserAccount(userAccountDomain);

            if(withDetail)
            {
                Set<BusinessLogDetail> businessLogDetails = businessLog
                        .getBusinessLogDetails();
                List<BusinessLogDetailDomain> list2 = new ArrayList<BusinessLogDetailDomain>();
                for (BusinessLogDetail businessLogDetail : businessLogDetails)
                {
                    BusinessLogDetailDomain businessLogDetailDomain = new BusinessLogDetailDomain();
                    ConvertUtil.entity2domain(businessLogDetail,
                        businessLogDetailDomain);
                    list2.add(businessLogDetailDomain);
                }
                businessLogDomain.setBusinessLogDetails(list2);
            }
            list.add(businessLogDomain);
        }
        ListUtil.clonePagedInfo(list, businessLogList);
        return list;
    }
}
