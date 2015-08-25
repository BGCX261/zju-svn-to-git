package cn.itcast.bbs.service.article;

import java.util.List;

import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.article.Vote;
import cn.itcast.bbs.entities.article.VoteItem;
import cn.itcast.bbs.entities.article.VoteRecord;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public interface VoteService {

	/**
	 * 
	 * @param id
	 * @return 指定id的Vote
	 */
	Vote getVote(int id);

	/**
	 * 
	 * @param id
	 * @return 指定id的投票项
	 */
	VoteItem getVoteItem(int id);

	/**
	 * 
	 * @param ids
	 * @return 指定id的投票项的集合
	 */
	List<VoteItem> getVoteItems(int... ids);

	/**
	 * 以下情况不能投票: 投票过期, 该会员已经投过票.
	 * 
	 * @param vote
	 * @param voter
	 * @return 当前用户是否能参与投票
	 */
	boolean canVote(Vote vote, User voter);

	/**
	 * 投票
	 * 
	 * @param voteRecord
	 */
	void vote(VoteRecord voteRecord);

	/**
	 * 添加投票
	 * 
	 * @param vote
	 */
	void addNew(Vote vote);

	/**
	 * 更新投票信息
	 * 
	 * @param vote
	 */
	void updateVote(Vote vote);

	/**
	 * 删除投票, 所对应的投票项与投票记录也会被删除
	 * 
	 * @param id
	 */
	void deleteVote(int id);

	/**
	 * 删除投票项, 所对应的投票记录也会被删除
	 * 
	 * @param id
	 */
	void deleteVoteItem(int id);
}
