package cn.itcast.bbs.service.article.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.article.Topic;
import cn.itcast.bbs.entities.article.Vote;
import cn.itcast.bbs.entities.article.VoteItem;
import cn.itcast.bbs.entities.article.VoteRecord;
import cn.itcast.bbs.logic.helper.UserHelper;
import cn.itcast.bbs.service.article.VoteService;
import cn.itcast.bbs.service.base.BaseService;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
@Service("voteService")
@Transactional(readOnly = true)
public class VoteServiceImpl extends BaseService implements VoteService {

	public Vote getVote(int id) {
		return voteDao.get(id);
	}

	public VoteItem getVoteItem(int id) {
		return voteItemDao.get(id);
	}

	public List<VoteItem> getVoteItems(int... ids) {
		List<VoteItem> voteItems = new ArrayList<VoteItem>();
		if (ids == null) {
			return voteItems;
		}

		for (int id : ids) {
			voteItems.add(voteItemDao.get(id));
		}
		return voteItems;
	}

	public boolean canVote(Vote vote, User voter) {
		// 匿名用户不能投票
		if (UserHelper.isAnonymousUser(voter)) {
			return false;
		}

		// 投票已过期不能投票
		if (vote.getCloseTime().getTime() > System.currentTimeMillis()) {
			return false;
		}

		// 没有参与过这个投票的会员才可以进行投票
		return !voteRecordDao.isUserVoted(vote, voter);
	}

	@Transactional
	public void vote(VoteRecord voteRecord) {
		Vote vote = voteRecord.getVoteItems().iterator().next().getVote();

		// FIXME 投票已过期不能投票(测试时先把这个判断注释掉)
		// if (!canVote(vote, voteRecord.getVoter())) {
		// throw new ItcastException("用户不能参与投票, 可能是因为: 1, 投票过期或 2,是匿名用户投票或 3,已经参与过了");
		// }

		// Save voteRecord
		voteRecordDao.save(voteRecord);

		// 更新VoteItem相关信息
		for (VoteItem voteItem : voteRecord.getVoteItems()) {
			voteItem.setResult(voteItem.getResult() + 1);
			voteItemDao.update(voteItem); //
		}

		// 更新Vote相关信息
		vote.setResult(vote.getResult() + voteRecord.getVoteItems().size());
		voteDao.update(vote);
	}

	@Transactional
	public void addNew(Vote vote) {
		Topic topic = vote.getTopic();
		topic.getVotes().add(vote);
		topicDao.update(topic); // 在一的一方保存, 因为要生成索引列的值
	}

	@Transactional
	public void updateVote(Vote vote) {
		voteDao.update(vote);
	}

	@Transactional
	public void deleteVote(int id) {
		Vote vote = voteDao.get(id);
		vote.getTopic().getVotes().remove(vote);
		voteDao.delete(vote);
	}

	@Transactional
	public void deleteVoteItem(int id) {
		VoteItem voteItem = voteItemDao.get(id);
		Vote vote = voteItem.getVote();

		vote.getVoteItems().remove(voteItem);
		voteItemDao.delete(voteItem);

		// 重新计算vote的result.
		vote.setResult(vote.getResult() - voteItem.getResult());
	}
}
